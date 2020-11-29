package application.controller;

import application.model.Task;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class EventCell extends ListCell<Task> {

	private GridPane gridPane = new GridPane();
	private Button courseIcon = new Button();
	private Label taskLbl = new Label();
	private Label courseLbl = new Label();
	private Label dateLbl = new Label();
	
	@SuppressWarnings("unused")
	private FrontPageController parentController;
	
	/*
	 * Constructor
	 * 
	 * Creates cell instance with grid and labels configured
	 */
	public EventCell(FrontPageController controller) {
		setParentController(controller);
		configureGrid();
		configureItems();
		addItemsToGrid();
	}
	
	public void setParentController(FrontPageController parentController) {
	    this.parentController = parentController;
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
	    ColumnConstraints col2 = new ColumnConstraints();
	    col2.setHgrow(Priority.ALWAYS);
	    ColumnConstraints col3 = new ColumnConstraints();
	    gridPane.getColumnConstraints().addAll(col1,col2,col3);
	    
	    // right align labels
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
		dateLbl.getStyleClass().add("cellDate");
	}
	
	
	/*
	 * addItemsToGrid()
	 * 
	 * Adds fx items to grid
	 */
	private void addItemsToGrid() {
		gridPane.add(courseIcon, 0, 0, 1, 2);
		gridPane.add(taskLbl, 1, 0);
		gridPane.add(courseLbl, 1, 1);
		gridPane.add(dateLbl, 3, 1, 1, 2);
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
		dateLbl.setText(task.getDate());
		setGraphic(gridPane);
	}
	
}
