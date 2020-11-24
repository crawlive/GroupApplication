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
//import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
//import java.util.Map;
import java.util.Queue;
//import java.util.Set;
//import java.util.TreeMap;
import java.util.Set;

public class MainModel{

	public static HashMap<Integer, Task> taskMap = new HashMap<Integer, Task>();	//handles data from data.txt
	public static ArrayList<Task> events = new ArrayList<Task>();
	public static Queue<Task> completedQueue = new LinkedList<Task>();
	public static ArrayList<String> courses = new ArrayList<String>(Arrays.asList("Select a Course"));

	//-------------------------------TASK HASHMAPING -------------------------------//

	public static void changeCourseName(String oldName, String newName){
		changeAssignments(newName, oldName);		//change all associated assignments from the taskMap
		changeCourseEvents(newName, oldName);	//change the course from the events array list
		changeCourseCompleted(newName, oldName);//remove the course from the completed queue
		changeCourse(newName, oldName);			//remove the course from the course array list
	}

	/*
     * changeCourseCompleted
     *
     * find the key for the task that contains the given string and remove it from the events arraylist
     */
    public static void changeCourseCompleted(String newName, String name) {
    	Queue<Task> temp = new LinkedList<Task>();			//temp queue
        int size = completedQueue.size();					//size of regular queue
        Task element = null;								//temp element
        for (int i = 0; i < size; i++) {					//iterate through the queue
        	element = completedQueue.remove();				//remove head
            if (element.course.contains(name)) {			//if the element's course name == name
                element.course(newName);					//change the name
                temp.add(element);							//add updated element to the temp queue

            } else {
                temp.add(element);			//otherwise remove and add it to the temp queue
            }
        }
        size = temp.size();
        for (int i = 0; i < size; i++) {				//iterate through the queue
        	element = temp.remove();					//grab head
            completedQueue.add(element);				//add it the OG queue
        }
     }

	/*
     * changeCourse
     *
     * change specified course from all collections to the new name
     */
	public static void changeCourse(String newName, String name){
		String temp;
		for(int i = 0; i < courses.size(); i++) {	//iterate through the course arr list
			temp = courses.get(i);					//grab cur value at index
    		if(temp.contains(name)) {				//if the value is found
    			courses.set(i, newName);			//set the new element
    		}
        }
	}

	/*
     * changeCourseEvents
     *
     * find the key for the task that contains the given string and change it to the new course name
     */
    public static void changeCourseEvents(String newName, String name) {
    	Task temp = new Task();
    	for(int i = 0; i < events.size(); i++) {
    		temp = events.get(i);					//grab cur value at index
    		if(temp.course.contains(name)) {		//if the value is found
    			temp.course(newName);				//change the name
    			events.set(i, temp);				//set the new element
    		}
        }
     }


	/*
     * changeAssignments
     *
     * find the key for the task that contains the given string and change it to the new course name
     */
    public static void changeAssignments(String newName, String name) {
    	Task temp = new Task();
    	Task temp2 = new Task();
    	for(int i = 0; i < taskMap.size(); i++) {	//iterate through taskMap
    		temp = taskMap.get(i);					//grab cur value at index
    		if(temp.course.equals(name)) {			//if task in course
    			temp2 = temp;
    			temp2.course(newName);				//change the name
    			taskMap.replace(i, temp2);			//replace with the new element
    		}
        }
     }


	/*
     * deleteCourse
     *
     * remove specified course from all collections
     */
	public static void deleteCourse(String name){
		removeCourseAssignments(name);	//remove all associated assignments from the taskMap
		removeCourseEvents(name);		//remove the course from the events array list
		removeCourseCompleted(name);	//remove the course from the completed queue
		removeCourse(name);				//remove the course from the course array list
	}

	/*
     * removeCourse
     *
     * find the key for the task that contains the given string and remove it from the events arraylist
     */
    public static void removeCourse(String name) {
    	String temp;
    	for(int i = 0; i < courses.size(); i++) {	//iterate through the course arr list
    		temp = courses.get(i);					//grab cur value at index
    		if(temp.contains(name)) {				//if the value is found
    			courses.remove(i);					//remove it
    		}
        }
    }


	/*
     * removeCourseCompleted
     *
     * find the key for the task that contains the given string and remove it from the events arraylist
     */
    public static void removeCourseCompleted(String name) {
    	Queue<Task> temp = new LinkedList<Task>();			//temp queue
        int size = completedQueue.size();					//size of regular queue
        Task element = null;								//temp element
        for (int i = 0; i < size; i++) {					//iterate throught the queue
        	element = completedQueue.peek();				//peek head
            if (element.course.contains(name)) {			//if the element's course name == name
                element = completedQueue.remove();			//remove it from the queue
            } else {
                temp.add(completedQueue.remove());			//otherwise remove and add it to the temp queue
            }
        }
        size = temp.size();
        for (int i = 0; i < size; i++) {					//iterate throught the queue
        	element = temp.remove();						//grab head
            completedQueue.add(element);					//add it the OG queue
        }
     }

	/*
     * removeCourseEvents
     *
     * find the key for the task that contains the given string and remove it from the events arraylist
     */
    public static void removeCourseEvents(String name) {
    	Task temp = null;
    	for(int i = 0; i < events.size(); i++) {	//iterate through the course arr list
    		temp = events.get(i);					//grab cur value at index
    		if(temp.course.contains(name)) {		//if the value is found
    			events.remove(i);					//remove it
    		}
        }
     }

	/*
	 * removeCourseAssignments
     *
     * find the key for the task that contains the given string and remove it from the taskMap
     */
    public static void removeCourseAssignments(String name) {
    	taskMap.entrySet().removeIf(entry->(name.equals(entry.getValue().course)));	//using lambda expression to remove
    																				//values that contain the course
    	updateKeys();
    }

    /*
     * updateKeys
     *
     * update the taskMap to host the keys in order
     */
    public static void updateKeys() {
    	Set<Entry<Integer, Task>> set = taskMap.entrySet();				//create an iteration set
    	Iterator<Entry<Integer, Task>> iterator = set.iterator();		//iterator
    	Task curValue = null;											//task value holder
    	int place, curKey, i = 0;										//placement, current key, where task needs to be
    	while(iterator.hasNext()) {										//iterate through taskMap
    		@SuppressWarnings("rawtypes")
			Map.Entry me = (Map.Entry)iterator.next();					//grab the current set entry
    		curValue = (Task)me.getValue();								//grab the entry's task
    		place = curValue.placement;									//grab the task's placement
    		curKey = (Integer)me.getKey();								//grab the current entry key
    		if(place != i){												//if the current placement != i
    			curValue.placement = i;									//set it to i
    			taskMap.put(i, curValue);								//add updated task to the taskMap
    			taskMap.remove(curKey);									//remove the old task at the old position
    		}
    		i++;														//increment i
    	}
    }

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
     *
	public static void sortHashMap(){
		Map<Integer, Task> map = new TreeMap<Integer, Task>(taskMap);
		Set<Map.Entry<Integer, Task>> set = map.entrySet();
		Iterator<Map.Entry<Integer, Task>> iterator = set.iterator();
		while(iterator.hasNext()) {
			Map.Entry me = (Map.Entry)iterator.next();
			System.out.print(me.getKey() + ": ");
			System.out.println(me.getValue());
		}

	}*/

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