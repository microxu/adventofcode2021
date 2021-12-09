package myadvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class day3 {
	public Map<String,String> resultMap = new HashMap<String,String>();
	
	public Map<String,List<String>> result = new HashMap<String,List<String>>();
	//public Map<String,List<String>> resultCO2 = new HashMap<String,List<String>>();
	
	public void getIncreased(List<String> list) {
		String gamma ="";
		String epsilon = "";

		int[] zList = new int[12];
		int[] oList = new int[12];
	    for(int i=0;i<list.size();i++) {

	    	for(int j=0;j<list.get(i).length();j++) {
                if(list.get(i).charAt(j)=='0') {
                	zList[j]=zList[j]+1;
                }else {
                	oList[j]=oList[j]+1;              	
                }
	    	}
	    }
	    for(int i=0;i<zList.length;i++) {
	    	if(zList[i]>oList[i]) {
	    		gamma=gamma+"0";
	    		epsilon=epsilon+"1";
	    	}else {
	    		gamma=gamma+"1";
	    		epsilon=epsilon+"0";    		
	    	}
	    }

		this.resultMap.put("gamma",gamma);
		this.resultMap.put("epsilon",epsilon);
	}
	
	public void getSub(int index,String type) {
		List<String> list = result.get(type);
		List<String> r0 = new ArrayList<String>();
		List<String> r1 = new ArrayList<String>();
	    for(int i=0;i<list.size();i++) {
	    	if(list.get(i).substring(index, index+1).equals("0")) {
	    		r0.add(list.get(i));
	    	}else {
	    		r1.add(list.get(i));	    		
	    	}
	    }
    	switch(type) {
		case "O2":
	        if(r0.size()>r1.size()) {
	    		this.result.put(type,r0);   
	        }else if(r0.size()<r1.size()) {
	    		this.result.put(type,r1); 
	        }else if(r0.size()==r1.size()) {
	    		this.result.put(type,r1); 
	        }
			break;
		case "CO2":
	        if(r0.size()>r1.size()) {
	    		this.result.put(type, r1);
	        }else if(r0.size()<r1.size()) {
	        	this.result.put(type, r0);
	        }else if(r0.size()==r1.size()) {
	    		this.result.put(type, r0);
	        }
			break;
	     }
	}
	
    public static void main(String args[]) throws IOException{  
    	day3 d =new day3();
    	File file = new File("src/main/resources/input3.txt");
    	InputStream in = new FileInputStream(file);

    	Reader reader = new InputStreamReader(in);
    	BufferedReader bufferedReader = new BufferedReader(reader);
//
		String str = null;

		List<String> list = new ArrayList<String>();
		while((str = bufferedReader.readLine()) != null)
		{
			list.add(str);
		}
		String type="CO2";
		d.result.put(type,list);  
        for(int i=0;i<12;i++) {
		   d.getSub(i,type);
		   if(d.result.get(type).size()==1) {
			   break;
		   }
        }
        System.out.println(Integer.parseUnsignedInt(d.result.get(type).get(0),2));
		type="O2";
		d.result.put(type,list);  
        for(int i=0;i<12;i++) {
		   d.getSub(i,type);
		   if(d.result.get(type).size()==1) {
			   break;
		   }
        }
        System.out.println(Integer.parseUnsignedInt(d.result.get(type).get(0),2));
        System.out.println(3623*1883);
		//close
		in.close();
		bufferedReader.close();
       }  
}
