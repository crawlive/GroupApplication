/*
 * EditCourseController.java - Controller for the EditCourse.fxml view
 */

package application.controller;

import java.io.IOException;

import application.model.AddCourseModel;
import application.model.EditCourseModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditCourseController extends EditCourseModel {

	String updatedColor;

	@FXML
	private Stage modal, window;

	@FXML // fx:id="red, peach, orange, yellow, green, blue, purple"
	private Button red, peach, orange, yellow, green, blue, purple; // Value injected by FXMLLoader

	@FXML // fx:id="submit"
	private Button submit; // Value injected by FXMLLoader

	@FXML // fx:id="deleteCourseButton"
	private Button deleteCourseButton; // Value injected by FXMLLoader

	@FXML // fx:id="courseToDelete"
	private TextField newCourseName; // Value injected by FXMLLoader

	@FXML
	private Label courseArea; // Course Name

	public static FrontPageController parentController;

	/**
	 * setParentController
	 * 
	 * @param the parentController loaded in FrontPageController
	 * @return the parentController
	 */
	public static void setParentController(FrontPageController parentController) {
		EditCourseController.parentController = parentController;
		// System.out.println("PARENT CONTROLLER " + parentController.getCurrentView());
	}

	/**
	 * initialize
	 * 
	 * Initializes with the course information of the cell to be editted
	 */
	@FXML
	void initialize() {
		String name = CourseCell.finalName; // grab the name from the course button
		courseArea.setText(name); // Set the textArea to the courseName
		updatedColor = AddCourseModel.getColor(courseArea.getText());
	}

	/*
	 * clickedSubmit
	 *
	 * grabs the data inputed into the Edit Course fields and replaces and/or
	 * changes the color of the course
	 */
	@FXML
	void clickedSubmit(ActionEvent event) {
		String oldName = courseArea.getText();
		String newName = newCourseName.getText();

		if (newName.isEmpty() == false) { // if a value is input for a new course name
			newName = newName.replace("\n", ""); // remove the \n and
			changeCourseName(oldName, newName, updatedColor); // edit the name
		}
		changeCourseName(oldName, oldName, updatedColor);
		refreshListView();
	}

	/**
	 * clickedRed
	 * 
	 * Set the color field to red when the corresponding button is clicked
	 */
	@FXML
	void clickedRed(ActionEvent event) {
		updatedColor = "e40d0d";
	}

	/**
	 * clickedPeach
	 * 
	 * Set the color field to peach when the corresponding button is clicked
	 */
	@FXML
	void clickedPeach(ActionEvent event) {
		updatedColor = "FB634F";
	}

	/**
	 * clickedOrange
	 * 
	 * Set the color field to orange when the corresponding button is clicked
	 */
	@FXML
	void clickedOrange(ActionEvent event) {
		updatedColor = "FF4D00";
	}

	/**
	 * clickedYellow
	 * 
	 * Set the color field to yellow when the corresponding button is clicked
	 */
	@FXML
	void clickedYellow(ActionEvent event) {
		updatedColor = "F8D520";
	}

	/**
	 * clickedGreen
	 * 
	 * Set the color field to green when the corresponding button is clicked
	 */
	@FXML
	void clickedGreen(ActionEvent event) {
		updatedColor = "#116936";
	}

	/**
	 * clickedBlue
	 * 
	 * Set the color field to blue when the corresponding button is clicked
	 */
	@FXML
	void clickedBlue(ActionEvent event) {
		updatedColor = "#305a8c";
	}

	/**
	 * clickedPurple
	 * 
	 * Set the color field to purple when the corresponding button is clicked
	 */
	@FXML
	void clickedPurple(ActionEvent event) {
		updatedColor = "#9B51E0";
	}

	/**
	 * refreshListView
	 * 
	 * refreshes the list views on front page appropriately
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

	/**
	 * deleteCourse
	 *
	 * Deletes the course and all related mentions in every collection
	 */
	// if pressed, look for course named the same and remove it
	@FXML
	void deleteCourse(ActionEvent event) {
		String name = courseArea.getText();
		name = name.replace("\n", ""); // remove the \n and
		deleteCourse(name);
		System.out.println(courses);
		System.out.println(colors);
		refreshListView();
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
