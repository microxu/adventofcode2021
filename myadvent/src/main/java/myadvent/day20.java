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
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class day20 {
	public int[] en=new int[512];
	public List<List<Integer>> input=new ArrayList<>();

	
	public void firstOne() {
		List<List<Integer>> c=new ArrayList<>();
		c=this.input;
		adjustInput(c,20);
		for(int i=0;i<2;i++)
			c=oneStep(c,i);
		
		int r=getlights(c);
		System.out.println(r);
	}

	public void secondOne() {
		List<List<Integer>> c=new ArrayList<>();
		c=this.input;
		adjustInput(c,120);
		
		for(int i=0;i<50;i++)
			c=oneStep(c,i);
		
		int r=getlights(c);
		System.out.println(r);
	}
	
	private List<List<Integer>> oneStep(List<List<Integer>> cInput,int times){
		List<List<Integer>> output=new ArrayList<>();
		for(int i=0;i<cInput.size();i++) {
			List<Integer> nl=new ArrayList<>();
			for(int j=0;j<cInput.get(0).size();j++) {
				String v1=getSourcePoints(i-1,j-1,cInput,times);
				String v2=getSourcePoints(i-1,j,cInput,times);
				String v3=getSourcePoints(i-1,j+1,cInput,times);
				String v4=getSourcePoints(i,j-1,cInput,times);
				String v5=getSourcePoints(i,j,cInput,times);
				String v6=getSourcePoints(i,j+1,cInput,times);
				String v7=getSourcePoints(i+1,j-1,cInput,times);
				String v8=getSourcePoints(i+1,j,cInput,times);
				String v9=getSourcePoints(i+1,j+1,cInput,times);
				String t=v1+v2+v3+v4+v5+v6+v7+v8+v9;

				int index=Integer.parseUnsignedInt(t,2);
				nl.add(en[index]);
				
			}
			output.add(nl);
		}
		return output;
	}
	
	private void adjustInput(List<List<Integer>> input,int scale) {
		for(int m=0;m<input.size();m++) {
			for(int i=0;i<scale;i++) {
				input.get(m).add(0, 0);
				input.get(m).add(input.get(m).size(),0);
			}
		}
		List<Integer> iTemp=new ArrayList<>();
		for(int i=0;i<input.get(0).size();i++) {
			iTemp.add(0);
		}
		for(int i=0;i<scale;i++) {
			input.add(0, iTemp);
			input.add(input.size(), iTemp);
		}		
	}
	
	private int getlights(List<List<Integer>> output) {

		int r=0;
		for(int i=0;i<output.size();i++)
			for(int j=0;j<output.get(i).size();j++) {
				if(output.get(i).get(j)==1)
					r+=1;
			}
				
		return r;
	}
	
	private String getSourcePoints(int x,int y,List<List<Integer>> input, int times) {
		  int i=times%2;
	      try{
	    	  return String.valueOf(input.get(x).get(y));
	       }catch(Exception e){
	    	   if(i==1)
	    		   return "1";
	    	   else
	    		   return "0";
	       }
	}
	
	public void getInput() throws IOException {

		File file = new File("src/main/resources/input20.txt");
		InputStream in = new FileInputStream(file);

		Reader reader = new InputStreamReader(in);
		BufferedReader bufferedReader = new BufferedReader(reader);

		String str = null;
		
		int r=0;
		while((str = bufferedReader.readLine()) != null)
		{
			if(StringUtils.isNotBlank(str)) {
				String[] temp=str.split("");
				if(r==0) {
					for(int i=0;i<temp.length;i++) {
						if(temp[i].equals("."))
							en[i]=0;
						else
							en[i]=1;
					}
				}else {
					List<Integer> iTemp=new ArrayList<>();
					for(int i=0;i<temp.length;i++) {
						if(temp[i].equals("."))
							iTemp.add(0);
						else
							iTemp.add(1);					
					
					}

					input.add(iTemp);
				}
				r+=1;
			}
		}
		
		in.close();
		bufferedReader.close();	
	}
	
	public static void main(String args[]) throws IOException{
		
		long startTime = System.currentTimeMillis(); //get started
		
		day20 d =new day20();	
		d.getInput();
		
		d.secondOne();
		
		long endTime = System.currentTimeMillis(); //done
		System.out.println("running time: " + (endTime - startTime) + "ms"); //running time

    }
	
}
