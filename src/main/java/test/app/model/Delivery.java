package test.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Delivery {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(unique = true, nullable = false)
    private String jmbg;
	
	@Column(unique = true, nullable = false)
    private String licna;
	
	@Column(nullable = false, name = "ime")
    private String imeIPrezime;
	
	@OneToMany(mappedBy = "delivery", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Narudzbina> orders = new ArrayList<>();

	public Delivery() {
		super();
	}

	public Delivery(String jmbg, String brojLK, String imeIPrezime) {
		super();
		this.jmbg = jmbg;
		this.licna = brojLK;
		this.imeIPrezime = imeIPrezime;
	}

	public Delivery(Long id, String jmbg, String brojLK, String imeIPrezime) {
		super();
		this.id = id;
		this.jmbg = jmbg;
		this.licna = brojLK;
		this.imeIPrezime = imeIPrezime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Delivery other = (Delivery) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

	public List<Narudzbina> getOrders() {
		return orders;
	}

	public void setOrders(List<Narudzbina> orders) {
		this.orders = orders;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getBrojLK() {
		return licna;
	}

	public void setBrojLK(String brojLK) {
		this.licna = brojLK;
	}

	public String getImeIPrezime() {
		return imeIPrezime;
	}

	public void setImeIPrezime(String imeIPrezime) {
		this.imeIPrezime = imeIPrezime;
	}

	@Override
	public String toString() {
		return "Dostavljac [id=" + id + ", jmbg=" + jmbg + ", brojLK=" + licna + ", imeIPrezime=" + imeIPrezime + "]";
	}
	
	
	
	

}
