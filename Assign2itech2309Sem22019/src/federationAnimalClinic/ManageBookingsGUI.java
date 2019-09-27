package federationAnimalClinic;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class ManageBookingsGUI implements ActionListener {

	JFrame jfMain = new JFrame(" Federation Animal Clinic ");
	JButton jbMakeBooking, jbViewBooking, jbDeleteBooking, jbTransferBooking, jbQuitBooking;
	Clinic myClinic;
	
	public ManageBookingsGUI (Clinic c) {
	 jfMain.add(createTopPanel(), BorderLayout.NORTH);
	 jfMain.add(createMainPanel(), BorderLayout.CENTER);	
	 jfMain.add(createBottomPanel(), BorderLayout.SOUTH);
	 jfMain.setBounds(150,100, 300,400);
	 jfMain.setVisible(true);
	 myClinic = c;
	 
	}
	
	
	public JPanel createTopPanel() {
		JPanel topPanel = new JPanel();
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
		jp.setLayout(new GridLayout(6,1,5,5));
		jp.add(new JLabel("Manage Appointments"));
		jbMakeBooking = new JButton("Make");
		jbMakeBooking.setPreferredSize(new Dimension(70, 70));
		jp.add(jbMakeBooking);
		jbMakeBooking.addActionListener(this);
		jbViewBooking = new JButton("View");
		jbViewBooking.setPreferredSize(new Dimension(70, 70));
		jp.add(jbViewBooking);
		jbDeleteBooking = new JButton("Cancel");
		jbDeleteBooking.setPreferredSize(new Dimension(70, 70));
		jp.add(jbDeleteBooking);
		jbTransferBooking = new JButton("Transfer"); 
		jp.add(jbTransferBooking);
		jbTransferBooking.setPreferredSize(new Dimension(70, 70));
		
		return jp;
	}
	private JPanel createBottomPanel() {
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		jbQuitBooking = new JButton("Quit");
		jbQuitBooking.addActionListener(this);
		jp.add(jbQuitBooking, BorderLayout.EAST);
		return jp;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// add more options here
		//if (e.getSource() == jbMakeBooking)
			//new BookingGUI(myClinic);
		if (e.getSource() == jbQuitBooking)
			this.jfMain.dispose();
		else if (e.getSource()==jbMakeBooking){
			Facade f = new Facade();
		f.jpanel();
		
		f.action();
		}
	}

}
