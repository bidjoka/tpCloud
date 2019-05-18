package cloud;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Key;
import static com.googlecode.objectify.ObjectifyService.ofy;

import object.Petition;
import object.PetitionIndex;

public class ApiRepository {

	static {
        ObjectifyService.register(Petition.class);
        ObjectifyService.register(PetitionIndex.class);
    }
	
	public ApiRepository() {
		
	}
	
	public Petition creer(Petition petCreer) {
		ofy().save().entity(petCreer).now();
		return petCreer;	
	}
	
	
}
