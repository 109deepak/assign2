package federationAnimalClinic;

import federationAnimalClinic.Controller;
import federationAnimalClinic.Model;
import federationAnimalClinic.Singleton;

public class MVCmain {
	

	
	Model m = new Model();
	Singleton v = new Singleton(null);
	Controller c = new Controller(m, v);
	public void main(){
		c.initView();	
	}

	
	
	
	
	

}
