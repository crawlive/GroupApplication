package application.model;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AddCourseModel extends MainModel{

	public static String courseAbb;

	/*
     * addCourse
     *
     * add a new course to the the courses arraylist, throw alerts for existing and completion
     */
	public static boolean addCourse(String name){
		boolean retVal;
		if(courses.contains(name) == true){
			sendAlert("ce");
			retVal = false;
		}
		else{
			courses.add(name);
			sendAlert("ca");
			retVal = true;
		}
		return retVal;
	}

	/*
     * createCourseButton
     *
     * Takes the name of the new course and creates the button for the new course
     */
	public static void createCourseButton(String name, String color){
		colors.add(color);
		courseAbb = findAbbreviation(name);
	}
	
	/*
     * getCourse
     *
     * Closes modal and removes effect from the front page window
     */
    protected String getColor(int lastClicked) {
    	String colorPicked = "";
    	if(lastClicked == 1){
    		colorPicked = "red";
    	}
    	if(lastClicked == 2){
    		colorPicked = "peach";
    	}
    	if(lastClicked == 3){
    		colorPicked = "orange";
    	}
    	if(lastClicked == 4){
    		colorPicked = "yellow";
    	}
    	if(lastClicked == 5){
    		colorPicked = "green";
    	}
    	if(lastClicked == 6){
    		colorPicked = "blue";
    	}
    	if(lastClicked == 7){
    		colorPicked = "purple";
    	}
		return colorPicked;
    }

	/*
     * findAbbreviation
     *
     * Takes a string and returns the abbreviation string
     */
	public static String findAbbreviation(String name){
		String abbreviation;
		if(name.contains(" ")){
			abbreviation = name.replaceAll("\\B.|\\P{L}", "").toUpperCase(); //only letters which are at the beginning of a word
		}
		else{
			abbreviation = name.substring(0,2).toUpperCase();
		}
		System.out.println("code " + abbreviation );
		return abbreviation;
	}

}