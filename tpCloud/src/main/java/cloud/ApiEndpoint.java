package cloud;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;


import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.NotFoundException;
import com.googlecode.objectify.ObjectifyService;

import object.Petition;

@Api(name = "myApi", version = "v1",
	namespace = @ApiNamespace(ownerDomain = "helloworld.example.com",
		ownerName = "helloworld.example.com",
		packagePath = ""))
public class ApiEndpoint {
	
	private static final Logger logger = Logger.getLogger(ApiEndpoint.class.getName());
	
	static {
        ObjectifyService.register(Petition.class);
    }
	
	@ApiMethod(name = "trouver")
	public Petition trouverPetition(@Named("id") String id) throws NotFoundException {

		Petition pet = ofy().load().type(Petition.class).id(id).now();
        if (pet == null) {
            throw new NotFoundException("la petition d': " + id +"n'existe pas");
        }
        return pet;
    }
	
	@ApiMethod(name="petition",
			   path = "petition/{nom}/{message}/{auteur}",
			   httpMethod = HttpMethod.POST)
	public Petition CreerPetition(@Named("nom") String nom,
			@Named("message") String message, 
			@Named("auteur") String auteur) {
		Petition pet = new Petition(nom, message, auteur);
		ofy().save().entity(pet).now();
        logger.info("petition : " + pet.getId());
        return pet;
    }
	
	@ApiMethod(path = "petition/{id}",
			   httpMethod = HttpMethod.POST)
	public void supprimerPetition(@Named("id") Long id) throws NotFoundException {
		checkExists(id);
        ofy().delete().type(Petition.class).id(id).now();
        logger.info("suppression de la petition : " + id);
	}
	
	@ApiMethod(name = "petitions",
			   path = "petitions",
			   httpMethod = HttpMethod.GET)
    public Collection<Petition> list() {
        List<Petition> petitions = ofy().load().type(Petition.class).list();
        return petitions;
    }
	
	private void checkExists(Long id) throws NotFoundException {
        try {
            ofy().load().type(Petition.class).id(id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("ne peut pas trouver de petition avec l'id: " + id);
        }
    }
	
}
