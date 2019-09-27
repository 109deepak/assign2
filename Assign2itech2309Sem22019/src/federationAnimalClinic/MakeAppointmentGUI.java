package federationAnimalClinic;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

//Iteration 1: Make Appointments Screen
public class MakeAppointmentGUI implements ActionListener {

	JFrame jfMain = new JFrame(" Federation Animal Clinic ");
	JLabel jLSelectAnimal, jLSelectVeterinarian, jLSelectRoom, jLSelectTypeofAppointment, jlAdditionalRequirements,
			jlDateTime;
	JButton jbSave, jbCancel, jbQuitBooking;
	JTextField jtaAdditionalRequirements, jtaDateTime;
	Clinic myClinic;

	ArrayList<String> Animals = new ArrayList<String>();
	ArrayList<String> Veterinarian = new ArrayList<String>();
	JComboBox<String> jcbAnimals, jcbVeterinarians, jcbRoom, jcbTypeofAppointments;

	public MakeAppointmentGUI(Clinic c) {
		setupData();
		jfMain.add(createTopPanel(), BorderLayout.NORTH);
		jfMain.add(createMainPanel(), BorderLayout.CENTER);
		jfMain.add(createBottomPanel(), BorderLayout.SOUTH);
		jfMain.setBounds(200, 150, 500, 500);
		jfMain.setVisible(true);
		myClinic = c;

	}

	private void setupData() {
		ArrayList<ArrayList<String>> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("FederationClinic.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				ArrayList<String> temp = new ArrayList<String>(Arrays.asList(values));
				records.add(temp);
			}
			for (ArrayList<String> record : records) {
				String type = record.get(0);

				if (type.contentEquals("Veterinarian")) {
					Veterinarian.add(record.get(1));
				} else if (type.contentEquals("Animal") || type.contentEquals("InsuredAnimal")) {
					Animals.add(record.get(1));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public JPanel createTopPanel() {
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout(10, 10));
		ImageIcon ii = new ImageIcon(getAnimalImage());
		JLabel jlpic = new JLabel(ii);
		jlpic.setSize(5, 5);
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
		return kit.getImage("dog.jpg"); // https://pixabay.com/photos/dog-animal-portrait-pet-brown-3277416/
	}

	private JPanel createMainPanel() {
		JPanel jp = new JPanel();

		jp.setLayout(new GridLayout(15, 1, 5, 5));
		jp.add(new JLabel("Book Appointments"));

		// Animal Selection
		jLSelectAnimal = new JLabel("Select an Animal");
		jp.add(jLSelectAnimal);
		jcbAnimals = new JComboBox<String>();
		jcbAnimals.addItem("Select Animal..");
		for (String animal : Animals) {
			jcbAnimals.addItem(animal);
		}
		jp.add(jcbAnimals);

		// Veterinarian Selection
		jLSelectVeterinarian = new JLabel("Select a Veterinarian");
		jp.add(jLSelectVeterinarian);
		jcbVeterinarians = new JComboBox<String>();
		jcbVeterinarians.addItem("Select Veterinarian..");
		for (String Veterinarian : Veterinarian) {
			jcbVeterinarians.addItem(Veterinarian);
		}
		jp.add(jcbVeterinarians);

		// Room Selection
		jLSelectRoom = new JLabel("Select a Room");
		jp.add(jLSelectRoom);
		jcbRoom = new JComboBox<String>();
		jcbRoom.addItem("Select Room..");
		jcbRoom.addItem("101A");
		jcbRoom.addItem("101B");
		jcbRoom.addItem("102A");
		jcbRoom.addItem("102B");
		jp.add(jcbRoom);

		// Appointment Type Selection
		jLSelectTypeofAppointment = new JLabel("Select Type of Appointment");
		jp.add(jLSelectTypeofAppointment);
		jcbTypeofAppointments = new JComboBox<String>();
		jcbTypeofAppointments.addItem("Select Type of Appointment..");
		jcbTypeofAppointments.addItem("Doctor Appointment");
		jcbTypeofAppointments.addItem("GP");
		jp.add(jcbTypeofAppointments);

		// Appointment Date Time Selection
		jlDateTime = new JLabel("Select Appointment DateTime");
		jp.add(jlDateTime);
		jtaDateTime = new JTextField();
		jp.add(jtaDateTime);

		// Additional Requirements
		jlAdditionalRequirements = new JLabel("Additional Requirements");
		jp.add(jlAdditionalRequirements);
		jtaAdditionalRequirements = new JTextField();
		jp.add(jtaAdditionalRequirements);

		return jp;
	}

	private JPanel createBottomPanel() {
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		jbSave = new JButton("Save");
		jbSave.addActionListener(this);
		jp.add(jbSave, BorderLayout.NORTH);

		jbCancel = new JButton("Reset");
		jbCancel.addActionListener(this);
		jp.add(jbCancel, BorderLayout.WEST);

		jbQuitBooking = new JButton("Quit");
		jbQuitBooking.addActionListener(this);
		jp.add(jbQuitBooking, BorderLayout.EAST);
		return jp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbQuitBooking)
			this.jfMain.dispose();
		else if (e.getSource() == jbSave) {
			saveAppointment();

		} else if (e.getSource() == jbCancel) {
			jtaAdditionalRequirements.setText("");
			jtaDateTime.setText("");
			jcbAnimals.setSelectedIndex(0);
			jcbVeterinarians.setSelectedIndex(0);
			jcbRoom.setSelectedIndex(0);
			jcbTypeofAppointments.setSelectedIndex(0);
		}

	}

	private void saveAppointment() {
		String Animals = jcbAnimals.getSelectedItem().toString().trim();
		String Veterinarians = jcbVeterinarians.getSelectedItem().toString().trim();
		String Room = jcbRoom.getSelectedItem().toString().trim();
		String AppointmentType = jcbTypeofAppointments.getSelectedItem().toString().trim();
		String AppointmentDateTime = jtaDateTime.getText().toString().trim();
		String AdditionalRequirements = jtaAdditionalRequirements.getText().toString().trim();

		File appointment = new File("Appointment" + new Random(100).toString() + ".csv");

		try {
			PrintWriter writer = new PrintWriter(appointment);
			StringBuilder sb = new StringBuilder();
			sb.append("Animal,");
			sb.append(',');
			sb.append("Veterian");
			sb.append(',');
			sb.append("Room");
			sb.append(',');
			sb.append("Appointment Type");
			sb.append(',');
			sb.append("Appointment DateTime");
			sb.append(',');
			sb.append("Additional Requirements");
			sb.append('\n');

			sb.append(Animals);
			sb.append(',');
			sb.append(Veterinarians);
			sb.append(',');
			sb.append(Room);
			sb.append(',');
			sb.append(AppointmentType);
			sb.append(',');
			sb.append(AppointmentDateTime);
			sb.append(',');
			sb.append(AdditionalRequirements);
			sb.append('\n');

			writer.write(sb.toString());
			writer.close();
			if (appointment.exists())
				JOptionPane.showMessageDialog(jfMain, "Saved Successfully!" + appointment.getAbsolutePath());
			else
				JOptionPane.showMessageDialog(jfMain, "Error saving appointment!");

		} catch (FileNotFoundException e1) {
			System.out.println(e1.getMessage());
		}

	}
}
