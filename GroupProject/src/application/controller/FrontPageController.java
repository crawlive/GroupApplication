package application.controller;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FrontPageController {

    @FXML
    private ToggleGroup todo_date_toggle;

    @FXML
    private Button courseBtn;

    
    /*
     * openAddTask
     * 
     * Opens add task scene when user clicks new task button
     */
    @FXML
    void openAddTask(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/NewAssignment.fxml"));
        AnchorPane newPane = loader.load();
        Scene scene = new Scene(newPane);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    
    /*
     * openAddCourse
     * 
     * Opens add course scene when user clicks add course button
     */
    @FXML
    void openAddCourse(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/AddCourse.fxml"));
        AnchorPane newPane = loader.load();
        Scene scene = new Scene(newPane);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    
    /*
     * openEditCourse
     * 
     * Opens edit course scene when user clicks edit course button
     */
    @FXML
    void openEditCourse(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/EditCourse.fxml"));
        AnchorPane newPane = loader.load();
        Scene scene = new Scene(newPane);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
