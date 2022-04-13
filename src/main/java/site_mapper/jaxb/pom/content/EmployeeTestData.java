package site_mapper.jaxb.pom.content;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Employees", namespace = "EmpTestData")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeTestData {
	@XmlElement(name = "Employee", namespace = "EmpTestData")
	private List<EmployeeContent> employees;

	public List<EmployeeContent> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeContent> employees) {
		this.employees = employees;
	}
		
}
