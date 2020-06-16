package javae.slab10_springMVC_2.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javae.slab10_springMVC_2.domain.Glowa;
import javae.slab10_springMVC_2.domain.Pilka;

public interface GlowaRepository extends CrudRepository<Glowa, Long>{
	@Query("SELECT g From Glowa g LEFT JOIN g.owner t WHERE t=null") //bo 'owner' to 'Trener'
	List<Glowa> findGlowaWithoutOwner();
}


