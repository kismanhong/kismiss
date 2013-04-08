//package com.softtech.kismiss.main;
//
//import com.softtech.kismiss.enumer.CalculationType;
//import com.softtech.kismiss.enumer.HorizontalAlignment;
//import com.softtech.kismiss.enumer.Orientation;
//import com.softtech.kismiss.enumer.PaperType;
//import com.softtech.kismiss.property.Detail;
//import com.softtech.kismiss.property.Header;
//import com.softtech.kismiss.property.Calculation;
//import com.softtech.kismiss.property.FieldGroup;
//import com.softtech.kismiss.property.Kismiss;
//import com.softtech.kismiss.property.Property;
//import com.softtech.kismiss.property.RecordNumber;
//
///**
// * @author Kisman Hong
// * test Kismiss Reports
// */
////@Band(columnFooterHeight=0, columnHeaderHeight=0, detailHeight=0, pageFooterHeight=0, titleHeight=0)
//@Kismiss(name = "Person", columnAutoSize = true, paperType= PaperType.A4, orientation=Orientation.Landscape,
//		isCrosstab=true, crosstabListHeaderWidth=200)
//@Header(columnHeaderHeight=65, isColumnHeaderBold=true, columnHeaderColor="#BDBBBC")
//@Detail(lineWidth=0.5)
////@Group(calculation = {"phoneNumber : Sum","capacity : Sum"}, groupBy="country", leftPadding=20, backColor="#DEDEDE")
//public class PersonCrosstab {
//	
//	private Integer id;
//	
//	private Employee employee;
//
//	@FieldGroup
//	private String lastName;
//
//	@FieldGroup
//	private String firstName;
//
//	@FieldGroup(label="Alamak")
//	private String address;
//
//	@FieldGroup
//	private double phoneNumber;
//
//	@FieldGroup
//	private String postCode;
//
//	private String country;
//
//	private double capacity;
//
//	@Property( width = 45,  position = 5 , columnHierarchy={"Information Address"} , heightPortion=15)
//	public String getCountry() {
//		return country;
//	}
//
//	public void setCountry(String country) {
//		this.country = country;
//	}
//
//	//Group must be after property
//	@Property(width = 45,  position = 6, horizontalAlignment = HorizontalAlignment.Right, columnHierarchy={"Information Address"}, heightPortion=15)
////	@Calculation(calculationType = CalculationType.Sum, backColor="#cccccc")
//	public double getPhoneNumber() {
//		return phoneNumber;
//	}
//
//	public void setPhoneNumber(double phoneNumber) {
//		this.phoneNumber = phoneNumber;
//	}
//
//	@Property(name = "Post Code", width = 50,  position = 4, columnHierarchy={"NAME","COMPLETE","FIRST NAME"}, heightPortion={15,15,20})
//	public String getPostCode() {
//		return postCode;
//	}
//
//	public void setPostCode(String postCode) {
//		this.postCode = postCode;
//	}
//
//	@Property(name = "Address", width = 100,  position = 3, columnHierarchy={"NAME","COMPLETE"}, heightPortion={15,15} )
//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	@Property(name = "First Name", width = 100,  position = 1, columnHierarchy={"NAME","CINCAI NAME","NICKNAME"}, heightPortion={15,15,20}, 
//			isShownWhenCrosstab=true, rowGroup=true, height=30)
//	public String getFirstName() {
//		return firstName;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//	@Property(name = "Last Name", width = 100,  position = 2, columnHierarchy={"NAME","CINCAI NAME","NICKNAME"}, heightPortion={15,15,20},
//			isShownWhenCrosstab=true, columnGroup=true, height=30)
//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//
//	@Property(name = "No", width = 20,  position = 0, isShownWhenCrosstab=true)
//	@RecordNumber
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	@Property(name = "Capacity", width = 45,  position = 7, horizontalAlignment = HorizontalAlignment.Center)	
//	@Calculation(attribute = "capacity", calculationType = CalculationType.Sum, backColor="#cccccc")
//	public double getCapacity() {
//		return capacity;
//	}
//
//	public void setCapacity(double capacity) {
//		this.capacity = capacity;
//	}
//
//	@Property(name = "Employee", width = 45,  position = {8,9}, columnHierarchy={"GOOD"}, heightPortion=30, 
//			horizontalAlignment = HorizontalAlignment.Center, innerProperty={"Test:person.lastName"})
//	public Employee getEmployee() {
//		return employee;
//	}
//
//	public void setEmployee(Employee employee) {
//		this.employee = employee;
//	}
//	
//	
//}