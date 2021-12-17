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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class day17 {
    List<Integer> yResult=new  ArrayList<Integer>();
    List<int[]>  xynResult=new ArrayList<int[]>();
    Set<String> xyResult=new HashSet<String>();
    
    public void firstOne() {
    	int n=1;
    	int x=0;
    	int y=0;
    	for(n=1;n<400;n++) {
    		for(x=-400;x<400;x++) {
    			for(y=-400;y<400;y++) {

    				int yvalue=yValue(y,n);
    				int xvalue=xValue(x,n);
    				if(xvalue>=94 && xvalue<=151 && yvalue>=-156 && yvalue<=-103) {
    				//if(xvalue>=20 && xvalue<=30 && yvalue>=-10 && yvalue<=-5) {
    					yResult.add(y);
    					xynResult.add(new int[] {x,y,n});
    					xyResult.add((String.valueOf(x)+","+String.valueOf(y)));
    				}
    				}

    		}
    	}
    	//int yMax=Collections.max(yResult);
    	System.out.println(xyResult.size());
//    	System.out.println(xynResult.size());
//    	for(int i=0;i<xynResult.size();i++)
//    		System.out.println(Arrays.toString(xynResult.get(i)));
    	
    }
	public int yValue(int y, int n) {
		int r=0;
		for(int i=0;i<n;i++) {
			
			r=r+y;
			y=y-1;
		}
		return r;
	}
	
	public int xValue(int x, int n) {
		int r=0;
		for(int i=0;i<n;i++) {
			r=r+x;
			if(x==0)
				x=0;
			else
				if(x>0)
					x=x-1;
				else
					x=x+1;
			
		}
		return r;
	}
	
	public void getInput() throws IOException {
		
		File file = new File("src/main/resources/input17.txt");
		InputStream in = new FileInputStream(file);

		Reader reader = new InputStreamReader(in);
		BufferedReader bufferedReader = new BufferedReader(reader);

		String str = null;

		while((str = bufferedReader.readLine()) != null)
		{
			String[] sTemp=str.split("");

		}
	
		in.close();
		bufferedReader.close();	

	}
	
	public static void main(String args[]) throws IOException{
		
		long startTime = System.currentTimeMillis(); //get started
		
		day17 d =new day17();	
		//d.getInput();

        d.firstOne();

		long endTime = System.currentTimeMillis(); //done
		System.out.println("running time: " + (endTime - startTime) + "ms"); //running time

    }
}
