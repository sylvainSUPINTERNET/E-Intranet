package net.opendevup.scolarite.controllers;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.opendevup.scolarite.dao.EtudiantRepository;
import net.opendevup.scolarite.entities.Etudiant;


// Le dispatcher servet est utile que dans un project web dynamic (à indiquer dans web.xml + root-servet.xml (qui contiendras le routing des jsp avec WEB-INF etc)
// si on a utiliser spring boot, il est auto configurer a l'execution de l'application (ConfigAuto) d'ailleurs visible dans les logo dispatcher servlet [/]


// Pour gerer les try catch et pas les mixer, on peut creer un controller dédier qu'à ca
@Controller
@RequestMapping(value="/etudiants") // equivalent of prefix in symfony router annotation
// dans la convention etudiants et le controller index action 
// url localhost:8080/etudiants/index
public class EtudiantController {
	
	@Autowired // si on oublie ca => NulPointerException 
	EtudiantRepository etudiantRepository;
	
	@Value(value = "${dirUploadPhoto}")
	private String uploadPhotoDir;
	
	@RequestMapping(value="/index") // @RequestParams => dit au servlet de regarder dans l'object Request (equivalent de req.params dans nodejs)
	public String index(
			Model model, // model c'est la view
			@RequestParam(name="page", defaultValue="0") int page, 
			@RequestParam(name="keyword", defaultValue="") String keyword
			) { 
		Page<Etudiant> etds = etudiantRepository.chercherEtudiants("%"+keyword+"%",PageRequest.of(page, 5));
// interface Page va rajouter plein de property pour l'affichage dans le DOM (thymeleaf)
		
		int pagesCount = etds.getTotalPages(); 
		int[] pages = new int[pagesCount];
		
		for(int i =0; i < pagesCount; i ++) pages[i] = i; 
		
		model.addAttribute("pageEtudiants", etds);
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", page); // page en param
		model.addAttribute("keyword", keyword);
		
		return "etudiants"; // par default le dispatcher servlet de Spring Boot
	}
	
	
	@RequestMapping(value="/form", method=RequestMethod.GET)
	public String formEtudiant(Model model) {
		model.addAttribute("etudiant", new Etudiant()); // ici on va bind dans le formulaire l'object etudiant pour definir les field grâce a thymeleaf (On peut aussi utiliser un constructeur et passer des valeur pour les placeholder par exemple pour l'update)
		// par exemple dans le cas d'un update on aurait recuperer les data de l'utilisateur cliquer et passer en param dans le form de modification
		// model.addAttribute("etudiant", new Etudiant("salut", new Date(), "xx@Gmail.fr", "xx"));
		return "formEtudiant";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	// on peut recuperer les data avec String nom, Date dateNaissance .... mais c'est aps une bonne pratique
	// BindingResult correspond au erreur envoyer par le dispatcherServlet si il n'a pas validé la donéne recu (@Valid et entity validator)
	public String saveEtudiant(@Valid Etudiant etudiant, BindingResult bindingResult,@RequestParam("pictureFile") MultipartFile photo) throws Exception, IOException { // ici on demande a dispatcherServlet de stocker les données de la requête dans etudiant
		// POUR UPLOAD pas oublier de changer le type par defaut et le mettre en multipartfile
		// RequestParam va mapper automatiquement le champ photo à l'object Multipartfile photo
		if(bindingResult.hasErrors()) {
			// display error message
			
			// les messages de validations => internasionalisation automatique en fonction de la langue du browser
			return "formEtudiant";
		}
		
		if(!photo.isEmpty()) {
			etudiant.setPhoto(photo.getOriginalFilename()); // en principe c'est déjà fait de base mais on peut mettre le nom original de la photo
			photo.transferTo(new File(uploadPhotoDir+photo.getOriginalFilename())); // bonne pratique, avoir une DB contenant que les picture (surtout pas mettre dans le static)
		} 
		
		etudiantRepository.save(etudiant);
		//return "redirect:/etudiants/index"; // si on met / on doit mettre le full path // si il y a pas / on met juste le RequestMapping de l'action et automatiquement il va lire le RequestMapping du controller (prefix comme symfony)
		return "redirect:index"; 
		
		// on peut aussi faire un return "Index"; ca marchera MAIS il faudra redefinir tout genre copier coller (mauvaise pratique)
	}
	
}
