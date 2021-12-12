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
import java.util.stream.Collectors;

public class day11 {

	public List<int[]> points=new ArrayList<int[]>();
	public int rows=0;
	public int cols=0;
	public int flashs=0;
    
    public void firstOne() {
        for(int i=0;i<100;i++) {
        	allPointsAfterOneDay(); 	
        }
    }
    
    public int secondOne() {
    	int r=0;
    	while(checkStatus()) {
    		r=r+1;
    		allPointsAfterOneDay(); 
    	}
    	return r;
    }
    
    private void allPointsAfterOneDay() {
    	oneday();
    	List<int[]> c=copy();
    	for(int x=0;x<c.size();x++) {
    		for(int y=0;y<c.get(x).length;y++) {
    			if(c.get(x)[y]==10) {
    				List<int[]> t=Arrays.asList(new int[] {x,y});
    				surroundingAfterAFlash(t);
    			}
    		}
    	} 
    	reset();
    }
    
	private void surroundingAfterAFlash(List<int[]> p){
		for (int[] point : p) {
			this.flashs+=1;
			List<int[]> temp=surroundings(point);
			if(temp.size()>0) {
				surroundingAfterAFlash(temp);
			}
		}
	}
	
	private List<int[]> surroundings(int[] point) {
		int x= point[0];
		int y=point[1];

		List<int[]> r=new ArrayList<int[]>();
		
		//up
		if(x-1>=0) {
			if(this.points.get(x-1)[y]<10) {
				this.points.get(x-1)[y]=this.points.get(x-1)[y]+1;
				if(this.points.get(x-1)[y]==10 ) {
					r.add(new int[] {x-1,y});
				}
			}
		}
		
		//down
		if(x+1<rows) {
			if(this.points.get(x+1)[y]<10) {
				this.points.get(x+1)[y]=this.points.get(x+1)[y]+1;
				if(this.points.get(x+1)[y]==10 ) {
					r.add(new int[] {x+1,y});
				}
			}
		}	
		
		//left
		if(y-1>=0) {
			if(this.points.get(x)[y-1]<10) {
				this.points.get(x)[y-1]=this.points.get(x)[y-1]+1;
				if(this.points.get(x)[y-1]==10 ) {
					r.add(new int[] {x,y-1});
				}
			}
		}
		
		//right
		if(y+1<cols) {
			if(this.points.get(x)[y+1]<10) { 
				this.points.get(x)[y+1]=this.points.get(x)[y+1]+1;
				if(this.points.get(x)[y+1]==10 ) {
					r.add(new int[] {x,y+1});
				}
			}
		}
		
		//up+left
		if((x-1)>=0 && (y-1)>=0) {
			if(this.points.get(x-1)[y-1]<10) { 
				this.points.get(x-1)[y-1]=this.points.get(x-1)[y-1]+1;
				if(this.points.get(x-1)[y-1]==10) {
					r.add(new int[] {x-1,y-1});
				}
			}
		}
		
		//down+left
		if((x+1)<rows && (y-1)>=0) {
			if(this.points.get(x+1)[y-1]<10) { 
				this.points.get(x+1)[y-1]=this.points.get(x+1)[y-1]+1;
				if(this.points.get(x+1)[y-1]==10) {
					r.add(new int[] {x+1,y-1});
				}
			}
		}
		//up+right
		if((x-1)>=0 && (y+1)<cols) {
			if(this.points.get(x-1)[y+1]<10) {
				this.points.get(x-1)[y+1]=this.points.get(x-1)[y+1]+1;
				if(this.points.get(x-1)[y+1]==10 ) {
					r.add(new int[] {x-1,y+1});
				}
			}
		}
		//down+right
		if((x+1)<rows && (y+1)<cols) {
			if(this.points.get(x+1)[y+1]<10) {
				this.points.get(x+1)[y+1]=this.points.get(x+1)[y+1]+1;
				if(this.points.get(x+1)[y+1]==10 ) {
					r.add(new int[] {x+1,y+1});
				}
			}
		}
		return r;
	}
	
	private void reset() {
		this.points=this.points.stream().map(m->Arrays.stream(m).map(
				(int n) ->{
				   if(n==10)
					   return n=0;
				   else
					   return n;
				}).toArray()).collect(Collectors.toList());
	}
	
	private void oneday() {
		this.points=this.points.stream().map(m->Arrays.stream(m).map((int n) ->n+1).toArray()).collect(Collectors.toList());
	}
	
	private List<int[]> copy(){
		return this.points.stream().map(m-> Arrays.copyOf(m, m.length)).collect(Collectors.toList());

	}
	
	private boolean checkStatus() {    	
		return this.points.stream().filter(m->Arrays.stream(m).anyMatch(n->n!=0)).count() >0? true:false;
	}
	
	public void getInput() throws IOException {
		File file = new File("src/main/resources/input11.txt");
		InputStream in = new FileInputStream(file);

		Reader reader = new InputStreamReader(in);
		BufferedReader bufferedReader = new BufferedReader(reader);

		String str = null;

		while((str = bufferedReader.readLine()) != null)
		{ 
			String[] sTemp=str.trim().split("");
            int[] iTemp=new int[sTemp.length];
            for(int i=0;i<sTemp.length;i++) {
            	iTemp[i]=Integer.parseInt(sTemp[i]);
            }
            this.points.add(iTemp);
		}
	
		in.close();
		bufferedReader.close();		
		this.rows=this.points.size();
		this.cols=this.points.get(0).length;
		
	}
	
	public static void main(String args[]) throws IOException{
    	
    	long startTime = System.currentTimeMillis(); //get started
    	
    	day11 d =new day11();	
    	d.getInput();
//    	
//    	d.firstOne();
//   	System.out.println(d.flashs);
    	
    	int r=d.secondOne();
    	System.out.println(r);
    	
        long endTime = System.currentTimeMillis(); //done
        System.out.println("running time:" + (endTime - startTime) + "ms"); //running time

    }
}
