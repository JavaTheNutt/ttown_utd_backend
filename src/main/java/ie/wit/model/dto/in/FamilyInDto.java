package ie.wit.model.dto.in;

import ie.wit.model.entity.FamilyEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class FamilyInDto{
	@NotNull
	@NotEmpty
	private String familyName;
	private String streetAddress;
	private String townAddress;
	private Float paidSoFar;
	private Boolean willingToVolunteer;
	private Long doctor;
	
	//todo constructor, getters and setters
	public FamilyInDto(){}
	
	public String getFamilyName(){
		return familyName;
	}
	public void setFamilyName(String familyName){
		this.familyName = familyName;
	}
	public String getStreetAddress(){
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress){
		this.streetAddress = streetAddress;
	}
	public String getTownAddress(){
		return townAddress;
	}
	public void setTownAddress(String townAddress){
		this.townAddress = townAddress;
	}
	public Float getPaidSoFar(){
		return paidSoFar >= 0.0 ? paidSoFar : Float.valueOf(0);
	}
	public void makePayment(Float amount){
		paidSoFar += amount;
	}
	public void setPaidSoFar(Float paidSoFar){
		this.paidSoFar = paidSoFar;
	}
	public Boolean getWillingToVolunteer(){
		return willingToVolunteer != null ? willingToVolunteer : false;
	}
	public void setWillingToVolunteer(Boolean willingToVolunteer){
		this.willingToVolunteer = willingToVolunteer;
	}
	public Long getDoctor(){
		return doctor;
	}
	public void setDoctor(Long doctor){
		this.doctor = doctor;
	}
	public FamilyEntity getAsEntity(){
		return new FamilyEntity(this.familyName, this.streetAddress, this.townAddress, this.paidSoFar, this.willingToVolunteer, this.doctor);
	}
}
