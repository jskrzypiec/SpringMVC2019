package javae.slab10_springMVC_2.controller.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javae.slab10_springMVC_2.domain.Glowa;
import javae.slab10_springMVC_2.domain.Pilka;
import javae.slab10_springMVC_2.domain.Trener;
import javae.slab10_springMVC_2.domain.Zawodnik;
import javae.slab10_springMVC_2.service.SportService;

@Controller("sportWebController")
public class SportController {
	
	SportService ss;
	
	@Autowired
	public SportController(SportService ss) {
		this.ss = ss;
	}
	
	//wypisanie wszystkiego
	@GetMapping("/sport")
	public String home(Model model) {
		model.addAttribute("glowa_lista", ss.getAllGlowas() );
		model.addAttribute("pilka_lista", ss.getAllPilkas() );
		model.addAttribute("trener_lista", ss.getAllTreners() );
		model.addAttribute("zawodnik_lista", ss.getAllZawodniks() );
		model.addAttribute("pilki_trenerzy_lista", ss.getPilki_Trenerzy() );
		model.addAttribute("pilki_zawodnicy_lista", ss.getPilki_Zawodnicy() );
		return "sport-wypisanie";
	}
	
	//1 - głowa
	@GetMapping("/glowa/new")
	public String newGlowa(Model model){
		model.addAttribute("glowa", new Glowa());
		return "glowa-add";
	}
	@PostMapping("/glowa/add")
	public String addGlowa(@Valid Glowa glowa, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			//model.addAttribute("glowa", glowa);
			return "glowa-add";
		}
		ss.save(glowa);
		model.addAttribute("glowa_lista", ss.getAllGlowas() );
		model.addAttribute("pilka_lista", ss.getAllPilkas() );
		model.addAttribute("trener_lista", ss.getAllTreners() );
		model.addAttribute("zawodnik_lista", ss.getAllZawodniks() );
		model.addAttribute("pilki_trenerzy_lista", ss.getPilki_Trenerzy() );
		model.addAttribute("pilki_zawodnicy_lista", ss.getPilki_Zawodnicy() );
		return "sport-wypisanie";
	}
	@GetMapping("/glowa/delete/{id}")
	public String deleteGlowa(@PathVariable("id") long id, Model model) {
		ss.removeGlowa(id);
		model.addAttribute("glowa_lista", ss.getAllGlowas() );
		model.addAttribute("pilka_lista", ss.getAllPilkas() );
		model.addAttribute("trener_lista", ss.getAllTreners() );
		model.addAttribute("zawodnik_lista", ss.getAllZawodniks() );
		model.addAttribute("pilki_trenerzy_lista", ss.getPilki_Trenerzy() );
		model.addAttribute("pilki_zawodnicy_lista", ss.getPilki_Zawodnicy() );
		return "sport-wypisanie";
	}
	@GetMapping("/glowa/edit/{id}")
	public String editGlowa(@PathVariable("id") long id, Model model) {
		model.addAttribute("glowa", ss.getGlowaById(id));
		return "glowa-edit";
	}
	@PostMapping("/glowa/edit")
	public String editGlowa2(@Valid Glowa glowa, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			//model.addAttribute("glowaToEdit", glowa);
			return "glowa-edit";
		}
		ss.save(glowa);
		model.addAttribute("glowa_lista", ss.getAllGlowas() );
		model.addAttribute("pilka_lista", ss.getAllPilkas() );
		model.addAttribute("trener_lista", ss.getAllTreners() );
		model.addAttribute("zawodnik_lista", ss.getAllZawodniks() );
		model.addAttribute("pilki_trenerzy_lista", ss.getPilki_Trenerzy() );
		model.addAttribute("pilki_zawodnicy_lista", ss.getPilki_Zawodnicy() );
		return "sport-wypisanie";
	}
	
	//2 - pilka
	@GetMapping("/pilka/new")
	public String newPilka(Model model){
		model.addAttribute("pilka", new Pilka());
		return "pilka-add";
	}
	@PostMapping("/pilka/add")
	public String addPilka(@Valid Pilka pilka, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "pilka-add";
		}
		ss.save(pilka);
		model.addAttribute("glowa_lista", ss.getAllGlowas() );
		model.addAttribute("pilka_lista", ss.getAllPilkas() );
		model.addAttribute("trener_lista", ss.getAllTreners() );
		model.addAttribute("zawodnik_lista", ss.getAllZawodniks() );
		model.addAttribute("pilki_trenerzy_lista", ss.getPilki_Trenerzy() );
		model.addAttribute("pilki_zawodnicy_lista", ss.getPilki_Zawodnicy() );
		return "sport-wypisanie";
	}
	@GetMapping("/pilka/delete/{id}")
	public String deletePilka(@PathVariable("id") long id, Model model) {
		ss.removePilka(id);
		model.addAttribute("glowa_lista", ss.getAllGlowas() );
		model.addAttribute("pilka_lista", ss.getAllPilkas() );
		model.addAttribute("trener_lista", ss.getAllTreners() );
		model.addAttribute("zawodnik_lista", ss.getAllZawodniks() );
		model.addAttribute("pilki_trenerzy_lista", ss.getPilki_Trenerzy() );
		model.addAttribute("pilki_zawodnicy_lista", ss.getPilki_Zawodnicy() );
		return "sport-wypisanie";
	}
	@GetMapping("/pilka/edit/{id}")
	public String editPilka(@PathVariable("id") long id, Model model){
		model.addAttribute("pilka", ss.getPilkaById(id) );
		return "pilka-edit";
	}
	@PostMapping("/pilka/edit")
	public String editPilka2(@Valid Pilka pilka, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "pilka-edit";
		}
		ss.save(pilka);
		model.addAttribute("glowa_lista", ss.getAllGlowas() );
		model.addAttribute("pilka_lista", ss.getAllPilkas() );
		model.addAttribute("trener_lista", ss.getAllTreners() );
		model.addAttribute("zawodnik_lista", ss.getAllZawodniks() );
		model.addAttribute("pilki_trenerzy_lista", ss.getPilki_Trenerzy() );
		model.addAttribute("pilki_zawodnicy_lista", ss.getPilki_Zawodnicy() );
		return "sport-wypisanie";
	}
	
	//3 - trener
	@GetMapping("/trener/new")
	public String newTrener(Model model){
		model.addAttribute("trener", new Trener());
		model.addAttribute("glowa_lista", ss.getAllGlowas_withoutOwner() );
		return "trener-add";
	}
	@PostMapping("/trener/add")
	public String addTrener(@Valid Trener trener, BindingResult bindingResult, Model model, Glowa glowa) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("glowa_lista", ss.getAllGlowas() );
			return "trener-add";
		}
		//trener.setGlowa(glowa);
		//System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+trener);
		//System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+glowa);
		ss.save(trener);
		model.addAttribute("glowa_lista", ss.getAllGlowas() );
		model.addAttribute("pilka_lista", ss.getAllPilkas() );
		model.addAttribute("trener_lista", ss.getAllTreners() );
		model.addAttribute("zawodnik_lista", ss.getAllZawodniks() );
		model.addAttribute("pilki_trenerzy_lista", ss.getPilki_Trenerzy() );
		model.addAttribute("pilki_zawodnicy_lista", ss.getPilki_Zawodnicy() );
		return "sport-wypisanie";
	}
	@GetMapping("/trener/delete/{id}")
	public String deleteTrener(@PathVariable("id") long id, Model model) {
		ss.removeTrener(id);
		model.addAttribute("glowa_lista", ss.getAllGlowas() );
		model.addAttribute("pilka_lista", ss.getAllPilkas() );
		model.addAttribute("trener_lista", ss.getAllTreners() );
		model.addAttribute("zawodnik_lista", ss.getAllZawodniks() );
		model.addAttribute("pilki_trenerzy_lista", ss.getPilki_Trenerzy() );
		model.addAttribute("pilki_zawodnicy_lista", ss.getPilki_Zawodnicy() );
		return "sport-wypisanie";
	}
	@GetMapping("/trener/edit/{id}")
	public String editTrener(@PathVariable("id") long id, Model model) {
		model.addAttribute("trener", ss.getTrenerById(id) );
		model.addAttribute("glowa_lista", ss.getAllGlowas_withoutOwner() );
		return "trener-edit";
	}
	@PostMapping("/trener/edit")
	public String editTrener2(@Valid Trener trener, BindingResult bindingResult, Model model, Glowa glowa) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("glowa_lista", ss.getAllGlowas() );
			return "trener-edit";
		}
		ss.save(trener);
		model.addAttribute("glowa_lista", ss.getAllGlowas() );
		model.addAttribute("pilka_lista", ss.getAllPilkas() );
		model.addAttribute("trener_lista", ss.getAllTreners() );
		model.addAttribute("zawodnik_lista", ss.getAllZawodniks() );
		model.addAttribute("pilki_trenerzy_lista", ss.getPilki_Trenerzy() );
		model.addAttribute("pilki_zawodnicy_lista", ss.getPilki_Zawodnicy() );
		return "sport-wypisanie";
	}
	
	//4 - zawodnik
	@GetMapping("/zawodnik/new")
	public String newZawodnik(Model model){
		model.addAttribute("zawodnik", new Zawodnik());
		return "zawodnik-add";
	}
	@PostMapping("/zawodnik/add")
	public String addZawodnik(@Valid Zawodnik zawodnik, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "zawodnik-add";
		}
		ss.save(zawodnik);
		model.addAttribute("glowa_lista", ss.getAllGlowas() );
		model.addAttribute("pilka_lista", ss.getAllPilkas() );
		model.addAttribute("trener_lista", ss.getAllTreners() );
		model.addAttribute("zawodnik_lista", ss.getAllZawodniks() );
		model.addAttribute("pilki_trenerzy_lista", ss.getPilki_Trenerzy() );
		model.addAttribute("pilki_zawodnicy_lista", ss.getPilki_Zawodnicy() );
		return "sport-wypisanie";
	}
	@GetMapping("/zawodnik/delete/{id}")
	public String deleteZawodnik(@PathVariable("id") long id, Model model) {
		ss.removeZawodnik(id);
		model.addAttribute("glowa_lista", ss.getAllGlowas() );
		model.addAttribute("pilka_lista", ss.getAllPilkas() );
		model.addAttribute("trener_lista", ss.getAllTreners() );
		model.addAttribute("zawodnik_lista", ss.getAllZawodniks() );
		model.addAttribute("pilki_trenerzy_lista", ss.getPilki_Trenerzy() );
		model.addAttribute("pilki_zawodnicy_lista", ss.getPilki_Zawodnicy() );
		return "sport-wypisanie";
	}
	@GetMapping("/zawodnik/edit/{id}")
	public String editZawodnik(@PathVariable("id") long id, Model model) {
		model.addAttribute("zawodnik", ss.getZawodnikById(id) );
		return "zawodnik-edit";
	}
	@PostMapping("/zawodnik/edit")
	public String editZawodnik2(@Valid Zawodnik zawodnik, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("zawodnik", zawodnik );
			return "zawodnik-edit";
		}
		ss.save(zawodnik);
		model.addAttribute("glowa_lista", ss.getAllGlowas() );
		model.addAttribute("pilka_lista", ss.getAllPilkas() );
		model.addAttribute("trener_lista", ss.getAllTreners() );
		model.addAttribute("zawodnik_lista", ss.getAllZawodniks() );
		model.addAttribute("pilki_trenerzy_lista", ss.getPilki_Trenerzy() );
		model.addAttribute("pilki_zawodnicy_lista", ss.getPilki_Zawodnicy() );
		return "sport-wypisanie";
	}
	
	
}
