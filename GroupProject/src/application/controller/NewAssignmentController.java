/*
 * NewAssignmentController.java - Controller for the NewAssignment.fxml view
 */
package application.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import application.model.MainModel;
import application.model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewAssignmentController extends MainModel {

	// course list for the drop down box
	ObservableList<String> courseList = FXCollections.observableArrayList(courses);

	FrontPageController parentController;

	public void setParentController(FrontPageController parentController) {
		this.parentController = parentController;
	}

	// FXML VARIABLES
	@FXML
	private Stage modal, window;
	@FXML
	private TextField nameField; // NAME
	@FXML
	private CheckBox notesBox; // NOTES TYPE
	@FXML
	private CheckBox quizBox; // QUIZ TYPE
	@FXML
	private CheckBox examBox; // EXAM TYPE
	@FXML
	private CheckBox homeworkBox; // HW TYPE
	@FXML
	private CheckBox meetingBox; // MEETING TYPE
	@FXML
	private ChoiceBox<String> courseBox; // COURSE
	@FXML
	private DatePicker dateField; // WE NEED TO GRAB THE DATE FIELD //MICHAELS TASK
	@FXML
	private Button submitButton;

	/**
	 * initialize
	 * 
	 * Initializes course box
	 */
	@FXML
	void initialize() {
		courseBox.setValue("Select a Course");
		courseBox.setItems(courseList);
	}

	/**
	 * submitAssignment
	 * 
	 * Creates a new assignment when submit button is clicked
	 */
	@FXML
	void submitAssignment(ActionEvent event) {
		Task temp = new Task();
		// grab all the variables from the fields
		temp.name(nameField.getText());
		temp.course(courseBox.getValue());
		temp.setCompletedDate("NULL");
		temp.setCompletedYmd();
		// format/grab the date properly
		LocalDate date = dateField.getValue();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formattedString = date.format(formatter);
		temp.date(formattedString);

		// HANDLE THE TYPE
		if (notesBox.isSelected()) {
			temp.type("Notes");
		}
		if (homeworkBox.isSelected()) {
			temp.type("Homework");
		}
		if (quizBox.isSelected()) {
			temp.type("Quiz");
		}
		if (examBox.isSelected()) {
			temp.type("Exam");
		}
		if (meetingBox.isSelected()) {
			temp.type("Meeting");
		}

		addNewTask(temp); // FUNCTION CALL: add new task to the proper collections
		refreshListView();
		sendAlert("ta");
	}

	/**
	 * refreshListView
	 * 
	 * refreshes the list views on front page appropriately
	 */
	void refreshListView() {
		if (parentController.getCurrentView() == "todo") {
			parentController.loadTodoView();
		} else { // view == "date"
			parentController.loadDateView();
		}
		parentController.loadEventsView();
	}

	// ---------------TASK TYPE CHECKBOX HANDLING---------------//

	/**
	 * handleNotes
	 * 
	 * selects the notes checkbox, deselects all other checkboxes
	 */
	@FXML
	void handleNotes(ActionEvent event) {
		if (notesBox.isSelected()) {
			homeworkBox.setSelected(false);
			quizBox.setSelected(false);
			examBox.setSelected(false);
			meetingBox.setSelected(false);
		}
	}

	/**
	 * handleHomework
	 * 
	 * selects the homework checkbox, deselects all other checkboxes
	 */
	@FXML
	void handleHomework(ActionEvent event) {
		if (homeworkBox.isSelected()) {
			notesBox.setSelected(false);
			quizBox.setSelected(false);
			examBox.setSelected(false);
			meetingBox.setSelected(false);
		}
	}

	/**
	 * handleQuiz
	 * 
	 * selects the quiz checkbox, deselects all other checkboxes
	 */
	@FXML
	void handleQuiz(ActionEvent event) {
		if (quizBox.isSelected()) {
			notesBox.setSelected(false);
			homeworkBox.setSelected(false);
			examBox.setSelected(false);
			meetingBox.setSelected(false);
		}
	}

	/**
	 * handleExam
	 * 
	 * selects the exam checkbox, deselects all other checkboxes
	 */
	@FXML
	void handleExam(ActionEvent event) {
		if (examBox.isSelected()) {
			notesBox.setSelected(false);
			homeworkBox.setSelected(false);
			quizBox.setSelected(false);
			meetingBox.setSelected(false);
		}
	}

	/**
	 * handleMeeting
	 * 
	 * selects the notes meetingcheckbox, deselects all other checkboxes
	 */
	@FXML
	void handleMeeting(ActionEvent event) {
		if (meetingBox.isSelected()) {
			notesBox.setSelected(false);
			homeworkBox.setSelected(false);
			quizBox.setSelected(false);
			examBox.setSelected(false);
		}
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