package com.softtech.kismiss.main;

import com.softtech.kismiss.enumer.CalculationType;
import com.softtech.kismiss.enumer.ColorMode;
import com.softtech.kismiss.enumer.HorizontalAlignment;
import com.softtech.kismiss.enumer.Orientation;
import com.softtech.kismiss.enumer.PaperType;
import com.softtech.kismiss.property.Calculation;
import com.softtech.kismiss.property.ConditionalStyle;
import com.softtech.kismiss.property.ConditionalStyles;
import com.softtech.kismiss.property.Detail;
import com.softtech.kismiss.property.FieldGroup;
import com.softtech.kismiss.property.Header;
import com.softtech.kismiss.property.Kismiss;
import com.softtech.kismiss.property.Property;
import com.softtech.kismiss.property.RecordNumber;
import com.softtech.kismiss.property.Style;
import com.softtech.kismiss.property.Styles;

/**
 * @author Kisman Hong
 * test Kismiss Reports
 */
@Kismiss(name = "Person", columnAutoSize = true, paperType= PaperType.A4, orientation=Orientation.Landscape,
		pageNumberAlignment = HorizontalAlignment.Center, noDataFound="Tidak Ada Data", isTitleEveryPage=true) //, isCrosstab=true)
@Header(columnHeaderHeight=65, isColumnHeaderBold=true , columnHeaderColor="#BDBBBC", lineWidth=0.5, isColumnHeaderEveryPage=false ) //, oddEvenColor="#DEDEDE")
@Detail(lineWidth=0.5)
@Styles(styles = { 
	@Style( name="color",
		conditionalStyles = 
			@ConditionalStyles(conditionalStyles = { 
				@ConditionalStyle(backColor="#cccccc", mode=ColorMode.Opaque, conditionalExpression="new Boolean($F{firstName}.equals(\"Kisman\"))",
					leftLineWidth=2, rightLineWidth=5, topLineWidth=5, leftLineColor="#EEEEEE", foreColor="#EEEEEE", topLineColor="#000000"
					), 
					
				@ConditionalStyle() 
					
			}
		)
	), 
	
	@Style( name="style2",
		conditionalStyles = 
			@ConditionalStyles(conditionalStyles = { 
				@ConditionalStyle()
			}
		)
	)
})

//@Group(calculation = {"phoneNumber : Sum","capacity : Sum"}, groupBy="country", leftPadding=20, backColor="#DEDEDE")
public class Person {
	
	private Integer id;
	
	@FieldGroup(label={"phoneNumber:Employee Phone Number","postCode"})
	private Employee employee;

	@FieldGroup
	private String lastName;

	@FieldGroup
	private String firstName;

	@FieldGroup(label="Alamak")
	private String address;

	@FieldGroup
	private double phoneNumber;

	@FieldGroup
	private String postCode;

	private String country;

	private double capacity;

	@Property( width = 45,  position = 5 , columnHierarchy={"Information Address"} , heightPortion=15)
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Property(width = 45,  position = 6, horizontalAlignment = HorizontalAlignment.Right, columnHierarchy={"Information Address"}, heightPortion=15)
	@Calculation( calculationType = CalculationType.Sum, backColor="#cccccc")
	public double getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(double phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Property(name = "Post Code", width = 50,  position = 4, columnHierarchy={"NAME","COMPLETE","FIRST NAME"}, heightPortion={15,15,20}, colorMode= ColorMode.Opaque)
	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	@Property(name = "Address", width = 100,  position = 3, columnHierarchy={"NAME","COMPLETE"}, heightPortion={15,15} )
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Property(name = "First Name", width = 100,  position = 1, columnHierarchy={"NAME","CINCAI NAME","NICKNAME"}, heightPortion={15,15,20},
		  colorMode=ColorMode.Opaque, style="color")
//		, textFieldExpression="$F{firstName}.equals(\"Kisman\")?\"\":$F{firstName}")
//	 , printWhenExpression="new Boolean($F{firstName}.equals(\"Kisman\"))")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Property(name = "Last Name", width = 100,  position = 2, columnHierarchy={"NAME","CINCAI NAME","NICKNAME"}, heightPortion={15,15,20})
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Property(name = "No", width = 20,  position = 0, whenHeaderHAlignment=HorizontalAlignment.Left)
	@RecordNumber(isResetWhenGrouped=true, resetGroupName="country")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Property(name = "Capacity", width = 45,  position = 7, horizontalAlignment = HorizontalAlignment.Center)	
	@Calculation(attribute = "capacity", calculationType = CalculationType.Sum, backColor="#cccccc")
	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	@Property(name = "Employee", width = 45,  position = {8,9}, columnHierarchy={"GOOD"}, heightPortion=30, 
			horizontalAlignment = HorizontalAlignment.Center, innerProperty={"Salary:salary","Capacity:capacity"})
//	@Calculation(attribute = "employee.salary,employee.capacity", calculationType = {CalculationType.Sum, CalculationType.Average}, backColor="#cccccc")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
}