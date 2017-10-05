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

package view;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("SleazyGomoku.fxml"));
			Scene scene = new Scene(root,315,305);
			scene.getStylesheets().add(getClass().getResource("SleazyGomoku.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Sleazy Gomoku");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
