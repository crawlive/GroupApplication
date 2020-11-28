package application.controller;

import application.model.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class TaskCell extends ListCell<Task>{
	
	private GridPane gridPane = new GridPane();
	private Button courseIcon = new Button();
	private Label taskLbl = new Label();
	private Label courseLbl = new Label();
	private Label typeLbl = new Label();
	private Label dateLbl = new Label();
	private CheckBox checkCompleted = new CheckBox();
	
	
	/*
	 * Constructor
	 * 
	 * Creates cell instance with grid and labels configured
	 */
	public TaskCell() {
		configureGrid();
		configureItems();
		addItemsToGrid();
		configureCheckboxHandler();
	}

	
	/*
	 * configureGrid()
	 * 
	 * Configure grid layout
	 */
	private void configureGrid() {
		
		// adjust spacing
		gridPane.setHgap(10);
		gridPane.setPadding(new Insets(0, 0, 20, 0));
		
		// column configurations
		ColumnConstraints col1 = new ColumnConstraints();
		ColumnConstraints col2 = new ColumnConstraints();
	    ColumnConstraints col3 = new ColumnConstraints();
	    col3.setHgrow(Priority.ALWAYS);
	    ColumnConstraints col4 = new ColumnConstraints();
	    gridPane.getColumnConstraints().addAll(col1,col2,col3,col4);
	    
	    // right align labels
	    GridPane.setHalignment(typeLbl, HPos.RIGHT);
		GridPane.setHalignment(dateLbl, HPos.RIGHT);
	}

	
	/*
	 * configureItems()
	 * 
	 * Configure grid items, attach styling
	 */
	private void configureItems() {
		courseIcon.getStyleClass().add("smallCourseIcon");
		taskLbl.getStyleClass().add("cellHeader");
		courseLbl.getStyleClass().add("cellSubHeader");
		typeLbl.getStyleClass().add("cellHeader");
		dateLbl.getStyleClass().add("cellDate");
	}
	
	
	/*
	 * addItemsToGrid()
	 * 
	 * Adds fx items to grid
	 */
	private void addItemsToGrid() {
		gridPane.add(checkCompleted, 0, 0, 1, 2);
		gridPane.add(courseIcon, 1, 0, 1, 2);
		gridPane.add(taskLbl, 2, 0);
		gridPane.add(courseLbl, 2, 1);
		gridPane.add(typeLbl, 3, 0, 1, 1);
		gridPane.add(dateLbl, 3, 1, 1, 1);
	}

	/*
	 * udpateItem()
	 * 
	 * Overrides default list cell visuals
	 */
	@Override
	protected void updateItem(Task task, boolean empty) {
		super.updateItem(task, empty);
		
		if(empty || task == null) {
    		clearContent();
    	} else {
    		addContent(task);
    	}
	}


	/*
	 * clearContent()
	 * 
	 * Remove cell text and graphics
	 */
	private void clearContent() {
		setText(null);
		setGraphic(null);		
	}
	
	
	/*
	 * addContent
	 * 
	 * Add task data to cell by setting fx items
	 */
	private void addContent(Task task) {
		setText(null);
		courseIcon.setText("AP");
		taskLbl.setText(task.getName());
		courseLbl.setText(task.getCourse());
		typeLbl.setText(task.getType());
		dateLbl.setText(task.getDate());
		checkCompleted.setIndeterminate(false);
		checkCompleted.setSelected(false);
		setGraphic(gridPane);
	}
	
	/*
	 * configureCheckboxHandler
	 * 
	 * If box is checked, task is moved to completed.
	 * If box is unchecked, task is moved todo list.
	 */
	private void configureCheckboxHandler() {
		EventHandler<ActionEvent> eh = new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		        if (event.getSource() instanceof CheckBox) {
		            if(checkCompleted.isSelected()) {
		            	// TODO: move task to completed
		            	System.out.println("move task to completed");
		            	refreshTodoList();
		            	refreshCompletedList();
		            } else {
		            	// TODO: move task to todo list
		            	System.out.println("move task to todo list");
		            	refreshCompletedList();
		            	refreshTodoList();
		            }
		        }
		    }
		};

		checkCompleted.setOnAction(eh);
	}
	
	/*
	 * refreshTodoList
	 * 
	 * Uses the FrontPageController to refresh the todo list.
	 */
	private void refreshTodoList() {
		
	}
	
	
	/*
	 * refreshCompletedList
	 * 
	 * Uses the FrontPageController to refresh the todo list.
	 */
	private void refreshCompletedList() {
		
	}
	
}
