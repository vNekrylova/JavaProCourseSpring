package org.example;

import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        UserService userService = context.getBean(UserService.class);

        userService.createUser("Валерия");
        userService.createUser("Валерий");

        System.out.println("Вывод всех пользователей:");
        for (User user : userService.getAllUsers()) {
            System.out.println(user.toString());
            System.out.println("----");
        }

        userService.updateUser(2L, "Валера");

        userService.deleteUser(2L);

        context.close();
    }
}