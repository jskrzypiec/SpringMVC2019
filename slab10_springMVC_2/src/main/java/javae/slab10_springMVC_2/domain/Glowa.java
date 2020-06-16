package javae.slab10_springMVC_2.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "glowa")
public class Glowa {
	private long id;
	@NotNull
	@DecimalMin(value="5", message="srednica musi być w przedziale [5,50]")
	@DecimalMax(value="50", message="srednica musi być w przedziale [5,50]")
	private float srednica;
	@JsonIgnore
	private Trener owner;
	
	
	public Glowa(float srednica) {
		this.srednica = srednica;
	}
	public Glowa() {}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public float getSrednica() {
		return srednica;
	}
	public void setSrednica(float srednica) {
		this.srednica = srednica;
	}
	@OneToOne(mappedBy="glowa", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	public Trener getOwner() {
		return owner;
	}
	public void setOwner(Trener owner) {
		this.owner = owner;
	}

	
	@Override
	public String toString() {
		//return "Glowa [id=" + id + ", srednica=" + srednica + ", owner=" + owner + "]";
		return "Glowa [id=" + id + ", srednica=" + srednica + "]";
	}
	
	
	@PreRemove
	private void preRemove() {
		if(this.getOwner()!=null) {
			this.getOwner().setGlowa(null);
		}
	}
}
