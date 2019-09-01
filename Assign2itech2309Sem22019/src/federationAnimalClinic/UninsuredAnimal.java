/**
 * 
 */
package federationAnimalClinic;


public class UninsuredAnimal extends Animal {

	/**
	 * @param name
	 * @param age
	 * @param hoursTreated
	 * @param aBreed
	 */
	public UninsuredAnimal(String name, int age, int hoursTreated, String aBreed) {
		super(name, age, hoursTreated, aBreed);
		
	}

	/**
	 * @param name
	 * @param age
	 * @param hoursTreated
	 * @param aBreed
	 */
	public UninsuredAnimal(String name, int age, String animalType, String preferredVeterinarian,
			String insuredHealthFundNo, int hoursTreated, String aBreed) {
		super(name, age, hoursTreated, aBreed);
		
	}

	public String createMessageMvToANewVet (){
		String output = "";
		
		output += "Assigning uninsured animal " + this.getName() +
								  " to the waiting list until a veterinarian becomes available somewhere\n";
		
		System.out.print(output);
		return output;
	}
	
	public String createMessageMv(){
		String output = "";
		
		output += "Will need to assign uninsured animal " + this.getName() +
								  " to the waiting list until a veterinarian becomes available\n";
		System.out.print(output);
		return output;
	}
	
	@Override
	public String getAnimalType() {
		return "Uninsured";
	}
	
	@Override
	public boolean isInsured() {
		return false;
	}

	
	@Override
	public String toString() {
		return "Uninsured Animal - " + super.toString();
	}

}
