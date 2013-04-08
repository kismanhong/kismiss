//package com.softtech.kismiss.main;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Random;
//
//import com.softtech.kismiss.constant.ReportFactory;
//
//
//public class ReportCrosstabTest {
//	
//	public static void main(String args[]) throws Exception
//	{
//		List<PersonCrosstab> persons = new ArrayList<PersonCrosstab>();
//		Random random = new Random();
//		for(int i=0; i < 100; i++)
//		{
//			Employee employee = new Employee();
//			employee.setLastName("hhe");
//			PersonCrosstab person = new PersonCrosstab();
////			employee.setPerson(person);
//			person.setEmployee(employee);
//			person.setAddress("Jalan Lurus belok dikit");
//			person.setCountry("South Africa");
//			person.setFirstName("Kisman");
//			person.setLastName("Kisman");
//			person.setPhoneNumber(989);
//			person.setPostCode("01234");
//			person.setCapacity(random.nextInt(1000));
//			person.setId(60);
//			persons.add(person);
//		}
//		
//		for(int i=0; i < 567; i++)
//		{
//			PersonCrosstab person = new PersonCrosstab();
//			person.setAddress("Jalan Lurus belok dikit");
//			person.setCountry("Hongkong City");
//			person.setFirstName("Kisman Hong");
//			person.setLastName("Hong");
//			person.setPhoneNumber(989);
//			person.setPostCode("01234");
//			person.setCapacity(random.nextInt(1000));
//			person.setId(60);
//			persons.add(person);
//		}
//		
//		for(int i=0; i < 344; i++)
//		{
//			PersonCrosstab person = new PersonCrosstab();
//			person.setAddress("Jalan Lurus belok dikit");
//			person.setCountry("Hongkong City");
//			person.setFirstName("Kisman Stress");
//			person.setLastName("Hong Stress");
//			person.setPhoneNumber(989);
//			person.setPostCode("01234");
//			person.setCapacity(random.nextInt(1000));
//			person.setId(60);
//			persons.add(person);
//		}
//		
//		for(int i=0; i < 145; i++)
//		{
//			PersonCrosstab person = new PersonCrosstab();
//			person.setAddress("Jalan Lurus belok dikit");
//			person.setCountry("Hongkong City");
//			person.setFirstName("Kisman Jiayou");
//			person.setLastName("Hong Jiayou");
//			person.setPhoneNumber(989);
//			person.setPostCode("01234");
//			person.setCapacity(random.nextInt(1000));
//			person.setId(60);
//			persons.add(person);
//		}
//		
//		for(int i=0; i < 987; i++)
//		{
//			PersonCrosstab person = new PersonCrosstab();
//			person.setAddress("Jalan Lurus belok dikit");
//			person.setCountry("Hongkong City");
//			person.setFirstName("Kisman Tujuan kamu apa?");
//			person.setLastName("Hong hehe");
//			person.setPhoneNumber(989);
//			person.setPostCode("01234");
//			person.setCapacity(random.nextInt(1000));
//			person.setId(60);
//			persons.add(person);
//		}
//		
//		for(int i=0; i < 678; i++)
//		{
//			PersonCrosstab person = new PersonCrosstab();
//			person.setAddress("Jalan Lurus belok dikit");
//			person.setCountry("Hongkong City");
//			person.setFirstName("Kisman apa gunanya");
//			person.setLastName("Hong stupid");
//			person.setPhoneNumber(989);
//			person.setPostCode("01234");
//			person.setCapacity(random.nextInt(1000));
//			person.setId(60);
//			persons.add(person);
//		}
//		
//		for(int i=0; i < 555; i++)
//		{
//			PersonCrosstab person = new PersonCrosstab();
//			person.setAddress("Jalan Lurus belok dikit");
//			person.setCountry("Hongkong City");
//			person.setFirstName("Kisman clever");
//			person.setLastName("Hong clever");
//			person.setPhoneNumber(989);
//			person.setPostCode("01234");
//			person.setCapacity(random.nextInt(1000));
//			person.setId(60);
//			persons.add(person);
//		}
//		
//		for(int i=0; i < 222; i++)
//		{
//			PersonCrosstab person = new PersonCrosstab();
//			person.setAddress("Jalan Lurus belok dikit");
//			person.setCountry("Hongkong City");
//			person.setFirstName("Kisman good");
//			person.setLastName("Hong good");
//			person.setPhoneNumber(989);
//			person.setPostCode("01234");
//			person.setCapacity(random.nextInt(1000));
//			person.setId(60);
//			persons.add(person);
//		}
//		
////		for(int i=0; i < 333; i++)
////		{
////			PersonCrosstab person = new PersonCrosstab();
////			person.setAddress("Jalan Lurus belok dikit");
////			person.setCountry("Hongkong City");
////			person.setFirstName("Kisman sip");
////			person.setLastName("Hong sip");
////			person.setPhoneNumber(989);
////			person.setPostCode("01234");
////			person.setCapacity(random.nextInt(1000));
////			person.setId(60);
////			persons.add(person);
////		}
////		
////		for(int i=0; i < 333; i++)
////		{
////			PersonCrosstab person = new PersonCrosstab();
////			person.setAddress("Jalan Lurus belok dikit");
////			person.setCountry("Hongkong City");
////			person.setFirstName("Kisman Bad");
////			person.setLastName("Hong Bad");
////			person.setPhoneNumber(989);
////			person.setPostCode("01234");
////			person.setCapacity(random.nextInt(1000));
////			person.setId(60);
////			persons.add(person);
////		}
////		
////		for(int i=0; i < 333; i++)
////		{
////			PersonCrosstab person = new PersonCrosstab();
////			person.setAddress("Jalan Lurus belok dikit");
////			person.setCountry("Hongkong City");
////			person.setFirstName("Kisman Nothing");
////			person.setLastName("Hong Nothing");
////			person.setPhoneNumber(989);
////			person.setPostCode("01234");
////			person.setCapacity(random.nextInt(1000));
////			person.setId(60);
////			persons.add(person);
////		}
////		
////		for(int i=0; i < 333; i++)
////		{
////			PersonCrosstab person = new PersonCrosstab();
////			person.setAddress("Jalan Lurus belok dikit");
////			person.setCountry("Hongkong City");
////			person.setFirstName("Kisman empty");
////			person.setLastName("Hong empty");
////			person.setPhoneNumber(989);
////			person.setPostCode("01234");
////			person.setCapacity(random.nextInt(1000));
////			person.setId(60);
////			persons.add(person);
////		}
////		
////		for(int i=0; i < 333; i++)
////		{
////			PersonCrosstab person = new PersonCrosstab();
////			person.setAddress("Jalan Lurus belok dikit");
////			person.setCountry("Hongkong City");
////			person.setFirstName("Kisman foolish");
////			person.setLastName("Hong foolish");
////			person.setPhoneNumber(989);
////			person.setPostCode("01234");
////			person.setCapacity(random.nextInt(1000));
////			person.setId(60);
////			persons.add(person);
////		}
////		
////		for(int i=0; i < 333; i++)
////		{
////			PersonCrosstab person = new PersonCrosstab();
////			person.setAddress("Jalan Lurus belok dikit");
////			person.setCountry("Hongkong City");
////			person.setFirstName("Kisman dolly");
////			person.setLastName("Hong empty");
////			person.setPhoneNumber(989);
////			person.setPostCode("01234");
////			person.setCapacity(random.nextInt(1000));
////			person.setId(60);
////			persons.add(person);
////		}
////		
////		for(int i=0; i < 333; i++)
////		{
////			PersonCrosstab person = new PersonCrosstab();
////			person.setAddress("Jalan Lurus belok dikit");
////			person.setCountry("Hongkong City");
////			person.setFirstName("Kisman semangat");
////			person.setLastName("Hong semangat");
////			person.setPhoneNumber(989);
////			person.setPostCode("01234");
////			person.setCapacity(random.nextInt(1000));
////			person.setId(60);
////			persons.add(person);
////		}
//		
////		System.out.println("test enum :"+HorizontalAlignment.Center.toString());
////		Class clazzs = Kismiss.class;
////		
////		Method[] methods = clazzs.getMethods();
////		for (Method method : methods) {
////			System.out.println("value of "+method.getName()+" :"+method.getDefaultValue());
////		}
////		
////		Class clazz = ReportConstant.Paper_A1.class;
////		Field[] fields = clazz.getFields();
////		for (Field field : fields) {
////			System.out.println("Field name :"+field.getName() +"{}"+"Field value :");
////		}
////		
//		KismissReport report = KismissReport.getInstance();
////		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
//		
////		report.putConstraint("firstName", 94, 25, "first name", "First Nama", 0,ReportFactory.isFalse);
////		report.putConstraint("lastName", 55, 25, "last name", "Last Name", 1, ReportFactory.isFalse);
////		report.putConstraint("address", 50, 25, "address", "Address", 2, ReportFactory.isFalse);
////		report.putConstraint("country", 50, 25, "country", "Country", 3, ReportFactory.isFalse);
////		report.putConstraint("phoneNumber", 50, 25, "phone number", "Phone Number", 4, ReportFactory.isFalse);
////		report.putConstraint("postCode", 50, 25, "Post Code", "Post Code", 5, ReportFactory.isFalse);
////		report.putConstraint("capacity", 50, 25, "Capacity", "Capacity", 6, ReportFactory.isFalse);
//		
////		String[] groupClas = {"phoneNumber : Sum"};
////		report.addGroup("firstName", 20, 20, groupClas);
////		report.addGroup(groupBy, bandHeight, height, groupCals);
////		Map<String, Object> header = new HashMap<String, Object>();
//////		header.put(ReportFactory.REPORT_NAME, "Kisman Hong");
////		report.setreportParams(header);
//		List<String> subs = new ArrayList<String>();
//		subs.add("Print Date :"+new SimpleDateFormat("dd MMM yyyy hh:mm:ss").format(new Date()));
//		subs.add("PERCOBAAN PENGGUNAAN KISMISS REPORT DENGAN SENANG HATI INI DILAKUKAN SEMOGA BERMANFAAT BAGI BANYAK ORANG DAN DIRI SENDIRI");
//		subs.add("AKHIRNYA BISA JUGA!!! THANKS");
//		
//		HashMap<String, Object> params = new HashMap<String, Object>();
////		params.put(ReportFactory.BACKGROUND_URL, "D:/others/watermark.JPG");
//		params.put(ReportFactory.TITLE, "TESTING KISSMIS REPORT @ BY KISMAN HONG");
////		params.put(ReportFactory.INFOTITLE, "Report");
////		params.put(ReportFactory.INFORMATION, "PersonCrosstabal Kissmis Report");
////		params.put(ReportFactory.DATE, sdf.format(new Date()));
//		params.put(ReportFactory.PDF_NAME, "kismissTest.pdf");
//		params.put(ReportFactory.XLS_NAME, "kismissTest.xls");
//		params.put(ReportFactory.SUB_TITLE, subs);
//		
//		report.setSubTitleFontSize(8);
//
////		String[] groupClas = {"phoneNumber : Sum", "capacity : Sum"};
//
////		com.softtech.kismiss.access.Group group = new com.softtech.kismiss.access.Group("firstName", groupClas, 20, 20);
//		report.generateAnnotatedPdfFiles(PersonCrosstab.class, persons, "D:/Personal/Test/", params);
//		report.close();
//	
////		params.put(ReportFactory.PDF_NAME, "kismissTest1.pdf");
////		report.generateAnnotatedPdfFiles(Person.class, persons, "D:/example/", params);
////		report.close();
////		report.generateAnnotatedXlsFiles(Person.class, persons, "D:/example/", params);
////		report.close();
////		report.generateAnn
////		report.generateAnn(new Person(), persons, "D:/example/", params);
//		
//	}
//	
//	
//
//	public static void mains1(String args[]) throws Exception
//	{
//		List<Person> persons = new ArrayList<Person>();
//		for(int i=0; i < 100; i++)
//		{
//			Person person = new Person();
//			person.setAddress("Jalan Lurus belok dikit");
//			person.setCountry("Sibebur");
//			person.setFirstName("Kisman");
//			person.setLastName("Hong");
//			person.setPhoneNumber(989);
//			person.setPostCode("01234");
////			person.setCapacity(5);
//			persons.add(person);
//		}
//		
//		for(int i=0; i < 100; i++)
//		{
//			Person person = new Person();
//			person.setAddress("Jalan Lurus belok dikit");
//			person.setCountry("Hongkong");
//			person.setFirstName("Kisman");
//			person.setLastName("Hong");
//			person.setPhoneNumber(989);
//			person.setPostCode("01234");
////			person.setCapacity(-2);
//			persons.add(person);
//		}
//		
////		System.out.println("test enum :"+HorizontalAlignment.Center.toString());
////		Class clazzs = Kismiss.class;
////		
////		Method[] methods = clazzs.getMethods();
////		for (Method method : methods) {
////			System.out.println("value of "+method.getName()+" :"+method.getDefaultValue());
////		}
////		
////		Class clazz = ReportConstant.Paper_A1.class;
////		Field[] fields = clazz.getFields();
////		for (Field field : fields) {
////			System.out.println("Field name :"+field.getName() +"{}"+"Field value :");
////		}
////		
////		KismissReport report = KismissReport.getInstance();
//		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
//		
//		
////		String[] groupClas = {"phoneNumber : Sum","phoneNumber : Average"};
////		report.addGroup("phoneNumber", 22, 0, 0, 22, groupClas);
////		Map<String, Object> header = new HashMap<String, Object>();
//////		header.put(ReportFactory.REPORT_NAME, "Kisman Hong");
////		report.setreportParams(header);
//		
//		HashMap<String, String> params = new HashMap<String, String>();
////		params.put(ReportFactory.BACKGROUND_URL, "D:/others/watermark.JPG");
//		params.put(ReportFactory.TITLE, "TESTING KISSMIS REPORT @ BY KISMAN HONG");
//		params.put(ReportFactory.INFOTITLE, "Report");
//		params.put(ReportFactory.INFORMATION, "Personal Kissmis Report");
//		params.put(ReportFactory.DATE, sdf.format(new Date()));
////		report.generateStandardPdfReportsFile(new CollectorProductivity(), arr, "D:/example/", params);
////		report.generateAnnotatedXlsFiles(new Person(), persons, "D:/example/", params);
////		report.gen
////		report.close();
////		report.generateAnn(new Person(), persons, "D:/example/", params);
//		
//	}
//	
//
//	public void Test() 
//	{
//		
//	}
//}
