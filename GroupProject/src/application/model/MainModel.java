/*
 * This file contains the MainModel class, which holds all collection based functions for the program
 */
package application.model;

import application.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class MainModel{

	public static HashMap<Integer, Task> taskMap = new HashMap<Integer, Task>();	//handles data from data.txt
	public static ArrayList<Task> events = new ArrayList<Task>();
	public static Queue<Task> completedQueue = new LinkedList<Task>();



}