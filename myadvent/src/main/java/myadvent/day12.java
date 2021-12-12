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
import java.util.List;
import java.util.Map;
import java.util.Set;

public class day12 {
	public Map<String,List<String>> nodes = new HashMap<String,List<String>>();
	
	public List<List<String>> routes=new ArrayList<List<String>>();

	public void firstOne() {
		List<String> route=new ArrayList<String>();
		route.add("START");
		getChildNotesFor1("start",route);
	}
	
	public void secondOne() {
		List<String> route=new ArrayList<String>();
		route.add("START");
		getChildNotesFor2("start",route);
	}
	
	private void getChildNotesFor1(String node,List<String> route){
		
		for(String n:this.nodes.get(node)) {
			if(n.equals("end")) {
				route.add("END");
				this.routes.add(route);

			}else if(!alreadyHas(route,n)){
				List<String> t=copyList(route);
				t.add(n);
				getChildNotesFor1(n,t);				
			}
		}
	}
	
	private void getChildNotesFor2(String node,List<String> route){
		
		for(String n:this.nodes.get(node)) {
			if(n.equals("end")) {
				route.add("END");
				this.routes.add(route);

			}else if(canVisit(route,n)){
				List<String> t=copyList(route);
				t.add(n);
				getChildNotesFor2(n,t);				
			}
		}
	}
	private List<String> copyList(List<String> l){
		List<String> n=new ArrayList<String>();
		for(String s:l)
			n.add(s);
		return n;
	}
	
	private boolean canVisit(List<String>r, String n) {
		if(isLowStr(n)) {
			Set<String> set = new HashSet<>();
			Set<String> exist = new HashSet<>();
			for(String s:r) {
				if(isLowStr(s)) {
		            if (set.contains(s)) {
		                exist.add(s);
		            } else {
		                set.add(s);
		            }
				}	
			}
			if(exist.contains(n))
				return false;
			else if(set.contains(n) && exist.size()>0)
				return false;
		}
		return true;
	}
	
	private boolean alreadyHas(List<String> r,String n) {
		if(isLowStr(n)) {
			return r.stream().anyMatch(s->s.equals(n));		
		}
		return false;
	}
	
	private boolean isLowStr(String word) {		
		for(int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (Character.isUpperCase(c)) {
				return false;
				}
			}
		return true;
	}

	public void getInput() throws IOException {
		File file = new File("src/main/resources/input12.txt");
		InputStream in = new FileInputStream(file);

		Reader reader = new InputStreamReader(in);
		BufferedReader bufferedReader = new BufferedReader(reader);

		String str = null;

		while((str = bufferedReader.readLine()) != null)
		{ 
			String[] sTemp=str.trim().split("-");
			String start=sTemp[0].trim();
			String end=sTemp[1].trim();
			
			if(sTemp[1].trim().equals("start")||sTemp[0].trim().equals("end")) {
				start=sTemp[1].trim();
				end=sTemp[0].trim();			
			}
			
			if(this.nodes.get(start)!=null) {					
				this.nodes.get(start).add(end);
			}else {
				List<String> n=new ArrayList<String>();
				n.add(end);
				this.nodes.put(start, n);
			}
			if(!end.equals("end") && ! start.equals("start")) {
				if(this.nodes.get(end)!=null) {					
					this.nodes.get(end).add(start);
				}else {
					List<String> n=new ArrayList<String>();
					n.add(start);
					this.nodes.put(end, n);
				}
			}
		}
	
		in.close();
		bufferedReader.close();		
	}

	public static void main(String args[]) throws IOException{
		
		long startTime = System.currentTimeMillis(); //get started
		
		day12 d =new day12();	
		d.getInput();
		d.firstOne();
		System.out.println(d.routes.size());
		
		d.routes=new ArrayList<List<String>>();
		d.secondOne();
		System.out.println(d.routes.size());
		
		long endTime = System.currentTimeMillis(); //done
		System.out.println("running time:" + (endTime - startTime) + "ms"); //running time

    }
}
