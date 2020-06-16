package javae.slab10_springMVC_2.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;

@Entity
public class Zawodnik {
	private long id;
	@Pattern(regexp="[A-Za-z]+")
	private String imie;
	@Pattern(regexp="[A-Za-z]+")
	private String nazw;
	@DecimalMin(value="1000", message="rok urodzenia musi być w przedziale[1000,2020]")
	@DecimalMax(value="2020", message="rok urodzenia musi być w przedziale[1000,2020]")
	private int yob;
	private Set<Pilka> pilki;
	
	
	public Zawodnik(String imie, String nazw, int yob) {
		this.imie = imie;
		this.nazw = nazw;
		this.yob = yob;
	}
	public Zawodnik() {}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}
	public String getNazw() {
		return nazw;
	}
	public void setNazw(String nazw) {
		this.nazw = nazw;
	}
	public int getYob() {
		return yob;
	}
	public void setYob(int yob) {
		this.yob = yob;
	}
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(
			name = "zawodnik_pilki", 
			joinColumns = @JoinColumn(name = "zawodnik_id", referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "pilka_id", referencedColumnName = "id"))
	public Set<Pilka> getPilki() {
		return pilki;
	}
	public void setPilki(Set<Pilka> pilki) {
		this.pilki = pilki;
	}
	
	
	@Override
	public String toString() {
		return "Zawodnik [id=" + id + ", imie=" + imie + ", nazw=" + nazw + ", yob=" + yob + "]";
	}
	
	
	
	
	
}
