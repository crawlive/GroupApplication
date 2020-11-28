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
	String newCourseName, colorChosen;

	@FXML
	private Stage modal, window;

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
    void initialize(){
    	//create existing courses using MainModel.courses arraylist
    }

    @FXML
    void createdNewCourseName(ActionEvent event) {
    	MainModel.courses.add(courseName.getText());		//adds the course name to the arraylist
    }

    @FXML
    void clickedRed(ActionEvent event) {
    	colorChosen = "red";

    }

    @FXML
    void clickedPeach(ActionEvent event) {
    	colorChosen = "peach";

    }

    @FXML
    void clickedOrange(ActionEvent event) {
    	colorChosen = "orange";
    }

    @FXML
    void clickedYellow(ActionEvent event) {
    	colorChosen = "yellow";

    }

    @FXML
    void clickedGreen(ActionEvent event) {
    	colorChosen = "green";

    }

    @FXML
    void clickedBlue(ActionEvent event) {
    	colorChosen = "blue";

    }

    @FXML
    void clickedPurple(ActionEvent event) {
    	colorChosen = "purple";
    }

    @FXML
    void clickedSubmit(ActionEvent event) {
    	newCourseName = courseName.getText();
    	boolean courseCreated = addCourse(newCourseName);
    	if(courseCreated == true){
        	//createCourseButton(name, colorChosen); //located in AddCourseModel
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
