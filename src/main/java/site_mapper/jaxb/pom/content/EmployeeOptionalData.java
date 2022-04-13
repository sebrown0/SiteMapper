/**
 * 
 */
package site_mapper.jaxb.pom.content;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * @author SteveBrown
 * @version 1.0
 *  Initial
 * @version 1.1
 * 	
 * @since 1.0
 *
 */
@XmlRootElement(name="Optional", namespace = "Employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeOptionalData  {	
	@XmlElement(name="Title",namespace="Employees")
	private String title;
	@XmlElement(name="Street",namespace="Employees")
	private String street;
	@XmlElement(name="PostCode",namespace="Employees")
	private String postCode;
	@XmlElement(name="Bank",namespace="Employees")
	private String bank;
	@XmlElement(name="IBAN",namespace="Employees")
	private String ibanNumber;
	@XmlElement(name="EmailAddress",namespace="Employees")
	private String emailAddress;
	@XmlElement(name="MobileNumber",namespace="Employees")
	private String mobileNumber;
	@XmlElement(name="TaxStatus",namespace="Employees")
	private String taxStatus;	
	@XmlElement(name="ContractType",namespace="Employees")
	private String contractType;	
	@XmlElement(name="Company",namespace="Employees")
	private String company;
	@XmlElement(name="Department",namespace="Employees")
	private String department;
	@XmlElement(name="Schedule",namespace="Employees")
	private String schedule;
	@XmlElement(name="EmploymentType",namespace="Employees")
	private String employmentType;
	@XmlElement(name="EmploymentTitle",namespace="Employees")
	private String employeeTitle;	
	@XmlElement(name="IsSpecialPartTimer",namespace="Employees")
	private boolean isSpecialPartTimer;
	@XmlElement(name="Grade",namespace="Employees")
	private String grade;	
	@XmlElement(name="CostCenter",namespace="Employees")
	private String costCentre;
	
//	private List<Allowance> Z_taxablePermanentAllowances = new ArrayList<>();
//	private List<Allowance> Z_nonTaxablePermanentAllowances = new ArrayList<>();
	
	

	public String getStreet() {
		return street;
	}	
	public void setStreet(String street) {
		this.street = street;	
	}
	
	public String getPostCode() {
		return postCode;
	}	
	public void setPostCode(String postCode) {
		this.postCode = postCode;	
	}
	
	public String getBank() {
		return bank;
	}	
	public void setBank(String bank) {
		this.bank = bank;	
	}
	
	public String getIbanNumber() {
		return ibanNumber;
	}	
	public void setIbanNumber(String ibanNumber) {
		this.ibanNumber = ibanNumber;	
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;	
	}
		
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;		
	}
	
	public String getTaxStatus() {
		return taxStatus;
	}
	public void setTaxStatus(String taxStatus) {
		this.taxStatus = taxStatus;		
	}
	
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	
	public String getCompany() {
		return company;
	}	
	public void setCompany(String company) {
		this.company = company;
	}
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getSchedule() {
		return schedule;
	}	
	public void setSchedule(String schedule) {
		this.schedule = schedule;		
	}
	
	public String getEmploymentType() {
		return employmentType;
	}	
	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;	
	}	
	public boolean isSpecialPartTimer() {
		return isSpecialPartTimer;
	}
	
	public void setSpecialPartTimer(boolean isSpecialPartTimer) {
		this.isSpecialPartTimer = isSpecialPartTimer;	
	}
	
	public String getGrade() {
		return grade;
	}	
	public void setGrade(String grade) {
		this.grade = grade;		
	}
	
	public String getCostCentre() {
		return costCentre;
	}	
	public void setCostCentre(String costCentre) {
		this.costCentre = costCentre;		
	}
	
//	public List<Allowance> getTaxablePermanentAllowances() {
//		return taxablePermanentAllowances;
//	}	
//	public void setTaxablePermanentAllowances(List<Allowance> taxablePermanentAllowances) {
//		this.taxablePermanentAllowances = taxablePermanentAllowances;		
//	}
	
//	public List<Allowance> getNonTaxablePermanentAllowances() {
//		return nonTaxablePermanentAllowances;
//	}	
//	public void setNonTaxablePermanentAllowances(List<Allowance> nonTaxablePermanentAllowances) {
//		this.nonTaxablePermanentAllowances = nonTaxablePermanentAllowances;	
//	}
	
	public void setEmployeeTitle(String employeeTitle) {
		this.employeeTitle = employeeTitle;		
	}	
	public String getEmployeeTitle() {
		return employeeTitle;
	}
	
}