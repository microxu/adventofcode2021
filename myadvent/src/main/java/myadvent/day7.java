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
import java.util.List;
import java.util.Map;

public class day7 {

	List<Integer> total = new ArrayList<Integer>();
	Map<Integer,Integer> rMap = new HashMap<Integer,Integer>();	
	public int firstOne(List<Integer>l,int p) {
		int r=0;
		for(int i=0;i<l.size();i++) {
			r=r+Math.abs(l.get(i)-p);
		}
		return r;
	}
	
	public int getSteps(int steps) {
		int r=0;
		if(steps==0)
			return r;
		if(this.rMap.get(steps)!=null) {
			r= this.rMap.get(steps);
		}else {
			for(int i=1;i<=steps;i++) {
				r=r+i;
			}
			this.rMap.put(steps, r);
		}
		return r;
	}
	
	public int secondOne(List<Integer>l,int p) {
		int r=0;
		for(int i=0;i<l.size();i++) {
			r=r+this.getSteps(Math.abs(l.get(i)-p));
		}
		return r;
	}
	
	public void makeADictionary(int steps) {
		this.rMap.put(1, 1);
		for(int i=2;i<=steps;i++) {
			this.rMap.put(i, rMap.get(i-1)+i);
		}
		
	}
	public static void main(String args[]) throws IOException{  

		File file = new File("src/main/resources/input7.txt");
		InputStream in = new FileInputStream(file);

		Reader reader = new InputStreamReader(in);
		BufferedReader bufferedReader = new BufferedReader(reader);

		String str = null;
		List<Integer> crab = new ArrayList<Integer>();

		while((str = bufferedReader.readLine()) != null)
		{ 
			String[] t=str.split(",");
			for(int i=0;i<t.length;i++) {
				crab.add(Integer.parseInt(t[i].trim()));
			}
		}
		in.close();
		bufferedReader.close();
		long startTime = System.currentTimeMillis(); //获取开始时间	
		day7 d =new day7();	
		d.makeADictionary(1200);
		for(int i=1;i<1200;i++) {
			int r=d.secondOne(crab, i);
			d.total.add(r);
		}
		//System.out.println(Arrays.toString(d.total.toArray()));
		 System.out.println("最小值: " + Collections.min(d.total));
		 
		// System.out.println((new Double(rr)).longValue());
	     long endTime = System.currentTimeMillis(); //获取结束时间
	     System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间
	}

	
}
