/*
 * EditCourseModel.java - Model for editting courses
 */
package application.model;

public class EditCourseModel extends MainModel {

	/**
	 * changeCourseName
	 *
	 * find and rename all oldName with newName in all the collections
	 * 
	 * @param the old name
	 * @param the new name
	 * @param the new color
	 */
	public static void changeCourseName(String oldName, String newName, String updatedColor) {
		changeAssignments(newName, oldName); // change all associated assignments from the taskMap
		changeCourseEvents(newName, oldName); // change the course from the events array list
		changeCourseCompleted(newName, oldName);// remove the course from the completed queue
		changeCourse(newName, oldName, updatedColor); // remove the course from the course array list
	}

	/**
	 * changeCourseCompleted
	 *
	 * Update the course field for tasks in the array of completed tasks
	 * 
	 * @param the old course name for the course to be updated
	 * @param the new course name
	 */
	public static void changeCourseCompleted(String newName, String name) {
		Task temp = new Task();
		for (int i = 0; i < completedList.size(); i++) {
			temp = completedList.get(i); // grab cur value at index
			if (temp.course.contains(name)) { // if the value is found
				temp.course(newName); // change the name
				completedList.set(i, temp); // set the new element
			}
		}
	}

	/**
	 * changeCourse
	 *
	 * change specified course from all collections to the new name
	 * 
	 * @param the old course name for the course to be updated
	 * @param the new course name
	 * @param the color
	 */
	public static void changeCourse(String newName, String name, String color) {
		String temp;
		for (int i = 0; i < courses.size(); i++) { // iterate through the course arr list
			temp = courses.get(i); // grab cur value at index
			if (temp.contains(name)) { // if the value is found
				courses.set(i, newName); // set the new element
				colors.set(i, color);
			}
		}
	}

	/**
	 * changeCourseEvents
	 *
	 * find the key for the task that contains the given string and change it to the
	 * new course name
	 * 
	 * @param the old course name for the course to be updated
	 * @param the new course name
	 */
	public static void changeCourseEvents(String newName, String name) {
		Task temp = new Task();
		for (int i = 0; i < events.size(); i++) {
			temp = events.get(i); // grab cur value at index
			if (temp.course.contains(name)) { // if the value is found
				temp.course(newName); // change the name
				events.set(i, temp); // set the new element
			}
		}
	}

	/**
	 * changeAssignments
	 *
	 * find the key for the task that contains the given string and change it to the
	 * new course name
	 * 
	 * @param the old course name for the course to be updated
	 * @param the new course name
	 */
	public static void changeAssignments(String newName, String name) {
		Task temp = new Task();
		Task temp2 = new Task();
		for (int i = 0; i < taskMap.size(); i++) { // iterate through taskMap
			temp = taskMap.get(i); // grab cur value at index
			if (temp.course.equals(name)) { // if task in course
				temp2 = temp;
				temp2.course(newName); // change the name
				taskMap.replace(i, temp2); // replace with the new element
			}
		}
	}

	/**
	 * deleteCourse
	 *
	 * remove specified course from all collections
	 * 
	 * @param the course to delete
	 */
	public static void deleteCourse(String name) {
		removeCourseAssignments(name); // remove all associated assignments from the taskMap
		removeCourseEvents(name); // remove the course from the events array list
		removeCourseCompleted(name); // remove the course from the completed queue
		removeCourse(name); // remove the course from the course array list
	}

	/**
	 * removeCourse
	 *
	 * find the key for the task that contains the given string and remove it from
	 * the events arraylist
	 * 
	 * @param the course to remove
	 */
	public static void removeCourse(String name) {
		String temp;
		for (int i = 0; i < courses.size(); i++) { // iterate through the course arr list
			temp = courses.get(i); // grab cur value at index
			if (temp.contains(name)) { // if the value is found
				courses.remove(i); // remove it
				colors.remove(i);
			}
		}
	}

	/**
	 * removeCourseCompleted
	 *
	 * find the key for the task that contains the given string and remove it from
	 * the completed arraylist
	 * 
	 * @param the course to remove
	 */
	public static void removeCourseCompleted(String name) {
		Task temp = null;
		for (int i = 0; i < completedList.size(); i++) { // iterate through the course arr list
			temp = completedList.get(i); // grab cur value at index
			if (temp.course.contains(name)) { // if the value is found
				completedList.remove(i); // remove it
			}
		}
	}

	/**
	 * removeCourseEvents
	 *
	 * find the key for the task that contains the given string and remove it from
	 * the events arraylist
	 */
	public static void removeCourseEvents(String name) {
		Task temp = null;
		for (int i = 0; i < events.size(); i++) { // iterate through the course arr list
			temp = events.get(i); // grab cur value at index
			if (temp.course.contains(name)) { // if the value is found
				events.remove(i); // remove it
			}
		}
	}

	/**
	 * removeCourseAssignments
	 *
	 * find the key for the task that contains the given string and remove it from
	 * the taskMap
	 * 
	 * @param the course to remove
	 */
	public static void removeCourseAssignments(String name) {
		taskMap.entrySet().removeIf(entry -> (name.equals(entry.getValue().course))); // using lambda expression to
																						// remove
																						// values that contain the
																						// course
		updateKeys();
	}
}