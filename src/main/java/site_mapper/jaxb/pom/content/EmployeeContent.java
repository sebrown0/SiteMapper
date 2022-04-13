/**
 * 
 */
package site_mapper.jaxb.pom.content;

import java.math.BigDecimal;

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
@XmlRootElement(name="Employee", namespace = "Function")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeContent {//implements EmployeeRequired, EmployeeOptional  {
	@XmlElement(name="Required", namespace="Employees")
	private EmployeeRequiredData required;
	
	@XmlElement(name="Optional", namespace="Employees")
	private EmployeeOptionalData optional;
	
	public String getFirstName() {
		return getSanitised(required.getFirstName());
	}

	public String getLastName() {
		return getSanitised(required.getLastName());
	}
	//The only setter!
	public void setEmpCode(String empCode) {
		required.setEmpCode(empCode);
	}
	
	public String getEmpCode() {
		return getSanitised(required.getEmpCode());
	}

	public BigDecimal getAnnualSalary() {
		return required.getAnnualSalary();
//		return BigDecimal.valueOf(Long.valueOf(required.getAnnualSalary()));
	}

	
	public BigDecimal getHourlyRate() {
		return required.getHourlyRate();
	}

	public String getIdCardNumber() {
		return getSanitised(required.getIdCardNumber());
	}

	public String getAddressOne() {
		return getSanitised(required.getAddressOne());
	}

	public String getTown() {
		return getSanitised(required.getTown());
	}
	
	
	public String getCountry() {
		return getSanitised(required.getCountry());
	}

	public String getGender() {		
		return getSanitised(required.getGender());
	}

	public String getDateOfBirth() {
		return getSanitised(required.getDateOfBirth());
	}

	public String getDateOfEmployement() {
		return getSanitised(required.getDateOfEmployement());
	}

	public String getTaxNumber() {
		return getSanitised(required.getTaxNumber());
	}

	public String getNiNumber() {
		return getSanitised(required.getNiNumber());
	}

	public String getPayGroup() {
		return getSanitised(required.getPayGroup());
	}

	public String getStreet() {
		return getSanitised(optional.getStreet());
	}

	public String getPostCode() {
		return getSanitised(optional.getPostCode());
	}

	public String getBank() {
		return getSanitised(optional.getBank());
	}

	public String getIbanNumber() {
		return getSanitised(optional.getIbanNumber());
	}

	public String getEmailAddress() {
		return getSanitised(optional.getEmailAddress());
	}

	public String getMobileNumber() {
		return getSanitised(optional.getMobileNumber());
	}

	public String getTaxStatus() {
		return getSanitised(optional.getTaxStatus());
	}

	public String getContractType() {
		return getSanitised(optional.getContractType());
	}

	public String getEmployeeTitle() {
		return getSanitised(optional.getEmployeeTitle());
	}

//	public Company getCompany() {
	public String getCompany() {
		// TODO Auto-generated method stub
//		return new Company("TODO-SPECIFY NAME AND PAYGROUP");
		return getSanitised(optional.getCompany());
	}

	public String getDepartment() {
		return getSanitised(optional.getDepartment());
	}

	public String getSchedule() {
		return getSanitised(optional.getSchedule());
	}

	public String getEmploymentType() {
		return getSanitised(optional.getEmploymentType());
	}
	
	public boolean isSpecialPartTimer() {
		return optional.isSpecialPartTimer();
	}
	
	public String getGrade() {
		return getSanitised(optional.getGrade());
	}
	
	public String getCostCentre() {
		return getSanitised(optional.getCostCentre());
	}
	
//	public List<Allowance> getTaxablePermanentAllowances() {
//		//TODO
//		return null;
//	}
//	
//	public List<Allowance> getNonTaxablePermanentAllowances() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
//	@Override //RequiredFields
//	public String getFirstName() {
//		return getSanitised(required.getFirstName());
//	}
//	@Override //RequiredFields
//	public EmployeeRequired setFirstName(String firstName) {
//		required.setFirstName(firstName);
//		return this;
//	}
//	@Override //RequiredFields
//	public String getLastName() {
//		return getSanitised(required.getLastName());
//	}
//	@Override //RequiredFields
//	public EmployeeRequired setLastName(String lastName) {
//		required.setLastName(lastName);
//		return this;
//	}
//	@Override //RequiredFields
//	public String getEmpCode() {
//		return getSanitised(required.getEmpCode());
//	}
//	@Override //RequiredFields
//	public EmployeeRequired setEmpCode(String empCode) {
//		required.setEmpCode(empCode);
//		return this;
//	}
//	@Override //RequiredFields
//	public BigDecimal getAnnualSalary() {
//		return required.getAnnualSalary();
////		return BigDecimal.valueOf(Long.valueOf(required.getAnnualSalary()));
//	}
//	@Override //RequiredFields
//	public EmployeeRequired setAnnualSalary(BigDecimal annualSalary) {
//		required.setAnnualSalary(annualSalary);
//		return this;
//	}
//	@Override //RequiredFields
//	public BigDecimal getHourlyRate() {
//		return required.getHourlyRate();
//	}
//	@Override //RequiredFields
//	public EmployeeRequired setHourlyRate(BigDecimal hourlyRate) {
//		required.setHourlyRate(hourlyRate);
//		return this;
//	}
//	@Override //RequiredFields
//	public String getIdCardNumber() {
//		return getSanitised(required.getIdCardNumber());
//	}
//	@Override //RequiredFields
//	public EmployeeRequired setIdCardNumber(String idCardNumber) {
//		required.setIdCardNumber(idCardNumber);
//		return this;
//	}
//	@Override //RequiredFields
//	public String getAddressOne() {
//		return getSanitised(required.getAddressOne());
//	}
//	@Override //RequiredFields
//	public EmployeeRequired setAddressOne(String addressOne) {
//		required.setAddressOne(addressOne);
//		return this;
//	}
//	@Override //RequiredFields
//	public String getTown() {
//		return getSanitised(required.getTown());
//	}
//	@Override //RequiredFields
//	public EmployeeRequired setTown(String town) {
//		required.setTown(town);
//		return this;
//	}
//	@Override //RequiredFields
//	public String getCountry() {
//		return getSanitised(required.getCountry());
//	}
//	@Override //RequiredFields
//	public EmployeeRequired setCountry(String country) {
//		required.setCountry(country);
//		return this;
//	}
//	@Override //RequiredFields
//	public String getGender() {		
//		return getSanitised(required.getGender());
//	}
//	@Override //RequiredFields
//	public EmployeeRequired setGender(Gender gender) {
//		required.setGender(gender.name());
//		return this;
//	}
//	@Override //RequiredFields
//	public String getDateOfBirth() {
//		return getSanitised(required.getDateOfBirth());
//	}
//	@Override //RequiredFields
//	public EmployeeRequired setDateOfBirth(String dateOfBirth) {
//		required.setDateOfBirth(dateOfBirth);
//		return this;
//	}
//	@Override //RequiredFields
//	public String getDateOfEmployement() {
//		return getSanitised(required.getDateOfEmployement());
//	}
//	@Override //RequiredFields
//	public EmployeeRequired setDateOfEmployement(String dateOfEmployement) {
//		required.setDateOfEmployement(dateOfEmployement);
//		return this;
//	}
//	@Override //RequiredFields
//	public String getTaxNumber() {
//		return getSanitised(required.getTaxNumber());
//	}
//	@Override //RequiredFields
//	public EmployeeRequired setTaxNumber(String taxNumber) {
//		required.setTaxNumber(taxNumber);
//		return this;
//	}
//	@Override //RequiredFields
//	public String getNiNumber() {
//		return getSanitised(required.getNiNumber());
//	}
//	@Override //RequiredFields
//	public EmployeeRequired setNiNumber(String niNumber) {
//		required.setNiNumber(niNumber);
//		return this;
//	}
//	@Override //RequiredFields
//	public String getPayGroup() {
//		return getSanitised(required.getPayGroup());
//	}
//	@Override //RequiredFields
//	public EmployeeRequired setPayGroup(String payGroup) {
//		required.setPayGroup(payGroup);
//		return this;
//	}
//	@Override
//	public String getStreet() {
//		return getSanitised(optional.getStreet());
//	}
//	@Override
//	public EmployeeOptional setStreet(String street) {
//		optional.setStreet(street);
//		return this;
//	}
//	@Override
//	public String getPostCode() {
//		return getSanitised(optional.getPostCode());
//	}
//	@Override
//	public EmployeeOptional setPostCode(String postCode) {
//		optional.setPostCode(postCode);
//		return this;
//	}
//	@Override
//	public String getBank() {
//		return getSanitised(optional.getBank());
//	}
//	@Override
//	public EmployeeOptional setBank(String bank) {
//		optional.setBank(bank);
//		return this;
//	}
//	@Override
//	public String getIbanNumber() {
//		return getSanitised(optional.getIbanNumber());
//	}
//	@Override
//	public EmployeeOptional setIbanNumber(String ibanNumber) {
//		optional.setIbanNumber(ibanNumber);
//		return null;
//	}
//	@Override
//	public String getEmailAddress() {
//		return getSanitised(optional.getEmailAddress());
//	}
//	@Override
//	public EmployeeOptional setEmailAddress(String emailAddress) {
//		optional.setEmailAddress(emailAddress);
//		return this;
//	}
//	@Override
//	public String getMobileNumber() {
//		return getSanitised(optional.getMobileNumber());
//	}
//	@Override
//	public EmployeeOptional setMobileNumber(String mobileNumber) {
//		optional.setMobileNumber(mobileNumber);
//		return this;
//	}
//	@Override
//	public String getTaxStatus() {
//		return getSanitised(optional.getTaxStatus());
//	}
//	@Override
//	public EmployeeOptional setTaxStatus(TaxStatus taxStatus) {
//		optional.setTaxStatus(taxStatus.name());
//		return this;
//	}
//	@Override
//	public String getContractType() {
//		return getSanitised(optional.getContractType());
//	}
//	@Override
//	public EmployeeOptional setEmployeeTitle(EmployeeTitle employeeTitle) {
//		optional.setEmployeeTitle(employeeTitle.name());
//		return this;
//	}
//	@Override
//	public String getEmployeeTitle() {
//		return getSanitised(optional.getEmployeeTitle());
//	}
//	@Override
//	public EmployeeOptional setContractType(ContractType contractType) {
//		optional.setContractType(contractType.name());
//		return this;
//	}
//	@Override
//	public Company getCompany() {
//		// TODO Auto-generated method stub
//		return new Company("TODO-SPECIFY NAME AND PAYGROUP");
//	}
//	@Override
//	public EmployeeOptional setCompany(Company company) {
//		//TODO
//		optional.setCompany(company.getName());
//		return this;
//	}
//	@Override
//	public String getDepartment() {
//		return getSanitised(optional.getDepartment());
//	}
//	@Override
//	public EmployeeOptional setDepartment(String department) {
//		optional.setDepartment(department);
//		return this;
//	}
//	@Override
//	public String getSchedule() {
//		return getSanitised(optional.getSchedule());
//	}
//	@Override
//	public EmployeeOptional setSchedule(String schedule) {
//		optional.setSchedule(schedule);
//		return this;
//	}
//	@Override
//	public String getEmploymentType() {
//		return getSanitised(optional.getEmploymentType());
//	}
//	@Override
//	public EmployeeOptional setEmploymentType(EmploymentType employmentType) {
//		optional.setEmploymentType(employmentType.name());
//		return this;
//	}
//	@Override
//	public boolean isSpecialPartTimer() {
//		return optional.isSpecialPartTimer();
//	}
//	@Override
//	public EmployeeOptional setSpecialPartTimer(boolean isSpecialPartTimer) {
//		optional.setSpecialPartTimer(isSpecialPartTimer);
//		return this;
//	}
//	@Override
//	public String getGrade() {
//		return getSanitised(optional.getGrade());
//	}
//	@Override
//	public EmployeeOptional setGrade(String grade) {
//		optional.setGrade(grade);
//		return this;
//	}
//	@Override
//	public String getCostCentre() {
//		return getSanitised(optional.getCostCentre());
//	}
//	@Override
//	public EmployeeOptional setCostCentre(String costCentre) {
//		optional.setCostCentre(costCentre);
//		return this;
//	}
//	@Override
//	public List<Allowance> getTaxablePermanentAllowances() {
//		//TODO
//		return null;
//	}
//	@Override
//	public EmployeeOptional setTaxablePermanentAllowances(List<Allowance> taxablePermanentAllowances) {
//		// TODO Auto-generated method stub
//		return this;
//	}
//	@Override
//	public List<Allowance> getNonTaxablePermanentAllowances() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public EmployeeOptional setNonTaxablePermanentAllowances(List<Allowance> nonTaxablePermanentAllowances) {
//		// TODO Auto-generated method stub
//		return this;
//	}
		
	public EmployeeRequiredData getRequired() {
		return required;
	}
	public EmployeeOptionalData getOptional() {
		return optional;
	}
	
	private String getSanitised(String str) {
		return str.replace("\"", "");
	}
}