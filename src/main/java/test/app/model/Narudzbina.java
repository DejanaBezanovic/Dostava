package test.app.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Narudzbina {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 	@Column(name = "id")
	    private Long id;
	 	
	 	@Column(unique = true, nullable = false)
	    private int number;
	 	
	 	@Column(nullable = false)
	    private LocalDate datum;
	 	
	 	@Column(nullable = false)
	    private String adress;
	 	
	 	@Column
	    private double price;
	 	
	 	@Column
	    private String description;
	 	
	 	@ManyToOne(fetch = FetchType.LAZY)
	 	private Delivery delivery;
	 	
	 	@OneToOne(mappedBy = "order" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	    private Bill bill;

		public Narudzbina() {
			super();
		}

		public Narudzbina(int number, LocalDate date, String adress, double price, String description) {
			super();
			this.number = number;
			this.datum = date;
			this.adress = adress;
			this.price = price;
			this.description = description;
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

		public String getAdress() {
			return adress;
		}

		public void setAdress(String adress) {
			this.adress = adress;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Delivery getDelivery() {
			return delivery;
		}

		public void setDelivery(Delivery delivery) {
			this.delivery = delivery;
		}
		
		public Bill getBill() {
			return bill;
		}

		public void setBill(Bill bill) {
			this.bill = bill;
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
			Narudzbina other = (Narudzbina) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Order [id=" + id + ", number=" + number + ", date=" + datum + ", adress=" + adress + ", price="
					+ price + ", description=" + description + ", delivery=" + delivery + "]";
		}
	 	
	 	
	 	
	
	

}
