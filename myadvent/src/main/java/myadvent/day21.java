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

public class day21 {

	public Map<String,List<Long>> rollState=new HashMap<>();
	
	public void firstOne(int pos1,int pos2) {
		int s1=0;
		int s2=0;
		int rolls=0;
		int die=1;
		while(s1!=1000 && s2!=1000) {
			rolls+=3;
			int die1=die+die+1+die+2;
			
			
			int space1=(die1+pos1)%10;
			if(space1==0) {
				pos1=10;
				s1=s1+pos1;
			}else {
				pos1=space1;
				s1=s1+pos1;
			}
			if(s1==1000)
				break;
			rolls+=3;
			int die2=die+3+die+4+die+5;
			int space2=(die2+pos2)%10;
			if(space2==0) {
				pos2=10;
				s2=s2+pos2;
			}else {
				pos2=space2;
				s2=s2+pos2;
			}
			die+=6;
		}
		
		int r=s1==1000?s2:s1;
		System.out.println(r*rolls);
		
	}

	
	public List<Long> secondOne(int pos1,int pos2,int s1,int s2) {
		if(s1>=21)
			return new ArrayList<Long>(Arrays.asList(Long.valueOf(1),Long.valueOf(0)));

		if(s2>=21)
			return new ArrayList<Long>(Arrays.asList(Long.valueOf(0),Long.valueOf(1)));
		
		String state_key=String.valueOf(pos1)+","+String.valueOf(pos2)+","+String.valueOf(s1)+","+String.valueOf(s2);
		if(rollState.get(state_key)!=null)
			return rollState.get(state_key);
		
		List<Long> results=new ArrayList<>(Arrays.asList(Long.valueOf(0),Long.valueOf(0)));

		for(int i=1;i<4;i++)
			for(int j=1;j<4;j++)
				for(int m=1;m<4;m++) {
					int new_pos1;
			    	int new_s1=0;
			    	if((i+j+m+pos1)%10==0) {
			    		new_pos1=10;
			    		new_s1=s1+new_pos1;
			    	}else {
			    		new_pos1=(i+j+m+pos1)%10;
			    		new_s1=s1+new_pos1;
			    	}
			    	List<Long> t=secondOne(pos2,new_pos1,s2,new_s1);
			    	results.set(0, results.get(0)+t.get(1));
			    	results.set(1, results.get(1)+t.get(0));
			    };
		rollState.put(state_key, results);
		return results;
	}
	
	public void getInput() throws IOException {

		File file = new File("src/main/resources/input21.txt");
		InputStream in = new FileInputStream(file);

		Reader reader = new InputStreamReader(in);
		BufferedReader bufferedReader = new BufferedReader(reader);

		String str = null;

		while((str = bufferedReader.readLine()) != null)
		{
			if(StringUtils.isNotBlank(str)) {

			}
		}
		
		in.close();
		bufferedReader.close();	
		
	}
	
	public static void main(String args[]) throws IOException{
		
		long startTime = System.currentTimeMillis(); //get started
		
		day21 d =new day21();	
		//d.getInput();
		List<Long> r=d.secondOne(9, 3,0,0);
		System.out.println(r);
		
		long endTime = System.currentTimeMillis(); //done
		System.out.println("running time: " + (endTime - startTime) + "ms"); //running time

    }
}
