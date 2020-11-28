package application.model;

public class AddCourseModel extends MainModel{

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
		String courseAbb = findAbbreviation(name);						//find the name that will be in the circle
		//FINISH ME
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
			abbreviation = name.substring(0,2);
		}
		System.out.println("code " + abbreviation );
		return abbreviation;
	}

}