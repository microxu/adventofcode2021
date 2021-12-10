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
import java.util.Stack;

import myadvent.day9.ComparatorList;

public class day10 {
	List<String> inputChunks=new ArrayList<String>();
	List<Long> scores=new ArrayList<Long>();

	
	public int firstOne() {
		int points=0;
		for(int i=0;i<this.inputChunks.size();i++) {
			String[] singleLine=this.inputChunks.get(i).split("");
			Stack<String> stChunks=new Stack<String>();
			for(int j=0;j<singleLine.length;j++) {
				int point=0;
		    	switch(singleLine[j]) {
	    		case "(":
	    			stChunks.push("(");
	    			break;
	    		case "{":
	    			stChunks.push("{");
	    			break;
	    		case "<":
	    			stChunks.push("<");
	    			break;
	    		case "[":
	    			stChunks.push("[");
	    			break;
	    		//pop
	    		case ")":
	    			if(!stChunks.pop().equals("(")) {
	    				point=getPoint(singleLine[j]);
	    			}
	    			break;
	    		case "}":
	    			if(!stChunks.pop().equals("{")) {
	    				point=getPoint(singleLine[j]);
	    			}
	    			break;
	    		case ">":
	    			if(!stChunks.pop().equals("<")) {
	    				point=getPoint(singleLine[j]);
	    			}
	    			break;
	    		case "]":
	    			if(!stChunks.pop().equals("[")) {
	    				point=getPoint(singleLine[j]);
	    			}
	    			break;
		    	}
		    	if(point>0) {
		    		points=points+point;
		    		break;
		    	}
			}
			
		}
		return points;
	}

	public Long secondOne() {
		boolean ignoreLine=false;
		for(int i=0;i<this.inputChunks.size();i++) {
			ignoreLine=false;
			String[] singleLine=this.inputChunks.get(i).split("");
			Stack<String> stChunks=new Stack<String>();
			for(int j=0;j<singleLine.length;j++) {
				int point=0;
		    	switch(singleLine[j]) {
	    		case "(":
	    			stChunks.push("(");
	    			break;
	    		case "{":
	    			stChunks.push("{");
	    			break;
	    		case "<":
	    			stChunks.push("<");
	    			break;
	    		case "[":
	    			stChunks.push("[");
	    			break;
	    		//pop
	    		case ")":
	    			if(!stChunks.pop().equals("(")) {
	    				ignoreLine=true;
	    			}
	    			break;
	    		case "}":
	    			if(!stChunks.pop().equals("{")) {
	    				ignoreLine=true;
	    			}
	    			break;
	    		case ">":
	    			if(!stChunks.pop().equals("<")) {
	    				ignoreLine=true;
	    			}
	    			break;
	    		case "]":
	    			if(!stChunks.pop().equals("[")) {
	    				ignoreLine=true;
	    			}
	    			break;
		    	}
		    	if(ignoreLine) {
		    		break;
		    	}
			}
			if(!ignoreLine) {
				getCompletion(stChunks);
			}
			
		}
		sortList(this.scores);
		return this.scores.get(Math.round(this.scores.size()/2));
	}
	
	public void getCompletion(Stack<String> stChunks) {
		Long totalScore=Long.valueOf(0);
		while (!stChunks.empty()) {
	    	switch(stChunks.pop()) {
    		case "(":
    			totalScore= totalScore *5 + getPointFor2(")");
    			break;
    		case "{":
    			totalScore= totalScore *5 + getPointFor2("}");
    			break;
    		case "<":
    			totalScore= totalScore *5 + getPointFor2(">");
    			break;
    		case "[":
    			totalScore= totalScore *5 + getPointFor2("]");
    			break;
	    	}
	    	
		}
		scores.add(totalScore);
	}
	public void getInput() throws IOException {
		File file = new File("src/main/resources/input10.txt");
		InputStream in = new FileInputStream(file);

		Reader reader = new InputStreamReader(in);
		BufferedReader bufferedReader = new BufferedReader(reader);

		String str = null;

		while((str = bufferedReader.readLine()) != null)
		{ 
			this.inputChunks.add(str.trim());
		}
	
		in.close();
		bufferedReader.close();		
	}

	private int getPoint(String foundStr) {
		int rPoint=0;
	
    	switch(foundStr) {
		case ")":
			rPoint=3;
			break;
		case "}":
			rPoint=1197;
			break;
		case ">":
			rPoint=25137;
			break;
		case "]":
			rPoint=57;
			break;	
			
    	}
    	return rPoint;
	}

	private int getPointFor2(String foundStr) {
		int rPoint=0;
	
    	switch(foundStr) {
		case ")":
			rPoint=1;
			break;
		case "}":
			rPoint=3;
			break;
		case ">":
			rPoint=4;
			break;
		case "]":
			rPoint=2;
			break;	
			
    	}
    	return rPoint;
	}
	
	private void sortList(List<Long> list){
		ComparatorList cl = new ComparatorList();
		Collections.sort(list, cl);
	}
	
	class ComparatorList implements Comparator<Long>{
		@Override
		public int compare(Long o1, Long o2) {
			return o1>o2 ? 1:-1;
			}
	}
	
	public static void main(String args[]) throws IOException{
    	
    	long startTime = System.currentTimeMillis(); //get started
    	
    	day10 d =new day10();	
    	d.getInput();
        Long firstResult=d.secondOne();
        System.out.println(firstResult);
        long endTime = System.currentTimeMillis(); //done
        System.out.println("running time:" + (endTime - startTime) + "ms"); //running time

    }
}
