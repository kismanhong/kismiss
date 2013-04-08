package com.softtech.kismiss.main;

import java.net.URL;

public class Test {

	  private Test() {
	    super();
	  }
	    
	  public static final void main(final String[] args) throws Throwable {
	    final URL location;
	    final String classLocation = KismissReport.class.getName().replace('.', '/') 
	              + ".class";
	    final ClassLoader loader = KismissReport.class.getClassLoader();
	    if (loader == null) {
	     System.out.println("Cannot load the class");
	   } else {
	      location = loader.getResource(classLocation);
	    System.out.println("Class "+location.getPath() + "; Class name :"+KismissReport.class.getSimpleName());
	    }
	  }
	
}
