package application.newAssignment;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class NewAssignmentController {
	
	//VARIBLES
	String assignmentName;
	String course;
	String date;
	int type;
	
	//FXML VARIABLES
    @FXML
    private CheckBox notesBox;
    @FXML
    private CheckBox quizBox;
    @FXML
    private CheckBox examBox;
    @FXML
    private TextField nameField;
    @FXML
    private CheckBox homeworkBox;   
    @FXML
    private Button submitButton;   
    @FXML
    private TextField courseField;
    
    //Methods
    @FXML
    void submitAssignment(ActionEvent event) {
    	System.out.println("Submitting Assignment");
    	assignmentName = nameField.getText();
    	if(notesBox.isSelected()) {
    		type=1;
    	}
    	if(homeworkBox.isSelected()) {
    		type=2;
    	}
    	if(quizBox.isSelected()) {
    		type=3;
    	}
    	if(examBox.isSelected()) {
    		type=4;
    	}
    	System.out.println(assignmentName);
    	System.out.println("Type: " + type);
    	
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
}