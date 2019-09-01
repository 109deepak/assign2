package federationAnimalClinic;
import java.util.*;

public class Veterinarian {

	
		private String specialisation; // Veterinarian's special skill
		private ArrayList<Animal> listOfAnimals; // Veterinarian's personal list of animals responsible for care
		private int maxNumOfAnimals; // Maximum no. of animals a Veterinarian can have at one time
		private double hourlyRate; // Hourly rate of pay
		private static int vetIdNo = 1000; // Internal use only - no get/set methods
		private String name;  // Name of Veterinarian
		private int age;  // Age of Veterinarian
		private String identifier; // Veterinarian's staff id 

		public final boolean HasAnimals(){  // does the Vet have any animals registered for care?
			return !listOfAnimals.isEmpty();
		}
		
		public final boolean hasCapacity() {
			return (this.listOfAnimals.size() < this.maxNumOfAnimals);
		}

		public Veterinarian(){  // constructor to set up a Veterinarian object
			this("unknown name", 0, 0, "Unknown",100);
		}

		public Veterinarian(String name, int age, int animals2, String specialisation){
			this(name,age, animals2,specialisation, 100);
			}

		public Veterinarian(String name, int age, int animals2, String specialisation, double hourlyRate){
			this.name = name;
			this.age = age;
			listOfAnimals = new ArrayList<Animal>();
			listOfAnimals.clear();
			maxNumOfAnimals = animals2;
			this.specialisation = specialisation;
			this.setIdentifier();
			this.hourlyRate = hourlyRate;
		}

		public void addAnAnimal(Animal a) {
			if (this.listOfAnimals==null)
				this.listOfAnimals = new ArrayList<Animal>();
			if (a!=null && this.listOfAnimals.size() < this.maxNumOfAnimals)
				this.listOfAnimals.add(a);
		}
		
		public String toString(){
			return "*******************\n Dr " + this.name + "\n\t id number: " + 
					identifier + 
					"\n\t Max Number of Patients: " + this.maxNumOfAnimals;
		}

		public final double getHourlyRate(){
			return this.hourlyRate;
		}

		

		public final String printListOfAnimals(){
			String temp = "";
			temp += "\nList of Animals registered for Dr " + this.name + "\n\n";
			for (int i = 0; i < this.listOfAnimals.size(); i++){
				temp += ((Animal) this.listOfAnimals.get(i)) + "\n";
			}
			return temp;
		}

		public final void setIdentifier(){
			this.identifier = "V" + String.valueOf(vetIdNo++);
		}

		public String getName() {
			return this.name;
		}
	
		
	}
