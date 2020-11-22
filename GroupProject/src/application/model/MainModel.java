/*
 * This file contains the MainModel class, which holds all collection based functions for the program
 */
package application.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

public class MainModel{

	public static HashMap<Integer, Task> taskMap = new HashMap<Integer, Task>();	//handles data from data.txt
	public static ArrayList<Task> events = new ArrayList<Task>();
	public static Queue<Task> completedQueue = new LinkedList<Task>();
	public static ArrayList<String> courses = new ArrayList<String>();
	
	
	/*
     * checkDates
     *
     * check to see if any of the tasks are past the due date, if so return true, else return false
     */
	public static void checkDates(){							
	
	}
	
	
	//-------------------------------TASK HASHMAPING -------------------------------//
    
	/*
     * addData
     *
     * add a new task to the proper collections
     */
	public static void addNewTask(Task newTask){
		if(newTask.type.equals("m") == false) {					//if the task is not a meeting add the task normally
			int size = taskMap.size();							//indices are zero based (see importData)
			taskMap.put(size, newTask);							//add the task to the end of the map
		}
		if(newTask.type.equals("e") == true) {					//if the task is an exam
			events.add(newTask);								//add the task to the array list
		}
	}
	
	/*
     * adjustDataIndice
     *
     * adjusts the data per the placement index
     */
	public static void adjustDataIndice(Task task){
		//---------------------------------------------------------FINISH ME
		//use temp.placement to track placement from listview
		//taskMap.put(task.placement, task);					//add task to taskMap
		
	}
	
	/*
     * sortHashMap
     *
     * sorts the hash map by index			----------------------------WORK IN PROGRESS
     */
	public static void sortHashMap(){
		Map<Integer, Task> map = new TreeMap<Integer, Task>(taskMap);
		Set<Map.Entry<Integer, Task>> set = map.entrySet();
		Iterator<Map.Entry<Integer, Task>> iterator = set.iterator();
		while(iterator.hasNext()) {
			Map.Entry me = (Map.Entry)iterator.next();
			System.out.print(me.getKey() + ": ");
			System.out.println(me.getValue());
		}

	}
	
	
	//-------------------------------COMPLETED QUEUE -------------------------------//
	/*
     * addToQueue
     *
     * removes the tasks from the hashmap and adds that task to a queue
     */
	public static void addToQueue(Task temp){
		taskMap.remove(temp.placement);							//remove the task from the hashmap
		temp.completedDate();									//set the completed date to current date
		completedQueue.add(temp);								//add it to the queue
	}
	
	/*
     * checkQueueDates
     *
     * check to see if any of the elements in the queue have been in the queue longer than 40 days
     */
	public static void checkQueueDates(){						//FINISH AFTER LISTVIEW
		if(completedQueue.isEmpty() == false){
			Task temp = completedQueue.peek();
			Calendar fortyDaysAgoCal = Calendar.getInstance();
			fortyDaysAgoCal.add(Calendar.DAY_OF_MONTH, -40);
			Date fortyDaysAgo = fortyDaysAgoCal.getTime();

			if(temp.completedDate.before(fortyDaysAgo) == false){
				completedQueue.remove();
				//remove from list view as well
				checkQueueDates();								//recursive call to continue checking
																//until the if is false match up
			}	
		}
	}


	//-------------------------------EVENTS ARRAYLIST-------------------------------//
	

}