package application;

import application.model.MainModel;
import application.model.TextModel;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			StackPane root = (StackPane)FXMLLoader.load(getClass().getResource("view/FrontPage.fxml"));
			Scene scene = new Scene(root,1440,880);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Assignment Tracker");
		    primaryStage.setMaximized(true); // opens full screen
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TextModel.importData();				//import all data to respective files
		TextModel.importEvents();
		TextModel.importCompleted();
		//MainModel.checkDates();
		launch(args);
		TextModel.saveToFiles();			//after launching save everything into respective files
	}
}
