package javae.slab10_springMVC_2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javae.slab10_springMVC_2.dao.GlowaRepository;
import javae.slab10_springMVC_2.dao.PilkaRepository;
import javae.slab10_springMVC_2.dao.TrenerRepository;
import javae.slab10_springMVC_2.dao.ZawodnikRepository;
import javae.slab10_springMVC_2.domain.Glowa;
import javae.slab10_springMVC_2.domain.Pilka;
import javae.slab10_springMVC_2.domain.Trener;
import javae.slab10_springMVC_2.domain.Zawodnik;


@SpringBootApplication
public class Slab10SpringMvc2Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Slab10SpringMvc2Application.class, args);
		
		TrenerRepository tr= ctx.getBean(TrenerRepository.class);
		ZawodnikRepository zr= ctx.getBean(ZawodnikRepository.class);
		GlowaRepository gr= ctx.getBean(GlowaRepository.class);
		PilkaRepository pr= ctx.getBean(PilkaRepository.class);
		
		//listy
		List<Pilka> pList = new ArrayList<>();
		List<Trener> tList = new ArrayList<>();
		List<Glowa> gList = new ArrayList<>();
		List<Zawodnik> zList = new ArrayList<>();

		
		////////////////////////
		//relacja trener-głowa//
		////////one-one/////////
		////////////////////////
		Glowa g1 = new Glowa(40.2f);
		gr.save(g1);
		//
		Trener t1 = new Trener("Adam", "Malysz");
		t1.setGlowa(g1);
		tr.save(t1);
		///////////////
		//-kasowanie-//
		//tr.delete(t1);
		//gr.deleteById(g1.getId());
		
		
		////////////////////////
		//relacja trener-piłki//
		///////many-one/////////
		////////////////////////
		Pilka p1 = new Pilka("Mikasa");
		p1.setTrener(t1);
		pr.save(p1);
		Pilka p2 = new Pilka("Molten");
		p2.setTrener(t1);
		pr.save(p2);
		///////////////
		//-kasowanie-//
		//pr.delete(p1);
		//tr.deleteById(t1.getId());
		
		
		//////////////////////////
		//relacja zawodnik-piłki//
		/////////many-many////////
		Set<Pilka> ppList = new HashSet<>();
		pList.clear();
		Pilka p3 = new Pilka("Spokey");
		pr.save(p3);
		Pilka p4 = new Pilka("VIVO");
		pr.save(p4);
		//
		Zawodnik z1 = new Zawodnik("Jakub", "Skrzypiec", 1997);
		Zawodnik z2 = new Zawodnik("Jakub", "Koc", 1999);
		Zawodnik z3 = new Zawodnik("Krzysztof", "Doker", 1995);
		Zawodnik z4 = new Zawodnik("Andrzej", "Piec", 1998);
		ppList.clear();
		ppList.add(p3);
		ppList.add(p4);
		z1.setPilki(ppList);
		z2.setPilki(ppList);
		zr.save(z1);
		zr.save(z2);
		zr.save(z3);
		zr.save(z4);
		///////////////
		//-kasowanie-//
		//zr.delete(z1);
		//pr.delete(p3);
		
		
		//////////////////////
		//wypisanie
		//1 głowa
		gList.clear();
		gList = (List<Glowa>) gr.findAll();
		for(Glowa g:gList) {
			System.out.println(g);
		}
		//2 trener
		tList.clear();
		tList = (List<Trener>) tr.findAll();
		for(Trener t:tList) {
			System.out.println(t + "[glowa]:" + t.getGlowa() + "[pilki]"+ t.getPilki());
		}
		//3 pilki
		pList.clear();
		pList = (List<Pilka>) pr.findAll();
		for(Pilka p:pList) {
			System.out.println(p + "[trener]" + p.getTrener() + "[zawodnicy]" + p.getZawodnicy());
		}
		//4 zawodnicy
		zList.clear();
		zList = (List<Zawodnik>) zr.findAll();
		for(Zawodnik z:zList) {
			System.out.println(z + "[pilki]" + z.getPilki());
		}
		
		//(5) trenerzy-piłki
		pList.clear();
		pList = pr.findPilki_Trenerzy();
		for(Pilka p:pList) {
			System.out.println("trener_id:" + p.getTrener().getId() + " ,pilka_id:" + p.getId() );
		}
		
		//(6) zawodnicy-piłki
		pList.clear();
		pList = pr.findPilki_Zawodnicy();
		for(Pilka p:pList) {
			for(Zawodnik z:p.getZawodnicy()) {
				System.out.println("zawodnik_id:" + z.getId() + " ,pilka_id:" + p.getId() );
			}
		}
	}

}
