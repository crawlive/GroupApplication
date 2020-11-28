package application.controller;

import application.model.AddCourseModel;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;

public class CourseCell extends ListCell<String> {

	private Button courseIcon = new Button();
	
	
	/*
	 * Constructor
	 * 
	 * Creates cell instance with style configured
	 */
	public CourseCell() {
		courseIcon.getStyleClass().add("menuCourseIcon");
	}


	/*
	 * udpateItem()
	 * 
	 * Overrides default list cell visuals
	 */
	@Override
	protected void updateItem(String course, boolean empty) {
		super.updateItem(course, empty);
		
		if(empty || course == null) {
    		clearContent();
    	} else {
    		addContent(course);
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
	 * Add string data to cell by setting fx items
	 */
	private void addContent(String course) {
		setText(null);
		courseIcon.setText(AddCourseModel.findAbbreviation(course));
		// TODO: (@beth) change red to color based on course
		courseIcon.setStyle("-fx-background-color: "+ "red" + ";");
		setGraphic(courseIcon);
	}
	
}
