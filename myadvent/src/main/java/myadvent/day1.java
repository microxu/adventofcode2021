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

public class day1 {
	public Map<String,Long> resultMap = new HashMap<String,Long>();
	
	public void getIncreased(List<Long> list) {
		long increased=0;
		long decreased=0;
		long firstone=0;
	    for(int i=0;i<list.size();i++) {
	    	if (firstone==0) {
	    		firstone = list.get(i);
	    	}else {
	    		if(firstone < list.get(i)) {
	    			increased=increased+1;
	    		}else if(firstone > list.get(i)){
	    			decreased=decreased+1;
	    		}
	    		firstone = list.get(i);
	    	}
	    }
		this.resultMap.put("increased",increased);
		this.resultMap.put("decreased",decreased);
	}
	
	public List<Long> getNewList(List<Long> list) {
		List<Long> list2 = new ArrayList<Long>();
		for(int i=0;i<list.size();i++) {
			if(i+2<=list.size()-1) {
				list2.add(list.get(i)+list.get(i+1)+list.get(i+2));
			}
		}
		return list2;
	}
	
    public static void main(String args[]) throws IOException{  
    	day1 d =new day1();
    	File file = new File("src/main/resources/input1.txt");
    	InputStream in = new FileInputStream(file);

    	Reader reader = new InputStreamReader(in);
    	BufferedReader bufferedReader = new BufferedReader(reader);
//
		String str = null;

		List<Long> list = new ArrayList<Long>();
		while((str = bufferedReader.readLine()) != null)
		{
			list.add(Long.parseLong(str));
		}
		List<Long> list2=d.getNewList(list);
		d.getIncreased(list2);
		long increased=d.resultMap.get("increased");
		long decreased=d.resultMap.get("decreased");
			System.out.println(increased);		
			System.out.println(decreased);	
		//close
		in.close();
		bufferedReader.close();
       }  
}
