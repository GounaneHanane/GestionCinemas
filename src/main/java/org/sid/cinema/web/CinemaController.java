package org.sid.cinema.web;

import java.io.File;
import java.io.FileInputStream;

import org.sid.cinema.dao.CategorieRepository;
import org.sid.cinema.dao.CinemaRepository;
import org.sid.cinema.dao.FilmRepository;
import org.sid.cinema.dao.SalleRepository;
import org.sid.cinema.entities.*;
import org.sid.cinema.dao.ProjectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CinemaController {

	@Autowired
	private CinemaRepository cinemaRepository;
	@Autowired
	private SalleRepository salleRepository;
	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private ProjectionRepository projectionRepository;
	@Autowired
	private CategorieRepository categoryRepository;

	

	@GetMapping(path = "/index")
	public String index() {
		return "test";
	}

	/// film
	@GetMapping(path = "/listFilm")
	public String afficher(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "motCle", defaultValue = "") String motCle) {
		Page<Film> pageDeFilm = filmRepository.findBytitreContains(motCle, PageRequest.of(page, size));
		model.addAttribute("pageFilm", pageDeFilm);
		model.addAttribute("CurrentFilm", page);
		model.addAttribute("size", size);
		model.addAttribute("motCle", motCle);
		model.addAttribute("page", new int[pageDeFilm.getTotalPages()]);
		return "listFilm";

	}

	@GetMapping(path = "/deleteFilm")
	public String delete(Long id, String motCle, String page, String size) {
		filmRepository.deleteById(id);
		return "redirect:/listFilm?page=" + page + "&motCle=" + motCle + "&size=" + size;
	}

	@GetMapping(path = "/formSaisie")
	public String fromFilm(Model model) {
		model.addAttribute("film", new Film());
		model.addAttribute("mode", "new");
		return "formSaisie";
	}

	
	@PostMapping(path = "/saveFilm2")
	public String saveFilm(Film film, @RequestParam(name = "picture") MultipartFile file) throws Exception {
		if (!(file.isEmpty())) {
			System.out.println("--------------");
			film.setPhoto(file.getOriginalFilename());
			file.transferTo(new File(System.getProperty("user.home") + "/cinema/images/" + file.getOriginalFilename()));
		}
		filmRepository.save(film);
		return "redirect:/listFilm";
	}

	@RequestMapping(value = "/getPhoto", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto(String photos) throws Exception {
		File file = new File(System.getProperty("user.home") + "/cinema/images/" + photos);
		return org.apache.commons.io.IOUtils.toByteArray(new FileInputStream(file));
	}

	///// Cinema
	@GetMapping(path = "/listCinema")
	public String afficherCinema(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "motCle", defaultValue = "") String motCle) {
		Page<Cinema> pageDeCinema = cinemaRepository.findBynameContains(motCle, PageRequest.of(page, size));
		model.addAttribute("pageCinema", pageDeCinema);
		model.addAttribute("CurrentCinema", page);
		model.addAttribute("size", size);
		model.addAttribute("motCle", motCle);
		model.addAttribute("page", new int[pageDeCinema.getTotalPages()]);
		return "listCinema";

	}

	@GetMapping(path = "/deleteCinema")
	public String deleteCinema(Long id) {
		cinemaRepository.deleteById(id);
		return "redirect:/listCinema";

	}

	@GetMapping(path = "/AjouterCinema")
	public String fromCinema(Model model) {
		model.addAttribute("Cinema", new Cinema());
		return "AjouterCinema";
	}

	@PostMapping(path = "/saveCinema")
	public String saveCinema(Cinema cinema) {
		cinemaRepository.save(cinema);
		return "redirect:/listCinema";
	}

	// *****************************************

	@GetMapping(path = "/listCategorie")
	public String afficherCategorie(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "motCle", defaultValue = "") String motCle) {
		Page<Categorie> pageDeCategorie = categoryRepository.findBynameContains(motCle, PageRequest.of(page, size));
		model.addAttribute("pageCinema", pageDeCategorie);
		model.addAttribute("CurrentCinema", page);
		model.addAttribute("size", size);
		model.addAttribute("motCle", motCle);
		model.addAttribute("page", new int[pageDeCategorie.getTotalPages()]);
		return "listCategorie";

	}

	@GetMapping(path = "/deleteCategorie")
	public String deletegorie(Long id) {
		categoryRepository.deleteById(id);
		return "redirect:/listCategorie";
	}

	///// ************************* \\\\\\\
	@GetMapping(path =  "/AjouterCategorie")
	public String fromCategorie(Model model) {
		model.addAttribute("categorie", new Categorie());
		return "AjouterCategorie";
	}

	@PostMapping(path = "/EditCategorie")
	public String EditCategorie(Categorie categorie) {
		categoryRepository.save(categorie);
		return "redirect:/listCategorie";
	}
	@GetMapping(path = "/ModifierCategorie")
	public String ModifierCategorie(Long id,Model model) {
		Categorie categorie=categoryRepository.getOne(id);
		model.addAttribute("categorie", categorie);
		return "ModifierCategorie";
	}

	@PostMapping(path = "/MCategorie")
	public String saveCategorie(Categorie categorie) {
		categoryRepository.save(categorie);
		return "redirect:/listCategorie";
	}
	// Salle
	@GetMapping(path = "/listSalle")
	public String afficherSalle(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "motCle", defaultValue = "") String motCle) {
		Page<Salle> pageDeSalle=salleRepository.findBynameContains(motCle, PageRequest.of(page, size));
		model.addAttribute("pageSalle", pageDeSalle);
		model.addAttribute("CurrentCinema", page);
		model.addAttribute("size", size);
		model.addAttribute("motCle", motCle);
		model.addAttribute("page", new int[pageDeSalle.getTotalPages()]);
		return "listSalle";

	}

	@GetMapping(path = "/deleteSalle")
	public String deleteSalle(Long id) {
		salleRepository.deleteById(id);
		return "redirect:/listSalle";
	}

	@GetMapping(path =  "/AjouterSalle")
	public String fromSalle(Model model) {
		model.addAttribute("salle", new Salle());
		return "AjouterSalle";
	}

	@PostMapping(path = "/SaveSalle")
	public String EditSalle(Salle salle) {
		salleRepository.save(salle);
		return "redirect:/listSalle";
	}
	@GetMapping(path = "/ModifierSalle")
	public String ModifierSalle(Long id,Model model) {
		Salle salle=salleRepository.getOne(id);
		model.addAttribute("salle", salle);
		return "ModifierSalle";
	}

	@PostMapping(path = "/ModifierSalle")
	public String saveSalle(Salle salle) {
		salleRepository.save(salle);
		return "redirect:/listSalle";
	}
