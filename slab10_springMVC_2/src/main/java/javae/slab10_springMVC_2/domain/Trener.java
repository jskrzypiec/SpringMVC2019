package javae.slab10_springMVC_2.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;


@Entity
@Table(name = "trener")
public class Trener {
	
	private long id;
	@Pattern(regexp="[A-Za-z]+")
	private String imie;
	@Pattern(regexp="[A-Za-z]+")
	private String nazw;
	private Glowa glowa;
	private Set<Pilka> pilki;
	
	
	
	public Trener(String imie, String nazw) {
		this.imie = imie;
		this.nazw = nazw;
	}
	public Trener() {}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") 
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
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="glowa_id", referencedColumnName = "id", nullable = true)
	public Glowa getGlowa() {
		return glowa;
	}
	public void setGlowa(Glowa glowa) {
		this.glowa = glowa;
	}
	@OneToMany(mappedBy = "trener", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	/*@JoinTable(
			name = "trener_pilki", 
			joinColumns = @JoinColumn(name = "trener_id", referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "pilka_id", referencedColumnName = "id"))*/
	public Set<Pilka> getPilki() {
		return pilki;
	}
	public void setPilki(Set<Pilka> pilki) {
		this.pilki = pilki;
	}
	
	
	
	@Override
	public String toString() {
		return "Trener [id=" + id + ", imie=" + imie + ", nazw=" + nazw + "]";
	}
	
	
	@PreRemove
	private void preRemove() {
		if(this.getPilki()!=null) {
			for(Pilka pil : this.getPilki()) {
				pil.setTrener(null);
			}
		}
	}
	
}
