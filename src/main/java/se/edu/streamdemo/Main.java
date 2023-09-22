package se.edu.streamdemo;

import se.edu.streamdemo.data.DataManager;
import se.edu.streamdemo.task.Deadline;
import se.edu.streamdemo.task.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Task (stream) manager");
        DataManager dataManager = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dataManager.loadData();

        System.out.println("Printing all data ...");
//        printAllData(tasksData);

        System.out.println("Printing deadlines ... before sorting" );
        printDeadlines(tasksData);
        System.out.println("Printing deadlines ... before sorting" );
        printDeadlinesUsingStreams(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        System.out.println("Total number of deadlines: " + countDeadlinesUsingStreams(tasksData));

        ArrayList<Task> filteredList = filterTaskListByString(tasksData,"11");
        System.out.println(filteredList);

    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    private static int countDeadlinesUsingStreams(ArrayList<Task> tasksdata){
        int count = (int) tasksdata.stream()
                .filter((t)-> t instanceof Deadline)
                .count();
        return count;
    }

    public static void printAllData(ArrayList<Task> tasksData) {
        System.out.println("Using Iteration");
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }
    public static void printAllDatausingStreams(ArrayList<Task> tasks){
        System.out.println("Using Streams");
        tasks.stream()
                .forEach(System.out::println);

    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        System.out.println("using iteration");
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printDeadlinesUsingStreams(ArrayList<Task> tasks){
        tasks.stream()
                .filter((t) -> t instanceof Deadline)
                .sorted((t1,t2)-> t1.getDescription().compareToIgnoreCase(t2.getDescription()))
                .forEach(System.out::println);

    }

    public static ArrayList<Task> filterTaskListByString(ArrayList<Task> tasks, String filter) {
        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream()
                .filter((t)-> t.getDescription().contains(filter))
                .collect(Collectors.toList());
        return filteredList;
    }

}