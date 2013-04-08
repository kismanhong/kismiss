package com.softtech.kismiss.handler;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kisman Hong
 * @email kismanhong@gmail.com
 * simplify report generation
 * ReadErrors is used for getting the error message/s from properties file, 
 * in this case, read from com/softtech/kismiss/properties/error-messages.properties
 * this is used for java 1.5 above
 */
public class ReadErrors {
	
		private static Logger logger = LoggerFactory.getLogger(ReadErrors.class);

	    /**
	     * blank 
	     */
	    private final static transient String BLANK = "";
	    
	    /**
	     * param 0
	     */
	    private final static transient String PARAM0 = "{0}";
 
	    /**
	     * Defines properties file name from which the messages will be loaded
	     */
	    private final static transient String FILE_NAME =
	            "com/softtech/kismiss/properties/error-messages";

	    /**
	     * Static variable to provide quick and convenient way to get the instance
	     * of this class.
	     */
	    public static ReadErrors instance = new ReadErrors();
 
	    /**
	     * Holds the messages which have been retrieved from file
	     */
	    private Properties props;
	    
	    private ResourceBundle resourceBundle;
	
//	public static String getErrorMessage(String code)
//	{
//		Properties properties = new Properties();
//	    try {
//	    	System.out.println(ReadErrors.class.getCanonicalName());
//	        properties.load(new FileInputStream("src/main/java/error-messages.properties"));
//	        
//	        System.out.println(properties.getProperty("00000001"));
//	        System.out.println(properties.getProperty("00000001", "kisman hong"));
//	    } catch (IOException e) {
//	    	e.printStackTrace();
//	    }
//
//		return null;
//	}
	
//	public static void main(String args[])
//	{
//		getErrorMessage("");
//	}
	
	   /**
     * Get message for specified error code.
     *
     * @param code error code of the message to be retrieved
     * @return error message
     */
    public String getErrorMessage(String code) {
        if (code != null)
            return "[ KIS-"+code+" ] = "+resourceBundle.getString(code);;
        return BLANK;
    }


    /**
     * Get message for specified error code and embbed a value into the message.
     *
     * @param code error code of the message to be retrieved
     * @param param   value to be embedded in the message
     * @return error message
     */
    public String getErrorMessage(String code, Object param) {
        if (code != null) {
//            String strMsg = props.getProperty(code, BLANK);
            String strMsg  = resourceBundle.getString(code);
            if (strMsg.indexOf(PARAM0) > -1 && param != null) {
                strMsg = strMsg.replaceAll("\\B\\{0\\}", param.toString());
                return "[ KIS-"+code+" ] = "+strMsg;
            } else
                return "[ KIS-"+code+" ] = "+strMsg;
        }
        return BLANK;
    }


    /**
     * Get message for specified error code and embbed several values into the message.
     *
     * @param code error code of the message to be retrieved
     * @param params  values to be embbeded in the message
     * @return error message
     */
    public String getErrorMessage(String code, Object[] params) {
        if (code != null) {
//            String strMsg = props.getProperty(code, BLANK);
        	String strMsg  = resourceBundle.getString(code);
            if (params == null)
                return strMsg;
            for (int i = 0; i < params.length; i++) {
                if (strMsg.indexOf("{" + i + "}") > -1 && params[i] != null)
                    strMsg = strMsg.replaceAll("\\B\\{" + i + "\\}", params[i].toString());
            }
            return "[ KIS-"+code+" ] = "+ strMsg;
        }
        return BLANK;
    }
    
    /**
     * @param code
     * @param arg0
     * @param arg1
     * @return String
     * getting error message
     */
    public String getErrorMessage(String code, String arg0, String arg1)
    {
    	if (code != null) {
//            String errMessage = props.getProperty(code, BLANK);
//            if (params == null)
//                return errMessage;
    		String errMessage  = resourceBundle.getString(code);
                    errMessage = errMessage.replaceAll("\\B\\{0\\}", arg0);
                    errMessage = errMessage.replaceAll("\\B\\{1\\}", arg1);
            return "[ KIS-"+code+" ] = "+errMessage;
        }
        return BLANK;
    }


    /**
     * Default contructor.
     * Perform initial task to inisiating messages value.
     */
    private ReadErrors() {
		// TODO Auto-generated constructor stub
        defined();
    }


    /**
     * Defined and read error messages from properties file.
     */
    private void defined() {
        if (props == null)
            props = new Properties();
        FileInputStream in = null;
        try {
//            in = new FileInputStream(FILE_NAME);
        	resourceBundle = ResourceBundle.getBundle(FILE_NAME);
//            props.load(in);
        } catch (Exception e) {
            logger.warn("Error messages configuration file (\""
                    + FILE_NAME
                    + "\") not found.",
                    e);
        	System.out.println("Error loading error-message");
        	e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException ignore) {
            }
        }
    }
}
