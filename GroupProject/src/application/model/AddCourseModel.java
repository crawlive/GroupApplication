package application.model;

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
     * getColor
     *
     * 
     */
    public static String getColor(String course) {
    	int index = courses.indexOf(course);
    	String color = colors.get(index);
    	return color;
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
		return abbreviation;
	}

}