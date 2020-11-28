/*
 * EditCourse Controller Class
 */

package application.controller;

import java.io.IOException;

import application.model.EditCourseModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditCourseController extends EditCourseModel{

	String colorChosen;
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
    private Label courseArea; //Course Name

    @FXML
    void initialize(){
    	//String name = /*FINISH ME*/
    	courseArea.setText("Applications");		//Set the textArea to the courseName
    }

    /*
     * clickedSubmit
     *
     * grabs the data inputted into the Edit Course fields and replaces and/or changes the color of the course
     */
    @FXML
    void clickedSubmit(ActionEvent event) {
    	String oldName = courseArea.getText();
    	String newName = newCourseName.getText();
    	if(newName.isEmpty() == false){					//if a value is input for a new course name
    		newName = newName.replace("\n", "");		//remove the \n and
    		changeCourseName(oldName, newName);			//edit the name
    	}
    	//changeCourseColor(colorChosen);				//change the color of course

    }

    /*
     * deleteCourse
     *
     * Deletes the course and all related mentions in every collection
     */
    //if pressed, look for course named the same and remove it
    @FXML
    void deleteCourse(ActionEvent event) {
    	String name = courseArea.getText();
    	name = name.replace("\n", "");				//remove the \n and
    	deleteCourse(name);
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

//TESTING PRINTS
/*/TESTING PRINT STATEMENTS
for (int index: MainModel.taskMap.keySet()){
    int key = index;
    String value = MainModel.taskMap.get(index).toString();
    System.out.println("KEY: " + key + " " + "Value: " + value);
}*/