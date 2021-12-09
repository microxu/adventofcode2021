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
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class day5 {

       public int a[][]=new int[1000][1000];
	   public void firstone(String str) {
		 	String[] r=str.split("->");
		 	String[] f=r[0].split(",");
		 	String[] l=r[1].split(",");
		 	if( Integer.parseInt(f[0].trim())==Integer.parseInt(l[0].trim())) {
		 		int i=(Integer.parseInt(f[1].trim())>Integer.parseInt(l[1].trim()))?Integer.parseInt(l[1].trim()):Integer.parseInt(f[1].trim());
		 		int j=(Integer.parseInt(f[1].trim())>Integer.parseInt(l[1].trim()))?Integer.parseInt(f[1].trim()):Integer.parseInt(l[1].trim());
		 		for(int m=i;m<j+1;m++) {
		 			a[Integer.parseInt(f[0].trim())][m]=a[Integer.parseInt(f[0].trim())][m]+1;
		 		}
		 		
		 	}else if( Integer.parseInt(f[1].trim())==Integer.parseInt(l[1].trim())) {
		 		int i=(Integer.parseInt(f[0].trim())>Integer.parseInt(l[0].trim()))?Integer.parseInt(l[0].trim()):Integer.parseInt(f[0].trim());
		 		int j=(Integer.parseInt(f[0].trim())>Integer.parseInt(l[0].trim()))?Integer.parseInt(f[0].trim()):Integer.parseInt(l[0].trim());
		 		for(int m=i;m<j+1;m++) {
		 			a[m][Integer.parseInt(f[1].trim())]=a[m][Integer.parseInt(f[1].trim())]+1;
		 		}			 		
		 	}
	   }
	   
	   public void secondone(String str) {
		 	String[] r=str.split("->");
		 	String[] f=r[0].split(",");
		 	String[] l=r[1].split(",");
	 		int x0=Integer.parseInt(f[0].trim());
	 		int y0=Integer.parseInt(f[1].trim());
	 		int x1=Integer.parseInt(l[0].trim());
	 		int y1=Integer.parseInt(l[1].trim());
	 		int sx=0;
	 		int sy=0;
	 		int m=1;
	 		int h=0;
		 	if( x0==x1) {
		 		sx=x0;
		 		sy=y0;
		 		if(y0<y1) {
		 			m=1;
		 		}else {
		 			m=-1;
		 		}
 				h=Math.abs(y0-y1);

		 		for(int mm=0;mm<h+1;mm++) {
		 			a[sx][sy+mm*m]=a[sx][sy+mm*m]+1;
		 		}
		 		
		 	}else if( y0==y1) {
		 		sy=y0;
		 		sx=x0;
		 		h=Math.abs(x0-x1);
		 		if(x0<x1) {
		 			m=1;
		 		}else {
		 			m=-1;
		 		}	 		
		 		for(int mm=0;mm<h+1;mm++) {
		 			a[sx+mm*m][sy]=a[sx+mm*m][sy]+1;
		 		}			 		
		 	}else  {
		 		if(Math.abs(x0-x1)==Math.abs(y0-y1)) {
		 			if((x0<x1 && y0<y1) || (x0>x1 && y0>y1)) {
		 				m=1;
		 				if(x0<x1) {
		 					sx=x0;
		 					sy=y0;
		 				}else {
		 					sx=x1;
		 					sy=y1;
		 				}
		 				h=Math.abs(x0-x1);
				 		for(int mm=0;mm<h+1;mm++) {
				 			a[sx+mm][sy+mm*m]=a[sx+mm][sy+mm*m]+1;
				 		}	
		 			}else {
		 				m=-1;
		 				if(x0<x1) {
		 					sx=x0;
		 					sy=y0;
		 				}else {
		 					sx=x1;
		 					sy=y1;
		 				}
		 				h=Math.abs(x0-x1);
				 		for(int mm=0;mm<h+1;mm++) {
				 			a[sx+mm][sy+mm*m]=a[sx+mm][sy+mm*m]+1;
				 		}	 				
		 			}
		 		}
		 		
		 	}
	   }
	   public static void main(String args[]) throws IOException{  
	    	day5 d =new day5();
	    	File file = new File("src/main/resources/input5.txt");
	    	InputStream in = new FileInputStream(file);

	    	Reader reader = new InputStreamReader(in);
	    	BufferedReader bufferedReader = new BufferedReader(reader);
	
			String str = null;

			while((str = bufferedReader.readLine()) != null)
			{ 
				d.secondone(str);
			}
			int r=0;
			for (int i=0;i<d.a.length;i++) {
				for(int j=0;j<d.a[i].length;j++) {
					if(d.a[i][j]>1) {
						r=r+1;
					}
				}
			}
	        System.out.println (r);
//	        for (int i=0;i<d.a.length;i++)
//	            System.out.println ( Arrays.toString (d.a[i]));
			//close
			in.close();
			bufferedReader.close();
	       }  
}
