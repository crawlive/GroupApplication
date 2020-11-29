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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.Queue;
import java.util.Set;

public class MainModel {

	public static HashMap<Integer, Task> taskMap = new HashMap<Integer, Task>(); // handles data from data.txt
	public static ArrayList<Task> events = new ArrayList<Task>();
	public static Queue<Task> completedQueue = new LinkedList<Task>();
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
		if(s.equals("ec")){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("INFORMATION");
			alert.setHeaderText("Course Editted");
			alert.setContentText("Course was successfully editted!");
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
		Set<Entry<Integer, Task>> set = taskMap.entrySet(); // create an iteration set
		Iterator<Entry<Integer, Task>> iterator = set.iterator(); // iterator
		Task curValue = null; // task value holder
		int place, curKey, i = 0; // placement, current key, where task needs to be
		while (iterator.hasNext()) { // iterate through taskMap
			@SuppressWarnings("rawtypes")
			Map.Entry me = (Map.Entry) iterator.next(); // grab the current set entry
			curValue = (Task) me.getValue(); // grab the entry's task
			place = curValue.placement; // grab the task's placement
			curKey = (Integer) me.getKey(); // grab the current entry key
			if (place != i) { // if the current placement != i
				curValue.placement = i; // set it to i
				taskMap.put(i, curValue); // add updated task to the taskMap
				taskMap.remove(curKey); // remove the old task at the old position
			}
			i++; // increment i
		}
	}

	/*
	 * addData
	 *
	 * add a new task to the proper collections
	 */
	public static void addNewTask(Task newTask) {
		if (newTask.type.equals("m") == false) { // if the task is not a meeting add the task normally
			int size = taskMap.size(); // indices are zero based (see importData)
			newTask.placement=size;
			taskMap.put(size, newTask); // add the task to the end of the map
		}
		if ((newTask.type.equals("e") == true) || (newTask.type.equals("m") == true)) { // if the task is an exam
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
		ArrayList<Task> taskList = new ArrayList<Task>();
		taskList.addAll(taskMap.values());
		taskList.sort(Comparator.comparing(Task::getYmd));
		return taskList;
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
		
		for(Task t : MainModel.completedQueue){
			sortedList.add(t);;
		}
	
		sortedList.sort(Comparator.comparing(Task::getCompletedYmd));
		
		Collections.reverse(sortedList);
		
		return sortedList;
	}

	// -------------------------------COMPLETED QUEUE
	// -------------------------------//
	/*
	 * addToQueue
	 *
	 * removes the tasks from the hashmap and adds that task to a queue
	 */
	public static void addToQueue(Task temp) {
		taskMap.remove(temp.placement); // remove the task from the hashmap
		temp.completedDate(); // set the completed date to current date
		completedQueue.add(temp); // add it to the queue
	}

	/*
	 * removeFromQueue
	 *
	 * removes the tasks from the hashmap and adds that task to a queue
	 */
	public static void removeFromQueue(Task temp) {
		for (Task t : MainModel.completedQueue) {
			if (t == temp) {
				MainModel.completedQueue.remove(temp);
				addNewTask(temp);
			}
		}
	}


	// -------------------------------EVENTS/COURSES
	// ARRAYLIST-------------------------------//

}