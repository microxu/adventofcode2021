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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class day6 {
	public List<Long> halfResult =new ArrayList<Long>();
	public List<Long> result =new ArrayList<Long>();
	Map<Integer,Long> rMap = new HashMap<Integer,Long>();	
	public List<Integer> getMidList(int timer,int days){
		List<Integer> r =new ArrayList<Integer>();
		List<Integer> fish=new ArrayList<Integer>();
		fish.add(timer);
		for(int i=0;i<days;i++) {
			if(i==0) {
				r=this.oneDay(fish);
			}else {
				r=this.oneDay(r);
			}
		}
		return r;
	}
	
	public void getHalfNumbers(List<Integer> l, int days){
		
		for(int i=0;i<l.size();i++) {
			List<Integer> temp= this.getMidList(l.get(i), days);
			this.halfResult.add(Long.valueOf(temp.size()));
		}
	}
	
	public void getOneResult(List<Integer>l) {
		long r =0;
		for(int i=0;i<l.size();i++) {
			r=r+halfResult.get(l.get(i));
		}
		result.add(r);
	}
	
	private List<Integer> oneDay(List<Integer> l){
		List<Integer> r =new ArrayList<Integer>();
		int time8=0;
		for(int i=0;i<l.size();i++) {
			if(l.get(i)==0) {
				r.add(6);
				time8=time8+1;
			}else {
				r.add(l.get(i)-1);
			}
		}
		for(int i=0;i<time8;i++) {
			r.add(8);
		}
		return r;
	}
	private Map<Integer,Long> oneDay1( Map<Integer,Long> m){
		Map<Integer,Long> cMap = new HashMap<Integer,Long>();	
		cMap.put(0, m.get(0));
		cMap.put(1,  m.get(1));
		cMap.put(2, m.get(2));
		cMap.put(3,  m.get(3));
		cMap.put(4,  m.get(4));
		cMap.put(5,  m.get(5));
		cMap.put(6,  m.get(6));
		cMap.put(7,  m.get(7));
		cMap.put(8,  m.get(8));
		for (Integer key : m.keySet()) {
			long l=m.get(key);
			if(key==0) {
				cMap.put(6,Long.valueOf(cMap.get(6)+l));
				cMap.put(8,Long.valueOf(cMap.get(8)+l));
				cMap.put(0,Long.valueOf(cMap.get(0)-l));
			}else {	
				cMap.put(key-1,Long.valueOf(cMap.get(key-1)+l));
				cMap.put(key,Long.valueOf(cMap.get(key)-l));
			}		
		}
		return cMap;
	}
	
	public void otherMethod(int timer) {
		rMap = new HashMap<Integer,Long>();	
		rMap.put(0, Long.valueOf(0));
		rMap.put(1, Long.valueOf(0));
		rMap.put(2, Long.valueOf(0));
		rMap.put(3, Long.valueOf(0));
		rMap.put(4, Long.valueOf(0));
		rMap.put(5, Long.valueOf(0));
		rMap.put(6, Long.valueOf(0));
		rMap.put(7, Long.valueOf(0));
		rMap.put(8, Long.valueOf(0));
		rMap.put(timer, Long.valueOf(1));
		Map<Integer,Long> cMap = new HashMap<Integer,Long>();		
		for(int i=0;i<256;i++) {
			if(i==0) {
				cMap=this.oneDay1(rMap);
			}else
			{
				cMap=this.oneDay1(cMap);
			}
		}
		long r=0;
		for (Integer key : cMap.keySet()) {
			r=r+cMap.get(key);
		}
		result.add(r);
	}
	
	public static void main(String args[]) throws IOException{  

		File file = new File("src/main/resources/input6.txt");
		InputStream in = new FileInputStream(file);

		Reader reader = new InputStreamReader(in);
		BufferedReader bufferedReader = new BufferedReader(reader);

		String str = null;
		List<Integer> fish = new ArrayList<Integer>();

		while((str = bufferedReader.readLine()) != null)
		{ 
			String[] t=str.split(",");
			for(int i=0;i<t.length;i++) {
				fish.add(Integer.parseInt(t[i].trim()));
			}
		}
		in.close();
		bufferedReader.close();

		List<Integer> firstStep = Arrays.asList(1,2,3,4,5);
//		List<Integer> secondStep = Arrays.asList(0,1,2,3,4,5,6,7,8);
		
		long startTime = System.currentTimeMillis(); //获取开始时间		
		long rr=0;
		day6 d =new day6();		
//		d.getHalfNumbers(secondStep, 128);
//		
//		for(int i=0;i<firstStep.size();i++) {
//			List<Integer>temp=d.getMidList(firstStep.get(i),128);
//			d.getOneResult(temp);
//		}
//		
//		long rr=0;
//		for(int i=0;i<fish.size();i++){
//			rr=rr+d.result.get(fish.get(i)-1);
//		}
		for(int i=0;i<firstStep.size();i++) {
			d.otherMethod(firstStep.get(i));
		}
		for(int i=0;i<fish.size();i++){
			rr=rr+d.result.get(fish.get(i)-1);
	    }
        System.out.println(rr);
        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间

	}
}
