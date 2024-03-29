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
import java.util.List;
import java.util.Map;

public class day15 {

	List<int[]> points= new ArrayList<int[]>();
	List<int[]> points2= new ArrayList<int[]>();
	int[][] pointsWeight;
	int row;
	int col;
	
	public void firstOne() {

		Map<String,Integer> cRouts = new HashMap<String,Integer>();
		cRouts.put("0,0", 1);
		pointsWeight[0][0]=1;
		while(cRouts.size()>0) {
			cRouts=getChildNotes(cRouts);
		}
		System.out.println(pointsWeight[row-1][col-1]-1);

	}

	public void secondOne() {
		points=points2;
		Map<String,Integer> cRouts = new HashMap<String,Integer>();
		cRouts.put("0,0", 1);
		pointsWeight[0][0]=1;

		while(cRouts.size()>0) {
			cRouts=getChildNotes(cRouts);
		}
		
		System.out.println(pointsWeight[row-1][col-1]-1);
	}

	private Map<String,Integer> getChildNotes(Map<String,Integer> n){
		Map<String,Integer> c = new HashMap<String,Integer>();
		for (Map.Entry<String, Integer> e : n.entrySet()) {
			String[] s=e.getKey().split(",");
			int x=Integer.parseInt(s[0]);
			int y=Integer.parseInt(s[1]);
			//right
			int v=0;
			String k="";
			Integer w;
			if(y+1<col) {
				v=this.points.get(x)[y+1] + pointsWeight[x][y];
				k=String.valueOf(x)+","+String.valueOf(y+1);
				if(pointsWeight[x][y+1]==0) {
					pointsWeight[x][y+1]=v;					
					c.put(k, this.points.get(x)[y+1]);
				}
				else if(pointsWeight[x][y+1]>=v) {
					pointsWeight[x][y+1]=v;
					c.put(k, this.points.get(x)[y+1]);
				}

			}
			//down
			if(x+1<row) {
				v=this.points.get(x+1)[y]  + pointsWeight[x][y];
				k=String.valueOf(x+1)+","+String.valueOf(y);
				if(pointsWeight[x+1][y]==0) {
					pointsWeight[x+1][y]=v;					
					c.put(k, this.points.get(x+1)[y]);
				}
				else if(pointsWeight[x+1][y]>=v) {
					pointsWeight[x+1][y]=v;
					c.put(k, this.points.get(x+1)[y]);
				}
			}
			//left
			if(y-1>=0) {
				v=this.points.get(x)[y-1] + pointsWeight[x][y];
				k=String.valueOf(x)+","+String.valueOf(y-1);
				if(pointsWeight[x][y-1]==0) {
					pointsWeight[x][y-1]=v;					
					c.put(k, this.points.get(x)[y-1]);
				}
				else if(pointsWeight[x][y-1]>=v) {
					pointsWeight[x][y-1]=v;
					c.put(k, this.points.get(x)[y-1]);
				}
			}
			//up
			if(x-1>=0) {
				v=this.points.get(x-1)[y]  + pointsWeight[x][y];
				k=String.valueOf(x-1)+","+String.valueOf(y);
				if(pointsWeight[x-1][y]==0) {
					pointsWeight[x-1][y]=v;					
					c.put(k, this.points.get(x-1)[y]);
				}
				else if(pointsWeight[x-1][y]>=v) {
					pointsWeight[x-1][y]=v;
					c.put(k, this.points.get(x-1)[y]);
				}
			}			
		}
		return c;
	}
	
	public void getInput2(int times) {
		
		List<int[]> s=colTimes(times);
		
		for(int j=0;j<times;j++) {
			if(j>0)
				s=newRow(s);	
			for(int[] l : s)
				points2.add(l);
		}
		row=points2.size();
		col=points2.get(0).length;
		pointsWeight=new int[row][col];
	}
	
	private List<int[]> newRow(List<int[]> rows){
		List<int[]> r= new ArrayList<int[]>();
		for(int[] row:rows) {
			r.add(newLine(row));
		}
		return r;
	}
	
	private List<int[]> colTimes(int times){
		List<int[]> r=new ArrayList<int[]> ();
		
		for(int[] l:points) {
			int len=l.length;
			int[] newline=new int[len*times];
			int[] n= l;
			for(int j=0;j<times;j++) {
				if(j>0)
					n=newLine(n);
				for(int i=0;i<n.length;i++) {
					newline[i+j*l.length]=n[i];
				}
			}
			r.add(newline);
		}	
		return r;
	}
	
	private int[] newLine(int[] l) {
		int[] r=new int[l.length];
		for(int i=0;i<l.length;i++) {
			int v=l[i]+1;
			if(v>=10)
				v=1;
			r[i]=v;
		}		
		return r;
	}
	
	
	public void getInput() throws IOException {
		
		File file = new File("src/main/resources/input15.txt");
		InputStream in = new FileInputStream(file);

		Reader reader = new InputStreamReader(in);
		BufferedReader bufferedReader = new BufferedReader(reader);

		String str = null;

		while((str = bufferedReader.readLine()) != null)
		{
			String[] sTemp=str.split("");
			int[] iTemp=new int[sTemp.length];
			for(int i=0;i<iTemp.length;i++)
				iTemp[i]=Integer.parseInt(sTemp[i]);
			points.add(iTemp);
		}
	
		in.close();
		bufferedReader.close();	
		
		row=points.size();
		col=points.get(0).length;
		pointsWeight=new int[row][col];
	}
	
	public static void main(String args[]) throws IOException{
		
		long startTime = System.currentTimeMillis(); //get started
		
		day15 d =new day15();	
		d.getInput();
		d.firstOne();
		d.getInput2(5);
		d.secondOne();

		long endTime = System.currentTimeMillis(); //done
		System.out.println("running time: " + (endTime - startTime) + "ms"); //running time

    }
}
