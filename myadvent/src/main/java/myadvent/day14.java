package myadvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class day14 {

	Map<String,String> rules = new HashMap<String,String>();	
	Map<String,Long> occurs=new HashMap<String,Long>();

	public void firstOne() {
	
		String s="NNSOFOCNHBVVNOBSBHCB";
		Map<String,Long> p=new HashMap<String,Long>();
		for(int i=0;i<s.length()-1;i++) {
			p.put(s.substring(i,i+2), Long.valueOf(1));
		}
		
		occurs=new HashMap<String,Long>();
		occurs.put("N", Long.valueOf(1));
		occurs.put("B", Long.valueOf(1));

		for(int i=0;i<10;i++) {
			p=eachStep(p);

		}
		setOccurs(p);
		getResult();
	}

	public void secondOne() {
		
		String s="NNSOFOCNHBVVNOBSBHCB";
		Map<String,Long> p=new HashMap<String,Long>();
		for(int i=0;i<s.length()-1;i++) {
			p.put(s.substring(i,i+2), Long.valueOf(1));
		}
		
		occurs=new HashMap<String,Long>();
		occurs.put("N", Long.valueOf(1));
		occurs.put("B", Long.valueOf(1));

		for(int i=0;i<40;i++) {
			p=eachStep(p);

		}
		setOccurs(p);
		getResult();
	}
	

	private Map<String,Long> eachStep(Map<String,Long> p) {
		Map<String,Long> c=new HashMap<String,Long>();
		
		for (Map.Entry<String, Long> e : p.entrySet()) {
			String k=e.getKey();
			Long v=e.getValue();
			String rule=this.rules.get(k);
			if(rule!=null) {
				String[] t=k.split("");
				setMapValue(c,t[0]+rule,v);
				setMapValue(c,rule+t[1],v);
			}
		}
		return c;
	}
	
	private void setMapValue(Map<String,Long> m,String k,Long v) {
		Long value=m.get(k);
		if(value==null) {
			m.put(k, v);
		}else {
			m.put(k, value+v);
		}
	}
	private void setOccurs(Map<String,Long> p) {

		for (Map.Entry<String, Long> e : p.entrySet()) {
			String k=e.getKey();
			Long v=e.getValue();	
			String[] t=k.split("");
			setMapValue(occurs,t[0],v);
			setMapValue(occurs,t[1],v);		
		}
	}
	
	private void getResult() {
		List<Long> r =this.occurs.values().stream().map(m->m/2).collect(Collectors.toList());
		Long rr= Collections.max(r)  - Collections.min(r);
		System.out.println(rr);
	}
	
	public void getInput() throws IOException {
		File file = new File("src/main/resources/input14.txt");
		InputStream in = new FileInputStream(file);

		Reader reader = new InputStreamReader(in);
		BufferedReader bufferedReader = new BufferedReader(reader);

		String str = null;

		while((str = bufferedReader.readLine()) != null)
		{
			String[] sTemp=str.split("->");
			this.rules.put(sTemp[0].trim(), sTemp[1].trim());
		}
	
		in.close();
		bufferedReader.close();	

	}

	public static void main(String args[]) throws IOException{
		
		long startTime = System.currentTimeMillis(); //get started
		
		day14 d =new day14();	
		d.getInput();
		d.firstOne();
		d.secondOne();
		long endTime = System.currentTimeMillis(); //done
		System.out.println("running time: " + (endTime - startTime) + "ms"); //running time

    }
}
