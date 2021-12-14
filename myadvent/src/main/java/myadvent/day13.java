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

public class day13 {

	public List<String> folds=new ArrayList<String>(Arrays.asList("y=655","x=447","y=327","x=223","y=163","x=111","y=81","x=55","y=40","x=27","x=13","x=6"));
	public int[][] input=new int[895][1311];

	public int firstOne() {
		int[][] in = input;
		String fold=folds.get(0);

		String[] f=fold.split("=");
		switch(f[0]) {
    		case "x":
    			in= foldX(Integer.parseInt(f[1].trim()),in); 
    			break;
    		case "y":
    			in= foldY(Integer.parseInt(f[1].trim()),in); 
    			break;
	    }
		return getResult(in);
	}
	
	public void secondOne() {
		int[][] in = input;
		for(String fold:folds) {
			String[] f=fold.split("=");
	    	switch(f[0]) {
    		case "x":
    			in= foldX(Integer.parseInt(f[1].trim()),in); 
    			break;
    		case "y":
    			in= foldY(Integer.parseInt(f[1].trim()),in); 
    			break;
	    	}
		}
		drawResult(in);
	}
	
	private int[][] foldX(int x,int[][] in) {
		int xRow=in.length-1;
		int[][] temp=new int[x][in[0].length];
		for(int i=0;i<x;i++) {
			for(int j=0;j<in[i].length;j++) {
				temp[i][j]=in[i][j]+in[xRow-i][j];
			}
		}
		return temp;
	}

	private int[][] foldY(int y,int[][] in) {
		int yCol=in[0].length-1;
		int[][] temp=new int[in.length][y];
		for(int i=0;i<in.length;i++) {
			for(int j=0;j<y;j++) {
				temp[i][j]=in[i][j]+in[i][yCol-j];
			}
		}
		return temp;
	}

	private int getResult(int[][] in) {
		int r=0;
		for(int i=0;i<in.length;i++) {
			for(int j=0;j<in[i].length;j++) {
				if(in[i][j]>0)
					r+=1;
			}
		}
		return r;
	}
	
	private void drawResult(int[][] in) {
		String temp="";
		for(int i=0;i<in.length;i++) {
			temp="";
			for(int j=0;j<in[i].length;j++) {
				if((j+1)%5==0)
					temp=temp+" ";
				if(in[i][j]>0)
					temp=temp+"#";
				else
					temp=temp+".";

			}
			System.out.println(temp);
		}
	}
	
	public void getInput() throws IOException {
		File file = new File("src/main/resources/input13.txt");
		InputStream in = new FileInputStream(file);

		Reader reader = new InputStreamReader(in);
		BufferedReader bufferedReader = new BufferedReader(reader);

		String str = null;

		while((str = bufferedReader.readLine()) != null)
		{ 
			String[] sTemp=str.trim().split(",");
			input[Integer.parseInt(sTemp[1].trim())][Integer.parseInt(sTemp[0].trim())]=1;
		}
	
		in.close();
		bufferedReader.close();		
	}
	
	public static void main(String args[]) throws IOException{
		
		long startTime = System.currentTimeMillis(); //get started
		
		day13 d =new day13();	
		d.getInput();
		
		int r=d.firstOne();
		System.out.println(r);
		
		d.secondOne();
		
		long endTime = System.currentTimeMillis(); //done
		System.out.println("running time:" + (endTime - startTime) + "ms"); //running time

    }
}
