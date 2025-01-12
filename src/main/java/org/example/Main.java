package org.example;

import org.example.interfaces.IAuthenticationService;
import org.example.interfaces.IFileHandler;
import org.example.interfaces.IUserService;
import org.example.models.*;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

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
            System.out.println("7. Exit");
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
                    } while (!ok);
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
                    } while (!ok);
                }
                case 3 -> {
                    System.out.println("Introduceti username: ");
                    String username = scanner.next();

                    if (userService.findUserByUsername(username).isPresent()) {
                        System.out.println("Utilizatorul a fost gasit!");
                    } else {
                        System.out.println("Utilizatorul nu a fost gasit!");
                    }
                }
                case 4 -> {
                    System.out.println("Introduceti username: ");
                    String username = scanner.next();

                    if (userService.findAdminUserByUsername(username).isPresent()) {
                        System.out.println("Utilizatorul-Admin a fost gasit!");
                    } else {
                        System.out.println("Utilizatorul-Admin nu a fost gasit!");
                    }
                }
                case 5 -> {
                    System.out.println("Introduceti username: ");
                    String username = scanner.next();
                    System.out.println("Introduceti password: ");
                    String password = scanner.next();

                    if (authenticationService.authenticateStandardUser(userService, username, password)) {
                        System.out.println("Utilizatorul a fost logat!");
                        Optional<User> user = userService.findUserByUsername(username);
                        user.ifPresent(value -> menuForStandardUsers(value, fileHandler));
                    } else {
                        System.out.println("Utilizatorul nu a fost logat!");
                    }
                }
                case 6 -> {
                    System.out.println("Introduceti username: ");
                    String username = scanner.next();
                    System.out.println("Introduceti password: ");
                    String password = scanner.next();

                    if (authenticationService.authenticateAdminUser(userService, username, password)) {
                        System.out.println("Utilizatorul-Admin a fost logat!");
                        Optional<AdminUser> user = userService.findAdminUserByUsername(username);
                        user.ifPresent(value -> menuForAdminUsers(value, fileHandler));
                    } else {
                        System.out.println("Utilizatorul-Admin nu a fost logat!");
                    }
                }
                case 7 -> {
                    System.out.println("Exiting...");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void menuForStandardUsers(User user, IFileHandler fileHandler) {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("=== User Menu ===");
            System.out.println("1. Creeaza Postare");
            System.out.println("2. Vizualizeaza Postari");
            System.out.println("3. Da Like Postarii");
            System.out.println("4. Comenteaza la Postare");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> createPost(user, fileHandler, scanner);
                case 2 -> viewPosts(fileHandler);
                case 3 -> likePost(user, fileHandler, scanner);
                case 4 -> commentOnPost(user, fileHandler, scanner);
                case 5 -> {
                    System.out.println("Logging out...");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void menuForAdminUsers(AdminUser user, IFileHandler fileHandler) {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("=== Admin Menu ===");
            System.out.println("1. Creeaza Postare");
            System.out.println("2. Vizualizeaza Postari");
            System.out.println("3. Sterge Postare");
            System.out.println("4. Da Like Postarii");
            System.out.println("5. Comenteaza la Postare");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> createPost(user, fileHandler, scanner);
                case 2 -> viewPosts(fileHandler);
                case 3 -> {
                    System.out.println("Introduceti titlul postarii: ");
                    String title = scanner.nextLine();
                    List<Post> posts = fileHandler.loadPosts();
                    for (Post post : posts) {
                        if (post.getTitle().equals(title)) {
                            posts.remove(post);
                            fileHandler.savePosts(posts);
                            System.out.println("Postarea a fost stearsa cu succes!");
                            break;
                        }
                    }
                }
                case 4 -> likePost(user, fileHandler, scanner);
                case 5 -> commentOnPost(user, fileHandler, scanner);
                case 6 -> {
                    System.out.println("Logging out...");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void createPost(User user, IFileHandler fileHandler, Scanner scanner) {
        System.out.println("Introduceti titlul postarii: ");
        String title = scanner.nextLine();
        System.out.println("Introduceti continutul postarii: ");
        String content = scanner.nextLine();
        Post post = new Post(title, content, user.getUsername());
        List<Post> posts = fileHandler.loadPosts();
        posts.add(post);
        fileHandler.savePosts(posts);
        System.out.println("Postarea a fost creata cu succes!");
    }

    public static void viewPosts(IFileHandler fileHandler) {
        List<Post> posts = fileHandler.loadPosts();
        if (posts.isEmpty()) {
            System.out.println("Nu exista postari.");
        } else {
            for (Post post : posts) {
                System.out.println("Titlu: " + post.getTitle());
                System.out.println("Continut: " + post.getContent());
                System.out.println("Author: " + post.getAuthor());
                Like likes = post.getLikes();
                if (likes.getUsernames().isEmpty()) {
                    System.out.println("Likes: 0");
                } else {
                    System.out.println("Likes: " + likes.getUsernames().size());
                }
                System.out.println("Comments:");
                for (Comment comment : post.getComments()) {
                    System.out.println("Author: " + comment.getCommentText());
                    System.out.println("Text: " + comment.getAuthor());
                }
                System.out.println("-----------");
            }
        }
    }

    public static void likePost(User user, IFileHandler fileHandler, Scanner scanner) {
        System.out.println("Introduceti titlul postarii: ");
        String title = scanner.nextLine();
        List<Post> posts = fileHandler.loadPosts();
        for (Post post : posts) {
            if (post.getTitle().equals(title)) {
                Like likes = post.getLikes();
                if (likes.getUsernames().contains(user.getUsername())) {
                    System.out.println("Ati dat deja like acestei postari.");
                } else {
                    likes.addUsername(user.getUsername());
                    post.setLikes(likes);
                    fileHandler.savePosts(posts);
                    System.out.println("Like adaugat cu succes!");
                }
                return;
            }
        }
        System.out.println("Postarea nu a fost gasita.");
    }

    public static void commentOnPost(User user, IFileHandler fileHandler, Scanner scanner) {
        System.out.println("Introduceti titlul postarii: ");
        String title = scanner.nextLine();
        System.out.println("Introduceti comentariul: ");
        String content = scanner.nextLine();
        List<Post> posts = fileHandler.loadPosts();
        for (Post post : posts) {
            if (post.getTitle().equals(title)) {
                Comment comment = new Comment(user.getUsername(), content);
                post.getComments().add(comment);
                fileHandler.savePosts(posts);
                System.out.println("Comentariu adaugat cu succes!");
                return;
            }
        }
        System.out.println("Postarea nu a fost gasita.");
    }
}