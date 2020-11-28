/*
 * AddCourse Controller Class
 */

package application.controller;

import java.io.IOException;
import java.util.ResourceBundle;

import application.model.AddCourseModel;
import application.model.MainModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCourseController extends AddCourseModel{
	//variables
	String newCourseName;
  	
  	int lastClicked = 1;

	@FXML
	private Stage modal, window;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // fx:id="courseName"
    private TextField courseName; // Value injected by FXMLLoader

    @FXML // fx:id="red, peach, orange, yellow, green, blue, purple"
    private Button red, peach, orange, yellow, green, blue, purple; // Value injected by FXMLLoader
    
    @FXML // fx:id="submitNewCourse"
    private Button submitNewCourse; // Value injected by FXMLLoader

    @FXML
    void initialize(){
    	//create existing courses using MainModel.courses arraylist
    }

    @FXML
    void createdNewCourseName(ActionEvent event) {
    	MainModel.courses.add(courseName.getText());		//adds the course name to the arraylist
    }

    @FXML
    void clickedSubmit(ActionEvent event) {
    	newCourseName = courseName.getText();
    	boolean courseCreated = addCourse(newCourseName);
    	//what color was selected
    	red.setOnAction(e->lastClicked=1);
    	peach.setOnAction(e->lastClicked=2);
    	orange.setOnAction(e->lastClicked=3);
    	yellow.setOnAction(e->lastClicked=4);
    	green.setOnAction(e->lastClicked=5);
    	blue.setOnAction(e->lastClicked=6);
    	purple.setOnAction(e->lastClicked=7);
    	String colorPicked = getColor(lastClicked);
    	if(courseCreated == true){
        	createCourseButton(newCourseName, colorPicked); //located in AddCourseModel
    	}
    }


    /*
     * close
     *
     * Closes modal and removes effect from the front page window
     */
    @FXML
    void close(ActionEvent event) throws IOException {
    	window.getScene().getRoot().setEffect(null);
        modal.close();
    }


    void passStages(Stage modal, Stage window) {
    	this.modal = modal;
    	this.window = window;
    }
}
