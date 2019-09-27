package federationAnimalClinic;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
public class Controller {
	
	public Model model;
	public Singleton view;
	
	
	public Controller(Model m, Singleton v){
		model = m;
		view = v;
	initView();
		
		}
	
	
	 public void initView(){
		 
		 
		 
		 for(String animal : model.Animals){
			 view.jcbAnimals.addItem(animal);
			 
		 }
		 
		view. jcbAnimals = new JComboBox<String>();
			view.jcbAnimals.addItem("Select Animal..");
			for (String animal :model. Animals) {
				view.jcbAnimals.addItem(animal);
			}
			
			
		 for (String Veterinarian : model.Veterinarian ) {
				view.jcbVeterinarians.addItem(Veterinarian);
		 
		 
	 }


	 }
	 
	 
	
	 
	 
	
	 
	 
	 
	 
	 
	 
	 
	 }
