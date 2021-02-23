package test.app.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Bill {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;
	
	@Column(unique = true, nullable = false)
    private int number;
	
	@Column(nullable = false)
    private LocalDate datum = LocalDate.now();
	
	@Column(nullable = false, name="price")
    private double finalPrice;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
	private Narudzbina order;

	public Bill() {
		super();
	}

	public Bill(int number, LocalDate date, double finalPrice, Narudzbina order) {
		super();
		this.number = number;
		this.datum = date;
		this.finalPrice = finalPrice;
		this.order = order;
		
	}

	public Bill(Long id, int number, LocalDate date, double finalPrice, Narudzbina order) {
		super();
		this.id = id;
		this.number = number;
		this.datum = date;
		this.finalPrice = finalPrice;
		this.order = order;
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
		Bill other = (Bill) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public LocalDate getDate() {
		return datum;
	}

	public void setDate(LocalDate date) {
		this.datum = date;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public Narudzbina getOrder() {
		return order;
	}

	public void setOrder(Narudzbina order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Bill [id=" + id + ", number=" + number + ", date=" + datum + ", finalPrice=" + finalPrice + ", order="
				+ order + "]";
	}
	
	
	
	

}
