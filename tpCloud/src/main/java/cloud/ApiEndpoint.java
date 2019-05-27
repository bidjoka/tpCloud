package cloud;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Collection;
import java.util.List;


import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.NotFoundException;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Ref;
import com.google.appengine.api.users.*;

import object.Petition;
import object.Signataire;

@Api(name = "myApi", version = "v1",
	namespace = @ApiNamespace(ownerDomain = "helloworld.example.com",
		ownerName = "helloworld.example.com",
		packagePath = ""))
public class ApiEndpoint {
	
	static {
        ObjectifyService.register(Petition.class);
        ObjectifyService.register(Signataire.class);
    }
	
	UserService userService = UserServiceFactory.getUserService();
	
	@ApiMethod(name="petition",
			   path = "petition/{nom}/{message}/{auteur}",
			   httpMethod = HttpMethod.POST)
	public Petition CreerPetition(@Named("nom") String nom,
			@Named("message") String message, 
			@Named("auteur") String auteur) {
		Petition pet = new Petition(nom, message, auteur);
		ofy().save().entity(pet).now();
     return pet;
	}
	
	
	@ApiMethod(name="connexion",
			   path = "connexion",
			   httpMethod = HttpMethod.POST)
	public void Connexion() {
		if (userService.getCurrentUser() == null) {
			userService.createLoginURL("/");
		}else {
			userService.createLogoutURL("/");
		}
	}

	@ApiMethod(name = "Mespetitions",
			   path = "Mespetitions/{auteur}",
			   httpMethod = HttpMethod.GET)
    public Collection<Petition> list(@Named("auteur") String auteur) {
        List<Petition> petitions = ofy().load().type(Petition.class)
        							.filter("auteur", auteur).list();
        return petitions;
    }
	
	@ApiMethod(name = "petitions",
			   path = "petitions",
			   httpMethod = HttpMethod.GET)
	public Collection<Petition> topList() {
		List<Petition> petitions = ofy().load().type(Petition.class).list();
		return petitions;
	}
	
	@ApiMethod(name = "signer",
			   path = "signer/{id}/{signeur}",
			   httpMethod = HttpMethod.GET)
	private void SignerPetition(@Named("id")Long id, @Named("signeur")String signeur) 
								throws NotFoundException {
        	Petition pet = ofy().load().type(Petition.class).id(id).now();
        	Signataire signataire = ofy().load().type(Signataire.class).parent(pet).id(signeur).now();
        	if(signataire == null) {
        		Signataire sign = new Signataire();
        		sign.setSignataire(signeur);
        		sign.setParent(Ref.create(pet));
        		ofy().save().entity(sign).now();
        		pet.signer();
        		ofy().save().entity(pet).now();
        	}
    }
	
}
