package federationAnimalClinic;

public class Facade {
	private Singleton singleton;


	public Facade(){
		singleton = new Singleton(null);
	}

	public void action(){
		singleton.actionPerformed(null);
	}
	public void jpanel(){
		singleton.createTopPanel();
	}
}
