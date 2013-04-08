package com.softtech.kismiss.comparator;

import java.util.Comparator;

import com.softtech.kismiss.model.CalculationInfo;

public class XComparator implements Comparator<Object>{
	   
    public int compare(Object cal1, Object cal2){
   
        /*
         * parameter are of type Object, so we have to downcast it
         * to Employee objects
         */
       
        int xValue1 = ((CalculationInfo)cal1).getXValue();        
        int xValue2 = ((CalculationInfo)cal2).getXValue();
       
        if(xValue1 > xValue2)
            return 1;
        else if(xValue1 < xValue2)
            return -1;
        else
            return 0;    
    }
   
}
