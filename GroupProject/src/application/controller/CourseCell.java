package application.controller;

import application.model.AddCourseModel;
import application.model.Task;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class CourseCell extends ListCell<Task> {

	private GridPane gridPane = new GridPane();
	private Button courseIcon = new Button();
	private Label courseLbl = new Label();
	
	
	/*
	 * Constructor
	 * 
	 * Creates cell instance with grid and labels configured
	 */
	public CourseCell() {
		configureGrid();
		configureItems();
		addItemsToGrid();
	}

	
	/*
	 * configureGrid()
	 * 
	 * Configure grid layout / styling
	 */
	private void configureGrid() {	
		// adjust spacing
		gridPane.setHgap(10);
		// column configurations
		ColumnConstraints col1 = new ColumnConstraints();
	    gridPane.getColumnConstraints().addAll(col1);
	    
	    // right align labels
		GridPane.setHalignment(courseLbl, HPos.LEFT);
	}

	
	/*
	 * configureItems()
	 * 
	 * Configure grid items, attach styling
	 */
	private void configureItems() {
		courseIcon.getStyleClass().add("smallCourseIcon");
		courseLbl.getStyleClass().add("cellSubHeader");
	}
	
	/*
	 * addItemsToGrid()
	 * 
	 * Adds fx items to grid
	 */
	private void addItemsToGrid() {
		gridPane.add(courseIcon, 0, 0, 1, 2);
		gridPane.add(courseLbl, 1, 1);
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
		courseIcon.setText(AddCourseModel.courseAbb);
		courseLbl.setText(task.getCourse());
		setGraphic(gridPane);
	}
	
}
