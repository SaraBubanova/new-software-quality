import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.File;

import commands.Command;
import commands.CommandInvoker;
import commands.NextSlideCommand;
import commands.OpenFileCommand;
import model.Presentation;
import model.Accessor;
import model.XMLAccessor;
import model.Slide;
import model.TextItem;

/** <p>The controller for the menu</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class MenuController extends JMenuBar {
	
	private JFrame parent; // the frame, only used as parent for the Dialogs
	private Presentation presentation; // Commands are given to the presentation
	private CommandInvoker commandInvoker;
	
	private static final long serialVersionUID = 227L;
	
	protected static final String ABOUT = "About";
	protected static final String FILE = "File";
	protected static final String EXIT = "Exit";
	protected static final String GOTO = "Go to";
	protected static final String HELP = "Help";
	protected static final String NEW = "New";
	protected static final String NEXT = "Next";
	protected static final String OPEN = "Open";
	protected static final String PAGENR = "Page number?";
	protected static final String PREV = "Prev";
	protected static final String SAVE = "Save";
	protected static final String VIEW = "View";
	
	protected static final String TESTFILE = "test.xml";
	protected static final String SAVEFILE = "dump.xml";
	
	protected static final String IOEX = "IO Exception: ";
	protected static final String LOADERR = "Load Error";
	protected static final String SAVEERR = "Save Error";

	public MenuController(JFrame frame, Presentation pres) {
		parent = frame;
		presentation = pres;
		commandInvoker = new CommandInvoker();
		JMenuItem menuItem;
		JMenu fileMenu = new JMenu(FILE);
		fileMenu.add(menuItem = mkMenuItem(OPEN));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileFilter(new FileNameExtensionFilter("XML Presentations", "xml"));
				int result = fileChooser.showOpenDialog(parent);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					Command command = new OpenFileCommand(presentation, parent, selectedFile.getPath());
					commandInvoker.executeCommand(command);
				}
			}
		});
		fileMenu.add(menuItem = mkMenuItem(NEW));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				presentation.clear();
				// Add a default slide
				Slide slide = new Slide();
				slide.setTitle("New Slide");
				slide.append(1, "Welcome to JabberPoint");
				slide.append(2, "This is a new presentation");
				presentation.append(slide);
				parent.repaint();
			}
		});
		fileMenu.add(menuItem = mkMenuItem(SAVE));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileFilter(new FileNameExtensionFilter("XML Presentations", "xml"));
				int result = fileChooser.showSaveDialog(parent);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					if (!selectedFile.getName().toLowerCase().endsWith(".xml")) {
						selectedFile = new File(selectedFile.getPath() + ".xml");
					}
					Accessor xmlAccessor = new XMLAccessor();
					try {
						xmlAccessor.saveFile(presentation, selectedFile.getPath());
						JOptionPane.showMessageDialog(parent, 
							"Presentation saved successfully!", 
							"Save Success", 
							JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException exc) {
						JOptionPane.showMessageDialog(parent, 
							IOEX + exc, 
							SAVEERR, 
							JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		fileMenu.addSeparator();
		fileMenu.add(menuItem = mkMenuItem(EXIT));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				presentation.exit(0);
			}
		});
		add(fileMenu);
		JMenu viewMenu = new JMenu(VIEW);
		viewMenu.add(menuItem = mkMenuItem(NEXT));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				Command command = new NextSlideCommand(presentation);
				commandInvoker.executeCommand(command);
			}
		});
		viewMenu.add(menuItem = mkMenuItem(PREV));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				presentation.prevSlide();
			}
		});
		viewMenu.add(menuItem = mkMenuItem(GOTO));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String pageNumberStr = JOptionPane.showInputDialog((Object)PAGENR);
				int pageNumber = Integer.parseInt(pageNumberStr);
				presentation.setSlideNumber(pageNumber - 1);
			}
		});
		add(viewMenu);
		JMenu helpMenu = new JMenu(HELP);
		helpMenu.add(menuItem = mkMenuItem(ABOUT));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AboutBox.show(parent);
			}
		});
		add(helpMenu);
	}

// create a menu item
	public JMenuItem mkMenuItem(String name) {
		JMenuItem menuItem = new JMenuItem(name);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(name.charAt(0), KeyEvent.CTRL_DOWN_MASK));
		return menuItem;
	}
}
