/*
 * This file contains the TextModel class, which holds all text file non-action based functions for the program and the collections
 */
package application.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;


public class TextModel{
	public static String dataFile = ".\\src\\application\\dataFiles\\data.txt";				//holds data for todo and date
	public static String eventFile = ".\\src\\application\\dataFiles\\events.txt";			//holds the tasks that go in event list
	public static String completedFile = ".\\src\\application\\dataFiles\\completed.txt";	//holds the tasks that have been completed


	//-------------IMPORTING DATA FUNCTIONS----------------//
	/*
     * import*
     *
     * Imports data from respective * text file and adds to their respective collection
     * 		if the file is not empty.
     * They all will return nothing, and take nothing
     */
	//Imports from data txt file to the taskMap
	public static void importData(){
		Scanner scan;
		Task temp;
		File file = new File(dataFile);						//file pointer
		if(file.length() != 0){								//if the file is not empty
			try {
				scan = new Scanner(file);					//scan pointer
				temp = new Task();
				String line;
				while(scan.hasNextLine()) {					//while there is a next line
					line = scan.nextLine();					//grab the line
					String[] lineArr = line.split(",");		//parse the line
					temp = convertData(lineArr);			//convert the data to a task variable
					MainModel.taskMap.put(temp.placement, temp);	//add the task to the map(according to placement number)
					if(MainModel.courses.contains(temp.course) == false){	//populate the course array
						MainModel.courses.add(temp.course);
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	//Imports from events txt file to the events arraylist
	public static void importEvents(){
		Scanner scan;
		Task temp;
		File file = new File(eventFile);					//file pointer
		if(file.length() != 0){								//if the file is not empty
			try {
				scan = new Scanner(file);					//scan pointer
				temp = new Task();
				String line;
				while(scan.hasNextLine()) {					//while there is a next line
					line = scan.nextLine();					//grab the line
					String[] lineArr = line.split(",");		//parse the line
					temp = convertData(lineArr);			//convert the data to a task variable
					MainModel.events.add(temp);				//add the task to the end of arraylist
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	//Imports from completed txt file to the completedQueue
	public static void importCompleted(){
		Scanner scan;
		Task temp;
		File file = new File(completedFile);				//file pointer
		if(file.length() != 0){								//if the file is not empty
			try {
				scan = new Scanner(file);					//scan pointer
				temp = new Task();
				String line;
				while(scan.hasNextLine()) {					//while there is a next line
					line = scan.nextLine();					//grab the line
					String[] lineArr = line.split(",");		//parse the line
					temp = convertData(lineArr);			//convert the data to a task variable
					MainModel.completedQueue.add(temp);		//queue the task to the head
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	//-------------SAVING DATA FUNCTIONS----------------//
	/*
     * save*
     *
     * Saves data from respective * collection and updates the respective files with the
     * 		closing or current sets of data. (depending on use)
     * They all will return nothing, and take nothing
     */
	//Saves the taskMap data to the file in order
	public static void saveData() throws IOException{
		Task temp = new Task();
		String line;
		FileWriter ifw = new FileWriter(dataFile);			//file writer pointer
		BufferedWriter iWriter = new BufferedWriter(ifw);	//buff pointer

		//iterate through the taskMap until end
		int size = MainModel.taskMap.size();
		for(int i = 0; i < size; i++){
			
			//System.out.println(size);
			//System.out.println(i);
			
			temp = MainModel.taskMap.get(i);				//grab each task in order

			//System.out.println(temp.toString());
			
			line = temp.toString();							//set the line
			
			//System.out.println(line);
			
			iWriter.write(line);							//write the line
		}
		iWriter.close();									//close pointers
		line="";											//special safety net
	}

	//Saves the events arraylist data to the events file in order
	public static void saveEvents() throws IOException{
		Task temp = new Task();
		String line;
		FileWriter ifw = new FileWriter(eventFile);			//file writer pointer
		BufferedWriter iWriter = new BufferedWriter(ifw);	//buff pointer

		//iterate through the taskMap until end
		for(int i = 0; i < MainModel.events.size(); i++){
			temp = MainModel.events.get(i);					//grab each task in order
			line = temp.toString();							//set the line
			iWriter.write(line);							//write the line
		}
		iWriter.close();									//close pointers
		line="";											//special safety net
	}

	//Saves the completedQueue data to the completed file in order
	public static void saveCompleted() throws IOException{
		Task temp = new Task();
		String line;
		FileWriter ifw = new FileWriter(completedFile);		//file writer pointer
		BufferedWriter iWriter = new BufferedWriter(ifw);	//buff pointer
		Iterator<Task> iterator = MainModel.completedQueue.iterator(); 	//queue iterator
		while(iterator.hasNext()){							//while items in queue
			temp = MainModel.completedQueue.poll();			//dequeue each task
			line = temp.toString();							//set the line from the task
			iWriter.write(line);							//write the line
		}
		iWriter.close();									//close pointers
		line="";											//special safety net
	}

	public static void saveToFiles(){
		try{
			saveData();
		}
		catch(IOException e){
			System.out.println("Data File problems");
			e.printStackTrace();
		}
		try{
			saveEvents();
		}
		catch(IOException e){
			System.out.println("Event File problems");
			e.printStackTrace();
		}
		try{
			saveCompleted();
		}
		catch(IOException e){
			System.out.println("Completed File problems");
			e.printStackTrace();
		}
	}

	//convert data from a string array into a task object variable then return it
	public static Task convertData(String[] data){
		Task temp = new Task();
		temp.type(data[0]);
		temp.name(data[1]);
		temp.course(data[2]);
		temp.date(data[3]);
		temp.placement(Integer.parseInt(data[4]));
		return temp;
	}
}