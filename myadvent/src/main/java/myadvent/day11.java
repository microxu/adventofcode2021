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

public class day11 {

	public List<int[]> points=new ArrayList<int[]>();
	public int rows=0;
	public int cols=0;
	public int flashs=0;
    public int[][] statusOneDay;
    
    public void firstOne() {
        for(int i=0;i<100;i++) {
        	initStatus();
        	allPointsAfterOneDay(); 	
        }
    }
    
    public int secondOne() {
    	int r=0;
    	while(checkStatus()) {
    		r=r+1;
    		initStatus();
    		allPointsAfterOneDay(); 
    	}
    	return r;
    }
    
    private void allPointsAfterOneDay() {
    	for(int x=0;x<this.points.size();x++) {
    		for(int y=0;y<this.points.get(x).length;y++) {
    			List<int[]> t=Arrays.asList(new int[] {x,y});
    			aPonitAndSurroundingAfterOneDay(t);
    		}
    	}  	
    }
    
	private void aPonitAndSurroundingAfterOneDay(List<int[]> p){
		for (int[] point : p) {
			List<int[]> temp=onePointAfterOneDay(point);
			if(temp.size()>0) {
				aPonitAndSurroundingAfterOneDay(temp);
			}
		}
	}
	
	private List<int[]> onePointAfterOneDay(int[] point) {
		int x= point[0];
		int y=point[1];
		int value=this.points.get(x)[y];
		boolean flash=false;
		List<int[]> r=new ArrayList<int[]>();
		if(value<10) 
			points.get(x)[y]=value+1;
				
		if(points.get(x)[y]==10 && statusOneDay[x][y]!=1) {
			statusOneDay[x][y]=1;
			flash=true;
			this.flashs=this.flashs+1;
		}
		
		//up
		if(x-1>=0) {
			if(flash) {
				if(this.points.get(x-1)[y]==9 && statusOneDay[x-1][y]==0) {
					r.add(new int[] {x-1,y});
					statusOneDay[x-1][y]=2;
				}else {
					//not flashed
					if(this.points.get(x-1)[y]<10) {
						this.points.get(x-1)[y]=this.points.get(x-1)[y]+1;
					}
				}
			}
		}
		//down
		if(x+1<rows) {
			if(flash) {
				if(this.points.get(x+1)[y]==9 && statusOneDay[x+1][y]==0) {
					r.add(new int[] {x+1,y});
					statusOneDay[x+1][y]=2;
				}else {
					if(this.points.get(x+1)[y]<10) {
						this.points.get(x+1)[y]=this.points.get(x+1)[y]+1;
					}
				}
			}
			
		}		
		//left
		if(y-1>=0) {
			if(flash) {
				if(this.points.get(x)[y-1]==9 && statusOneDay[x][y-1]==0) {
					r.add(new int[] {x,y-1});
					statusOneDay[x][y-1]=2;
				}else {
					if(this.points.get(x)[y-1]<10) {
						this.points.get(x)[y-1]=this.points.get(x)[y-1]+1;
					}
				}
					
			}		
		}
		//right
		if(y+1<cols) {
			if(flash) {
				if(this.points.get(x)[y+1]==9 && statusOneDay[x][y+1]==0) {
					r.add(new int[] {x,y+1});
					statusOneDay[x][y+1]=2;
				}else {
					if(this.points.get(x)[y+1]<10) {
						this.points.get(x)[y+1]=this.points.get(x)[y+1]+1;
					}
				}
			}		
		}
		//up+left
		if((x-1)>=0 && (y-1)>=0) {
			if(flash) {
				if(this.points.get(x-1)[y-1]==9 && statusOneDay[x-1][y-1]==0) {
					r.add(new int[] {x-1,y-1});
					statusOneDay[x-1][y-1]=2;
				}else {
					if(this.points.get(x-1)[y-1]<10) {
						this.points.get(x-1)[y-1]=this.points.get(x-1)[y-1]+1;
					}
				}
			}
		}
		//down+left
		if((x+1)<rows && (y-1)>=0) {
			if(flash) {
				if(this.points.get(x+1)[y-1]==9 && statusOneDay[x+1][y-1]==0) {
					r.add(new int[] {x+1,y-1});
					statusOneDay[x+1][y-1]=2;
				}else {
					if(this.points.get(x+1)[y-1]<10) {
						this.points.get(x+1)[y-1]=this.points.get(x+1)[y-1]+1;
					}
				}
			}
		}
		//up+right
		if((x-1)>=0 && (y+1)<cols) {
			if(flash) {
				if(this.points.get(x-1)[y+1]==9 && statusOneDay[x-1][y+1]==0) {
					r.add(new int[] {x-1,y+1});
					statusOneDay[x-1][y+1]=2;
				}else {
					if(this.points.get(x-1)[y+1]<10) {
						this.points.get(x-1)[y+1]=this.points.get(x-1)[y+1]+1;
					}
				}
			}
		}
		//down+right
		if((x+1)<rows && (y+1)<cols) {
			if(flash) {
				if(this.points.get(x+1)[y+1]==9 && statusOneDay[x+1][y+1]==0) {
					r.add(new int[] {x+1,y+1});
					statusOneDay[x+1][y+1]=2;
				}else {
					if(this.points.get(x+1)[y+1]<10) {
						this.points.get(x+1)[y+1]=this.points.get(x+1)[y+1]+1;
					}
				}
			}
		}
		return r;
	}
	
	public void initStatus() {
		this.statusOneDay=new int[rows][cols];
		for(int i=0;i<this.rows;i++) {
			for(int j=0;j<this.cols;j++) {
				this.statusOneDay[i][j]=0;
				if(this.points.get(i)[j]==10) {
					this.points.get(i)[j]=0;
				}
			}
		}
	}
	public boolean checkStatus() {
		boolean r=false;
    	for(int x=0;x<this.points.size();x++) {
    		for(int y=0;y<this.points.get(x).length;y++) {
    			if(this.points.get(x)[y]!=10) {
    				r=true;
    				break;
    			}
    			if(r) {
    				break;
    			}
    		}
    	} 
		return r;
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
    	//d.firstOne();
    	int r=d.secondOne();
    	//System.out.println(d.flashs);
    	System.out.println(r);
        long endTime = System.currentTimeMillis(); //done
        System.out.println("running time:" + (endTime - startTime) + "ms"); //running time

    }
}
