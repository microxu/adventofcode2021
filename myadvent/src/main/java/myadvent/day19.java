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
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class day19 {
	public List<List<String>> orientations=new ArrayList<List<String>>();
	public List<Map<String,List<Integer>>> input=new ArrayList<>();
	public Set<String> beacons = new HashSet<>();
	public List<List<Integer>> sum=new ArrayList<>();
	
	public day19() {
		this.orientations.add(new ArrayList<>(Arrays.asList("x","y","z")));
		this.orientations.add(new ArrayList<>(Arrays.asList("x","-y","-z")));
		this.orientations.add(new ArrayList<>(Arrays.asList("x","-z","y")));
		this.orientations.add(new ArrayList<>(Arrays.asList("x","z","-y")));

		this.orientations.add(new ArrayList<>(Arrays.asList("-x","-y","z")));
		this.orientations.add(new ArrayList<>(Arrays.asList("-x","y","-z")));
		this.orientations.add(new ArrayList<>(Arrays.asList("-x","z","y")));
		this.orientations.add(new ArrayList<>(Arrays.asList("-x","-z","-y")));

		this.orientations.add(new ArrayList<>(Arrays.asList("y","-x","z")));
		this.orientations.add(new ArrayList<>(Arrays.asList("y","x","-z")));
		this.orientations.add(new ArrayList<>(Arrays.asList("y","z","x")));
		this.orientations.add(new ArrayList<>(Arrays.asList("y","-z","-x")));

		this.orientations.add(new ArrayList<>(Arrays.asList("-y","x","z")));
		this.orientations.add(new ArrayList<>(Arrays.asList("-y","-x","-z")));
		this.orientations.add(new ArrayList<>(Arrays.asList("-y","-z","x")));
		this.orientations.add(new ArrayList<>(Arrays.asList("-y","z","-x")));

		this.orientations.add(new ArrayList<>(Arrays.asList("z","-y","x")));
		this.orientations.add(new ArrayList<>(Arrays.asList("z","y","-x")));
		this.orientations.add(new ArrayList<>(Arrays.asList("z","x","y")));
		this.orientations.add(new ArrayList<>(Arrays.asList("z","-x","-y")));

		this.orientations.add(new ArrayList<>(Arrays.asList("-z","y","x")));
		this.orientations.add(new ArrayList<>(Arrays.asList("-z","-y","-x")));
		this.orientations.add(new ArrayList<>(Arrays.asList("-z","-x","y")));
		this.orientations.add(new ArrayList<>(Arrays.asList("-z","x","-y")));
	}
	
	public void firstOne() {
		List<List<Integer>> t= adjustBeaconsByorientation(this.orientations.get(0),input.get(0));
		List<List<List<Integer>>> tNow=new ArrayList<>();
		tNow.add(t);
		putBeaconsToset(t);
		input.remove(0);
		 boolean gbool=false;

		 int i=0;
		 while(input.size()>0) {
			 gbool=false;

		     Map<String,List<Integer>> bs=input.get(i);
			 for(List<List<Integer>> n:tNow) {
				 for(List<String> model: this.orientations) {
					 List<List<Integer>> c = adjustBeaconsByorientation(model,bs);
					 if(findpairs(n,c)) {
						 putBeaconsToset(c);
						 tNow.add(c);
						 input.remove(i);
						 i=i-1;
						 gbool=true;
						 break;
					 }
				 }
				 if(gbool) {
					 break;
				 }
			 }
			 if(i==input.size() -1 &&input.size()>0)
				 i=0;
			 else 
				 i+=1;

	     }
		 System.out.println(this.beacons.size());
		 List<Integer> result=new ArrayList<>();
		 for(int j=0;j<this.sum.size()-1;j++) {
			 for(int m=j+1;m<this.sum.size();m++) {
				 
					 result.add(Math.abs(sum.get(j).get(0)-sum.get(m).get(0))+ Math.abs(sum.get(j).get(1)-sum.get(m).get(1)) + Math.abs(sum.get(j).get(2)-sum.get(m).get(2)));
					 
				 
			 }
		 }
		 Collections.sort(result,Collections.reverseOrder());
		 System.out.println(result.get(0));
	}
	
	private boolean findpairs(List<List<Integer>> s1,List<List<Integer>>s2){
		List<Integer> r=new ArrayList<Integer>();
		Map<String,Integer> diff=new HashMap<>();
		for(int i=0;i<s1.size();i++) {
			for(int j=0;j<s2.size();j++) {
				int xd=s1.get(i).get(0)-s2.get(j).get(0);
				int yd=s1.get(i).get(1)-s2.get(j).get(1);
				int zd=s1.get(i).get(2)-s2.get(j).get(2);
				String kd=String.valueOf(xd)+","+String.valueOf(yd)+","+String.valueOf(zd);
				if(diff.get(kd)==null)
					diff.put(kd, 1);
				else {
					diff.put(kd, diff.get(kd)+1);
				}
			}
		}
		for (Map.Entry<String, Integer> entry : diff.entrySet()) {
			if(entry.getValue()>=12) {
				String[] sum= entry.getKey().split(",");
				this.sum.add(new ArrayList<>(Arrays.asList(Integer.valueOf(sum[0]),Integer.valueOf(sum[1]),Integer.valueOf(sum[2]))));
				adjustBeacons(entry.getKey(),s2);
				return true;
			}
		}
        
		return false;
	}
	
	private List<List<Integer>> adjustBeaconsByorientation(List<String> o,Map<String,List<Integer>> m) {
		int len=m.get("x").size();
		List<List<Integer>>s =new ArrayList<>();
		int x=0;
		int y=0;
		int z=0;
		for(int i=0;i<len;i++) {
			if(o.get(0).contains("-"))
				x=m.get(o.get(0).substring(1)).get(i)* (-1);
			else 
				x=m.get(o.get(0)).get(i);
			if(o.get(1).contains("-"))
				y=m.get(o.get(1).substring(1)).get(i)* (-1);
			else 
				y=m.get(o.get(1)).get(i);
			if(o.get(2).contains("-"))
				z=m.get(o.get(2).substring(1)).get(i)* (-1);
			else 
				z=m.get(o.get(2)).get(i);
			s.add(Arrays.asList(x,y,z));
		}
		return s;
	}
	
	private void putBeaconsToset(List<List<Integer>> l) {
		for(List<Integer> b:l) {
			beacons.add(b.get(0)+","+b.get(1)+","+b.get(2));
		}
	}
	
	private void adjustBeacons(String d,List<List<Integer>> s) {
		String[] t=d.split(",");
		for(List<Integer> l:s) {
			l.set(0, l.get(0)+Integer.valueOf(t[0]));
			l.set(1, l.get(1)+Integer.valueOf(t[1]));
			l.set(2, l.get(2)+Integer.valueOf(t[2]));
		}
	}
	public void getInput() throws IOException {
		
		File file = new File("src/main/resources/input19.txt");
		InputStream in = new FileInputStream(file);

		Reader reader = new InputStreamReader(in);
		BufferedReader bufferedReader = new BufferedReader(reader);

		String str = null;

		List<Integer> x =new ArrayList<>();
		List<Integer> y =new ArrayList<>();
		List<Integer> z =new ArrayList<>();	
		Map<String,List<Integer>> scanner=new HashMap<>();
		
		
		while((str = bufferedReader.readLine()) != null)
		{
			if(StringUtils.isNotBlank(str)) {
			if(str.contains("scanner")) {
				scanner.put("x", x);
				scanner.put("y", y);
				scanner.put("z", z);
				input.add(scanner);
				
				x =new ArrayList<>();
				y =new ArrayList<>();
				z =new ArrayList<>();	
				scanner=new HashMap<>();
				continue;
			}else {
				String[] temp=str.split(",");
				x.add(Integer.valueOf(temp[0].trim()));
				y.add(Integer.valueOf(temp[1].trim()));
				z.add(Integer.valueOf(temp[2].trim()));
			}
			}
		}
	
		in.close();
		bufferedReader.close();	

	}
	
	public static void main(String args[]) throws IOException{
		
		long startTime = System.currentTimeMillis(); //get started
		
		day19 d =new day19();	
		d.getInput();
		
		d.firstOne();
		
		long endTime = System.currentTimeMillis(); //done
		System.out.println("running time: " + (endTime - startTime) + "ms"); //running time

    }
}
