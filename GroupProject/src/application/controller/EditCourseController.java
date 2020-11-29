/*
 * EditCourse Controller Class
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

public class EditCourseController extends EditCourseModel{
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
    private Label courseArea; //Course Name

    public static FrontPageController parentController;

	public static void setParentController(FrontPageController parentController) {
        EditCourseController.parentController = parentController;
        //System.out.println("PARENT CONTROLLER " + parentController.getCurrentView());
    }
    
    @FXML
    void initialize(){
    	String name = CourseCell.finalName;			//grab the name from the course button
    	courseArea.setText(name);		//Set the textArea to the courseName
    	updatedColor = AddCourseModel.getColor(courseArea.getText());
    }

    /*
     * clickedSubmit
     *
     * grabs the data inputed into the Edit Course fields and replaces and/or changes the color of the course
     */
    @FXML
    void clickedSubmit(ActionEvent event) {
    	String oldName = courseArea.getText();
    	String newName = newCourseName.getText();
    	
    	if(newName.isEmpty() == false){					//if a value is input for a new course name
    		newName = newName.replace("\n", "");		//remove the \n and
    		changeCourseName(oldName, newName, updatedColor);			//edit the name
    	}
    	changeCourseName(oldName, oldName, updatedColor);
    	refreshListView();
    }
    @FXML
    void clickedRed(ActionEvent event) { updatedColor = "e40d0d";}
    @FXML
    void clickedPeach(ActionEvent event) { updatedColor = "FB634F";}
    @FXML
    void clickedOrange(ActionEvent event) { updatedColor = "FF4D00";}
    @FXML
    void clickedYellow(ActionEvent event) { updatedColor = "F8D520";}
    @FXML
    void clickedGreen(ActionEvent event) { updatedColor = "#116936";}
    @FXML
    void clickedBlue(ActionEvent event) { updatedColor = "#305a8c";}
    @FXML
    void clickedPurple(ActionEvent event) { updatedColor = "#9B51E0";}
    
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
    	System.out.println(courses);
		System.out.println(colors);
    	refreshListView();
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