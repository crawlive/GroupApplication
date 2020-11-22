/*
 * This file contains the MainModel class, which holds all collection based functions for the program
 */
package application.model;

import application.model.Task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class MainModel{

	public static HashMap<Integer, Task> taskMap = new HashMap<Integer, Task>();	//handles data from data.txt
	public static ArrayList<Task> events = new ArrayList<Task>();
	public static Queue<Task> completedQueue = new LinkedList<Task>();
	//-------------------------------TASK HASHMAPING -------------------------------//
	//i want to set the events to what is currently in the events list

	//-------------------------------COMPLETED QUEUE -------------------------------//
	public static void addToQueue(Task temp){
		temp.completedDate();
		completedQueue.add(temp);
	}

	public static void checkDates(){						//FINISH AFTER LISTVIEW
		if(completedQueue.isEmpty() == false){
			Task temp = completedQueue.peek();
			Calendar fortyDaysAgoCal = Calendar.getInstance();
			fortyDaysAgoCal.add(Calendar.DAY_OF_MONTH, -30);
			Date fortyDaysAgo = fortyDaysAgoCal.getTime();

			if(temp.completedDate.before(fortyDaysAgo)){
				completedQueue.poll();
				//remove from list view as well
				checkDates();								//recursive call to continue checking
															//until the if is false match up
			}
		}
	}


	//-------------------------------EVENTS ARRAYLIST-------------------------------//
	//i want to set the events to what is currently in the events list
	//this will coincide with the boxes

}