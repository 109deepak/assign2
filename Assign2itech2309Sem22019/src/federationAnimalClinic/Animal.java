package federationAnimalClinic;

import java.io.File;
import java.util.*;

public abstract class Animal {

	
	
		private Veterinarian assignedVeterinarian=null; // Veterinarian assigned to Animal
		private static int pIdNo = 100000; // No get and set methods - internal use only
		private String name;
		private int age;
		private String identifier; // Animal identifier - unique for each animal in the system
		private int hoursTreated; // No of hours animal treated by Veterinarian
		private String animalBreed;

		public final String getName(){
			return name;
		}
		
		public abstract String getAnimalType();
		
		public Animal(String name, int age, int hoursTreated, String aBreed){ //create animal
			this.name = name;
			this.age = age;
			this.SetIdentifier();
			this.hoursTreated = hoursTreated;
			this.animalBreed = aBreed;
		}

		public abstract String createMessageMvToANewVet();
		public abstract String createMessageMv();
		
		
			
		public boolean assignVeterinarian (Clinic c) {
			return c.assignVeterinarian(this);
		}
		
		public String getPreferredVeterinarian () {  // will be overridden by Insured Animal
			return null;
		}

		
		public final void SetIdentifier(){
			this.identifier = "P" + pIdNo++;
		}

		
		public String toString(){
			String temp =  this.name + " Age "+ this.getAge() + " "+ "\n\t Identifier: "  + this.identifier + "\n\t Assigned Veterinarian: ";
			String temp2;
			if (this.assignedVeterinarian == null){
				temp2 = "not assigned as yet";
			}
			else{
				temp2 = this.assignedVeterinarian.getName() + "\n\t Fee for "+ this.hoursTreated + " hours consultation = $" + this.hoursTreated * this.assignedVeterinarian.getHourlyRate();
			}
			return temp + temp2 + "\n";
		}

		public void setAssignedVeterinarian(Veterinarian vet) {
			this.assignedVeterinarian = vet;
			
		}

		public Veterinarian getAssignedVeterinarian() {
			return this.assignedVeterinarian;
		}

		public abstract boolean isInsured();
		
		public int getAge() {
			return this.age;
		}
		
			

		
}
