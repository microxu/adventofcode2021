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
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static java.util.Arrays.asList;

public class day9 {

	public List<List<Integer>> inputInt = new ArrayList<List<Integer>>();
	public List<Integer> result = new ArrayList<Integer>();
	public List<int[]> points=new ArrayList<int[]>();
	public int[][] pointStatus ;
	public List<Integer> eachBasin=new ArrayList<Integer>();
	
	public void firstOne() {
		for(int i=1;i<inputInt.size()-1;i++) {
			for(int j=1;j<inputInt.get(i).size()-1;j++) {
				if(inputInt.get(i).get(j)<inputInt.get(i).get(j-1) && inputInt.get(i).get(j)<inputInt.get(i).get(j+1) && inputInt.get(i).get(j)<inputInt.get(i-1).get(j) && inputInt.get(i).get(j)<inputInt.get(i+1).get(j) ) {
					result.add(inputInt.get(i).get(j));
					int[] t= new int[] {i,j,inputInt.get(i).get(j)};
					points.add(t);
				}
			}

		}
	}
	
	public void initStatus() {
		int rowAll=this.inputInt.size();
		int colAll=this.inputInt.get(0).size();
		this.pointStatus =new int[rowAll][colAll];
		for(int i=0;i<rowAll;i++) {
			for(int j=0;j<colAll;j++) {
				this.pointStatus[i][j]=-1;
			}
		}
		for(int i=0;i<rowAll;i++) {
			this.pointStatus[i][0]=9;
			this.pointStatus[i][colAll-1]=9;
		}
		for(int i=0;i<colAll;i++) {
			this.pointStatus[0][i]=9;
			this.pointStatus[rowAll-1][i]=9;
		}		
	}
	
	private List<int[]> find4Directions(int[] point) {
		int xRow=point[0];
		int yCol=point[1];
		int cValue=point[2];

		pointStatus[xRow][yCol]=cValue;
		
		int row=this.inputInt.size();
		int col=this.inputInt.get(0).size();
		
		List<int[]> cPoints=new ArrayList<int[]>();
		//up
		for(int i=xRow-1;i>0;i--) {
			if(inputInt.get(i).get(yCol)<9 && this.pointStatus[i][yCol]==-1) {
				cPoints.add(new int[] {i,yCol,inputInt.get(i).get(yCol)});
				pointStatus[i][yCol]=inputInt.get(i).get(yCol);
				this.eachBasin.add(inputInt.get(i).get(yCol));

			}else {
				break;
			}
		}
		//down
		for(int i=xRow+1;i<row-1;i++) {
			if(inputInt.get(i).get(yCol)<9 && this.pointStatus[i][yCol]==-1) {
				cPoints.add(new int[] {i,yCol,inputInt.get(i).get(yCol)});
				pointStatus[i][yCol]=inputInt.get(i).get(yCol);
				this.eachBasin.add(inputInt.get(i).get(yCol));

			}else {
				break;
			}
		}
		//left
		for(int i=yCol-1;i>0;i--) {
			if(inputInt.get(xRow).get(i)<9 && this.pointStatus[xRow][i]==-1) {
				cPoints.add(new int[] {xRow,i,inputInt.get(xRow).get(i)});
				pointStatus[xRow][i]=inputInt.get(xRow).get(i);
				this.eachBasin.add(inputInt.get(xRow).get(i));
			}else {
				break;
			}		
		}
		//right
		for(int i=yCol+1;i<col-1;i++) {
			if(inputInt.get(xRow).get(i)<9 && this.pointStatus[xRow][i]==-1) {
				cPoints.add(new int[] {xRow,i,inputInt.get(xRow).get(i)});
				pointStatus[xRow][i]=inputInt.get(xRow).get(i);
				this.eachBasin.add(inputInt.get(xRow).get(i));
			}else {
				break;
			}		
		}
		return cPoints;
		
	}
	
	public void findOnePointAll(List<int[]> cSink,int steps) {
		
		for(int i=steps;i<cSink.size();i++) {
			List<int[]> temp=this.find4Directions(cSink.get(i));
			if(temp.size()>0) {
				findOnePointAll(temp,0);
			}
		}
		
	}
	
    public void sortList(List<Integer> list){
        ComparatorList cl = new ComparatorList();
        Collections.sort(list, cl);
    }
	
    class ComparatorList implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1>o2 ? -1:1;
        }

    }

	public static void main(String args[]) throws IOException{  

		File file = new File("src/main/resources/input9.txt");
		InputStream in = new FileInputStream(file);

		Reader reader = new InputStreamReader(in);
		BufferedReader bufferedReader = new BufferedReader(reader);

		String str = null;
		
		day9 d =new day9();	
        int row=0;
        List<Integer> topAndBottom=new ArrayList<Integer>();
		while((str = bufferedReader.readLine()) != null)
		{ 
			String[] temp=str.split("");
			if(row==0) {
				
				for(int i=0;i<temp.length+2;i++) {
					topAndBottom.add(9);
				}
				d.inputInt.add(topAndBottom);
				row=row+1;
			}
			List<Integer> lTemp=new ArrayList<Integer>();
			for(int i=0;i<temp.length+2;i++) {
				if(i==0) {
					lTemp.add(9);
				}else if(i==temp.length+1) {
					lTemp.add(9);
				}else {
					lTemp.add(Integer.parseInt(temp[i-1].trim()));
				}
			}
			d.inputInt.add(lTemp);
		}
		d.inputInt.add(topAndBottom);
	
		in.close();
		bufferedReader.close();

		long startTime = System.currentTimeMillis(); //get started
		
	
        d.firstOne();
        int r=1;
        List<Integer> result=new ArrayList<Integer>();

        for(int i=0;i<d.points.size();i++) {

        	d.initStatus();
        	d.eachBasin=new ArrayList<Integer>();

        	d.eachBasin.add(d.points.get(i)[2]);
        	List<int[]> onePoint = asList(d.points.get(i));

        	d.findOnePointAll(onePoint,0);
        	result.add(d.eachBasin.size());

        }
        
        d.sortList(result);
       

        System.out.println(result.get(0)*result.get(1)*result.get(2));
        
	     long endTime = System.currentTimeMillis(); //done
	     System.out.println("running time:" + (endTime - startTime) + "ms"); //running time
	}
}
