package javae.slab10_springMVC_2.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pilka")
public class Pilka {
	private long id;
	@Pattern(regexp="[A-Za-z]+")
	private String marka;
	@JsonIgnore
	private Trener trener;
	@JsonIgnore
	private Set<Zawodnik> zawodnicy;
	
	
	public Pilka(String marka) {
		this.marka = marka;
	}
	public Pilka() {}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMarka() {
		return marka;
	}
	public void setMarka(String marka) {
		this.marka = marka;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinTable(
		name = "trener_pilki", 
		joinColumns = @JoinColumn(name = "pilka_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "trener_id", referencedColumnName = "id"))
	public Trener getTrener() {
		return trener;
	}
	public void setTrener(Trener trener) {
		this.trener = trener;
	}
	@ManyToMany(mappedBy = "pilki", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	public Set<Zawodnik> getZawodnicy() {
		return zawodnicy;
	}
	public void setZawodnicy(Set<Zawodnik> zawodnicy) {
		this.zawodnicy = zawodnicy;
	}
	
	
	@Override
	public String toString() {
		return "Pilka [id=" + id + ", marka=" + marka + "]";
	}
	

}
