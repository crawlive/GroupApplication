/*
 * This file contains the Task class, which holds the object class that will be used
 * in the collections and functions for the program
 */
package application.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
	String type; 		//notes, hw, quiz, exam, meeting [Notes, Homework, Quiz, Exam or Meeting]
	String name;		//title
	String course;		//course name
	String date;		//due date
	String ymd;			//date in YYYY/MM/DD format
	int month;
	int day;
	int year;
	int placement;
	String completedDate; //Date completed for queue
	String completedYmd;

	/*
	 * Constructor
	 */
	public Task() {
		// empty constructor
	}

	public Task(String name, String course, String date, String type) {
		name(name);
		course(course);
		date(date);
		type(type);
	}


	//---------------------Getters for the Task Class-------------------//

	/**
	 * getName()
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * getCourse()
	 *
	 * @return the course
	 */
	public String getCourse() {
		return course;
	}

	/**
	 * getDate()
	 *
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * getCompletedDate()
	 *
	 * @return the completed date
	 */
	public String getCompletedDate() {
		return completedDate;
	}

	/**
	 * getYmd()
	 *
	 * @return the ymd (YYYY/MM/DD)
	 */
	public String getYmd() {
		return ymd;
	}

	/**
	 * getCompletedYmd()
	 *
	 * @return the completed date in ymd format
	 */
	public String getCompletedYmd() {
	    return this.completedYmd;
	}

	/**
	 * getType()
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	//---------------------Setters for the Task Class-------------------//

	/**
	 * type()
	 *
	 * @param type the type to set
	 */
	public void type(String type){
		this.type = type;
	}

	/**
	 * name()
	 *
	 * @param name the name to set
	 */
	public void name(String name){
		this.name = name;
	}


	/**
	 * course()
	 *
	 * @param course the course to set
	 */
	public void course(String course){
		this.course = course;
	}


	/**
	 * date()
	 *
	 * @param date the date to set
	 */
	public void date(String date){
		this.date = date;
		parseDate();
		this.ymd = this.year + "/" + this.month + "/" + this.day;
	}

	public void parseDate(){
		String regex = "[/]";
		String[] tokeArr = this.date.split(regex);
		this.month = Integer.parseInt(tokeArr[0]);
		this.day = Integer.parseInt(tokeArr[1]);
		this.year = Integer.parseInt(tokeArr[2]);
	}

	public void completedDate(){
		Date newDate = new Date();	//grabs the current date
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/YYYY");
	    String formattedDate = formatter.format(newDate);
		this.completedDate = formattedDate;

		System.out.println(newDate);
		System.out.println(formattedDate);

		String regex = "[/]";
		String[] tokeArr = formattedDate.split(regex);
		int mm = Integer.parseInt(tokeArr[0]);
		int dd = Integer.parseInt(tokeArr[1]);
		int yyyy = Integer.parseInt(tokeArr[2]);

		completedYmd = yyyy + "/" + mm + "/" + dd;
	}

	public void setCompletedDate(String date){
		this.completedDate = date;

		if(date.equals("NULL")) {
			this.completedDate = "NULL";
			this.completedYmd = 0000 + "/" + 00 + "/" + 00;
		} else {
			String regex = "[/]";
			String[] tokeArr = completedDate.split(regex);
			int mm = Integer.parseInt(tokeArr[0]);
			int dd = Integer.parseInt(tokeArr[1]);
			int yyyy = Integer.parseInt(tokeArr[2]);
			this.completedYmd = yyyy + "/" + mm + "/" + dd;
		}
	}

	public void setCompletedYmd(){
		this.completedYmd = "NULL";
	}

	public void placement(int placement){
		this.placement = placement;
	}

	public String toString() {
		String retString = type + "," + name + "," + course + "," + date + "," + completedDate + "," + placement +"\n";
		return retString;
	}

}