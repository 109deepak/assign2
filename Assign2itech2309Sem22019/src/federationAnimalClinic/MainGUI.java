package federationAnimalClinic;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MainGUI  implements ActionListener {
	JFrame mainJFrame;
	JPanel topPanel;
	JButton jbAppointments, jbAnimals, jbVeterinarians, jbFees, jbQuit;
	Clinic c ;
	
	public MainGUI() {
		mainJFrame = new JFrame(" Federation Animal Clinic ");
		mainJFrame.setBounds(0,100,350,300);
		mainJFrame.setLayout(new BorderLayout(20,20));
		c = new Clinic("FederationClinic.csv");
	}
	
	public void startGUI() {
		this.mainJFrame.add(createTopPanel(), BorderLayout.NORTH);
		JPanel emptyPanel = new JPanel();
		this.mainJFrame.add(emptyPanel, BorderLayout.WEST);
		this.mainJFrame.add(emptyPanel, BorderLayout.EAST);
		this.mainJFrame.add(createMainPanel(), BorderLayout.CENTER);
		this.mainJFrame.add(createBottomPanel(), BorderLayout.SOUTH);
		this.mainJFrame.setEnabled(true);
		this.mainJFrame.setVisible(true);
	}
	
	
	public JPanel createTopPanel() {
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout(10,10));
		ImageIcon ii = new ImageIcon(getAnimalImage());
		JLabel jlpic = new JLabel(ii);
		jlpic.setSize(5,5);
		topPanel.add(jlpic, BorderLayout.WEST);
		topPanel.add(new JLabel("Federation Animal Clinic System"), BorderLayout.CENTER);
		JLabel helpLabel = new JLabel("Help");
		helpLabel.setEnabled(false);
		topPanel.add(helpLabel, BorderLayout.EAST);
		topPanel.setVisible(true);
		return topPanel;
	}
	
	private Image getAnimalImage() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		return kit.getImage("dog.jpg"); //https://pixabay.com/photos/dog-animal-portrait-pet-brown-3277416/
	}
	

	private JPanel createMainPanel() {
		JPanel jp = new JPanel();
		//jp.setLayout(new BoxLayout(jp, BoxLayout.PAGE_AXIS));
		jp.setLayout(new GridLayout(4,3,20,7));
		jbAppointments = new JButton("Appointments");
		jp.add(jbAppointments);
		jbAppointments.addActionListener(this);
		jbAnimals = new JButton("Animals");
		jbAnimals.addActionListener(this);
		jp.add(jbAnimals);
		jbVeterinarians = new JButton("Veterinarians");
		jp.add(jbVeterinarians);
		jbVeterinarians.setEnabled(false);
		jbFees  = new JButton("Fees");
		jbFees.setEnabled(false);
		jp.add(jbFees);
		
		//jp.add(new JLabel(" ")); // to force empty 3rd column in grid layout
		return jp;
	}
	
	private JPanel createBottomPanel() {
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		jbQuit = new JButton("Quit");
		jp.add(jbQuit, BorderLayout.EAST);
		jbQuit.addActionListener(this);
		return jp;
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		AnimalGUI agui;
		ManageBookingsGUI bgui;
		
		if (e.getSource() == jbAnimals) 
			agui = new AnimalGUI(c.getAnimals());
		else if (e.getSource() == jbAppointments) {
			bgui = new ManageBookingsGUI(c);
		}
		else if (e.getSource() == jbQuit)
			System.exit(0);
		
	}

}

