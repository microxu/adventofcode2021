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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class day4 {
	
	public String InputNum="31,88,35,24,46,48,95,42,18,43,71,32,92,62,97,63,50,2,60,58,74,66,15,87,57,34,14,3,54,93,75,22,45,10,56,12,83,30,8,76,1,78,82,39,98,37,19,26,81,64,55,41,16,4,72,5,52,80,84,67,21,86,23,91,0,68,36,13,44,20,69,40,90,96,27,77,38,49,94,47,9,65,28,59,79,6,29,61,53,11,17,73,99,25,89,51,7,33,85,70";
	//public String InputNum="7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1";
	public Map<String,String> resultMap = new HashMap<String,String>();
	
	public Map<String,List<String>> result = new HashMap<String,List<String>>();
	//public Map<String,List<String>> resultCO2 = new HashMap<String,List<String>>();
	public boolean setK(int[][] ii,int k) {
		for(int i=0;i<ii.length;i++) {
			for(int j=0;j<ii[i].length;j++) {
				if(ii[i][j]==k) {
					ii[i][j]=ii[i][j] * (-1);
				}
			}
		}
		boolean r =true;
		for(int i=0;i<ii.length;i++) {
			r=true;
			for(int j=0;j<ii[i].length;j++) {
				if(ii[i][j]>0) {
					r=false;
					break;
				}
			}
			if(r) {
				break;
			}
		}
		return r;
	}
	public int getSum(int[][] ii) {
		int r=0;
		for(int i=0;i<ii.length;i++) {
			for(int j=0;j<ii[i].length;j++) {
				if(ii[i][j]>0) {
					r=r+ii[i][j];
				}
			}
		}	
		return r;
	}
	
	public void setAll(List<int[][]> list,int k) {
		for(int i=0;i<list.size();i++) {
			for(int m=0;m<list.get(i).length;m++) {
				for(int n=0;n<list.get(i)[m].length;n++) {
					if(list.get(i)[m][n]==k) {
						list.get(i)[m][n]=list.get(i)[m][n] * (-1);
					}
				}
			}
		}
	}
	
	public int checkAndRemove(List<int[][]> list,int k) {
		boolean r =true;
		int rr=0;
		Iterator<int[][]> iterator = list.iterator();
        while (iterator.hasNext()) {
        	int[][] t = iterator.next();
			for(int m=0;m<t.length;m++) {
				r=true;
				for(int n=0;n<t[m].length;n++) {
					if(t[m][n]>0) {
						r=false;
						break;
					}
				}
				if(r) {
					break;
				}
			}
			if(r) {
				if(list.size()==1) {
					rr= k * getSum(t);
				}else {
					iterator.remove();
				}
			}
        }
		return rr;
	}
	public int checkAndRemove2(List<int[][]> list,int k) {
		boolean r =true;
		int rr=0;
		Iterator<int[][]> iterator = list.iterator();
        while (iterator.hasNext()) {
        	int[][] t = iterator.next();
			for(int m=0;m<t.length;m++) {
				r=true;
				for(int n=0;n<t[m].length;n++) {
					if(t[n][m]>0) {
						r=false;
						break;
					}
				}
				if(r) {
					break;
				}
			}
			if(r) {
				if(list.size()==1) {
					rr= k * getSum(t);
				}else {
					iterator.remove();
				}
			}
        }
		return rr;
	}
	public int secondOne(List<int[][]> list,int k) {

		int rr=0;
		this.setAll(list, k);
		rr= checkAndRemove(list,k);
		if(rr==0) {
			rr= checkAndRemove2(list,k);
		}
        return rr;	    
	}
	public int firstone(List<int[][]> list,int k) {
		boolean r;
		int rr=0;

	    for(int i=0;i<list.size();i++) {
           r= this.setK(list.get(i), k);
           if(r) {
        	   if(list.size()==1) {
        		   rr= k * getSum(list.get(i));
        	   }else {
        		   list.remove(i);
        	   //rr= k * getSum(list.get(i));
        		   break;
        	   }
           }
	    }
        return rr;	    
	}
	

    public static void main(String args[]) throws IOException{  
    	day4 d =new day4();
    	File file = new File("src/main/resources/input4.txt");
    	InputStream in = new FileInputStream(file);

    	Reader reader = new InputStreamReader(in);
    	BufferedReader bufferedReader = new BufferedReader(reader);
//
		String str = null;
		int j=0;
		int m=0;
		int a[][]=new int[5][5];
		List<int[][]> list = new ArrayList<int[][]>();
		while((str = bufferedReader.readLine()) != null)
		{ 
			 if(str.equals("")) {
				 list.add(a);
				 j=0;
				 m=0;
				 a = new int[5][5];
			 }else {				 
				 String t[]=str.split(" ");
				 for(int i =0;i<t.length;i++) {
					 if(StringUtils.isNotBlank(t[i])) {
						 a[j][m] = Integer.parseInt(t[i]);
						 m=m+1;
					 }				 
				 }
				 j=j+1;	
				 m=0;
			 }
			 	
		}
		String dd[] = d.InputNum.split(",");
		int f1  =-1;
		for (int i=0;i<dd.length;i++) {
			f1=d.secondOne(list, Integer.parseInt(dd[i]));
			if(f1>0) {
				System.out.println(f1);
				break;
			}
			
		}

		//close
		in.close();
		bufferedReader.close();
       }  
}
