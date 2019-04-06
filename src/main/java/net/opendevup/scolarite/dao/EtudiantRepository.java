package net.opendevup.scolarite.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.opendevup.scolarite.entities.Etudiant;

// On peut creer une interface et implementer nos method classique find create etc

// MAIS ici on va utiliser l'interface dinamyque de JPA JpaRepository<TypeEntity, IdTypeFromEntity> qui va deja implementer toutes les method (create, delete, pagination etc)
//Ce truck JpaRepository vient du framework SpringData

// extends parcequ'on c'est une interface donc pas implements
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

	// Dans l'odre Spring Data > JPA > Hibernate > JBDC	 (le fait d'utiliser Spring data nous permet de ne pas avoir a faire des config.xml)
	
	// Spring data method default  
	// save
	// findAll
	// findAll with pagiable
	// getOne
	// delete
	// etc
	
	//Pas default il faut utilsier des noms conventions plutot que configuration donc FindByProperty
	public List<Etudiant> findByNom(String nom);
	public Page<Etudiant> findByNom(String n, Pageable p); // Pageable ajoute limit
	
	//Si on a requête custom on l'inique avec @Query (spring data) (equivalent de requête DQL en symfony)
	@Query("select e from Etudiant e where e.nom like :mc") // requête HQL (like dql for symfony)
	public Page<Etudiant> chercherEtudiants(@Param("mc") String mc, Pageable p); // Pageable ajoute limit

	@Query("select e from Etudiant e where e.dateNaissance > :date1 and e.dateNaissance < :date2") // requête HQL (like dql for symfony)
	public List<Etudiant> chercherEtudiants(@Param("date1") Date d1, @Param("date2") Date d2); // Pageable ajoute limit
}
