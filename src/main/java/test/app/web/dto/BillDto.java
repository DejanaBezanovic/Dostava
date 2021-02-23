package test.app.web.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

public class BillDto {
	
	private Long id;
	
	@NotBlank
	private int number;
	
	private LocalDate date = LocalDate.now();
	
	@NotBlank
	private double finalPrice;
	
	private Long orderId;

	public BillDto() {
		super();
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
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	
	
	

}
