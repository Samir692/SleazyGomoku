// Samir Huseynzade
//
// JS19Q4
//
// SleazyGomoku
//
// 2016/11/30 19:15:24
//
// This solution was submitted and prepared by Samir Huseynzade, JS19Q4 for the
// SleazyGomoku assignment of the Practical software engineering I. course.
//
// I declare that this solution is my own work.
//
// I have not copied or used third party solutions.
//
// I have not passed my solution to my classmates, neither  made it public.
//
// Students’ regulation of Eötvös Loránd University (ELTE Regulations
// Vol. II. 74/C. § ) states that as long as a student presents another
// student’s work - or at least the significant part of it - as his/her own
// performance, it will count as a disciplinary fault. The most serious
// consequence of a disciplinary fault can be dismissal of the student from
// the University.

package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameTable{
	
		public static final int maxx = 10;
		private int[][] buttGTable = new int[maxx][maxx];

		
		public void put(int colNum, int rowNum, int key){
			buttGTable[colNum][rowNum] = key;
		}
		public String giveKeyType(int key){
			String keyType = "";
			if (key == 1) {

				keyType = "X";

			} else {

				keyType = "O";

			}
			return keyType;
		}
		
		public int[][] get(){
			return buttGTable;
		}
		
		//reset your gameTable
		public void reset(){
			for(int i = 0; i < maxx; i ++){
				for(int j = 0; j < maxx; j++){
					buttGTable[i][j] = 0;
				}
			}
		}
		
		public int getSize(){
			return buttGTable.length;
		}
		
		public boolean isFull(){
			boolean isFull = false;
			for(int i = 0; i < maxx; i ++){
				for(int j = 0; j < maxx; j++){
					if(buttGTable[i][j] != 0){
						isFull =  true;
					}
					else{
						isFull = false;
					}
				}
			}
			return isFull;
		}


		public void remove(int cordX, int cordY, int key) {
			
			buttGTable[cordX][cordY] = 0;
			
		}

}	
