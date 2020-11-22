/*
 * This file contains the Task class, which holds the object class that will be used
 * in the collections and functions for the program
 */
package application.model;

import java.util.Date;

public class Task {
	String type; 		//notes, hw, quiz, exam [n, h, q, e or m]
	String name;		//title
	String course;		//course name
	String date;		//due date
	/*int month;
	int day;
	int year;*/
	int placement;
	Date completedDate; //Date completed for queue

	//---------------------Setters for the Task Class-------------------//
	public void type(String type){
		this.type = type;
	}

	public void name(String name){
		this.name = name;
	}

	public void course(String course){
		this.course = course;
	}

	public void date(String date){
		this.date = date;
		//parseDate();
	}
/*
	public void parseDate(){
		String regex = "[/]";
		String[] tokeArr = this.date.split(regex);
		this.month = Integer.parseInt(tokeArr[0]);
		this.day = Integer.parseInt(tokeArr[1]);
		this.year = Integer.parseInt(tokeArr[2]);
	}
*/
	public void completedDate(){
		this.completedDate = new Date();	//grabs the current date
	}

	public void placement(int placement){
		this.placement = placement;
	}
	
	public String toString() {
		String retString = type + "," + name + "," + course + "," + date + "," + placement +"\n";
		return retString;
	}

}
