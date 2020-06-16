package javae.slab10_springMVC_2.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javae.slab10_springMVC_2.domain.Trener;

public interface TrenerRepository extends CrudRepository<Trener, Long>{
	
	@Query("Select t from Trener t")
    List<Trener> myOwnQuery();
}
