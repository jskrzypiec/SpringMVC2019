package javae.slab10_springMVC_2.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javae.slab10_springMVC_2.domain.Glowa;
import javae.slab10_springMVC_2.domain.Pilka;
import javae.slab10_springMVC_2.domain.Trener;
import javae.slab10_springMVC_2.domain.Zawodnik;
import javae.slab10_springMVC_2.service.SportService;

@RestController
public class SportController {

	SportService ss;
	
	  @Autowired
	  public SportController(SportService ss) {
		  this.ss = ss;
	  }
	
	  //1-glowa
	  //create
	  @PostMapping("/api/glowa/new")
	  public Glowa addGlowa(@RequestBody Glowa glowa) {
		  Glowa glowaToAdd = new Glowa(glowa.getSrednica() );
		  ss.save(glowaToAdd);
		  return glowaToAdd;
		  //return ss.getAllGlowas();
	  }
	  //read all
	  @GetMapping("/api/glowa/all")
	  public List<Glowa> getGlowas() {
	    return ss.getAllGlowas();
	  }
	  //read one
	  @GetMapping("/api/glowa/{id}")
	  public Glowa getGlowaById(@PathVariable long id) {
		  return ss.getGlowaById(id);
	  }
	  //update
	  @PutMapping("api/glowa/{id}")
	  public Glowa updateGlowa(@RequestBody Glowa glowa, @PathVariable long id) {
		  Glowa glowaToUpdate = ss.getGlowaById(id);
		  glowaToUpdate.setSrednica(glowa.getSrednica() );
		  ss.save(glowaToUpdate);
		  return ss.getGlowaById(glowaToUpdate.getId() );
	  }
	  //delete
	  @DeleteMapping("/api/glowa/{id}")
	  public List<Glowa> deleteGlowa(@PathVariable long id) {
		  ss.removeGlowa(id);
		  return ss.getAllGlowas();
	  }
	  
	  
	  //2-pilka
	  //create
	  @PostMapping("/api/pilka/new")
	  public Pilka addPilka(@RequestBody Pilka pilka) {
		  Pilka pilkaToAdd = new Pilka(pilka.getMarka() );
		  ss.save(pilkaToAdd);
		  return pilkaToAdd;
		  //return ss.getAllPilkas();
	  }
	  //read all
	  @GetMapping("/api/pilka/all")
	  public List<Pilka> getPilkas() {
	    return ss.getAllPilkas();
	  }
	  //read one
	  @GetMapping("/api/pilka/{id}")
	  public Pilka getPilkaById(@PathVariable long id) {
		  return ss.getPilkaById(id);
	  }
	  //update
	  @PutMapping("api/pilka/{id}")
	  public Pilka updatePilka(@RequestBody Pilka pilka, @PathVariable long id) {
		  Pilka pilkaToUpdate = ss.getPilkaById(id);
		  pilkaToUpdate.setMarka(pilka.getMarka() );
		  ss.save(pilkaToUpdate);
		  return ss.getPilkaById(pilkaToUpdate.getId() );
	  }
	  //delete
	  @DeleteMapping("/api/pilka/{id}")
	  public List<Pilka> deletePilka(@PathVariable long id) {
		  ss.removePilka(id);
		  return ss.getAllPilkas();
	  }
	  
