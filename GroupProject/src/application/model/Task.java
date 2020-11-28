/*
 * This file contains the Task class, which holds the object class that will be used
 * in the collections and functions for the program
 */
package application.model;

import java.util.Date;

public class Task {
	String type; 		//notes, hw, quiz, exam, meeting [n, h, q, e or m]
	String name;		//title
	String course;		//course name
	String date;		//due date
	String ymd;			//date in YYYY/MM/DD format
	int month;
	int day;
	int year;
	int placement;
	Date completedDate; //Date completed for queue

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
	 * getYmd()
	 * 
	 * @return the ymd (YYYY/MM/DD)
	 */
	public String getYmd() {
		return ymd;
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
