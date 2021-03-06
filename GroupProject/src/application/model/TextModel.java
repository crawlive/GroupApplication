/*
 * MainModel.java - Model holding all text file non-action based functions for the program and the collections
 */
package application.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TextModel {
	public static String dataFile = ".\\src\\application\\dataFiles\\data.txt"; // holds data for todo and date
	public static String eventFile = ".\\src\\application\\dataFiles\\events.txt"; // holds the tasks that go in event
																					// list
	public static String completedFile = ".\\src\\application\\dataFiles\\completed.txt"; // holds the tasks that have
																							// been completed
	public static String courseFile = ".\\src\\application\\dataFiles\\courses.txt"; // holds the strings that go in
																						// courses list
	public static String notesFile = ".\\\\src\\\\application\\\\dataFiles\\\\notes.txt"; // holds the strings that go
																							// in the notes area

	// -------------IMPORTING DATA FUNCTIONS----------------//

	public static void importFiles() {
		importData(); // import all data to respective files
		importEvents();
		importCompleted();
		importCourses();
	}

	/**
	 * import*
	 *
	 * Imports data from respective * text file and adds to their respective
	 * collection if the file is not empty. They all will return nothing, and take
	 * nothing
	 */
	// Imports from data txt file to the taskMap
	public static void importData() {
		Scanner scan;
		Task temp;
		File file = new File(dataFile); // file pointer
		if (file.length() != 0) { // if the file is not empty
			try {
				scan = new Scanner(file); // scan pointer
				temp = new Task();
				String line;
				while (scan.hasNextLine()) { // while there is a next line
					line = scan.nextLine(); // grab the line
					String[] lineArr = line.split(","); // parse the line
					temp = convertData(lineArr); // convert the data to a task variable
					MainModel.taskMap.put(temp.placement, temp); // add the task to the map(according to placement
																	// number)
					if (MainModel.courses.contains(temp.course) == false) { // populate the course array
						MainModel.courses.add(temp.course);
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * importEvents
	 * 
	 * Imports from events txt file to the events arraylist
	 */
	public static void importEvents() {
		Scanner scan;
		Task temp;
		File file = new File(eventFile); // file pointer
		if (file.length() != 0) { // if the file is not empty
			try {
				scan = new Scanner(file); // scan pointer
				temp = new Task();
				String line;
				while (scan.hasNextLine()) { // while there is a next line
					line = scan.nextLine(); // grab the line
					String[] lineArr = line.split(","); // parse the line
					temp = convertData(lineArr); // convert the data to a task variable
					MainModel.events.add(temp); // add the task to the end of arraylist
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * importCompleted
	 * 
	 * Imports from completed txt file to the completedQueue
	 */
	public static void importCompleted() {
		Scanner scan;
		Task temp;
		File file = new File(completedFile); // file pointer
		if (file.length() != 0) { // if the file is not empty
			try {
				scan = new Scanner(file); // scan pointer
				temp = new Task();
				String line;
				while (scan.hasNextLine()) { // while there is a next line
					line = scan.nextLine(); // grab the line
					String[] lineArr = line.split(","); // parse the line
					temp = convertData(lineArr); // convert the data to a task variable
					MainModel.completedList.add(temp); // queue the task to the head
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * importCourses
	 * 
	 * Imports from events txt file to the events arraylist
	 */
	public static void importCourses() {
		Scanner scan;
		String course, color;
		int index;
		File file = new File(courseFile); // file pointer
		if (file.length() != 0) { // if the file is not empty
			try {
				scan = new Scanner(file); // scan pointer
				String line;
				while (scan.hasNextLine()) { // while there is a next line
					line = scan.nextLine(); // grab the line
					String[] lineArr = line.split(","); // parse the line
					course = lineArr[0];
					color = lineArr[1];
					if (MainModel.courses.contains(course) == true) {
						index = MainModel.courses.indexOf(course);
						MainModel.colors.add(index, color);
					} else {
						MainModel.courses.add(course); // add the course to the end of arraylist
						MainModel.colors.add(color); // add the color to the end of the arraylist
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * importNotes
	 *
	 * Imports from notes txt file to a string and returns it
	 */
	public static String importNotes() {
		Scanner scan;
		File file = new File(notesFile);
		if (file.length() != 0) {
			try {
				scan = new Scanner(file);
				String notes = scan.nextLine() + "\n";
				String line;
				while (scan.hasNextLine()) {
					line = scan.nextLine();
					notes += line;
					notes += "\n";

				}
				scan.close();

				return notes;

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// -------------SAVING DATA FUNCTIONS----------------//

	/**
	 * save*
	 *
	 * Saves data from respective * collection and updates the respective files with
	 * the closing or current sets of data. (depending on use) They all will return
	 * nothing, and take nothing
	 */
	// Saves the taskMap data to the file in order
	public static void saveData() throws IOException {
		Task temp = new Task();
		String line;
		FileWriter ifw = new FileWriter(dataFile); // file writer pointer
		BufferedWriter iWriter = new BufferedWriter(ifw); // buff pointer

		// iterate through the taskMap until end
		for (int key : MainModel.taskMap.keySet()) {
			temp = MainModel.taskMap.get(key); // grab each task in order
			line = temp.toString(); // set the line
			iWriter.write(line); // write the line
		}
		iWriter.close(); // close pointers
		line = ""; // special safety net
	}

	/**
	 * saveEvents
	 * 
	 * Saves the events arraylist data to the events file in order
	 * 
	 * @throws IOException
	 */
	public static void saveEvents() throws IOException {
		Task temp = new Task();
		String line;
		FileWriter ifw = new FileWriter(eventFile); // file writer pointer
		BufferedWriter iWriter = new BufferedWriter(ifw); // buff pointer

		// iterate through the taskMap until end
		for (int i = 0; i < MainModel.events.size(); i++) {
			temp = MainModel.events.get(i); // grab each task in order
			line = temp.toString(); // set the line
			iWriter.write(line); // write the line
		}
		iWriter.close(); // close pointers
		line = ""; // special safety net
	}

	/**
	 * saveCompleted
	 * 
	 * Saves the completedList data to the completed file in order
	 * 
	 * @throws IOException
	 */
	public static void saveCompleted() throws IOException {
		Task temp = new Task();
		String line;
		FileWriter ifw = new FileWriter(completedFile); // file writer pointer
		BufferedWriter iWriter = new BufferedWriter(ifw); // buff pointer

		// iterate through the taskMap until end
		for (int i = 0; i < MainModel.completedList.size(); i++) {
			temp = MainModel.completedList.get(i); // grab each task in order
			line = temp.toString(); // set the line
			iWriter.write(line); // write the line
		}
		iWriter.close(); // close pointers
		line = ""; // special safety net
	}

	/**
	 * saveCourses
	 * 
	 * Saves the courses data to the courses file in order
	 * 
	 * @throws IOException
	 */
	public static void saveCourses() throws IOException {
		String line, course, color;
		FileWriter ifw = new FileWriter(courseFile); // file writer pointer
		BufferedWriter iWriter = new BufferedWriter(ifw); // buff pointer

		// iterate through the taskMap until end
		for (int i = 0; i < MainModel.courses.size(); i++) {
			course = MainModel.courses.get(i); // grab each course
			color = MainModel.colors.get(i); // grab each color
			line = course + "," + color + "\n"; // create the line
			iWriter.write(line); // write the line
		}
		iWriter.close(); // close pointers
		line = ""; // special safety net

	}

	/**
	 * updateNotes
	 *
	 * Takes a string and writes it to notes.txt
	 * 
	 * @param s
	 * @throws IOException
	 */
	public static void updateNotes(String s) throws IOException {
		FileWriter ifw = new FileWriter(notesFile); // file writer pointer
		BufferedWriter iWriter = new BufferedWriter(ifw); // buff pointer
		iWriter.write(s); // write the line
		iWriter.close(); // close pointers
	}

	/**
	 * saveToFiles
	 * 
	 * Saves all the data to the files by calling the save funciton for every
	 * collection
	 */
	public static void saveToFiles() {
		try {
			saveData();
		} catch (IOException e) {
			System.out.println("Data File problems");
			e.printStackTrace();
		}
		try {
			saveEvents();
		} catch (IOException e) {
			System.out.println("Event File problems");
			e.printStackTrace();
		}
		try {
			saveCompleted();
		} catch (IOException e) {
			System.out.println("Completed File problems");
			e.printStackTrace();
		}
		try {
			saveCourses();
		} catch (IOException e) {
			System.out.println("Courses File problems");
			e.printStackTrace();
		}
	}

	/**
	 * convertData
	 * 
	 * convert data from a string array into a task object variable then return it
	 * 
	 * @return the task to convert
	 */
	public static Task convertData(String[] data) {
		Task temp = new Task();
		temp.type(data[0]);
		temp.name(data[1]);
		temp.course(data[2]);
		temp.date(data[3]);
		temp.setCompletedDate(data[4]);
		temp.placement(Integer.parseInt(data[5]));
		return temp;
	}
}