	  //3-trener
	  //create
	  @PostMapping("/api/trener/new")
	  public Trener addTrener(@RequestBody Trener trener) {
		  Trener trenerToAdd = new Trener(trener.getImie(), trener.getNazw() );
		  if(trener.getGlowa().getId()!=0) {
			  Glowa glowa = ss.getGlowaById(trener.getGlowa().getId());
			  if(glowa.getOwner()==null) {
				  trenerToAdd.setGlowa( glowa ); //jeżeli dodajemy tylko po id
				  ss.save(trenerToAdd.getGlowa() );
			  }
		  }
		  if(trener.getGlowa()!=null && trener.getGlowa().getId()==0) {
			  trenerToAdd.setGlowa(trener.getGlowa()); //jeżeli dodajemy tylko po średnicy
			  ss.save(trenerToAdd.getGlowa() );
		  }
		  if(trener.getPilki()!=null) {
			  for(Pilka pil:trener.getPilki()) {
				  if(pil.getMarka()==null) {
					  pil.setMarka(ss.getPilkaById(pil.getId()).getMarka());
				  }
			  }
			  trenerToAdd.setPilki(trener.getPilki());
			  ss.save(trenerToAdd);
			  for(Pilka pil:trenerToAdd.getPilki()) {
				  pil.setTrener(trenerToAdd); //dodanie relacji
				  ss.save(pil);
			  }
		  }
		  ss.save(trenerToAdd);
		  return trenerToAdd;
		  //return ss.getAllTreners();
	  }
	  //read all
	  @GetMapping("/api/trener/all")
	  public List<Trener> getTreners() {
	    return ss.getAllTreners();
	  }
	  //read one
	  @GetMapping("/api/trener/{id}")
	  public Trener getTrenerById(@PathVariable long id) {
		  return ss.getTrenerById(id);
	  }
	  //update
	  @PutMapping("api/trener/{id}")
	  public Trener updateTrener(@RequestBody Trener trener, @PathVariable long id) {
		  Trener trenerToUpdate = ss.getTrenerById(id);
		  trenerToUpdate.setImie(trener.getImie() );
		  trenerToUpdate.setNazw(trener.getNazw() );
		  if(trener.getGlowa()!=null) {
			  trener.getGlowa().setId( trenerToUpdate.getGlowa().getId() ); //ustawienie id głowy,żeby zmieniało obecną zamiast dodawać nową
			  trenerToUpdate.setGlowa(trener.getGlowa() );
			  ss.save(trenerToUpdate.getGlowa() );
		  }
		  if(trener.getPilki()!=null) {
			  //wersja z kasowaniem piłek z bazy danych
			  /*for( Pilka pil:trenerToUpdate.getPilki() ) { //kasowanie piłek z listy
				  ss.removePilka( pil.getId() );
			  }*/
			  for( Pilka pil:trenerToUpdate.getPilki() ) {
				  pil.setTrener(null); //zmienienie referencji w starych piłkach
			  }
			  trenerToUpdate.setPilki(trener.getPilki());
			  ss.save(trenerToUpdate);
			  for(Pilka pil:trenerToUpdate.getPilki()) {
				  pil.setTrener(trenerToUpdate); //dodanie relacji
				  ss.save(pil);
			  }
		  }
		  ss.save(trenerToUpdate);
		  return ss.getTrenerById(trenerToUpdate.getId() );
	  }
	  //delete
	  @DeleteMapping("/api/trener/{id}")
	  public List<Trener> deleteTrener(@PathVariable long id) {
		  ss.removeTrener(id);
		  return ss.getAllTreners();
	  }
	  
	  
	  //4-zawodnik
	  //create
	  @PostMapping("/api/zawodnik/new")
	  public Zawodnik addZawodnik(@RequestBody Zawodnik zawodnik) {
		  Zawodnik zawodnikToAdd = new Zawodnik(zawodnik.getImie(), zawodnik.getNazw(), zawodnik.getYob() );
		  if(zawodnik.getPilki()!=null) {
			  for(Pilka pil:zawodnik.getPilki()) {
				  if(pil.getMarka()==null) {
					  pil.setMarka(ss.getPilkaById(pil.getId()).getMarka());
				  }
			  }
			  zawodnikToAdd.setPilki(zawodnik.getPilki() );
			  for(Pilka pil:zawodnik.getPilki()) {
				  ss.save(pil);
			  }
		  }
		  ss.save(zawodnikToAdd);
		  return zawodnikToAdd;
		  //return ss.getAllZawodniks();
	  }
	  //read all
	  @GetMapping("/api/zawodnik/all")
	  public List<Zawodnik> getZawodniks() {
	    return ss.getAllZawodniks();
	  }
	  //read one
	  @GetMapping("/api/zawodnik/{id}")
	  public Zawodnik getZawodnikById(@PathVariable long id) {
		  return ss.getZawodnikById(id);
	  }
	  //update
	  @PutMapping("api/zawodnik/{id}")
	  public Zawodnik updateZawodnik(@RequestBody Zawodnik zawodnik, @PathVariable long id) {
		  Zawodnik zawodnikToUpdate = ss.getZawodnikById(id);
		  zawodnikToUpdate.setImie(zawodnik.getImie() );
		  zawodnikToUpdate.setNazw(zawodnik.getNazw() );
		  zawodnikToUpdate.setYob(zawodnik.getYob() );
		  if(zawodnik.getPilki()!=null) {
			  //wersja z kasowaniem pilek z bazy danych
			  /*for(Pilka pil:zawodnikToUpdate.getPilki()) { //skasowanie starych piłek
				 ss.removePilka(pil.getId() );  
			  }*/
			  zawodnikToUpdate.setPilki(zawodnik.getPilki() );
			  ss.save(zawodnikToUpdate);
			  for(Pilka pil:zawodnik.getPilki()) {
				  ss.save(pil);
			  }
		  }
		  ss.save(zawodnikToUpdate);
		  return ss.getZawodnikById(zawodnikToUpdate.getId() );
	  }
	  //delete
	  @DeleteMapping("/api/zawodnik/{id}")
	  public List<Zawodnik> deleteZawodnik(@PathVariable long id) {
		  ss.removeZawodnik(id);
		  return ss.getAllZawodniks();
	  }
}
