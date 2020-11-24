/*
 * EditCourse Controller Class
 */

package application.controller;

import java.io.IOException;

import application.model.MainModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditCourseController {

	@FXML
	private Stage modal, window;

    @FXML // fx:id="red"
    private Button red; // Value injected by FXMLLoader

    @FXML // fx:id="orange"
    private Button orange; // Value injected by FXMLLoader

    @FXML // fx:id="green"
    private Button green; // Value injected by FXMLLoader

    @FXML // fx:id="blue"
    private Button blue; // Value injected by FXMLLoader

    @FXML // fx:id="submit"
    private Button submit; // Value injected by FXMLLoader

    @FXML // fx:id="peach"
    private Button peach; // Value injected by FXMLLoader

    @FXML // fx:id="yellow"
    private Button yellow; // Value injected by FXMLLoader

    @FXML // fx:id="purple"
    private Button purple; // Value injected by FXMLLoader

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

    @FXML
    void clickedRed(ActionEvent event) {
    	System.out.println("Selected red");

    }

    @FXML
    void clickedPeach(ActionEvent event) {
    	System.out.println("Selected peach");

    }

    @FXML
    void clickedOrange(ActionEvent event) {
    	System.out.println("Selected orange");

    }

    @FXML
    void clickedYellow(ActionEvent event) {
    	System.out.println("Selected yellow");

    }

    @FXML
    void clickedGreen(ActionEvent event) {
    	System.out.println("Selected green");

    }

    @FXML
    void clickedBlue(ActionEvent event) {
    	System.out.println("Selected blue");

    }

    @FXML
    void clickedPurple(ActionEvent event) {
    	System.out.println("Selected purple");

    }

    @FXML
    void clickedSubmit(ActionEvent event) {
    	String oldName = courseArea.getText();
    	String newName = newCourseName.getText();
    	newName = newName.replace("\n", "");
    	for (int index: MainModel.taskMap.keySet()){
            int key = index;
            String value = MainModel.taskMap.get(index).toString();
            System.out.println("KEY: " + key + " " + "Value: " + value);
    	}
    	MainModel.changeCourseName(oldName, newName);		//edit the name
    	//TESTING PRINT STATEMENTS
    	for (int index: MainModel.taskMap.keySet()){
            int key = index;
            String value = MainModel.taskMap.get(index).toString();
            System.out.println("KEY: " + key + " " + "Value: " + value);
    	}
    	//change color of course (last color pressed is new color)

    }

    //if pressed, look for course named the same and remove it
    @FXML
    void deleteCourse(ActionEvent event) {
    	String name = courseArea.getText();
    	MainModel.deleteCourse(name);
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
