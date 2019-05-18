package cloud;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.Named;

import object.Petition;

@Api(name = "MonApi", version = "v1")
public class ApiEndpoint {

	public void CreerPetition(@Named("nom") String Nom, 
								@Named("message") String message, 
								@Named("nbSignataire") Long nb) {
	    
	    Petition pet = new Petition();
	    
	}
	
	public void trouverPetition(@Named("nom") String Nom, 
			@Named("message") String message, 
			@Named("nbSignataire") Long nb) {

Petition pet = new Petition();

}
	
	public void supprimerPetition(@Named("anciennom") String ancienNom, @Named("nvnom") String nouveauNom) {
	    

	}	
}
