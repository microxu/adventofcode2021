package myadvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.google.common.collect.Iterables;

public class day8 {

	public Map<Integer,Set<String>> setMap(String[] numbers) {
		Map<Integer,Set<String>> rMap = new HashMap<Integer,Set<String>>();	
		List<String> list = new ArrayList<String>();
		for(int i=0;i<numbers.length;i++) {
			String t = numbers[i];
			switch(numbers[i].length()) {
			case 2:
				rMap.put(1, new HashSet<>(Arrays.asList(t.split(""))));
				break;
			case 4:
				rMap.put(4, new HashSet<>(Arrays.asList(t.split(""))));
				break;
			case 3:
				rMap.put(7, new HashSet<>(Arrays.asList(t.split(""))));
				break;
			case 7:
				rMap.put(8, new HashSet<>(Arrays.asList(t.split(""))));
				break;
			default:
				list.add(numbers[i]);
			}
			
		}
        
		Iterator<String> iterator = Iterables.cycle(list).iterator();
		//Iterator<String> iterator = list.iterator();
		int n=0;
		boolean lNext=true;
        while (iterator.hasNext() && n<4 ) {
        	String t = iterator.next();
        	lNext=true;
			if(t.length()==6 && lNext) {
				Set<String> s6=new HashSet<>(Arrays.asList(t.split("")));
				if(rMap.get(1)!=null && !s6.containsAll(rMap.get(1))) {
					n=n+1;
					rMap.put(6, s6);
					iterator.remove();
					lNext=false;
				}
				if(rMap.get(4)!=null && s6.containsAll(rMap.get(4))) {
					n=n+1;
					rMap.put(9, s6);
					iterator.remove();
					lNext=false;
				}
			}
			if(t.length()==5 && lNext) {
				Set<String> s5=new HashSet<>(Arrays.asList(t.split("")));
				if(rMap.get(7)!=null && s5.containsAll(rMap.get(7))&& lNext ) {
					n=n+1;
					rMap.put(3, s5);
					iterator.remove();
					lNext=false;
				}
				if(rMap.get(9)!=null && rMap.get(9).containsAll(s5) && lNext) {
					n=n+1;
					rMap.put(5, s5);
					iterator.remove();
					lNext=false;
				}
			}
			if(n==4) {
				for(int i=0;i<list.size();i++) {
					Set<String> s=new HashSet<>(Arrays.asList(list.get(i).split("")));
					if(list.get(i).length()==5) {
						
						rMap.put(2, s);
					}else {
						rMap.put(0, s);
					}
				}  
			}
        }
  
        return rMap;
	}
	public static void main(String args[]) throws IOException{  

		File file = new File("src/main/resources/input8.txt");
		InputStream in = new FileInputStream(file);

		Reader reader = new InputStreamReader(in);
		BufferedReader bufferedReader = new BufferedReader(reader);

		String str = null;
        
        List<String> lines=new ArrayList<String>();
		while((str = bufferedReader.readLine()) != null)
		{ 
			lines.add(str);
		}
		in.close();
		bufferedReader.close();
		int result=0;
		long startTime = System.currentTimeMillis(); //get started
		day8 d =new day8();	
        for(int i=0;i<lines.size();i++) {
			String[] t=lines.get(i).split("\\|");
			String[] numbers=t[0].trim().split(" ");     
			String[] lights=t[1].trim().split(" ");
			Map<Integer,Set<String>> rMap = new HashMap<Integer,Set<String>>();
			rMap=d.setMap(numbers);
			String r="";
			for(int j=0;j<lights.length;j++) {
				Set<String> sTemp=new HashSet<>(Arrays.asList(lights[j].split("")));
				for (Map.Entry<Integer, Set<String>> entry : rMap.entrySet()) {
					 if(sTemp.containsAll(entry.getValue()) && entry.getValue().containsAll(sTemp)) {
						 r=r+String.valueOf(entry.getKey());
						 break;
					 }
				 
				}
		        
			}
			result=result+ Integer.valueOf(r);
        }
        System.out.println(result);

	     long endTime = System.currentTimeMillis(); //
	     System.out.println("running time: " + (endTime - startTime) + "ms"); //running time
	}
}