package object;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;


@Entity
@Cache
public class Petition {

	@Id 
	private Long id;
	private String nom;
	private String message;
	private String posteur; 
	
	private Long nbSignataire;
	
	public Petition() {
		
	}
	
	public Petition(Long id, String nom, String message, Long signataire, String posteur) {
		super();
		this.id = id;
		this.nom = nom;
		this.message = message;
		this.nbSignataire = signataire;
		this.posteur = posteur;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPosteur() {
		return posteur;
	}

	public void setPosteur(String posteur) {
		this.posteur = posteur;
	}

	public Long getNbSignataire() {
		return nbSignataire;
	}

	public void setNbSignataire(Long nbSignataire) {
		this.nbSignataire = nbSignataire;
	}
	
}

