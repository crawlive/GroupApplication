/*
 * FrontPage Controller Class
 */
package application.controller;

import application.Main;
import application.model.Task;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FrontPageController implements Initializable {

    @FXML
    private ToggleGroup todo_date_toggle;

    @FXML
    private Button courseBtn;
    
    @FXML
    private Label noEventsMsg; // Text that shows when there are no events
    
    @FXML
    private ListView<Task> todoListView;
    
    @FXML
    private ListView<Task> eventsListView;
    
    private ObservableList<Task> tasks = FXCollections.observableArrayList();;
    private ObservableList<Task> events = FXCollections.observableArrayList();;

    /*
     * openAddTask
     *
     * Opens add task scene when user clicks new task button
     */
    @FXML
    void openAddTask(ActionEvent event) throws IOException {

    	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/NewAssignment.fxml"));

        AnchorPane modalPane = loader.load();
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage modal = initModal(modalPane, window);

        NewAssignmentController controller = loader.getController();
        controller.passStages(modal, window);

        modal.show();

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

        AnchorPane modalPane = loader.load();
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage modal = initModal(modalPane, window);

        AddCourseController controller = loader.getController();
        controller.passStages(modal, window);

        modal.show();
    }


    /*
     * openEditCourse
     *
     * Opens edit course scene for a particular course when user clicks an existing course button
     */
    @FXML
    void openEditCourse(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/EditCourse.fxml"));

        AnchorPane modalPane = loader.load();
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage modal = initModal(modalPane, window);

        EditCourseController controller = loader.getController();
        controller.passStages(modal, window);

        // TODO: use EditCourse controller too pass in course info for edit
        // ...

        modal.show();
    }


    /*
     * initModal
     *
     * Initializes a new modal to appear over a darkened version of the current stage.
     */
    Stage initModal(Pane modalPane, Stage currentStage) {
    	Scene modalScene = new Scene(modalPane);
    	Stage modal = new Stage(StageStyle.TRANSPARENT);
    	modal.initModality(Modality.WINDOW_MODAL);
    	modal.initOwner(currentStage);
    	modal.setScene(modalScene);

    	// darken current stage
    	ColorAdjust darken = new ColorAdjust();
    	darken.setBrightness(-0.5);
        currentStage.getScene().getRoot().setEffect(darken);

        return modal;
    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// dummy data
		tasks.addAll(
    			new Task("task 1", "course 1", "12/12/12", "type 1"),
    	        new Task("task 2", "course 2", "12/12/12", "type 2")
    			);
		events.addAll(
    			new Task("event 1", "course 1", "12/12/12", "type 1"),
    	        new Task("event 2", "course 2", "12/12/12", "type 2")
    			);
		
		if (events.size() > 0) {
			noEventsMsg.setVisible(false);
			noEventsMsg.setManaged(false);
		} else {
			noEventsMsg.setVisible(true);
			noEventsMsg.setManaged(true);
		}
		
		// add observable list to list view
		todoListView.setItems(tasks);
		eventsListView.setItems(events);
		
    	// customize the list view cell
    	todoListView.setCellFactory(taskListView -> new TaskCell());
    	eventsListView.setCellFactory(taskListView -> new EventCell());
	}
}
