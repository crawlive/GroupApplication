/*
 * This file contains the MainModel class, which holds all collection based functions for the program
 */
package application.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.*;


public class MainModel {

	public static HashMap<Integer, Task> taskMap = new HashMap<Integer, Task>(); // handles data from data.txt
	public static ArrayList<Task> events = new ArrayList<Task>();
	public static ArrayList<Task> completedList = new ArrayList<Task>();
	public static ArrayList<String> courses = new ArrayList<String>(Arrays.asList("Select a Course"));
	public static ArrayList<String> colors = new ArrayList<String>();

	/*
	 * sendAlert
	 *
	 * Sends an alert based on the passed string
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
		if(s.equals("ta")){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("INFORMATION");
			alert.setHeaderText("New Task Created");
			alert.setContentText("Task was successfully created!");
			alert.showAndWait();
		}
		
	}

	/*
	 * pastDue
	 *
	 * check to see if the given Task is PAST the due date, if so return true, else
	 * return false the return value is utilized to determine if a task should be
	 * mark as past-due
	 */
	public static boolean pastDue(Task task) {
		Date curDate = new Date();
		SimpleDateFormat formatDate = new SimpleDateFormat("MM/dd/yyyy");
		String today = formatDate.format(curDate);
		if (task.date.equals(today) == true) { // quick check doesn't really need to be here
			return false;
		}

		String regex = "[/]"; // parse the string based off the delimiter
		String[] tokeArr = today.split(regex);
		int mm = Integer.parseInt(tokeArr[0]);
		int dd = Integer.parseInt(tokeArr[1]);
		int yy = Integer.parseInt(tokeArr[2]);

		if (yy == task.year) { // same year
			if (mm == task.month) { // same month
				if (dd == task.day) { // same day
					return false; // not past due date
				} else if (dd > task.day) { // today is bigger than due day
					return true; // we are past the due date
				} else { // today is smaller than due day
					return false; // we are before the due day
				}
			} else if (mm > task.month) { // current month is bigger than due month
				return true; // we are past the due date
			} else { // current month is smaller than due month
				return false; // we are past the due date
			}
		} else if (yy > task.year) { // current year is bigger than due year
			return true; // we are past the due date
		} else { // current year is less than due year
			return false; // we are before the due date
		}

	}

	/*
	 * compareDates
	 *
	 * compares two dates and returns true if task1 is older than task2 and false if
	 * task2 is older
	 */
	public static boolean compareDates(Task task1, Task task2) {
		if (task1.date.contentEquals(task2.date) == true) { // quick check doesn't really need to be here
			return false;
		}

		if (task1.year == task2.year) { // same year
			if (task1.month == task2.month) { // same month
				if (task1.day == task2.day) { // same day
					return false; // they're equal return task1//fix to return based off h,q,e,m
				} else if (task1.day > task2.day) { // today is bigger than due day
					return true; // we are past the due date
				} else { // today is smaller than due day
					return false; // we are before the due day
				}
			} else if (task1.month > task2.month) { // current month is bigger than due month
				return true; // we are past the due date
			} else { // current month is smaller than due month
				return false; // we are past the due date
			}
		} else if (task1.year > task2.year) { // current year is bigger than due year
			return true; // we are past the due date
		} else { // current year is less than due year
			return false; // we are before the due date
		}

	}

	// -------------------------------TASK HASHMAPING
	// -------------------------------//

	/*
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

	/*
	 * addData
	 *
	 * add a new task to the proper collections
	 */
	public static void addNewTask(Task newTask) {
		ArrayList<Task> sortedArr = new ArrayList<Task>();
		//if (newTask.type.equals("Meeting") == false) { // if the task is not a meeting add the task normally
			sortedArr.addAll(taskMap.values()); 
			sortedArr.add(newTask);
			sortedArr.sort(Comparator.comparing(Task::getDate));
			taskMap.clear();
			for (Task task : sortedArr) {
				task.placement = sortedArr.indexOf(task);
				taskMap.put(sortedArr.indexOf(task), task);
			}
		//}
		if ((newTask.type.equals("Exam") == true) || (newTask.type.equals("Meeting") == true)) { // if the task is an exam
			events.add(newTask); // add the task to the array list
		}
	}

	// -------------------------------SORTED LISTS
		// -------------------------------//
	
	/*
	 * getSortedDateArray
	 *
	 * Sorts the hash map values by date and returns it
	 * as a sorted array list
	 */
	public static ArrayList<Task> getSortedDateArray() {
		ArrayList<Task> sortedList = new ArrayList<Task>();
		sortedList.addAll(taskMap.values());
		
		for(Task t : MainModel.completedList){
			sortedList.add(t);;
		}
		sortedList.sort(Comparator.comparing(Task::getYmd));
		return sortedList;
	}
	
	/*
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
	
	
	/*
	 * getSortedCompletedArray
	 *
	 * sorts the queue by completion date (most recent first) returns it
	 * as a sorted array list
	 */
	public static ArrayList<Task> getSortedCompletedArray() {
		ArrayList<Task> sortedList = new ArrayList<Task>();
		sortedList.addAll(completedList);
		sortedList.sort(Comparator.comparing(Task::getYmd));
		Collections.reverse(sortedList);
		return sortedList;
	}

	// -------------------------------COMPLETED QUEUE
	// -------------------------------//
	/*
	 * addToCompleted
	 *
	 * removes the tasks from the hashmap and adds that task to a the completed array list
	 */
	public static void addToCompleted(Task temp) {
		// TODO: must update keys for event
		updateKeys();
		for (int key : taskMap.keySet()) {
			if (taskMap.get(key).getName() == temp.getName()) {
				taskMap.remove(key); // remove the task from the hashmap
				if(!temp.completedDate.equals("NULL")) {
					temp.setCompletedYmd();		//if completed before null it out
				}
				temp.completedDate(); // set the completed date to current date
				completedList.add(temp); // add it to the list
				break;
			}
		}
		updateKeys();
	}

	/*
	 * removeFromCompleted
	 *
	 * removes the tasks from the arrayList and adds that task to a the task map
	 */
	public static void removeFromCompleted(Task temp) {
		MainModel.completedList.remove(temp);
		addNewTask(temp);
		updateKeys();
	}


	// -------------------------------EVENTS/COURSES
	// ARRAYLIST-------------------------------//

}