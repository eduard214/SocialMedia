package org.example;

import org.example.interfaces.IAuthenticationService;
import org.example.interfaces.IFileHandler;
import org.example.interfaces.IUserService;
import org.example.models.*;

import java.util.Scanner;

import java.util.List;

// todo: setup a java server and connect to it to be more sophisticated :)

public class Main {
    public static void main(String[] args) {
        boolean running = true;

        IUserService userService = new UserService();
        IAuthenticationService authenticationService = new AuthenticationService();
        IFileHandler fileHandler = new FileHandler();

        userService.LoadUsers(fileHandler.loadStandardUsers());
        userService.LoadAdmins(fileHandler.loadAdminUsers());

        while (running) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("=== Main Menu ===");
            System.out.println("1. Adauga Utilizator");
            System.out.println("2. Adauga Admin");
            System.out.println("3. Cauta Utilizator");
            System.out.println("4. Cauta Admin");
            System.out.println("5. Login Utilizator");
            System.out.println("6. Login Admin");
            System.out.println("7. Logout Utilizator");
            System.out.println("8. Logout Admin");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    boolean ok = false;

                    do {
                        System.out.println("Introduceti username: ");
                        String username = scanner.next();
                        System.out.println("Introduceti password: ");
                        String password = scanner.next();
                        System.out.println("Introduceti email: ");
                        String email = scanner.next();
                        StandardUser user = new StandardUser(username, password, email);

                        if (userService.addUser(user)) {
                            ok = true;
                        }
                        fileHandler.saveStandardUsers(userService.getAllStandardUsers());
                    } while(!ok);
                }
                case 2 -> {
                    boolean ok = false;

                    do {
                        System.out.println("Introduceti username: ");
                        String username = scanner.next();
                        System.out.println("Introduceti password: ");
                        String password = scanner.next();
                        System.out.println("Introduceti email: ");
                        String email = scanner.next();
                        AdminUser user = new AdminUser(username, password, email);

                        if (userService.addAdminUser(user)) {
                            ok = true;
                        }
                        fileHandler.saveAdminUsers(userService.getAllAdminUsers());
                    } while(!ok);
                }
                case 3 -> {
                    System.out.println("Introduceti username: ");
                    String username = scanner.next();

                    if (userService.findUserByUsername(username).isPresent()) {
                        System.out.println("Utilizatorul a fost gasit!");
                    }
                    else {
                        System.out.println("Utilizatorul nu a fost gasit!");
                    }
                }
                case 4 -> {
                    System.out.println("Introduceti username: ");
                    String username = scanner.next();

                    if (userService.findAdminUserByUsername(username).isPresent()) {
                        System.out.println("Utilizatorul-Admin a fost gasit!");
                    }
                    else {
                        System.out.println("Utilizatorul-Admin nu a fost gasit!");
                    }
                }
                case 5 -> System.out.println("Option 5 selected.");
                case 6 -> System.out.println("Option 6 selected.");
                case 7 -> System.out.println("Option 7 selected.");
                case 8 -> System.out.println("Option 8 selected.");
                case 9 -> {
                    System.out.println("Exiting...");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}