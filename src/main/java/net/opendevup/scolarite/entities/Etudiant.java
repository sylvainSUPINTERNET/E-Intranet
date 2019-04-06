package net.opendevup.scolarite.entities;

import java.io.Serializable; // because we use JPA
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity // on indique à Spring de se connecter au dataSource pour voir si une table correspond à notre class ici
// si elle n'existe pas il peut la crééer lui même si on lui dit de le faire dans application.properties
public class Etudiant implements Serializable {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="NOM", length=30)
	private String nom;
	
	private Date dateNaissance;
	private String email;
	private String photo;
	
	// si on creer un constructeur avec des valeur OU DOIT indiquer un constructeur empty => sinon erreur
	
	//Constructor for JPA and for ME 
	public Etudiant() { // No default constructor exception => si on le met pas
		super();
		// TODO Auto-generated constructor stub
	}
	
	//Constructor for ME
	public Etudiant(String nom, Date dateNaissance, String email, String photo) {
		super();
		this.nom = nom;
		this.dateNaissance = dateNaissance;
		this.email = email;
		this.photo = photo;
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


	public Date getDateNaissance() {
		return dateNaissance;
	}


	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}
	

}
