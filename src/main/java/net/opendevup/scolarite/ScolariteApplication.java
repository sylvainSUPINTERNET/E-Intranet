package net.opendevup.scolarite;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import net.opendevup.scolarite.dao.EtudiantRepository;
import net.opendevup.scolarite.entities.Etudiant;

@SpringBootApplication
public class ScolariteApplication {

	public static void main(String[] args) throws ParseException {
		ApplicationContext ctx = SpringApplication.run(ScolariteApplication.class, args);
		//ici on peut recuperer les bean instancier dans ce context par spring boot
		// ctx.getBean(arg);
		EtudiantRepository etudiantRepository = ctx.getBean(EtudiantRepository.class);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		etudiantRepository.save(new Etudiant("Ahmed", df.parse("1998-11-10"), "ahmed@gmail.com", "ahmed-picture.jpg"));
		etudiantRepository.save(new Etudiant("Tata", df.parse("1996-06-07"), "sylvain@gmail.com", "sylvain-picture.jpg"));
		etudiantRepository.save(new Etudiant("Toto", df.parse("1998-12-06"), "toto@gmail.com", "toto-picture1.jpg"));
		etudiantRepository.save(new Etudiant("Toto", df.parse("1998-12-06"), "toto@gmail.com", "toto-picture3.jpg"));
		etudiantRepository.save(new Etudiant("Toto", df.parse("1998-12-06"), "toto@gmail.com", "toto-picture2.jpg"));
		etudiantRepository.save(new Etudiant("Toto", df.parse("1998-12-06"), "toto@gmail.com", "toto-picture4.jpg"));
		etudiantRepository.save(new Etudiant("Toto", df.parse("1998-12-06"), "toto@gmail.com", "toto-picture5.jpg"));
		etudiantRepository.save(new Etudiant("Toto", df.parse("1998-12-06"), "toto@gmail.com", "toto-picture6.jpg"));
		etudiantRepository.save(new Etudiant("Toto", df.parse("1998-12-06"), "toto@gmail.com", "toto-picture7.jpg"));
		etudiantRepository.save(new Etudiant("Toto", df.parse("1998-12-06"), "toto@gmail.com", "toto-picture8.jpg"));
		etudiantRepository.save(new Etudiant("Toto", df.parse("1998-12-06"), "toto@gmail.com", "toto-picture9.jpg"));
		etudiantRepository.save(new Etudiant("Toto", df.parse("1998-12-06"), "toto@gmail.com", "toto-picture10.jpg"));
		etudiantRepository.save(new Etudiant("Toto", df.parse("1998-12-06"), "toto@gmail.com", "toto-picture11.jpg"));

		
		//On utilise l'interface Page de spring
		Page<Etudiant> etds = etudiantRepository.findAll(PageRequest.of(0, 5));
		for(Etudiant e : etds){
			System.out.println(e.getNom());
		}
		
		Page<Etudiant> etds2 = etudiantRepository.chercherEtudiants("%A%", PageRequest.of(0,5));		
		etds2.forEach(e -> e.getNom());
		
		
	}

}
