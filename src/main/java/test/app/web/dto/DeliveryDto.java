package test.app.web.dto;

import javax.validation.constraints.NotBlank;

public class DeliveryDto {
	
	private Long id;
	
	@NotBlank
    private String jmbg;

    @NotBlank
    private String brojLK;
    
    @NotBlank
    private String imeIPrezime;

	public DeliveryDto() {
		super();
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
		return brojLK;
	}

	public void setBrojLK(String brojLK) {
		this.brojLK = brojLK;
	}

	public String getImeIPrezime() {
		return imeIPrezime;
	}

	public void setImeIPrezime(String imeIPrezime) {
		this.imeIPrezime = imeIPrezime;
	}
    
    

}
