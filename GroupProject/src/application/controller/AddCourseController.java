/**
 * Sample Skeleton for 'Sample.fxml' Controller Class
 */

package application.controller;

import java.io.IOException;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AddCourseController {
	//variables
	String newCourseName;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    
    @FXML // fx:id="courseName"
    private TextField courseName; // Value injected by FXMLLoader

    @FXML // fx:id="red"
    private Button red; // Value injected by FXMLLoader

    @FXML // fx:id="orange"
    private Button orange; // Value injected by FXMLLoader

    @FXML // fx:id="green"
    private Button green; // Value injected by FXMLLoader

    @FXML // fx:id="blue"
    private Button blue; // Value injected by FXMLLoader

    @FXML // fx:id="peach"
    private Button peach; // Value injected by FXMLLoader

    @FXML // fx:id="yellow"
    private Button yellow; // Value injected by FXMLLoader

    @FXML // fx:id="purple"
    private Button purple; // Value injected by FXMLLoader

    @FXML // fx:id="submitNewCourse"
    private Button submitNewCourse; // Value injected by FXMLLoader


    @FXML
    void createdNewCourseName(ActionEvent event) {
   
    }

    @FXML
    void clickedRed(ActionEvent event) {
    	System.out.println("Selected red");

    }

    @FXML
    void clickedPeach(ActionEvent event) {
    	System.out.println("Selected peach");

    }

    @FXML
    void clickedOrange(ActionEvent event) {
    	System.out.println("Selected orange");

    }

    @FXML
    void clickedYellow(ActionEvent event) {
    	System.out.println("Selected yellow");

    }

    @FXML
    void clickedGreen(ActionEvent event) {
    	System.out.println("Selected green");

    }

    @FXML
    void clickedBlue(ActionEvent event) {
    	System.out.println("Selected blue");

    }

    @FXML
    void clickedPurple(ActionEvent event) {
    	System.out.println("Selected purple");
    }

    @FXML
    void clickedSubmit(ActionEvent event) {
    	System.out.println("Created New Course");
    	newCourseName = courseName.getText();
    	System.out.println("Course Name: " + newCourseName);
    }
    
    /*
     * close
     * Closes scene and cancels action. Returns back to front page.
     */
    @FXML
    void close(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/FrontPage.fxml"));
        StackPane newPane = loader.load();
        Scene scene = new Scene(newPane);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
