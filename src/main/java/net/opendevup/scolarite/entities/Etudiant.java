package net.opendevup.scolarite.entities;

import java.io.Serializable; // because we use JPA
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


@Entity // on indique à Spring de se connecter au dataSource pour voir si une table correspond à notre class ici
// si elle n'existe pas il peut la crééer lui même si on lui dit de le faire dans application.properties
public class Etudiant implements Serializable {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="NOM", length=30)
	@NotEmpty // annotation de validation
	@Size(min=2, max=30) // on peut rajouter , message="my message" MAIS il faudra revoir l'internationalisation
	private String nom;
	
	@DateTimeFormat(pattern="yyyy-MM-dd") // va servir quand on travail avec des formulaire sinon quand il va mapper il va donner une exception (pas oublier dans le controller mettre @Valid pour que le dispatcherServlet valide les données qu'il a recu
	private Date dateNaissance;
	@NotEmpty
	@Email
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
