package federationAnimalClinic;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class AnimalGUI {
 JFrame mainJFrame = new JFrame(" Federation Animal Clinic ");
 
 public AnimalGUI (ArrayList<Animal> theAnimals) {
	 mainJFrame.setBounds(100,100,600,600);
	 mainJFrame.add(createTopPanel(), BorderLayout.NORTH);
	 mainJFrame.add(createAnimalPanel(theAnimals), BorderLayout.CENTER);
	 mainJFrame.setVisible(true);
 }
	
 private JPanel createTopPanel() {
	 JPanel toppanel = new JPanel();
	 toppanel.add(new JLabel("List of Animals in this clinic")); 
	 return toppanel;
	 
 }
 
 
 private JPanel createAnimalPanel(ArrayList<Animal> alist) {
	 JPanel ap = new JPanel();
	 
	 JTextArea outputTextArea = new JTextArea("list of Animals will appear here");
	 JScrollPane scrollPane = new JScrollPane(outputTextArea);
	 outputTextArea.setColumns(50);
     outputTextArea.setRows(35);
     scrollPane = new JScrollPane(outputTextArea);

	 ap.add(scrollPane);
	 String AnimalString = getAnimalString(alist);
	 outputTextArea.setText(AnimalString);
	 ap.setVisible(true);
	 
	 return ap;
 }
 
 private String getAnimalString(ArrayList<Animal> alist) {
	 String ans = "";
	 for (Animal a : alist) {
		 ans += a.toString();
	 }
	 return ans;
 }
 
}
