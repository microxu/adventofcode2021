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
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class day18 {

	List<String> sInput =new ArrayList<String>();
	List<Integer> result=new ArrayList<Integer>();
	
	public void firstOne() {
		LinkedList<String> list = new LinkedList<>();
		copyStrToList(sInput.get(0),list);
		for(int i=1;i<sInput.size();i++) {
			copyStrToList(sInput.get(i),list);
			list.addFirst("[");
			list.addLast("]");
			reduceNum(list);
		}
		
		caculateList(list);
		System.out.println(list);
	}

	public void secondOne() {
		for(int i=0;i<sInput.size();i++) {
			for(int j=0;j<sInput.size();j++) {
				if(i!=j) {
					//only use one linkedlist can improve performance
					LinkedList<String> nlist = new LinkedList<>();
					copy2StrToList(sInput.get(i),sInput.get(j),nlist);
					reduceNum(nlist);	
					caculateList(nlist);
					for(int m=0;m<nlist.size();m++) {
						if(!nlist.get(m).equals("[") && !nlist.get(m).equals("]"))
							result.add(Integer.valueOf(nlist.get(m)));
					}
				}
			}

		}
		System.out.println(Collections.max(result));
	}
	
	private void reduceNum(LinkedList<String> list) {
		int brackets=0;
		for(int i=0;i<list.size();i++) {
			if(list.get(i).equals("["))
				brackets+=1;
			else if(list.get(i).equals("]"))
				brackets-=1;
			
			if(brackets>=5 && StringUtils.isNumeric(list.get(i+1)) && StringUtils.isNumeric(list.get(i+2)) ){
				explodeList(list,i);
				i=-1;
				brackets=0;
				continue;
			}
			if(i==list.size()-1) {
				boolean r=splitList(list);
				if(r) {
					i=-1;
					brackets=0;
					continue;
				}
			}
			
		}
		
	}
	
	private void explodeList(LinkedList<String> list ,int i) {
		int f=Integer.valueOf(list.get(i+1));
		int l=Integer.valueOf(list.get(i+2));
		int[] left=findleft(list,i);
		int[] right=findright(list,i);
		if(left[1]!=-1)
			list.set(left[0], String.valueOf(left[1]+f));
		if(right[1]!=-1)
			list.set(right[0], String.valueOf(right[1]+l));
		list.remove(i);
		list.remove(i);
		list.remove(i);
		list.remove(i);
		list.add(i, "0");		
	}
	
	private void caculateList(LinkedList<String> list) {
		for(int i=0;i<list.size();i++) {
			String t=list.get(i);

			if(t.equals("[")) {
				String f=list.get(i+1);
				String l=list.get(i+2);			
				if(!f.equals("[") && !f.equals("]") && !l.equals("[") && !l.equals("]")) {
					int v=3* Integer.valueOf(f) + 2 * Integer.valueOf(l);
					list.remove(i);
					list.remove(i);
					list.remove(i);
					list.remove(i);
					list.add(i, String.valueOf(v));
					i=-1;
					continue;
				}
			}
			
		}
	}
	private boolean splitList(LinkedList<String> list) {
		for(int i=0;i<list.size();i++) {
			String t=list.get(i);
			if(!t.equals("[") && !t.equals("]") && Integer.valueOf(t)>=10){
				int n=Integer.valueOf(t);
				int left=(int) Math.floor((double)n/2);
				int right=(int) Math.ceil((double)n/2);
				list.remove(i);
				
				list.add(i,"[");
				list.add(i+1,String.valueOf(left));
				list.add(i+2,String.valueOf(right));
				list.add(i+3,"]");
				return true;
			}
		}
		return false;
	}
	private int[] findleft(LinkedList<String> list , int index) {
		int[] r=new int[] {0,-1};
		for(int i=index;i>=0;i--) {
			if(StringUtils.isNumeric(list.get(i)) ){
				return new int[] {i,Integer.valueOf(list.get(i))};
			}
		}
		return r;		
	}

	private int[] findright(LinkedList<String> list , int index) {
		int[] r=new int[] {0,-1};
		for(int i=index+3;i<list.size();i++) {
			if(StringUtils.isNumeric(list.get(i)) ){
				return new int[] {i,Integer.valueOf(list.get(i))};
			}
		}
		return r;		
	}
	
	private void copyStrToList(String s,LinkedList<String> list) {			
		for(int j=0;j<s.length();j++) {
			String t=s.substring(j, j+1);
			if(!t.equals(","))
				list.add(t);
		}
	}

	private void copy2StrToList(String s1,String s2,LinkedList<String> nlist) {
		for(int j=0;j<s1.length();j++) {
			String t=s1.substring(j, j+1);
			if(!t.equals(","))
				nlist.add(t);
		}
		for(int j=0;j<s2.length();j++) {
			String t=s2.substring(j, j+1);
			if(!t.equals(","))
				nlist.add(t);
		}
		nlist.addFirst("[");
		nlist.addLast("]");
	}
	
	public void getInput() throws IOException {
		
		File file = new File("src/main/resources/input18.txt");
		InputStream in = new FileInputStream(file);

		Reader reader = new InputStreamReader(in);
		BufferedReader bufferedReader = new BufferedReader(reader);

		String str = null;

		while((str = bufferedReader.readLine()) != null)
		{
			sInput.add(str.trim());
		}
	
		in.close();
		bufferedReader.close();	

	}
	
	public static void main(String args[]) throws IOException{
		
		long startTime = System.currentTimeMillis(); //get started
		
		day18 d =new day18();	
		d.getInput();
		d.secondOne();
		long endTime = System.currentTimeMillis(); //done
		System.out.println("running time: " + (endTime - startTime) + "ms"); //running time

    }
}
