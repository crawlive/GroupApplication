/*
 * Assignment Tracker - Assignment Tracker provides a todo list for students to track all their tasks and assignments in one place.
 * 
 * By CS 34443 Team 14: Theresa Crawford, Vanessa Gerber, Taylor Kulawik, Bethany Salazar, Michael Stepp
 */

package application;

import application.model.TextModel;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

	/*
	 * start
	 * 
	 * Sets up and loads the Front Page view
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			StackPane root = (StackPane) FXMLLoader.load(getClass().getResource("view/FrontPage.fxml"));
			Scene scene = new Scene(root, 1440, 880);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Assignment Tracker");
			primaryStage.setMaximized(true); // opens full screen
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * main
	 * 
	 * Runs the application. Imports the text files, launching the JavaFX
	 * application, then saving the files.
	 */
	public static void main(String[] args) {
		TextModel.importFiles();
		launch(args);
		TextModel.saveToFiles(); // after launching save everything into respective files
	}

}
