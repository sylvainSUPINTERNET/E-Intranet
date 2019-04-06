package net.opendevup.scolarite.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.opendevup.scolarite.dao.EtudiantRepository;
import net.opendevup.scolarite.entities.Etudiant;


// Le dispatcher servet est utile que dans un project web dynamic (à indiquer dans web.xml + root-servet.xml (qui contiendras le routing des jsp avec WEB-INF etc)
// si on a utiliser spring boot, il est auto configurer a l'execution de l'application (ConfigAuto) d'ailleurs visible dans les logo dispatcher servlet [/]

@Controller
@RequestMapping(value="/etudiants") // equivalent of prefix in symfony router annotation
// dans la convention etudiants et le controller index action 
// url localhost:8080/etudiants/index
public class EtudiantController {
	
	@Autowired // si on oublie ca => NulPointerException 
	EtudiantRepository etudiantRepository;
	
	@RequestMapping(value="/index")
	public String index(Model model) { // model c'est la view
		List<Etudiant> etds = etudiantRepository.findAll();
		model.addAttribute("etudiants", etds);
		return "etudiants"; // par default le dispatcher servlet de Spring Boot
	}
}
