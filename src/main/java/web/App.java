package web;

import java.util.Scanner;
import java.io.IOException;
import java.net.URISyntaxException;

import web.parsers.UsersParser;
import web.parsers.DataParser;

public class App {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Please enter the user ID as a number.");
            Scanner scanner = new Scanner(System.in);

            if (scanner.hasNextInt()) {
                int userId = scanner.nextInt();
                System.out.println("\n");

                System.out.println("The user with the specified identifier is found in the database with the following data:");
                UsersParser.getUserById(userId);
                System.out.println("\n");

                System.out.println("The found user has the following pending tasks:");
                DataParser.getUncompletedTasks(userId);
                System.out.println("\n");

                System.out.println("Here are all comments on the latest post by the found user (they are also recorded in a JSON file):");
                DataParser.getCommentsToLastPost(userId);
                System.out.println("\n");

                System.out.println("Our database also includes the following additional features:");
                System.out.println("1. You can obtain a full list of all users, for example:");
                UsersParser.getUsers();
                System.out.println("\n");

                System.out.println("2. You can find a user by name, for example, with the name 'Samantha':");
                UsersParser.getUserByName();
                System.out.println("\n");

                System.out.println(
                        "3. You can modify any user data, for example, for the user found by your identifier - " + userId + ":");
                UsersParser.updateUser(userId);
                System.out.println("\n");

                System.out.println("4. You can add a new user to the database:");
                UsersParser.postUser();
                System.out.println("\n");

                System.out.println("5. You can delete a user from the database by the ID identifier, for example, for the user with ID = 8:");
                UsersParser.removeUser();
                System.out.println("\n");

                System.out.println("The program has finished its execution!");
                Thread.currentThread().interrupt();
            } else {
                System.out.println("You have not entered a number. Please provide the user ID as a numerical value.");
            }
        }
    }
}
