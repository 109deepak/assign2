package federationAnimalClinic;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Clinic {
	private ArrayList<Veterinarian> listOfVeterinarians;
	private ArrayList<Animal> listOfAnimals;
	private String inputFileName;
	private String name;
	
	public Clinic(String fileNameForInput){
		listOfVeterinarians = new ArrayList<Veterinarian>();
		listOfVeterinarians.clear();
		listOfAnimals = new ArrayList<Animal>();
		listOfAnimals.clear();
		inputFileName=fileNameForInput;
		setUpData();
		allocateAnimalsToVeterinarian();
		
	}
	
	public Clinic () {
		this("FederationClinic.csv");
		}
	
	private String getListOfVeterinarians() {
		String output="";
		output += "___________________\n\nList of registered veterinarians\n___________________\n\n";
		for (int i = 0; i < this.listOfVeterinarians.size(); i++){
			Veterinarian temp = ((Veterinarian)this.listOfVeterinarians.get(i));
			output += temp.toString() + "\n";
			if (temp.HasAnimals()){
				output += temp.printListOfAnimals();
			}
			else{
				output += "No animals assigned to this veterinarian as yet";
			}
			output += "\n";
		}
		return output;
	}
	
	private String getListOfAnimals () {
		String output ="";
		for (int i = 0; i < this.listOfAnimals.size(); i++){
			output += ((Animal)this.listOfAnimals.get(i)).toString() + "\n";
		}
		return output;
	}
	
	public ArrayList<Animal> getAnimals() {
		return this.listOfAnimals;
	}
	
	public String getInsuredAnimalsString () {
		String output ="";
		Animal a;
		for (int i = 0; i < this.listOfAnimals.size(); i++){
			a = (Animal)this.listOfAnimals.get(i);
			if (a.isInsured())
				output += a.toString() + "\n";
		}
				return output;
	}
	
	public ArrayList<Animal> getInsuredAnimals () {
		ArrayList<Animal> l = new ArrayList<Animal>();
		Animal a;
		for (int i = 0; i < this.listOfAnimals.size(); i++){
			a = (Animal)this.listOfAnimals.get(i);
			if (a.isInsured())
				l.add(a);
		}
				return l;
	}
	public Veterinarian findPreferredVet(String preferredVeterinarian) {
		Veterinarian temp=null;
		for (int i = 0; i < this.listOfVeterinarians.size(); i++){
			temp = listOfVeterinarians.get(i);
			if (temp.getName().equalsIgnoreCase(preferredVeterinarian))
				return temp;
		}
		return null;
	}
		
	public Veterinarian findAvailableVet(Veterinarian preferred) {
		Veterinarian v = null, possibleVet=null;
		boolean found=false;
		int preferredindex=this.listOfVeterinarians.size(); // when no preferred, set to end of list
		int i=0;  // default start at beginning of list
		if (preferred!=null) {  // if have a preferred, start searching at that position
			preferredindex=this.listOfVeterinarians.indexOf(preferred);
			if (preferredindex == (this.listOfVeterinarians.size()-1))
				i=0;  // wrap to start search at beginning of list because preferred index was last
			else
				i = preferredindex+1; // will start search at list position of preferred vet
			
				
		}
		
		boolean doneOnce = false;
		while (this.listOfVeterinarians!=null && (i< this.listOfVeterinarians.size() ) ) {
			if (i==(this.listOfVeterinarians.size()-1) && !doneOnce) { //only do this once
				i=0; // wrap search to start at beginning of list
				doneOnce = true;
			}
			possibleVet = this.listOfVeterinarians.get(i);
			if (possibleVet!=null && possibleVet.hasCapacity()) 
				return possibleVet;
			else   
				i++;
		}
		System.out.println("No available vet found");
		return null;
	}
	
	private String allocateAnimalsToVeterinarian() {
		String output="";
		ArrayList<Animal> list = this.listOfAnimals;
		for (int i = 0; i < list.size(); i++){
			Animal temp = (Animal)list.get(i);
			if (this.listOfVeterinarians == null) {
				output += "- No available veterinarians\n*******************\n"; 
				break;  // go no further will allocations, list of Veterinarians is empty - may need to check for availability in another clinic in a later version 
			}
			if (assignVeterinarian(temp) == false){  // attempt to assign animal to a veterinarian
				output += "\n********************************************************************\n Not yet assigned animal " + temp.getName() + "- No available veterinarians\n********************************************************************\n";
				output += (temp.createMessageMv());
			}
			else{
				output += "Assigning veterinarian " + temp.getAssignedVeterinarian().getName() + " to " + temp.getName() + "\n";
			}
		}
		return output;
	}

	public final boolean assignVeterinarian(Animal a){
		Clinic aClinic = this;
		Veterinarian preferredVet=null, anotherVet=null;
		if (a.getAnimalType().equals("Insured")){
			preferredVet = aClinic.findPreferredVet(a.getPreferredVeterinarian());
			if (preferredVet.hasCapacity()){  // allocate animal to this vet - if possible
				System.out.println("Allocating preferred vet " + preferredVet.getName() + " to " + a.getName());
				a.setAssignedVeterinarian( preferredVet);
				preferredVet.addAnAnimal(a);
				return true;
				}
			else {// that preferred vet has no capacity for new animals on their list, need to find another vet at this clinic
				anotherVet = aClinic.findAvailableVet(preferredVet); 
				if (anotherVet!=null) System.out.println("Replacement Vet "+ anotherVet.getName() + " has capacity");
				if (anotherVet!=null) {
					a.setAssignedVeterinarian(anotherVet);
					anotherVet.addAnAnimal(a);
					System.out.println(a.createMessageMvToANewVet());
					//System.out.println("Allocating "+ a.getName() + " to " + anotherVet.getName() + "\n");
					return true;
					}
				else { // insured animal hasn't been allocated to a vet yet..
					System.out.println(a.createMessageMv());
					System.out.println("Unable to allocate " + a.getName() + " to a vet in this clinic.\n");
					return false;
					}
				}
		}

		else if (a.getAnimalType().equals("Uninsured")){ 
			Veterinarian temp;
			temp = aClinic.findAvailableVet(null);
			if (temp!=null) {
				a.setAssignedVeterinarian( temp);
				temp.addAnAnimal(a);
				System.out.println(a.createMessageMvToANewVet());
				//System.out.println("Allocating uninsured "+ a.getName() + " to " + temp.getName() + "\n");
				return true;
			}
			else { // insured animal hasn't been allocated to a vet yet..
				System.out.println(a.createMessageMv());
				System.out.println("Unable to allocate uninsured " + a.getName() + " to a vet in this clinic.\n");
				return false;
			}
		}

		return false;
	}
	
	
	
	public final String run(){
		String output = "";

		System.out.println("Begin run\n");
		
		
		// Add details of data to output string

		output += this.getListOfVeterinarians();

		output += "\n";
		
		output += "\n___________________\n\nList of animals before veterinarians assigned\n___________________\n\n";
		
		output += this.getListOfAnimals();

		// assign animals to a particular veterinarian in this clinic
		
		output += "\n___________________\n\n Assigning Veterinarians to Animals\n___________________\n";
		
		output += this.allocateAnimalsToVeterinarian();
		
		// Add new information of assignments to output string 

		output += "\n___________________\n\nList of animals after veterinarians assigned\n___________________\n";
		
		output += this.getListOfAnimals();

		output += "___________________\n\nList of veterinarians after animals assigned\n___________________\n" + "\n";
		
		output += this.getListOfVeterinarians();
		
		return output;  // return string to calling method to print out
	}


	public void setUpData () {
 // set up data for clinic
		
		try {
			File inputFile = new File(inputFileName);
			this.setName(inputFileName);
			Scanner s = new Scanner(inputFile);
			String vName,  vSpeciality, line, tokens[], aName,  aBreed, aPreferredVet, aInsuranceNo;
			int vAge, vMax, aHoursTreated, aAge;
			while (s.hasNext()) {
				line = s.nextLine();
				tokens = line.split(",");
				if (tokens[0].equalsIgnoreCase("Veterinarian")) {
					vName = tokens[1];
					vAge = Integer.valueOf(tokens[2]);
					vMax = Integer.valueOf(tokens[3]);
					vSpeciality = tokens[4];
					addAVeterinarian(new Veterinarian(vName, vAge, vMax, vSpeciality));
				}
				if (tokens[0].equalsIgnoreCase("InsuredAnimal")) {
					aName = tokens[1];
					aAge = Integer.valueOf(tokens[2]);
					aPreferredVet = tokens[3];
					aInsuranceNo = tokens[4];
					aHoursTreated = Integer.valueOf(tokens[5]);
					aBreed = tokens[6];
					addAnAnimal(new InsuredAnimal(aName, aAge, "Insured", aPreferredVet, aInsuranceNo, aHoursTreated, aBreed));
					
				}
				if (tokens[0].equalsIgnoreCase("Animal")) {
					aName = tokens[1];
					aAge = Integer.valueOf(tokens[2]);
					aHoursTreated = Integer.valueOf(tokens[3]);
					aBreed = tokens[4];
					addAnAnimal(new UninsuredAnimal(aName, aAge, aHoursTreated, aBreed));
				}
			}
		}
		catch ( IOException e) 
		{
			System.out.println(e.getStackTrace());
			addAVeterinarian(new Veterinarian("Ben Casey", 32, 3, "Small Animals"));
			addAVeterinarian(new Veterinarian("Hawkeye Pierce", 47, 3, "Large Animals"));
			addAVeterinarian(new Veterinarian("Doogie Howser", 22, 1, "Horses"));
			// set up animal data
			addAnAnimal(new InsuredAnimal("Fred Bear", 2, "Insured", "Ben Casey", "HCF236788", 10, "Cockerspaniel"));
			addAnAnimal(new UninsuredAnimal("Betty Bird", 3, 7, "Budgerigar"));
			addAnAnimal(new InsuredAnimal("Bella Plant", 3, "Insured", "Ben Casey", "HCF265123", 23, "Rabbit"));
			addAnAnimal(new InsuredAnimal("Dagwood Dog", 8, "Insured", "Doogie Howser", "HCF265988", 2, "Labrador" ) );
			addAnAnimal(new InsuredAnimal("Ernie", 2, "Insured", "Ben Casey", "HCF134231", 1, "Guinea Pig" ) );
			addAnAnimal(new UninsuredAnimal("Tinkerbell", 4,  1, "Siamese cat") );
			addAnAnimal(new UninsuredAnimal("Slinky", 1, 1, "Blue Tongue Lizard") );
			addAnAnimal(new InsuredAnimal("Mickey Mouse", 5, "Insured", "Ben Casey", "HCF234511", 1, "Mouse") );
		}
	}
	
	private void setName(String inputFileName) {
		this.name = inputFileName;
		
	}

	public void addAnAnimal(Animal a) {
		if (this.listOfAnimals == null)
			this.listOfAnimals = new ArrayList<Animal>();
		if (a!=null) 
			this.listOfAnimals.add(a);
	}
	
	public void addAVeterinarian(Veterinarian v) {
		if (this.listOfVeterinarians == null)
			this.listOfVeterinarians = new ArrayList<Veterinarian>();
		if (v!=null) 
			this.listOfVeterinarians.add(v);
	}

	public String getName() {
		return this.name;
	}

	public String produceReport(String fname) {
		String output="";
		
		output += "\n___________________\n\nList of veterinarians \n___________________\n\n";
		output += this.getListOfVeterinarians();

		output += "\n";
		
		output += "\n___________________\n\nList of animals \n___________________\n\n";
		
		output += this.getListOfAnimals();

		output += "\n";
		
		try {
			File f = new File(fname);
			PrintWriter pw = new PrintWriter (f);
			pw.print(output);
		}
		catch (IOException ioe) 
		{
			System.out.println("Problem with File IO " + fname);
			ioe.printStackTrace();
		}
		
		return output;
				
	}


	@Override
	public String toString() {
		String output="";
		output += "\n___________________\n\nList of veterinarians \n___________________\n\n";
		output += this.getListOfVeterinarians();

		output += "\n";
		
		output += "\n___________________\n\nList of animals \n___________________\n\n";
		
		output += this.getListOfAnimals();
		return output;
	}

	public ArrayList<Veterinarian> getVeterinarians() {
		return this.listOfVeterinarians;
	}
}

