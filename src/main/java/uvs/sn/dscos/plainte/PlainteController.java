package uvs.sn.dscos.plainte;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping
@Slf4j
@RequiredArgsConstructor
public class PlainteController {

	private final PlainteService plainteService;

	@GetMapping("/ajouterPlainte")
	public String afficherFormulaire(Model model) {
		PlainteForm plainteForm = new PlainteForm();
		model.addAttribute("plainteForm", plainteForm);

		Map<String, String> provenanceMap = ConstantesPlainte.getProvenance();
		model.addAttribute("provenanceMap", provenanceMap);
		return "plaintes/ajout";
	}

	@PostMapping("/ajouterPlainte")
	public String ajouterPlainte(@Valid @ModelAttribute("plainteForm") PlainteForm plainteForm, BindingResult result, 
			Model model, final RedirectAttributes ra) {
		log.info("Plainte saisie {}", plainteForm);
		if (result.hasErrors()) {
			Map<String, String> provenanceMap = ConstantesPlainte.getProvenance();
			model.addAttribute("provenanceMap", provenanceMap);
			return "plaintes/ajout";
		}
		if (plainteService.estCeQueLeNumeroExisteDeja(plainteForm.getNumero())) {
			Map<String, String> provenanceMap = ConstantesPlainte.getProvenance();
			model.addAttribute("provenanceMap", provenanceMap);
			model.addAttribute("numeroExisteDeja", plainteForm.getNumero());
			return "plaintes/ajout";
		}
		try {
			plainteService.ajouterUnePlainte(plainteForm);
		} catch (ParseException e) {
			log.info("Impossible d'enregistrer la plainte");
		}
		ra.addFlashAttribute("successFlash", "Plainte " +plainteForm.getNumero()+ " enregistrée");
		return "redirect:/listePlainte";
	}
	
	@GetMapping("/listePlainte")
	public String liste(Model model) {
		log.info("Liste des plaintes");
		List<Plainte> listePlainte = plainteService.afficherListePlainte();
		model.addAttribute("listePlainte", listePlainte);
		return "plaintes/liste";
	}
}
