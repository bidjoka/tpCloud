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
	private String auteur; 
	
	private Long nbSignataire;
	
	public Long signer() {
	
		nbSignataire = nbSignataire ++;	
		return nbSignataire;
		
	}
	
	public Petition() {
		
	}
	
	public Petition(String nom, String message, String posteur) {
		
		this.nom = nom;
		this.message = message;
		this.auteur = posteur;
	}

	public Petition(String nom, String message) {

		this.nom = nom;
		this.message = message;
		
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

	public String getAuteur() {
		return auteur;
	}

	public void setPosteur(String auteur) {
		this.auteur = auteur;
	}

	public Long getNbSignataire() {
		return nbSignataire;
	}

	public void setNbSignataire(Long nbSignataire) {
		this.nbSignataire = nbSignataire;
	}
	
}

