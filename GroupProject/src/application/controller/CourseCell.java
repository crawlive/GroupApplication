package application.controller;

import java.io.IOException;

import application.Main;
import application.model.AddCourseModel;
import application.model.MainModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CourseCell extends ListCell<String> {

	public static String finalName;
	private FrontPageController parentController;
	private Button courseIcon = new Button();
	
	
	/*
	 * Constructor
	 * 
	 * Creates cell instance with style configured
	 */
	public CourseCell(FrontPageController controller) {
		setParentController(parentController);
		courseIcon.getStyleClass().add("menuCourseIcon");
	}
	
	
	public void setParentController(FrontPageController parentController) {
	    this.parentController = parentController;
	}
	
	public FrontPageController getParentController() {
		return this.parentController;
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
    		configureButtonHandler();
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
		String finalColor = AddCourseModel.getColor(course);
		courseIcon.setStyle("-fx-background-color: "+ finalColor + ";");
		setGraphic(courseIcon);
	}
	
	private String getCourseName(String abbreviation) {
		String courseName = "";
		String courseAbbreviation = "";
		int size = MainModel.courses.size();
		for (int i = 1; i < size; i++) {
			String course = MainModel.courses.get(i);
			if(course.contains(" ")){
				courseAbbreviation = course.replaceAll("\\B.|\\P{L}", "").toUpperCase(); //only letters which are at the beginning of a word
			}
			else{
				courseAbbreviation = course.substring(0,2).toUpperCase();
			}
			if(abbreviation.equals(courseAbbreviation)){
				courseName = course;
			}
		}
		return courseName;
	}
	
	
	/*
	 * configureButtonHandler
	 * 
	 * If courseIcon button is pressed, edit course is opened.
	 */
	private void configureButtonHandler() {
		courseIcon.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent event) {
		        try {
		        	finalName = getCourseName(courseIcon.getText());
		        	
		        	FXMLLoader loader = new FXMLLoader();
					loader.setLocation(Main.class.getResource("view/EditCourse.fxml"));

					AnchorPane modalPane = loader.load();
					Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
					Stage modal = initModal(modalPane, window);
					EditCourseController controller = loader.getController();
					controller.passStages(modal, window);
					controller.setParentController(getParentController());

					// TODO: use EditCourse controller too pass in course info for edit
					// ...

					modal.show();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		});
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
	
}
