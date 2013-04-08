package com.softtech.kismiss.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Example {
	private static List<Name> names = new ArrayList<Name>();
	public static void main(String[] args) {
		//fill data, this is example of data, for real get from database
		List<People> peoples = new ArrayList<People>();
		for(int i=0; i < 20; i++){
			String[] countryState = produceCountry();
			People people = new People();
			people.setName(produceName());
			people.setCountry(countryState[0]);
			people.setState(countryState[1]);
			people.setPostCode(producePostCode());
			people.setPhoneNumber(producePhoneNumber());
			peoples.add(people);
		}
		
		JasperPrint jasperPrint;
		try {
			JasperDesign design = JRXmlLoader.load("D:/Personal/Test/report4.jrxml");
			JasperReport report = JasperCompileManager.compileReport(design);
			
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(peoples);
			jasperPrint = JasperFillManager.fillReport(report, null, ds);
			JasperViewer.viewReport(jasperPrint);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
       
	}
	
	private static String produceName()
	{
		String result[] =new String[2];
		String[] potentialFirstNames = {"Peter", "John", "Steve", "George", "Ryan", "Bryan", "James", "Jason", "Margaret", "Elizabeth", "Cole", "Carlos", "Tony", "Gordon", "Simon"};
		String[] potentialLastNames = {"Brown", "Blair", "Smith", "Bros", "Baiden", "Woo", "Hong", "Man", "Yang Wee", "Momo", "Barack", "Cool", "Johansen", "Pier", "Corell"};
		Random rand = new Random();
		Random rand1 = new Random();
		int first = rand.nextInt(14);
		int last = rand1.nextInt(14);
		Name name = new Name(first, last);
		
		result[0] = potentialFirstNames[first];
		result[1] =potentialLastNames[last];
		names.add(name);
		
		return result[0]+" "+result[1];
	}
	
	private static String[] produceCountry()
	{
		String[] potentialFirstNames = {"Indonesia", "Malaysia", "Singapore", "Vietnam", "Thailand", "Kamboja", "Brunei", "Timor Leste", "Philipine", "Laos", "Myanmar", "Austria", "Japan", "Korea", "China"};
		String[] states = {"Jakarta", "Kuala Lumpur", "Singapore", "Phonephem", "Bangkok", "Kamboja", "Brunei", "Timor Leste", "Philipine", "Laos", "Myanmar", "Wina", "Tokyo", "Seol", "Beijing"};
		Random rand = new Random();
		int first = rand.nextInt(14);
		String[] result = new String[2];
		result[0] = potentialFirstNames[first];
		result[1] = states[first];
		return result;
	}
	
	private static String producePostCode(){
		String[] digits= {"0","1","2","3","4","5","6","7","8","9"};
		Random rand = new Random();
		String result = "";
		int first = rand.nextInt(10);
		for(int i=0; i < 5; i++){
			result += digits[first];
		}
		return result;
	}
	
	private static String producePhoneNumber(){
		String[] digits= {"0","1","2","3","4","5","6","7","8","9"};
		Random rand = new Random();
		String result = "";
		int first = rand.nextInt(10);
		for(int i=0; i < 13; i++){
			result += digits[first];
		}
		return result;
	}
	
	
	private static final class Name
	{
		private int firstName;
		private int lastName;
		public Name(int firstName, int lastName)
		{
			setFirstName(firstName);
			setLastName(lastName);
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + firstName;
			result = prime * result + lastName;
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
			Name other = (Name) obj;
			if (firstName != other.firstName)
				return false;
			if (lastName != other.lastName)
				return false;
			return true;
		}
		
		
		public void setFirstName(int firstName) {
			this.firstName = firstName;
		}
	
		public void setLastName(int lastName) {
			this.lastName = lastName;
		}
	}
}
