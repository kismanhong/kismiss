package com.softtech.kismiss.main;

import com.softtech.kismiss.enumer.CalculationPrintType;
import com.softtech.kismiss.enumer.HorizontalAlignment;
import com.softtech.kismiss.enumer.PaperType;
import com.softtech.kismiss.enumer.VerticalAlignment;
import com.softtech.kismiss.property.Detail;
import com.softtech.kismiss.property.FieldGroup;
import com.softtech.kismiss.property.Group;
import com.softtech.kismiss.property.Header;
import com.softtech.kismiss.property.Kismiss;
import com.softtech.kismiss.property.Property;
import com.softtech.kismiss.property.RecordNumber;

/**
 * @author Kisman Hong
 * test Kismiss Reports
 */
@Kismiss(name = "Employee", columnAutoSize = true, paperType = PaperType.A4, isTitleEveryPage=false)
@Header(columnHeaderHeight=25, isColumnHeaderBold=true, columnHeaderColor="#CCCCCC", lineWidth=0.5)
@Group(calculation = {"salary : Sum", "capacity : Average"}, groupBy="division", calculationPrintType=CalculationPrintType.Complete, horizontalAlignment = HorizontalAlignment.Right)
@Detail(lineWidth=0.5)
public class Employee {
	
	private Integer id;

	@FieldGroup
	private String lastName;

	@FieldGroup
	private String firstName;

	@FieldGroup(label="ALAMAK")
	private String address;

	@FieldGroup
	private String phoneNumber;

	@FieldGroup
	private String postCode;

	private String division;

	private Double salary;
	
	private Integer capacity;
	
	private Person person;

	@Property( width = 45,  position = 5, verticalAlignment=VerticalAlignment.Top, isShowInDetail=false)
	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	@Property(width = 45,  position = 6, horizontalAlignment = HorizontalAlignment.Right, fontSize=6, verticalAlignment= VerticalAlignment.Middle)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Property(name = "Post Code", width = 50,  position = 7)
	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	@Property(name = "Address", width = 100,  position = 3, fontSize=6)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Property(name = "First Name", width = 100,  position = 1, columnHierarchy="Name info", heightPortion=15)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Property(name = "Last Name", width = 100,  position = 2, columnHierarchy="Name info", heightPortion=15)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Property(name="No", width=30, position=0)
	@RecordNumber
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Property(name = "Salary", width = 45,  position = 4, horizontalAlignment = HorizontalAlignment.Center, fontSize=6)	
	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Property(width = 35, position = 8, horizontalAlignment = HorizontalAlignment.Center, fontSize=6, verticalAlignment=VerticalAlignment.Middle)	
	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
}