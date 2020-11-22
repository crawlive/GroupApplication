/*
 * This file contains the MainModel class, which holds all collection based functions for the program
 */
package application.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
	public static ArrayList<String> courses = new ArrayList<String>(Arrays.asList("Select a Course"));

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
		if((newTask.type.equals("e") == true) || (newTask.type.equals("m") == true)) {					//if the task is an exam
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

	/*
     * checkDay
     *
     * check to see if the given Task is PAST the due date, if so return true, else return false
     * the return value is utilized to determine if a task should be mark as past-due
     */
	public static boolean checkDay(Task task){
		Date curDate = new Date();
		SimpleDateFormat formatDate = new SimpleDateFormat("MM/dd/yyyy");
		String today = formatDate.format(curDate);
		if(task.date.equals(today) == true){	//quick check doesn't really need to be here
			return false;
		}

		String regex = "[/]";					//parse the string based off the delimiter
		String[] tokeArr = today.split(regex);
		int mm = Integer.parseInt(tokeArr[0]);
		int dd = Integer.parseInt(tokeArr[1]);
		int yy = Integer.parseInt(tokeArr[2]);

		if(yy == task.year){				//same year
			if(mm == task.month){				//same month
				if(dd == task.day){					//same day
					return false;						//not past due date
				}
				else if(dd > task.day){				//today is bigger than due day
					return true;						//we are past the due date
				}
				else{								//today is smaller than due day
					return false;						//we are before the due day
				}
			}
			else if(mm > task.month){			//current month is bigger than due month
				return true;						//we are past the due date
			}
			else{								//current month is smaller than due month
				return false;						//we are past the due date
			}
		}
		else if(yy > task.year){			//current year is bigger than due year
			return true;						//we are past the due date
		}
		else{								//current year is less than due year
			return false;						//we are before the due date
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


	//-------------------------------EVENTS/COURSES ARRAYLIST-------------------------------//

}