package object;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;


@Entity
@Cache
public class Signataire {
	@Id
	private
	String signataire;
	@Parent
	private
	Ref<Petition> parent;
	
	public Signataire(Ref<Petition> parent, String signataire) {
		this.setParent(parent);
		this.setSignataire(signataire);
	}
	
	public Signataire() {
		
	}

	public Ref<Petition> getParent() {
		return parent;
	}

	public void setParent(Ref<Petition> parent) {
		this.parent = parent;
	}

	public String getSignataire() {
		return signataire;
	}

	public void setSignataire(String signataire) {
		this.signataire = signataire;
	}

}
