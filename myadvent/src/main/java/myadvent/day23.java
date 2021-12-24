package myadvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class day23 {

	public void firstOne() {
//		#############
//		#...........#
//		###C#A#B#D###
//		  #D#C#A#B#
//		  #########
//		  
//		#AA.....B...#
//		###C#.#.#D###
//		  #D#C#.#B#
//		  #########
//		  
//		  A*5+B*2+A*7
//		  
//		#AA.....B...#
//		###.#.#C#D###
//		  #D#.#C#B#
//		  #########
//		  
//		  C*7+C*5
//		  
//		#AA.......D.#
//		###.#B#C#.###
//		  #D#B#C#.#
//		  #########
//		  
//		  B*5+D*2+B*7
//		#A.........A#
//		###.#B#C#D###
//		  #.#B#C#D#
//		  #########
//		  
//		  D*3+D*9
//		 #...........#
//		###A#B#C#D###
//		  #A#B#C#D#
//		  #########
		//  A*3+A*3
		
		System.out.println(5+20+7+700+500+50+2000+70+3000+9000+6);
	}
	public void secondOne() {
//		#############
//		#...........#
//		###C#A#B#D###
//		  #D#C#B#A#
//		  #D#B#A#C#
//		  #D#C#A#B#
//		  #########
//		  
//		  
//		#.......B.BA#
//		###C#.#.#D###
//		  #D#C#.#A#
//		  #D#B#A#C#
//		  #D#C#A#B#
//		  #########
//		  
		//A*7+B*4+B*4
//		  
//		#AA.....B.BA#
//		###C#.#.#D###
//		  #D#C#.#A#
//		  #D#B#.#C#
//		  #D#C#.#B#
//		  #########
//	
		//A*9+A*9
		
//		#AA.....B.BA#
//		###.#.#.#D###
//		  #D#.#.#A#
//		  #D#B#C#C#
//		  #D#C#C#B#
//		  #########
//	
		//C*8+C*8
		
//		#AA.B...B.BA#
//		###.#.#.#D###
//		  #D#.#.#A#
//		  #D#.#C#C#
//		  #D#C#C#B#
//		  #########
//		
		//C*8+B*4
		
//		#AA........A#
//		###.#.#.#D###
//		  #D#B#C#A#
//		  #D#B#C#C#
//		  #D#B#C#B#
//		  #########
//		  
		//B*5+B*6+B*7+D*6+A*3
		
//		#AA.D.....AA#
//		###.#.#.#.###
//		  #D#B#C#.#
//		  #D#B#C#C#
//		  #D#B#C#B#
//		  #########
//		  
		//D*6+A*3
		
//		#AA.D.....AA#
//		###.#B#C#.###
//		  #D#B#C#.#
//		  #D#B#C#.#
//		  #D#B#C#.#
//		  #########
//		  
		//B*9+C*6
		
//		#AA.......AA#
//		###.#B#C#D###
//		  #.#B#C#D#
//		  #.#B#C#D#
//		  #.#B#C#D#
//		  #########
//		  
		//D*9+D*11+D*11+D*11
		
//		#...........#
//		###A#B#C#D###
//		  #A#B#C#D#
//		  #A#B#C#D#
//		  #A#B#C#D#
//		  #########		
		
		//A*5+A*5+A*9+A*9
		System.out.println(7+70+18+800+800+40+800+50+60+70+6000+3+600+90+9000+11000+11000+11000+5+5+9+9);
	}
	public static void main(String args[]) throws IOException{
		
		long startTime = System.currentTimeMillis(); //get started
		day23 d=new day23();
		d.firstOne();
		d.secondOne();

		long endTime = System.currentTimeMillis(); //done
		System.out.println("running time: " + (endTime - startTime) + "ms"); //running time

    }
}
