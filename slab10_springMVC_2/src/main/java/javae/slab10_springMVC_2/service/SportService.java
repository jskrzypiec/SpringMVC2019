package javae.slab10_springMVC_2.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javae.slab10_springMVC_2.dao.GlowaRepository;
import javae.slab10_springMVC_2.dao.PilkaRepository;
import javae.slab10_springMVC_2.dao.TrenerRepository;
import javae.slab10_springMVC_2.dao.ZawodnikRepository;
import javae.slab10_springMVC_2.domain.Glowa;
import javae.slab10_springMVC_2.domain.Pilka;
import javae.slab10_springMVC_2.domain.Trener;
import javae.slab10_springMVC_2.domain.Zawodnik;

@Service
@Transactional
public class SportService {

	private GlowaRepository glowaRepository;
	private PilkaRepository pilkaRepository;
	private TrenerRepository trenerRepository;
	private ZawodnikRepository zawodnikRepository;
	
	@Autowired
	public SportService(GlowaRepository glowaRepository, PilkaRepository pilkaRepository,
			TrenerRepository trenerRepository, ZawodnikRepository zawodnikRepository) {
		this.glowaRepository = glowaRepository;
		this.pilkaRepository = pilkaRepository;
		this.trenerRepository = trenerRepository;
		this.zawodnikRepository = zawodnikRepository;
	}
	
	
	// glowa
	public Glowa save(Glowa glowa) {
		return glowaRepository.save(glowa);
	}
	public List<Glowa> getAllGlowas(){
		return (List<Glowa>) glowaRepository.findAll();
	}
	public List<Glowa> getAllGlowas_withoutOwner(){
		return glowaRepository.findGlowaWithoutOwner();
	}
	public Glowa getGlowaById(long id) {
		return glowaRepository.findById(id).get(); //optional,zły kod
	}
	public void removeGlowa(long id) {
		glowaRepository.deleteById(id);
	}
	
	// pilka
	public Pilka save(Pilka pilka) {
		return pilkaRepository.save(pilka);
	}
	public List<Pilka> getAllPilkas(){
		return (List<Pilka>) pilkaRepository.findAll();
	}
	public Pilka getPilkaById(long id) {
		return pilkaRepository.findById(id).get();
	}
	public void removePilka(long id) {
		pilkaRepository.deleteById(id);
	}
	
	// trener
	public Trener save(Trener trener) {
		return trenerRepository.save(trener);
	}
	public List<Trener> getAllTreners(){
		List<Trener> li = (List<Trener>) trenerRepository.myOwnQuery();
		for(Trener l : li) {
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@"+ l);
		}
		//return (List<Trener>) trenerRepository.findAll();
		return (List<Trener>) trenerRepository.myOwnQuery();
	}
	public Trener getTrenerById(long id) {
		return trenerRepository.findById(id).get();
	}
	public void removeTrener(long id) {
		trenerRepository.deleteById(id);
	}
	
	// zawodnik
	public Zawodnik save(Zawodnik zawodnik) {
		return zawodnikRepository.save(zawodnik);
	}
	public List<Zawodnik> getAllZawodniks(){
		return (List<Zawodnik>) zawodnikRepository.findAll();
	}
	public Zawodnik getZawodnikById(long id) {
		return zawodnikRepository.findById(id).get();
	}
	public void removeZawodnik(long id) {
		zawodnikRepository.deleteById(id);
	}

	// pilki - trenerzy
	public List<Pilka> getPilki_Trenerzy(){
		return pilkaRepository.findPilki_Trenerzy();
	}
	// pilki - zawodnicy
	public List<Pilka> getPilki_Zawodnicy(){
		return pilkaRepository.findPilki_Zawodnicy();
	}
	
}
