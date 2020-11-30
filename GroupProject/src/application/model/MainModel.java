/*
 * MainModel.java - Model holding all collection based functions for the program
 */
package application.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MainModel {

	public static HashMap<Integer, Task> taskMap = new HashMap<Integer, Task>(); // handles data from data.txt
	public static ArrayList<Task> events = new ArrayList<Task>();
	public static ArrayList<Task> completedList = new ArrayList<Task>();
	public static ArrayList<String> courses = new ArrayList<String>(Arrays.asList("Select a Course"));
	public static ArrayList<String> colors = new ArrayList<String>();

	/**
	 * sendAlert
	 *
	 * Sends an alert based on the passed string
	 * 
	 * @param the string
	 */
	public static void sendAlert(String s) {
		if (s.equals("ce")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("COURSE EXISTS");
			alert.setContentText("Course name already exists, enter a new name or exit please.");
			alert.showAndWait();
		}
		if (s.equals("ca")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("INFORMATION");
			alert.setHeaderText("Course Added");
			alert.setContentText("Course was successfully created!");
			alert.showAndWait();
		}
		if (s.equals("ta")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("INFORMATION");
			alert.setHeaderText("New Task Created");
			alert.setContentText("Task was successfully created!");
			alert.showAndWait();
		}

	}

	// -------------------------------TASK
	// HASHMAPING-------------------------------//

	/**
	 * updateKeys
	 *
	 * update the taskMap to host the keys in order of their occurrence in the set
	 */
	public static void updateKeys() {
		ArrayList<Task> indexArr = new ArrayList<Task>();
		indexArr.addAll(taskMap.values());
		indexArr.sort(Comparator.comparing(Task::getDate));
		taskMap.clear();
		events.clear();
		for (Task task : indexArr) {
			task.placement = indexArr.indexOf(task);
			taskMap.put(indexArr.indexOf(task), task);

			if ((task.type.equals("Exam")) || (task.type.equals("Meeting"))) { // if the task is an exam
				events.add(task); // add the task to the array list
			}
		}
	}

	/**
	 * addNewTask
	 *
	 * add a new task to the proper collections
	 * 
	 * @param the task to be added
	 */
	public static void addNewTask(Task newTask) {
		ArrayList<Task> sortedArr = new ArrayList<Task>();
		// if (newTask.type.equals("Meeting") == false) { // if the task is not a
		// meeting add the task normally
		sortedArr.addAll(taskMap.values());
		sortedArr.add(newTask);
		sortedArr.sort(Comparator.comparing(Task::getDate));
		taskMap.clear();
		for (Task task : sortedArr) {
			task.placement = sortedArr.indexOf(task);
			taskMap.put(sortedArr.indexOf(task), task);
		}
		// }
		if ((newTask.type.equals("Exam") == true) || (newTask.type.equals("Meeting") == true)) { // if the task is an
																									// exam
			events.add(newTask); // add the task to the array list
		}
	}

	// -------------------------------SORTED LISTS-------------------------------//

	/**
	 * getSortedDateArray
	 *
	 * Sorts the hash map values by date and returns it as a sorted array list
	 */
	public static ArrayList<Task> getSortedDateArray() {
		ArrayList<Task> sortedList = new ArrayList<Task>();
		sortedList.addAll(taskMap.values());

		for (Task t : MainModel.completedList) {
			sortedList.add(t);
			;
		}
		sortedList.sort(Comparator.comparing(Task::getYmd));
		return sortedList;
	}

	/**
	 * getSortedEvents
	 *
	 * Sorts the events list and returns it
	 */
	public static ArrayList<Task> getSortedEvents() {
		ArrayList<Task> sortedList = new ArrayList<Task>();
		sortedList.addAll(events);
		sortedList.sort(Comparator.comparing(Task::getYmd));
		return sortedList;
	}

	/**
	 * getSortedCompletedArray
	 *
	 * sorts the queue by completion date (most recent first) returns it as a sorted
	 * array list
	 */
	public static ArrayList<Task> getSortedCompletedArray() {
		ArrayList<Task> sortedList = new ArrayList<Task>();
		sortedList.addAll(completedList);
		sortedList.sort(Comparator.comparing(Task::getYmd));
		Collections.reverse(sortedList);
		return sortedList;
	}

	// -------------------------------COMPLETED
	// ARRAY-------------------------------//

	/**
	 * addToCompleted
	 *
	 * removes the tasks from the hashmap and adds that task to a the completed
	 * array list
	 */
	public static void addToCompleted(Task temp) {
		// TODO: must update keys for event
		updateKeys();
		for (int key : taskMap.keySet()) {
			if (taskMap.get(key).getName() == temp.getName()) {
				taskMap.remove(key); // remove the task from the hashmap
				if (!temp.completedDate.equals("NULL")) {
					temp.setCompletedYmd(); // if completed before null it out
				}
				temp.completedDate(); // set the completed date to current date
				completedList.add(temp); // add it to the list
				break;
			}
		}
		updateKeys();
	}

	/**
	 * removeFromCompleted
	 *
	 * removes the tasks from the arrayList and adds that task to a the task map
	 */
	public static void removeFromCompleted(Task temp) {
		MainModel.completedList.remove(temp);
		addNewTask(temp);
		updateKeys();
	}

}