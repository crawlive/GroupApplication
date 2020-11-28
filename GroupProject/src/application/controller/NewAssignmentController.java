/*
 * NewAssignment Controller Class
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

public class NewAssignmentController extends MainModel{

	//course list for the drop down box
	ObservableList<String> courseList = FXCollections.observableArrayList(courses);
	//FXML VARIABLES
	@FXML
	private Stage modal, window;
    @FXML
    private TextField nameField;			//NAME
    @FXML
    private CheckBox notesBox;				//NOTES TYPE
    @FXML
    private CheckBox quizBox;				//QUIZ TYPE
    @FXML
    private CheckBox examBox;				//EXAM TYPE
    @FXML
    private CheckBox homeworkBox;			//HW TYPE
    @FXML
    private CheckBox meetingBox;			//MEETING TYPE
    @FXML
    private ChoiceBox<String> courseBox;	//COURSE
    @FXML
    private DatePicker dateField;										//WE NEED TO GRAB THE DATE FIELD	//MICHAELS TASK
    @FXML
    private Button submitButton;

    @FXML
    void initialize(){
    	courseBox.setValue("Select a Course");
    	courseBox.setItems(courseList);
    }

    //Methods
    @FXML
    void submitAssignment(ActionEvent event) {
    	Task temp = new Task();
    	//grab all the variables from the fields
    	temp.name(nameField.getText());
    	temp.course(courseBox.getValue());

    	//format/grab the date properly
    	LocalDate date = dateField.getValue();
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/LLLL/yyyy");
    	String formattedString = date.format(formatter);
    	System.out.println("DATE: " + formattedString);
    	temp.date(formattedString);

    	//HANDLE THE TYPE
    	if(notesBox.isSelected()) {
    		temp.type("n");
    	}
    	if(homeworkBox.isSelected()) {
    		temp.type("h");
    	}
    	if(quizBox.isSelected()) {
    		temp.type("q");
    	}
    	if(examBox.isSelected()) {
    		temp.type("e");
    	}
    	if(meetingBox.isSelected()){
    		temp.type("m");
    	}

    	addNewTask(temp);	//FUNCTION CALL: add new task to the proper collections


    }

    // METHODS TO HANDLE CHECK BOXES
    @FXML
    void handleNotes(ActionEvent event) {
    	if(notesBox.isSelected()){
    		homeworkBox.setSelected(false);
    		quizBox.setSelected(false);
    		examBox.setSelected(false);
    		meetingBox.setSelected(false);
    	}
    }
    @FXML
    void handleHomework(ActionEvent event) {
    	if(homeworkBox.isSelected()){
    		notesBox.setSelected(false);
    		quizBox.setSelected(false);
    		examBox.setSelected(false);
    		meetingBox.setSelected(false);
    	}
    }
    @FXML
    void handleQuiz(ActionEvent event) {
    	if(quizBox.isSelected()){
    		notesBox.setSelected(false);
    		homeworkBox.setSelected(false);
    		examBox.setSelected(false);
    		meetingBox.setSelected(false);
    	}
    }
    @FXML
    void handleExam(ActionEvent event) {
    	if(examBox.isSelected()){
    		notesBox.setSelected(false);
    		homeworkBox.setSelected(false);
    		quizBox.setSelected(false);
    		meetingBox.setSelected(false);
    	}
    }
    @FXML
    void handleMeeting(ActionEvent event) {
    	if(meetingBox.isSelected()){
    		notesBox.setSelected(false);
    		homeworkBox.setSelected(false);
    		quizBox.setSelected(false);
    		examBox.setSelected(false);
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