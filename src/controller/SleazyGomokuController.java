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

package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.AddtoArrayList;
import model.GameTable;

public class SleazyGomokuController implements Initializable {

	@FXML
	private GridPane buttonsPane;
	
	@FXML	private Label playerLabel;

	private int cnt = 1;	private	int WinXCnt = 0;
	private	int WinOCnt = 0;

	private int num = 0;
	//private static final int max = 10;
	private ArrayList<Integer> deletedElement = new ArrayList<Integer>();
	int delCnt = 0;

	GameTable gmTable = new GameTable();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		for (int i = 0; i < GameTable.maxx; i++) {
			for (int j = 0; j < GameTable.maxx; j++) {
				
				final Button button = new Button();
				button.getProperties().put("coordinateX", i);
				button.getProperties().put("coordinateY", j);

				cnt++;
				buttonsPane.add(button, i, j);

				button.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent e) {

						int key;
						num++;
						
						Integer butCoordinateX = (Integer) button.getProperties().get("coordinateX");
						Integer butCoordinateY = (Integer) button.getProperties().get("coordinateY");
						
						if (num % 2 == 0) {							WinXCnt++;
							key = -1;
							button.setText(gmTable.giveKeyType(key));							
							playerLabel.setText("Player X");	
							
							button.setDisable(true);
							System.out.println(String.format("(%d,%d,%d)", butCoordinateX, butCoordinateY, key));
							gmTable.put(butCoordinateX, butCoordinateY, key);							
							int[][] buttGTable = gmTable.get();
							
								int resultOfAllCases = 0;
								int verticalResult = checkForSequencesVertically(buttGTable, butCoordinateX, butCoordinateY, key);

								int horizontalResult = checkForSequencesHorizontally(buttGTable, butCoordinateX, butCoordinateY, key);

								int diagonalResultLeftToRight = checkForSequencesLeftToRightDiagonally(buttGTable, butCoordinateX, butCoordinateY, key);

								int diagonalResultRightToLeft = checkForSequencesRightToLeftDiagonally(buttGTable, butCoordinateX, butCoordinateY, key);

							
							
							if (verticalResult >= 3) {

								resultOfAllCases = verticalResult;

							} else if (horizontalResult >= 3) {

								resultOfAllCases = horizontalResult;

							} else if (diagonalResultLeftToRight >= 3) {

								resultOfAllCases = diagonalResultLeftToRight;

							} 
							else if (diagonalResultRightToLeft >= 3) {

								resultOfAllCases = diagonalResultRightToLeft;

							}

							if (resultOfAllCases == 3) {
								System.out.println("1 button was deleted");
								ArrayList<Integer> arrr = removeElementFromArray(buttGTable, key, resultOfAllCases);

								int cordX = arrr.get(delCnt);
								int cordY = arrr.get(delCnt + 1);

								System.out.println(String.format(("%d, %d"), cordX, cordY));
								gmTable.remove(cordX, cordY, key);
								removeButton(cordX, cordY);
							} else if (resultOfAllCases == 4) {
								System.out.println("2 buttons was deleted");
								ArrayList<Integer> arrr = removeElementFromArray(buttGTable, key, resultOfAllCases);

								int cordX = arrr.get(delCnt);
								int cordY = arrr.get(delCnt + 1);

								int cordX2 = arrr.get(delCnt + 2);
								int cordY2 = arrr.get(delCnt + 3);

								System.out.println(String.format(("%d, %d"), cordX, cordY));
								System.out.println(String.format(("%d, %d"), cordX2, cordY2));

								gmTable.remove(cordX, cordY, key);
								gmTable.remove(cordX2, cordY2, key);
								removeButton(cordX, cordY);
								removeButton(cordX2, cordY2);
							} else if (resultOfAllCases == 5) {
								System.out.println("You win");								
								gmTable.reset();

								// show wind dialog
								Alert alert = new Alert(AlertType.INFORMATION);
								alert.setHeaderText(null);
								alert.setContentText("Congratulations!!!! " + gmTable.giveKeyType(key) + " has won the game with " + WinXCnt
										+ " steps. Are you readyyyyyyy for new oneeee????");
								alert.showAndWait();
								resetGame();
								num = 0;
								playerLabel.setText("Player X");
							}

						} else {
							key = 1;								WinOCnt++;
							button.setText(gmTable.giveKeyType(key));
							playerLabel.setText("Player O");
							button.setDisable(true);
							System.out.println(String.format("(%d,%d,%d)", butCoordinateX, butCoordinateY, key));
							gmTable.put(butCoordinateX, butCoordinateY, key);
							//playerLabel.setText("Player " + gmTable.giveKeyType(num));
							int[][] buttGTable = gmTable.get();
									int verticalResult = checkForSequencesVertically(buttGTable, butCoordinateX, butCoordinateY, key);
									int horizontalResult = checkForSequencesHorizontally(buttGTable, butCoordinateX, butCoordinateY,  key);

									int diagonalResultLeftToRight = checkForSequencesLeftToRightDiagonally(buttGTable, butCoordinateX, butCoordinateY, key);
									
									int diagonalResultRightToLeft = checkForSequencesRightToLeftDiagonally(buttGTable, butCoordinateX, butCoordinateY, key);
									
									int resultOfAllCases = 0;
							
							
							if (verticalResult >= 3) {

								resultOfAllCases = verticalResult;

							} else if (horizontalResult >= 3) {

								resultOfAllCases = horizontalResult;

							} else if (diagonalResultLeftToRight >= 3) {

								resultOfAllCases = diagonalResultLeftToRight;

							} 
							else if (diagonalResultRightToLeft >= 3) {

								resultOfAllCases = diagonalResultRightToLeft;

							}


							System.out.println(resultOfAllCases);
							if (resultOfAllCases == 3) {
								System.out.println("1 button was deleted");
								ArrayList<Integer> arrr = removeElementFromArray(buttGTable, key, resultOfAllCases);
								int cordX = arrr.get(delCnt);
								int cordY = arrr.get(delCnt + 1);

								System.out.println(String.format(("%d, %d"), cordX, cordY));
								gmTable.remove(cordX, cordY, key);
								removeButton(cordX, cordY);
							} else if (resultOfAllCases == 4) {
								System.out.println("2 element was deleted ");
								ArrayList<Integer> arrr = removeElementFromArray(buttGTable, key, resultOfAllCases);
								int cordX = arrr.get(delCnt);
								int cordY = arrr.get(delCnt + 1);

								int cordX2 = arrr.get(delCnt + 2);
								int cordY2 = arrr.get(delCnt + 3);

								System.out.println(String.format(("%d, %d"), cordX, cordY));
								System.out.println(String.format(("%d, %d"), cordX2, cordY2));

								gmTable.remove(cordX, cordY, key);
								gmTable.remove(cordX2, cordY2, key);
								removeButton(cordX, cordY);
								removeButton(cordX2, cordY2);
							} else if (resultOfAllCases == 5) {
								System.out.println("You win");								
								gmTable.reset();
								// show wind dialog
								Alert alert = new Alert(AlertType.INFORMATION);
								alert.setHeaderText(null);
								alert.setContentText("Congratulations!!!! " + gmTable.giveKeyType(key) + " has won the game with " + WinOCnt
										+ " steps. Are you readyyyyyyy for new oneeee????");
								alert.showAndWait();
								resetGame();
								num = 0;
								playerLabel.setText("Player X");
								WinOCnt = 0;
							}
						}
					}					
				});
				
			}
		}
		}

	// removes button from table
	public void removeButton(int xButton, int yButton) {
		Button button = (Button) buttonsPane.getChildren().get(xButton * 10 + yButton);
		button.setText("");
		button.setDisable(false);

	}

	public void resetGame() {
		for (int i = 0; i < GameTable.maxx; i++) {
			for (int j = 0; j < GameTable.maxx; j++) {
				Button btns = (Button) buttonsPane.getChildren().get(i * 10 + j);
				btns.setText("");
				btns.setDisable(false);

			}
		}
	}

	// return coordinates of random buttons
	public ArrayList<Integer> removeElementFromArray(int buttArray[][], int key, int count) {
		ArrayList<AddtoArrayList> keyList = new ArrayList<AddtoArrayList>();

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (buttArray[i][j] == key) {
					keyList.add(new AddtoArrayList(i, j));
				}
			}
		}

		Random rnd = new Random();
		Random rnd2 = new Random();
		int delElm,  delElm2 = 0;		do{
			delElm = rnd.nextInt(keyList.size());	
			delElm2 = rnd2.nextInt(keyList.size());		}
		while(delElm == delElm2);

		AddtoArrayList arr = keyList.get(delElm);
		AddtoArrayList arr2 = keyList.get(delElm2);

		int xButton = arr.getI();
		int yButton = arr.getJ();

		int xButton2 = arr2.getI();
		int yButton2 = arr2.getJ();

		if (count == 3) {
			// add 1 random button cord to arrList
			deletedElement.clear();
			deletedElement.add(xButton);
			deletedElement.add(yButton);

		} else if (count == 4) {
			// add 2 random button cord to arrList
			deletedElement.clear();
			deletedElement.add(xButton);
			deletedElement.add(yButton);
			deletedElement.add(xButton2);
			deletedElement.add(yButton2);

		}

		return deletedElement;

	}

	// check for sequences vertically
	private int checkForSequencesVertically(int[][] buttGTable, int col, int row, int key) { 
						int rightCnt = 0;
				int leftCnt = 0;
				int totalCnt = 0;
			    
			    	int colNewLeft = col - 1;
			    	while( colNewLeft >= 0){
			    		if(buttGTable[colNewLeft][row] == key){
			    			leftCnt++;
			    			colNewLeft--;
			    		}
			    		else{
			    			colNewLeft = -1;			    					;
			    		}
			    	}
			    
			    	int colNewRight = col + 1;
			    	while(colNewRight < GameTable.maxx){
			    		if( buttGTable[colNewRight][row] == key){
			    			rightCnt ++;
			    			colNewRight++;
			    		}
			    		else{
			    			colNewRight = GameTable.maxx;
			    		}
			    	}
			    	
			    totalCnt = leftCnt + rightCnt + 1;
			    return totalCnt;
			}

	// check for sequences horizontally

	private int checkForSequencesHorizontally(int[][] buttGTable, int col, int row, int key) {

		int rightCnt = 0;
		int leftCnt = 0;
		int totalCnt = 0;
	    
	    	int rowNewUp = row - 1;

	    	while( rowNewUp >= 0){
	    		if(buttGTable[col][rowNewUp] == key){

	    			leftCnt++;
	    			rowNewUp--;

	    		}
	    		else{
	    			rowNewUp = -1	;
	    		}
	    	}
	    	
	    
	    	int rowNewDown = row + 1;
	    	while(rowNewDown < GameTable.maxx){
	    		if( buttGTable[col][rowNewDown] == key){
	    			rightCnt ++;
	    			rowNewDown++;
	    		}
	    		else{
	    			rowNewDown = GameTable.maxx;
	    		}
	    	}
	    	
	    totalCnt = leftCnt + rightCnt + 1;
	    return totalCnt;
		
	}

	// check for sequences diagonally from left to right
	private int checkForSequencesLeftToRightDiagonally(int[][] buttGTable, int col, int row, int key) {
		int rightCnt = 0;
		int leftCnt = 0;
		int totalCnt = 0;
	    
	    	int colNewTopLeft = col - 1;
	    	int rowNewTopLeft = row - 1;
	    	
	    	while( colNewTopLeft >= 0 && rowNewTopLeft >= 0){
	    		if(buttGTable[colNewTopLeft][rowNewTopLeft] == key){
	    			leftCnt++;
	    			colNewTopLeft--;
	    			rowNewTopLeft--;
	    		}
	    		else{
	    			colNewTopLeft = -1;
	    		}
	    	}
	    
	    	int colNewButtomRight = col + 1;
	    	int rowNewButtomRight = row + 1;
	    	while(colNewButtomRight < GameTable.maxx && rowNewButtomRight < GameTable.maxx){
	    		if( buttGTable[colNewButtomRight][rowNewButtomRight] == key){
	    			rightCnt ++;
	    			colNewButtomRight++;
	    			rowNewButtomRight++;
	    		}
	    		else{
	    			colNewButtomRight = GameTable.maxx;
	    		}
	    	}
	    	
	    totalCnt = leftCnt + rightCnt + 1;
	    return totalCnt;
	    
	}

	// check for sequences diagonally from right to left

	private int checkForSequencesRightToLeftDiagonally(int[][] buttGTable, int col, int row, int key) {
		int rightCnt = 0;
		int leftCnt = 0;
		int totalCnt = 0;
	    
	    	int colNewButtomLeft = col - 1;
	    	int rowNewButtomLeft = row + 1;
	    	
	    	while( colNewButtomLeft >= 0 && rowNewButtomLeft < GameTable.maxx){
	    		if(buttGTable[colNewButtomLeft][rowNewButtomLeft] == key){
	    			leftCnt++;
	    			colNewButtomLeft--;
	    			rowNewButtomLeft++;
	    		}
	    		else{
	    			colNewButtomLeft = -1;
	    		}
	    	}
	    
	    	int colNewTopRight = col + 1;
	    	int rowNewTopRight = row - 1;
	    	while(colNewTopRight < GameTable.maxx && rowNewTopRight >= 0){
	    		if( buttGTable[colNewTopRight][rowNewTopRight] == key){
	    			rightCnt ++;
	    			colNewTopRight++;
	    			rowNewTopRight--;
	    		}
	    		else{
	    			colNewTopRight = GameTable.maxx;
	    		}
	    	}
	    	
	    totalCnt = leftCnt + rightCnt + 1;
	    return totalCnt;

	}

}

             
