package myadvent;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class day24 {

	Map<String,Integer> num=new HashMap<>();
	List<String> ops=new ArrayList<>();
	
	int[] up = new int[] {15, 12, 15, -1, -1, 2, -1, -1, 10, -1, 0, 0, -1, -1};
	int[] down = new int[] {-1, -1, -1, 9, 7, -1, 1, 16, -1, 15, -1, -1, 4, 0};

	
	public int[] search(int[] numbers) {
		int z=0;
		int[] zz=new int[14];
		int j=0;
		for(int i=0;i<14;i++) {
			if(up[i]==-1) {
				zz[i]=(z%26) - down[i];
				z=(int) Math.floorDiv(z, 26);
				if(zz[i]<1 || zz[i]>9)
					return null;
			}else {
				z=z*26+numbers[j] + up[i];
				zz[i]=numbers[j];
				j+=1;
			}
		}
		return zz;
	}
	
	private void firstOne() {
		int[] number;
	     for(int a=9;a>=1;a--)
		  for(int b=9;b>=1;b--)
		   for(int c=9;c>=1;c--)
		    for(int d=9;d>=1;d--)
			 for(int e=9;e>=1;e--)
			  for(int f=9;f>=1;f--)
			   for(int g=9;g>=1;g--) {
				   number= new int[] {a,b,c,d,e,f,g};
				   int[] r= search(number);
				   if(r!=null) {
					   System.out.println(Arrays.toString(r));
					   return;
				   }
			   }
	}

	private void secondOne() {
		int[] number;
	     for(int a=1;a<10;a++)
		  for(int b=1;b<10;b++)
		   for(int c=1;c<10;c++)
		    for(int d=1;d<10;d++)
			 for(int e=1;e<10;e++)
			  for(int f=1;f<10;f++)
			   for(int g=1;g<10;g++) {
				   number= new int[] {a,b,c,d,e,f,g};
				   int[] r= search(number);
				   if(r!=null) {
					   System.out.println(Arrays.toString(r));
					   return;
				   }
			   }
	}
	
	public static void main(String args[]) throws IOException{
		
		long startTime = System.currentTimeMillis(); //get started
		System.out.println(startTime); //running time
		day24 d =new day24();	

		d.firstOne();
		d.secondOne();
		long endTime = System.currentTimeMillis(); //done
		System.out.println("running time: " + (endTime - startTime) + "ms"); //running time

    }
	
}
