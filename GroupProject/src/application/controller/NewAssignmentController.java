/*
 * NewAssignment Controller Class
 */
package application.controller;

import java.io.IOException;

import application.model.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewAssignmentController {

	//FXML VARIABLES
	@FXML
	private Stage modal, window;
    @FXML
    private CheckBox notesBox;			//NOTES TYPE
    @FXML
    private CheckBox quizBox;			//QUIZ TYPE
    @FXML
    private CheckBox examBox;			//EXAM TYPE
    @FXML
    private CheckBox homeworkBox;		//HW TYPE
    @FXML
    private TextField nameField;		//NAME
    @FXML
    private TextField courseField;		//COURSE
    									//WE NEED TO GRAB THE DATE FIELD	//MICHAELS TASK
    @FXML
    private Button submitButton;


    //Methods
    @FXML
    void submitAssignment(ActionEvent event) {
    	System.out.println("Submitting Assignment");  //REMOVE
    	Task temp = new Task();
    	//grab all the variables from the fields
    	temp.name(nameField.getText());
    	temp.course(courseField.getText());
    	//temp.date(/*FIX ME*/);											//MICHAELS TASK
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
    	//THERESA'S TASKS:
    	//FUNCTION CALL:if type e then throw to arr list (events should print name and the due date)
    	//FUNCTION CALL:add to hashmap data (all types are added to hashmapping)

    }

    // METHODS TO HANDLE CHECK BOXES
    @FXML
    void handleNotes(ActionEvent event) {
    	if(notesBox.isSelected()){
    		homeworkBox.setSelected(false);
    		quizBox.setSelected(false);
    		examBox.setSelected(false);
    	}
    }
    @FXML
    void handleHomework(ActionEvent event) {
    	if(homeworkBox.isSelected()){
    		notesBox.setSelected(false);
    		quizBox.setSelected(false);
    		examBox.setSelected(false);
    	}
    }
    @FXML
    void handleQuiz(ActionEvent event) {
    	if(quizBox.isSelected()){
    		notesBox.setSelected(false);
    		homeworkBox.setSelected(false);
    		examBox.setSelected(false);
    	}
    }
    @FXML
    void handleExam(ActionEvent event) {
    	if(examBox.isSelected()){
    		notesBox.setSelected(false);
    		homeworkBox.setSelected(false);
    		quizBox.setSelected(false);
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