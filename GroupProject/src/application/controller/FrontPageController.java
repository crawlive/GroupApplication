/*
 * FrontPage Controller Class
 */
package application.controller;

import application.Main;
import application.model.MainModel;
import application.model.Task;
import application.model.TextModel;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
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
    private TitledPane todoPane, completedPane;

    @FXML
    private ListView<Task> todoListView, completedListView, dateListView, eventsListView;

    @FXML
    private ListView<String> courseView;

    @FXML
    private TextArea notes_textarea;

    @FXML
    private Button notes_save;


    private ObservableList<Task> todoList;
    private ObservableList<Task> completedTasks;
    private ObservableList<Task> listByDate;
    private ObservableList<Task> events;
    private ObservableList<String> courses;

	// ------------- SCENE CHANGES ----------------//

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
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Stage modal = initModal(modalPane, window);

		AddCourseController controller = loader.getController();
		controller.passStages(modal, window);

		modal.show();
	}

	/*
	 * openEditCourse
	 *
	 * Opens edit course scene for a particular course when user clicks an existing
	 * course button
	 */
	@FXML
	void openEditCourse(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/EditCourse.fxml"));

		AnchorPane modalPane = loader.load();
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
	 * Initializes a new modal to appear over a darkened version of the current
	 * stage.
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

	// -------------LIST VIEW FUNCTIONS----------------//

	/*
	 * toggleTodo
	 *
	 * Action when date toggle button is clicked. Calls loadTodo view to load the
	 * todo list.
	 */
	@FXML
	void toggleTodo(ActionEvent event) {
		loadTodoView();
	}

	/*
	 * toggleDate
	 *
	 * Action when the date toggle button is clicked. Calls loadTodo view to load
	 * the date list.
	 */
	@FXML
	void toggleDate(ActionEvent event) {
		loadDateView();
	}

	/*
	 * saveNotes
	 *
	 * Grab the string from the text area and save it to the notes.txt
	 */
	@FXML void saveNotes(ActionEvent event){
		String notes = notes_textarea.getText();
		try {
			TextModel.updateNotes(notes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * loadTodoView
	 *
	 * Load/display the list view(s) for the upcoming week of tasks and completed
	 * tasks.
	 */
	void loadTodoView() {
		System.out.println("loading todo view");
		dateListView.setVisible(false);
		dateListView.setManaged(false);

		todoPane.setVisible(true);
		todoPane.setManaged(true);
		completedPane.setVisible(true);
		completedPane.setManaged(true);

		todoList = FXCollections.observableArrayList();
		completedTasks = FXCollections.observableArrayList();

		int size = MainModel.taskMap.size();
		for (int i = 1; i < size; i++) {
			Task curr = MainModel.taskMap.get(i);

			if (curr!=null) {
				todoList.add(MainModel.taskMap.get(i));
			}
		}

		completedTasks.addAll(MainModel.getSortedCompletedArray());	
		
		// add observable list to list view
		todoListView.setItems(todoList);
		completedListView.setItems(completedTasks);

		// customize the list view cells
		todoListView.setCellFactory(todoListView -> new TaskCell(this, false));
		completedListView.setCellFactory(completedListView -> new TaskCell(this, true));

	}

	/*
	 * loadDateView
	 *
	 * Load/display the list view for tasks ordered by date
	 */
	void loadDateView() {
		dateListView.setVisible(true);
		dateListView.setManaged(true);

		todoPane.setVisible(false);
		todoPane.setManaged(false);
		completedPane.setVisible(false);
		completedPane.setManaged(false);

		listByDate = FXCollections.observableArrayList();
		
		int size = MainModel.getSortedDateArray().size();
		for (int i = 1; i < size; i++) {
			Task curr = MainModel.getSortedDateArray().get(i);

			if (curr!=null) {
				listByDate.add(MainModel.getSortedDateArray().get(i));
			}
		}

		// add observable list to list view
		dateListView.setItems(listByDate);

		// customize the list view cells
		dateListView.setCellFactory(dateListView -> new DateTaskCell());
	}

	/*
	 * loadEventsView
	 *
	 * Load/display the list view for events
	 */
	void loadEventsView() {
		events = FXCollections.observableArrayList();
		
		events.addAll(MainModel.getSortedEvents());
		
		// add observable list to list view
		eventsListView.setItems(events);

		// customize the list view cells
		eventsListView.setCellFactory(eventsListView -> new EventCell(this));

		if (events.size() > 0) {
			noEventsMsg.setVisible(false);
			noEventsMsg.setManaged(false);
		} else {
			noEventsMsg.setVisible(true);
			noEventsMsg.setManaged(true);
		}
    }

    /*
     * loadCourseView
     *
     * Load/display the list view for events
     */
    void loadCourseView() {

    	courses = FXCollections.observableArrayList();

		int size = MainModel.courses.size();
		for (int i = 1; i < size; i++) {
			courses.add(MainModel.courses.get(i));
		}

		// add observable list to list view
		courseView.setItems(courses);

    	// customize the list view cells
		courseView.setCellFactory(courseView -> new CourseCell(this));

    }

    /*
     * loadNotesView
     *
     * Load/display the list view for events
     */
    void loadNotesView() {
    	String text = TextModel.importNotes();
    	notes_textarea.setText(text);
    }


	// -------------INITIALIZATION----------------//

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadTodoView();
		loadEventsView();
		loadCourseView();
	}
}
