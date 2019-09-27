package federationAnimalClinic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Model {
	
	
	
	
	ArrayList<String> Animals = new ArrayList<String>();
	ArrayList<String> Veterinarian = new ArrayList<String>();
	
	
	
	
	public void setupData() {
		
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
	
	
	
	
	
	
	
	
	

}
