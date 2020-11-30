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
import application.model.*;
import javafx.stage.*;
import java.util.*;
import javafx.scene.control.*;
import javafx.event.*;

public class AddCourseController extends AddCourseModel{
	//variables
	String newCourseName;
	String finalColor;

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

    FrontPageController parentController;

	public void setParentController(FrontPageController parentController) {
        this.parentController = parentController;
    }
    
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
    	System.out.println(finalColor + " "+ newCourseName);
    	if(courseCreated == true){
        	createCourseButton(newCourseName, finalColor); //located in AddCourseModel
    	}
    	System.out.println(courses);
    	System.out.println(colors);
    	refreshListView();
    }
    
    @FXML
    void clickedRed(ActionEvent event) { finalColor = "e40d0d";}
    @FXML
    void clickedPeach(ActionEvent event) { finalColor = "FB634F";}
    @FXML
    void clickedOrange(ActionEvent event) { finalColor = "FF4D00";}
    @FXML
    void clickedYellow(ActionEvent event) { finalColor = "F8D520";}
    @FXML
    void clickedGreen(ActionEvent event) { finalColor = "#116936";}
    @FXML
    void clickedBlue(ActionEvent event) { finalColor = "#305a8c";}
    @FXML
    void clickedPurple(ActionEvent event) { finalColor = "#9B51E0";}

    
    /*
     * refreshListView
     * 
     * refreshes the list views on front page appropriately
     */
    void refreshListView() {
    	System.out.println(parentController.getCurrentView());
    	if(parentController.getCurrentView().equals("todo")) {
    		parentController.loadTodoView();
    	}
    	else { // view == "date"
    		parentController.loadDateView();
    	}
    	parentController.loadEventsView();
    	parentController.loadCourseView();
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
