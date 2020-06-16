package javae.slab10_springMVC_2.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javae.slab10_springMVC_2.domain.Pilka;

public interface PilkaRepository extends CrudRepository<Pilka, Long>{
	//piłki trenerzy
	@Query("SELECT p From Pilka p INNER JOIN p.trener t")
	List<Pilka> findPilki_Trenerzy();
	
	//piłki zawodnicy
	@Query("SELECT DISTINCT p From Pilka p INNER JOIN p.zawodnicy z") //distinct,bo i tak zwraca piłkę z całą listą - to przeiteruje po liście
	List<Pilka> findPilki_Zawodnicy();
	
	////////////////
	//do kasowania//
	////////////////
	@Modifying
	@Query("DELETE From Pilka p where p.id = :id")
	void deleteById(@Param("id") long id);
	//
	@Modifying
	@Query("DELETE From Pilka p where p = :pilka")
	void delete(@Param("pilka") Pilka pilka);
	//
	@Modifying
	@Query("DELETE From Pilka p where p IN :pilkiToDelete")
	void deleteAll(@Param("pilkiToDelete") List<Pilka> pilkiToDelete);
}