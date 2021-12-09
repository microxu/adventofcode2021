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
import java.util.List;

public class day9 {

	public List<List<Integer>> inputInt = new ArrayList<List<Integer>>();
	public List<int[]> points=new ArrayList<int[]>();
	public int[][] pointStatus ;
	public List<Integer> eachBasin=new ArrayList<Integer>();
	
	//first question to set points
	public void firstOne() {
		for(int i=0;i<inputInt.size();i++) {
			for(int j=0;j<inputInt.get(i).size();j++) {
				int cPoint= inputInt.get(i).get(j);
				//left(i,j-1) right(i,j+1) up(i-1,j),down(i+1,j)
				int pLeft=9;
				int pRight=9;
				int pUp=9;
				int pDown=9;
				if (j-1>=0) {
					pLeft=inputInt.get(i).get(j-1);
				}
				if(j+1<inputInt.get(i).size()) {
					pRight=inputInt.get(i).get(j+1);
				}
				if(i-1>=0) {
					pUp=inputInt.get(i-1).get(j);
				}
				if((i+1)<inputInt.size()) {
					pDown=inputInt.get(i+1).get(j);
				}
				if(cPoint<pLeft && cPoint<pRight && cPoint<pUp && cPoint<pDown ) {
					int[] t= new int[] {i,j,inputInt.get(i).get(j)};
					points.add(t);
				}
			}

		}
	}

	//second question method should be called after firstOne
	public void secondOne(List<int[]> aBasin,int steps) {
		
		for(int i=steps;i<aBasin.size();i++) {
			List<int[]> temp=this.find4Directions(aBasin.get(i));
			if(temp.size()>0) {
				secondOne(temp,0);
			}
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
		for(int i=xRow-1;i>=0;i--) {
			if(inputInt.get(i).get(yCol)<9 && this.pointStatus[i][yCol]==-1) {
				cPoints.add(new int[] {i,yCol,inputInt.get(i).get(yCol)});
				pointStatus[i][yCol]=inputInt.get(i).get(yCol);
				this.eachBasin.add(inputInt.get(i).get(yCol));

			}else {
				break;
			}
		}
		//down
		for(int i=xRow+1;i<row;i++) {
			if(inputInt.get(i).get(yCol)<9 && this.pointStatus[i][yCol]==-1) {
				cPoints.add(new int[] {i,yCol,inputInt.get(i).get(yCol)});
				pointStatus[i][yCol]=inputInt.get(i).get(yCol);
				this.eachBasin.add(inputInt.get(i).get(yCol));

			}else {
				break;
			}
		}
		//left
		for(int i=yCol-1;i>=0;i--) {
			if(inputInt.get(xRow).get(i)<9 && this.pointStatus[xRow][i]==-1) {
				cPoints.add(new int[] {xRow,i,inputInt.get(xRow).get(i)});
				pointStatus[xRow][i]=inputInt.get(xRow).get(i);
				this.eachBasin.add(inputInt.get(xRow).get(i));
			}else {
				break;
			}		
		}
		//right
		for(int i=yCol+1;i<col;i++) {
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

	public void initStatus() {

		int rowAll=this.inputInt.size();
		int colAll=this.inputInt.get(0).size();
		this.pointStatus =new int[rowAll][colAll];
		for(int i=0;i<rowAll;i++) {
			for(int j=0;j<colAll;j++) {
				this.pointStatus[i][j]=-1;
			}
		}	
	}
	
	public void getInput() throws IOException {
		File file = new File("src/main/resources/input9.txt");
		InputStream in = new FileInputStream(file);

		Reader reader = new InputStreamReader(in);
		BufferedReader bufferedReader = new BufferedReader(reader);

		String str = null;
		//read input file and populate a List<List<Integer>>
		while((str = bufferedReader.readLine()) != null)
		{ 
			String[] temp=str.split("");

			List<Integer> lTemp=new ArrayList<Integer>();
			for(int i=0;i<temp.length;i++) {
					lTemp.add(Integer.parseInt(temp[i].trim()));
			}
			this.inputInt.add(lTemp);
		}
	
		in.close();
		bufferedReader.close();		
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
		
		long startTime = System.currentTimeMillis(); //get started
		
		day9 d =new day9();	
		d.getInput();
		
	    //to get all the lowest points
        d.firstOne();
        
        List<Integer> result=new ArrayList<Integer>();

        //get each basin of former points
        for(int i=0;i<d.points.size();i++) {

        	d.initStatus();
        	d.eachBasin=new ArrayList<Integer>();
        	d.eachBasin.add(d.points.get(i)[2]);
        	List<int[]> onePoint = Arrays.asList(d.points.get(i));

        	d.secondOne(onePoint,0);
        	result.add(d.eachBasin.size());

        }
        
        d.sortList(result);
       
        System.out.println(result.get(0)*result.get(1)*result.get(2));
        long endTime = System.currentTimeMillis(); //done
	    System.out.println("running time:" + (endTime - startTime) + "ms"); //running time
	}
}
