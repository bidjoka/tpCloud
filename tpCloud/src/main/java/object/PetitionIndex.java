package object;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;


@Entity
@Cache
public class PetitionIndex {
	@Id 
	Long id;
	@Parent 
	Key<Petition> parent;
	boolean signature;
	
	public PetitionIndex(Long id, Key<Petition> parent) {
		super();
		this.id = id;
		this.parent = parent;
	}
	
	public PetitionIndex() {
		
	}

}