//// projection
	@GetMapping(path = "/listProjection")
	public String afficherProjection(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) 
	{
		Page<Projection> pageProjection=projectionRepository.findAll( PageRequest.of(page, size));
		model.addAttribute("pageProjection", pageProjection);
		model.addAttribute("CurrentCinema", page);
		model.addAttribute("size", size);
//		model.addAttribute("motCle", id);
		model.addAttribute("page", new int[pageProjection.getTotalPages()]);
		return "listProjection";

	}

	@GetMapping(path = "/deleteProjection")
	public String deleteProjection(Long id) {
		projectionRepository.deleteById(id);
		return "redirect:/listProjection";
	}

	@GetMapping(path =  "/AjouterProjection")
	public String fromProjection(Model model) {
		model.addAttribute("projection", new org.sid.cinema.entities.Projection());
		return "AjouterProjection";
	}

	@PostMapping(path = "/SaveProjection")
	public String EditProjection(Projection projection) {
			projectionRepository.save(projection);
		return "redirect:/listProjection";
	}
	@GetMapping(path = "/ModifierProjection")
	public String modifierProjection(Long id,Model model) 
	{
		Projection projection=projectionRepository.getOne(id);
		model.addAttribute("projecton",projection);
		return "ModifierProjection";
	}

	@PostMapping(path = "/EditProjection")
	public String saveProjection(Projection projection) {
		projectionRepository.save(projection);
		return "redirect:/listProjection";
	}	
	
	
}
