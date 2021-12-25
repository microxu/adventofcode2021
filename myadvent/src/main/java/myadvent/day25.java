package myadvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class day25 {

	public List<List<String>> maps=new ArrayList<>();
	
	public void firstOne() {
		int i=0;
		Map<String,Object> m =new HashMap<>();
		m.put("newMap", this.maps);
		while(true) {
			i+=1;
			m=nextStep((List<List<String>>) m.get("newMap"),">");
			boolean left=(boolean) m.get("next");
			m=nextStep((List<List<String>>) m.get("newMap"),"v");
			boolean down=(boolean) m.get("next");
			if(!left && !down)
				break;
		}
		System.out.println(i);
	}
	
	private Map<String,Object> nextStep(List<List<String>> m,String c) {
		Map<String,Object> r=new HashMap<>();
		boolean bNext=false;
		int x=0;
		int y=0;
		int rows=m.size();
		int cols=m.get(0).size();
		List<List<String>> nmap=copyList(m);
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++)
			{
				if(!c.equals(m.get(i).get(j)))
					continue;
				if (m.get(i).get(j).equals(">")) {
					x=i;
					y=(j+1) % cols;
				}else {
					x=(i+1) % rows;
					y=j;
				}
				if(m.get(x).get(y).equals(".")) {
					bNext=true;
					nmap.get(x).set(y, m.get(i).get(j));
					nmap.get(i).set(j, ".");
					
				}
			}
		r.put("newMap", nmap);
	    r.put("next", bNext);
		return r;
	}
	private List<List<String>> copyList(List<List<String>> m){
		List<List<String>> c=new ArrayList<>();
		for(int i=0;i<m.size();i++) {
			List<String> l=new ArrayList<>();
			for(int j=0;j<m.get(i).size();j++) {
				//c.get(i).set(j, m.get(i).get(j));
				l.add(m.get(i).get(j));
			}
			c.add(l);
		}
		return c;
	}
	public void getInput() throws IOException {

		File file = new File("src/main/resources/input25.txt");
		InputStream in = new FileInputStream(file);

		Reader reader = new InputStreamReader(in);
		BufferedReader bufferedReader = new BufferedReader(reader);

		String str = null;
		
		int r=0;
		while((str = bufferedReader.readLine()) != null)
		{
			if(StringUtils.isNotBlank(str)) {
				String[] temp=str.split("");
				List<String> line=new ArrayList<>();
				Collections.addAll(line,temp);
				this.maps.add(line);

			}
		}
		
		in.close();
		bufferedReader.close();	
	}
	
	public static void main(String args[]) throws IOException{
		
		long startTime = System.currentTimeMillis(); //get started
		
		day25 d =new day25();	
		d.getInput();
		
		d.firstOne();
		
		long endTime = System.currentTimeMillis(); //done
		System.out.println("running time: " + (endTime - startTime) + "ms"); //running time

    }
}
