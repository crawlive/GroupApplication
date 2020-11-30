/*
 * AddCourseController.Java - Controller for the AddCourse.fxml view
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

public class AddCourseController extends AddCourseModel {

	String newCourseName;
	String finalColor;

	@FXML
	private Stage modal, window;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML
	private TextField courseName; // Value injected by FXMLLoader

	@FXML
	private Button red, peach, orange, yellow, green, blue, purple;

	@FXML
	private Button submitNewCourse;

	FrontPageController parentController;

	/**
	 * setParentController
	 * 
	 * @param the parentController loaded in FrontPageController
	 * @return the parentController
	 */
	public void setParentController(FrontPageController parentController) {
		this.parentController = parentController;
	}

	/**
	 * createdNewCourseName
	 * 
	 * Sets the course name when a new name is entered in the course name text field
	 */
	@FXML
	void createdNewCourseName(ActionEvent event) {
		MainModel.courses.add(courseName.getText()); // adds the course name to the arraylist
	}

	/**
	 * clickedSubmit
	 * 
	 * Creates a new course when the submit button is pressed.
	 */
	@FXML
	void clickedSubmit(ActionEvent event) {
		newCourseName = courseName.getText();
		boolean courseCreated = addCourse(newCourseName);
		System.out.println(finalColor + " " + newCourseName);
		if (courseCreated == true) {
			createCourseButton(newCourseName, finalColor); // located in AddCourseModel
		}
		System.out.println(courses);
		System.out.println(colors);
		refreshListView();
	}

	/**
	 * clickedRed
	 * 
	 * Set the color field to red when the corresponding button is clicked
	 */
	@FXML
	void clickedRed(ActionEvent event) {
		finalColor = "e40d0d";
	}

	/**
	 * clickedPeach
	 * 
	 * Set the color field to peach when the corresponding button is clicked
	 */
	@FXML
	void clickedPeach(ActionEvent event) {
		finalColor = "FB634F";
	}

	/**
	 * clickedOrange
	 * 
	 * Set the color field to Orange when the corresponding button is clicked
	 */
	@FXML
	void clickedOrange(ActionEvent event) {
		finalColor = "FF4D00";
	}

	/**
	 * clickedYellow
	 * 
	 * Set the color field to yellow when the corresponding button is clicked
	 */
	@FXML
	void clickedYellow(ActionEvent event) {
		finalColor = "F8D520";
	}

	/**
	 * clickedGreen
	 * 
	 * Set the color field to green when the corresponding button is clicked
	 */
	@FXML
	void clickedGreen(ActionEvent event) {
		finalColor = "#116936";
	}

	/**
	 * clickedBlue
	 * 
	 * Set the color field to blue when the corresponding button is clicked
	 */
	@FXML
	void clickedBlue(ActionEvent event) {
		finalColor = "#305a8c";
	}

	/**
	 * clickedPurple
	 * 
	 * Set the color field to purple when the corresponding button is clicked
	 */
	@FXML
	void clickedPurple(ActionEvent event) {
		finalColor = "#9B51E0";
	}

	/**
	 * refreshListView
	 * 
	 * Refreshes the list views on front page appropriately
	 */
	void refreshListView() {
		System.out.println(parentController.getCurrentView());
		if (parentController.getCurrentView().equals("todo")) {
			parentController.loadTodoView();
		} else { // view == "date"
			parentController.loadDateView();
		}
		parentController.loadEventsView();
		parentController.loadCourseView();
	}

	// ---------------MODAL---------------//

	/**
	 * close
	 *
	 * Closes modal and removes effect from the front page window
	 */
	@FXML
	void close(ActionEvent event) throws IOException {
		window.getScene().getRoot().setEffect(null);
		modal.close();
	}

	/**
	 * passStages
	 * 
	 * Passes in the current stage and modal to this controller by setting
	 * this.modal and this.window
	 */
	void passStages(Stage modal, Stage window) {
		this.modal = modal;
		this.window = window;
	}

}
