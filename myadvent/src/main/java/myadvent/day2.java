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

public class day2 {
	public Map<String,Long> resultMap = new HashMap<String,Long>();
	
	public void getIncreased(List<String> list) {
		long horizontal=0;
		long depth=0;
		long firstone=0;
	    for(int i=0;i<list.size();i++) {
	    	String[]split =list.get(i).split(" ");
	    	switch(split[0]) {
	    		case "forward":
	    			horizontal= horizontal + Long.parseLong(split[1]); 
	    			break;
	    		case "up":
	    			depth= depth - Long.parseLong(split[1]);
	    			break;
	    		case "down":
	    			depth= depth + Long.parseLong(split[1]);
	    			break;
	    	}
	    }
		this.resultMap.put("horizontal",horizontal);
		this.resultMap.put("depth",depth);
	}
	
	public void getSecond(List<String> list) {
		long horizontal=0;
		long depth=0;
		long aim =0;
	    for(int i=0;i<list.size();i++) {
	    	String[]split =list.get(i).split(" ");
	    	switch(split[0]) {
	    		case "forward":
	    			horizontal= horizontal + Long.parseLong(split[1]); 
	    			depth= depth + aim * Long.parseLong(split[1]); 
	    			break;
	    		case "up":
	    			aim= aim - Long.parseLong(split[1]);
	    			break;
	    		case "down":
	    			aim= aim + Long.parseLong(split[1]);
	    			break;
	    	}
	    }
		this.resultMap.put("horizontal",horizontal);
		this.resultMap.put("depth",depth);
	}
	
    public static void main(String args[]) throws IOException{  
    	day2 d =new day2();
    	File file = new File("src/main/resources/input2.txt");
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
		//List<Long> list2=d.getNewList(list);
		d.getSecond(list);
		long horizontal=d.resultMap.get("horizontal");
		long depth=d.resultMap.get("depth");
			System.out.println(horizontal);		
			System.out.println(depth);	
			System.out.println(depth*horizontal);			
		//close
		in.close();
		bufferedReader.close();
       }  
}
