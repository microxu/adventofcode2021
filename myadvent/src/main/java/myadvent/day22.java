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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class day22 {
	public List<String> lines=new ArrayList<>();
	public Map<String,Boolean> lights=new HashMap<>();
	public void firstOne() {
		for(String line : this.lines) {
			boolean bOn=false;
			if(line.contains("on")) {
				bOn=true;
			}
			List<String> rangs=findIntegers(line);
			int x1=Integer.valueOf(rangs.get(0));
			int x2=Integer.valueOf(rangs.get(1));
			int y1=Integer.valueOf(rangs.get(2));
			int y2=Integer.valueOf(rangs.get(3));
			int z1=Integer.valueOf(rangs.get(4));
			int z2=Integer.valueOf(rangs.get(5));
			
			//System.out.printf("x1=%d, x2=%d,y1=%d,y2=%d,z1=%d,z2=%d\n",x1,x2,y1,y2,z1,z2);

			
			if(x1<=50 && x1>=-50 && x2<=50 && x2>=-50 && y1<=50 && y1>=-50 && y2<=50 && y2>=-50 && z1<=50 && z1>=-50 && z2<=50 && z2>=-50) {
				for(int i=x1;i<=x2;i++)
					for(int j=y1;j<=y2;j++)
						for(int m=z1;m<=z2;m++) {
							String k=String.valueOf(i)+","+String.valueOf(j)+","+String.valueOf(m);
							lights.put(k, bOn);
						}
			}
		}
		
		int r=0;
		for(boolean v:lights.values()) {
			if(v)
				r+=1;
		}
		System.out.println(r);
	}
   public void secondOne() {
	   List<Step> steps = new ArrayList<>();
	   List<Integer> X=new ArrayList<>();
	   List<Integer> Y =new ArrayList<>();
	   List<Integer> Z = new ArrayList<>();
		for(String line : this.lines) {
			int type=0;
			if(line.contains("on")) {
				type=1;
			}
			
			List<String> rangs=findIntegers(line);
			int x0=Integer.valueOf(rangs.get(0));
			int x1=Integer.valueOf(rangs.get(1));
			int y0=Integer.valueOf(rangs.get(2));
			int y1=Integer.valueOf(rangs.get(3));
			int z0=Integer.valueOf(rangs.get(4));
			int z1=Integer.valueOf(rangs.get(5));
			//System.out.printf("x1=%d, x2=%d,y1=%d,y2=%d,z1=%d,z2=%d\n",x0,x1,y0,y1,z0,z1);
			steps.add(new Step(type,x0,x1+1,y0,y1+1,z0,z1+1));
			X.add(x0);
			X.add(x1+1);
			Y.add(y0);
			Y.add(y1+1);
			Z.add(z0);
			Z.add(z1+1);		
		}
		 Collections.sort(X);
		 Collections.sort(Y);
		 Collections.sort(Z);
		 int len=X.size();
		 int[][][] points=new int[len][len][len];
		 
		 
		 for(Step s:steps) {
			 int x0=lower_bound(X,s.x0);
			 int x1=lower_bound(X,s.x1);
			 int y0=lower_bound(Y,s.y0);
			 int y1=lower_bound(Y,s.y1);
			 int z0=lower_bound(Z,s.z0);
			 int z1=lower_bound(Z,s.z1);
			 int v=s.type;
			 for(int x=x0;x<x1;x++)
				 for(int y=y0;y<y1;y++)
					 for(int z=z0;z<z1;z++)
						 points[x][y][z]=v;						 
		 }
		 long sum=0;
		 for(int x=0;x<len-1;x++)
			 for(int y=0;y<len-1;y++)
				 for(int z=0;z<len-1;z++)
					 sum += Long.valueOf(points[x][y][z]) *  (X.get(x+1)-X.get(x)) * (Y.get(y+1)-Y.get(y)) * (Z.get(z+1)-Z.get(z));
		 
		 System.out.println(sum);
   }
   
   class Step{
	   public int type;
	   public int x0,x1,y0,y1,z0,z1;
	   public Step(int t,int x0,int x1,int y0,int y1,int z0,int z1) {
		   type=t;
		   this.x0=x0;
		   this.x1=x1;
		   this.y0=y0;
		   this.y1=y1;
		   this.z0=z0;
		   this.z1=z1;
		   
	   }
   }


   public int lower_bound(List<Integer> a,int x){

		   int l=-1,r=a.size();
		   while(l+1<r) {
		     int m=(l+r)>>>1;
		     if(a.get(m)>=x) r=m;
		     else l=m;
		   }
		   return r;

   }

	private List<String> findIntegers(String stringToSearch) {
	    Pattern integerPattern = Pattern.compile("-?\\d+");
	    Matcher matcher = integerPattern.matcher(stringToSearch);

	    List<String> integerList = new ArrayList<>();
	    while (matcher.find()) {
	        integerList.add(matcher.group());
	    }

	    return integerList;
	}
	
	
	public void getInput() throws IOException {

		File file = new File("src/main/resources/input22.txt");
		InputStream in = new FileInputStream(file);

		Reader reader = new InputStreamReader(in);
		BufferedReader bufferedReader = new BufferedReader(reader);

		String str = null;

		while((str = bufferedReader.readLine()) != null)
		{
			if(StringUtils.isNotBlank(str)) {
				lines.add(str.trim());
			}
		}
		
		in.close();
		bufferedReader.close();	
		
	}
	
	public static void main(String args[]) throws IOException{
		
		long startTime = System.currentTimeMillis(); //get started
		System.out.println(startTime); //running time
		day22 d =new day22();	
		d.getInput();

		d.firstOne();

		d.secondOne();

		long endTime = System.currentTimeMillis(); //done
		System.out.println("running time: " + (endTime - startTime) + "ms"); //running time

    }
}
