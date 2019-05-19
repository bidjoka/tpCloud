package cloud;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.cmd.Query;

import object.Petition;

@Api(name = "MonApi", version = "v1")
public class ApiEndpoint {
	
	private static final Logger logger = Logger.getLogger(ApiEndpoint.class.getName());
	
	private static final int DEFAULT_LIST_LIMIT = 100;
	
	@ApiMethod(name = "trouver")
	public Petition trouverPetition(@Named("id") String id) throws NotFoundException {

		Petition pet = ofy().load().type(Petition.class).id(id).now();
        if (pet == null) {
            throw new NotFoundException("la petition d': " + id +"n'existe pas");
        }
        return pet;
    }
	
	@ApiMethod(name = "creer")
	public Petition CreerPetition(@Named("nom") String nom,
			@Named("message") String message, 
			@Named("auteur") String auteur) {
		Petition pet = new Petition(nom, message, auteur);
		ofy().save().entity(pet).now();
        logger.info("petition : " + pet.getId());
        return ofy().load().entity(pet).now();
    }
	
	@ApiMethod(name = "supprimer")
	public void supprimerPetition(@Named("id") Long id) throws NotFoundException {
		checkExists(id);
        ofy().delete().type(Petition.class).id(id).now();
        logger.info("suppression de la petition : " + id);
	}
	
	@ApiMethod(name = "lister")
    public CollectionResponse<Petition> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<Petition> query = ofy().load().type(Petition.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<Petition> queryIterator = query.iterator();
        List<Petition> petitionList = new ArrayList<Petition>(limit);
        while (queryIterator.hasNext()) {
            petitionList.add(queryIterator.next());
        }
        return CollectionResponse.<Petition>builder().setItems(petitionList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }
	
	private void checkExists(Long id) throws NotFoundException {
        try {
            ofy().load().type(Petition.class).id(id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("ne peut pas trouver de petition avec l'id: " + id);
        }
    }
	
}
