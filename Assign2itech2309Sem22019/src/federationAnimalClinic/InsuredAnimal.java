/**
 * 
 */
package federationAnimalClinic;


public class InsuredAnimal extends Animal {

	private String preferredVeterinarian=null; // Only useful for "Insured" - null for Uninsured animal
	private String insuredHealthFundNo; // Fund number for animals with insured health

	/**
	 * @param name
	 * @param age
	 * @param animalType
	 * @param preferredVeterinarian
	 * @param insuredHealthFundNo
	 * @param hoursTreated
	 * @param aBreed
	 */
	public InsuredAnimal(String name, int age, String animalType, String preferredVeterinarian,
			String insuredHealthFundNo, int hoursTreated, String aBreed) {
		super(name, age, hoursTreated, aBreed);
		this.preferredVeterinarian = preferredVeterinarian;
		this.insuredHealthFundNo = insuredHealthFundNo;
	}
	
	@Override
	public String createMessageMvToANewVet (){
		String output = "";
		
		output += "Preferred Veterinarian unavailable. Reassigning Insured Animal " + this.getName() + " to a different Vet at this clinic\n";
		
		System.out.print(output);
		return output;
	}
	
	@Override
	public String createMessageMv(){
		String output = "";
		output += "Will need to reassign Insured Animal " + this.getName() + " to a different clinic\n";
		
		System.out.print(output);
		return output;
	}
	
	@Override
	public String getPreferredVeterinarian () {
		return this.preferredVeterinarian;
	}

	@Override
	public String getAnimalType() {
		return "Insured";
	}
	
	@Override
	public String toString() {
		return "Insured Animal - " + super.toString() + 
				"\n\t Preferred Veterinarian: " + this.preferredVeterinarian + "\n\t Insured Health Fund Number: " + this.insuredHealthFundNo +"\n" ;
	}
	
	@Override
	public boolean isInsured() {
		return true;
	}

}
