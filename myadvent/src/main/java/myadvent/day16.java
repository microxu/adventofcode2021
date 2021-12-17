package myadvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day16 {
	Map<String,String> hex = new HashMap<String,String>();
	List<String> sBin=new ArrayList<String>();
	List<Integer> versions=new ArrayList<Integer>();
	List<String> op=new ArrayList<String>();
	Map<Integer,String> oper=new HashMap<Integer,String>();
	
	Stack<String> result=new Stack<String>();
	
	public day16() {
		hex.put("0", "0000");
		hex.put("1", "0001");
		hex.put("2", "0010");
		hex.put("3", "0011");
		hex.put("4", "0100");
		hex.put("5", "0101");
		hex.put("6", "0110");
		hex.put("7", "0111");
		hex.put("8", "1000");
		hex.put("9", "1001");
		hex.put("A", "1010");
		hex.put("B", "1011");
		hex.put("C", "1100");
		hex.put("D", "1101");
		hex.put("E", "1110");
		hex.put("F", "1111");
		oper.put(0, "SUM");
		oper.put(1, "MUL");
		oper.put(2, "MIN");
		oper.put(3, "MAX");
		oper.put(5, "GREATER");
		oper.put(6, "LESS");
		oper.put(7, "EQUAL");
		
	}
	
	public void firstOne() {
		List<String> c=new ArrayList<String>();
        for(String s:sBin) {
        	c.add(s);
        	while(c.size()>0)
        		c=getNextString(c);
        }       
        System.out.println(versions.stream().reduce(Integer::sum).orElse(0));
	}


	
	private List<String> getNextString(List<String> ss) {
		List<String> sub=new ArrayList<String>();
		for(String s:ss) {
			
			int type=binaryToInteger(s.substring(3, 6));
			switch (type){
			case 4:
				findA4(s,sub);
				break;
			default:
				versions.add(binaryToInteger( s.substring(0, 3)));
				int len=0;
				switch(s.substring(6, 7)){
				case "0":
					len=binaryToInteger(s.substring(7, 7+15));
					sub.add(s.substring(22,22+len));
					String left=s.substring(22+len);
					if(checkExist(left))
						sub.add(left);
						
					break;
				case "1":
					sub.add(s.substring(18));
					break;
				}
			}
		}
		return sub;
	}
	
	private void findA4(String s,List<String> sub) {
        String num="";
		versions.add(binaryToInteger( s.substring(0, 3)));
		for(int i=6;i<s.length();i++) {
			if(s.substring(i, i+1).equals("0")) {
				String left=s.substring(i+5);
				num=num+s.substring(i+1,i+5);
				//int n=Integer.parseInt(num, 2);
				long n=bin2Dec(num);
				op.add(String.valueOf(n));
				if(checkExist(left))
					sub.add(left);
				break;
			}else {
				num=num+s.substring(i+1,i+5);
				i+=4;
			}
		}
	}

    private long bin2Dec(String binaryString){
        long sum = Long.valueOf(0);
        for(int i = 0;i < binaryString.length();i++){
            char ch = binaryString.charAt(i);
            if(ch > '2' || ch < '0')
                throw new NumberFormatException(String.valueOf(i));
            sum = sum * 2 + (binaryString.charAt(i) - '0');
        }
        return sum;
    }

	private boolean checkExist(String s) {
		boolean r =false;
		for(int i=0;i<s.length();i++) {
			if(!s.substring(i, i+1).equals("0"))
				return true;
		}
		return r;
	}
	
//	private Long sum(List<String> n) {
//		Long r=Long.valueOf(0);
//		for(int i=0;i<n.size();i++)
//			r=r+Long.valueOf(n.get(i));
//		return r;
//	}
//	
//	private Long mul(List<String> n) {
//		Long r=Long.valueOf(1);
//		for(int i=0;i<n.size();i++)
//			r=r*Long.valueOf(n.get(i));
//		return r;
//	}
//	
//	private Long min(List<String> n) {
//		Long r=Long.valueOf(0);
//		r=Long.valueOf(n.get(0));
//		for(int i=0;i<n.size();i++) {	
//			Long t=Long.valueOf(n.get(i));
//			if(r>t)
//				r=t;
//		}
//		return r;
//	}
//	
//	private Long max(List<String> n) {
//		Long r=Long.valueOf(0);
//		r=Long.valueOf(n.get(0));
//		for(int i=0;i<n.size();i++) {	
//			Long t=Long.valueOf(n.get(i));
//			if(r<t)
//				r=t;
//		}
//		return r;
//	}
	
	public int binaryToInteger(String binary) {
	    char[] numbers = binary.toCharArray();
	    int result = 0;
	    for(int i=numbers.length - 1; i>=0; i--)
	        if(numbers[i]=='1')
	            result += Math.pow(2, (numbers.length-i - 1));
	    return result;
	}
	
	public void getInput() throws IOException {
		
		File file = new File("src/main/resources/input16.txt");
		InputStream in = new FileInputStream(file);

		Reader reader = new InputStreamReader(in);
		BufferedReader bufferedReader = new BufferedReader(reader);

		String str = null;

		while((str = bufferedReader.readLine()) != null)
		{
			String[] sTemp=str.split("");
			String bTemp="";
			for(int i=0;i<sTemp.length;i++) {
				bTemp=bTemp+hex.get(sTemp[i]);
			}
			sBin.add(bTemp);
		}
	
		in.close();
		bufferedReader.close();	
	}
	
	public static void main(String args[]) throws IOException{
		
		long startTime = System.currentTimeMillis(); //get started
		
		day16 d =new day16();	
		d.getInput();
        d.firstOne();
        
		long endTime = System.currentTimeMillis(); //done
		System.out.println("running time: " + (endTime - startTime) + "ms"); //running time

    }
}
