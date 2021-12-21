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

public class day16 {
	Map<String,String> hex = new HashMap<String,String>();

	String line="";
	
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
		
	}
	
	public void firstOne() {
		List<String> r=decode(0);
		System.out.println(r.get(0));
		System.out.println(r.get(1));
	}

	
	//learned from ericvan
	private List<String> decode(int pos) {
		
		int version=binaryToInteger( line.substring(pos, pos+3));
		int type=binaryToInteger(line.substring(pos+3, pos+6));
		long val=Long.valueOf(0);
		if(type==4) {
			pos+=6;
			String bin="";
			while (line.substring(pos, pos+1).equals("1")) {
				pos+=5;
				bin+=line.substring(pos-4, pos);
			}
			pos+=5;
			bin += line.substring(pos-4,pos);
			val=bin2Dec(bin);
			
		}else {
			int n;
			List<String> r;
			List<Long> sub=new ArrayList<>();
			pos+=6;
			if(line.substring(pos, pos+1).equals("0")) {
				pos+=16;
				n=pos+binaryToInteger(line.substring(pos-15, pos));
				while(pos<n) {
					r=decode(pos);
					pos=Integer.valueOf(r.get(2));
					version +=Integer.valueOf(r.get(0));
					sub.add(Long.valueOf(r.get(1)));
				}
			}else {
				pos+=12;
				n=binaryToInteger(line.substring(pos-11, pos));
				for(int i=0;i<n;i++) {
					r=decode(pos);
					pos=Integer.valueOf(r.get(2));
					version +=Integer.valueOf(r.get(0));
					sub.add(Long.valueOf(r.get(1)));				
				}
			}
			switch (type){
			case 0:
				val=sum(sub);
				break;
			case 1:
				val=mul(sub);
				break;
			case 2:
				val=min(sub);
				break;
			case 3:
				val=max(sub);
				break;
			case 5:
				val=sub.get(0)>sub.get(1)? 1:0;
				break;
			case 6:
				val=sub.get(0)<sub.get(1)? 1:0;
				break;
			case 7:
				val=sub.get(0)==sub.get(1)? 1:0;
				break;
				}
			}
		return new ArrayList<String>(Arrays.asList(String.valueOf(version),String.valueOf(val),String.valueOf(pos)));
		
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
	
	private Long sum(List<Long> n) {
		Long r=Long.valueOf(0);
		for(int i=0;i<n.size();i++)
			r=r+Long.valueOf(n.get(i));
		return r;
	}
	
	private Long mul(List<Long> n) {
		Long r=Long.valueOf(1);
		for(int i=0;i<n.size();i++)
			r=r*Long.valueOf(n.get(i));
		return r;
	}
	
	private Long min(List<Long> n) {
		Long r=Long.valueOf(0);
		r=Long.valueOf(n.get(0));
		for(int i=0;i<n.size();i++) {	
			Long t=Long.valueOf(n.get(i));
			if(r>t)
				r=t;
		}
		return r;
	}
	
	private Long max(List<Long> n) {
		Long r=Long.valueOf(0);
		r=Long.valueOf(n.get(0));
		for(int i=0;i<n.size();i++) {	
			Long t=Long.valueOf(n.get(i));
			if(r<t)
				r=t;
		}
		return r;
	}
	
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
			this.line="";
			for(int i=0;i<sTemp.length;i++) {
				this.line=this.line+hex.get(sTemp[i]);
			}
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
