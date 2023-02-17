package ru.job4j.pool;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Класс EmailNotification - сервис для рассылки почты.
 * @author Lenar Sharipov
 * @version 1.0
 */
@ThreadSafe
public class EmailNotification {
    private final ExecutorService pool =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * Метод emailTo() получает данные пользователя и подставляет в шаблон:
     * subject = Notification {username} to email {email}
     * body = Add a new event to {username}
     * @param user - метод принимает в качестве параметра модель данных User
     */
    public void emailTo(User user) {
        pool.submit(() -> {
            var userName = user.userName();
            var email = user.email();
            var subject = String.format("Notification %s to email %s", userName, email);
            var body = String.format("Add a new event to %s", userName);
            send(subject, body, email);
        });
    }

    public void send(String subject, String body, String email) {

    }

    /**
     * Метод close() закрывает pool
     */
    public void close() {
        pool.shutdown();
    }

    public static void main(String[] args) {
        EmailNotification emailNotification = new EmailNotification();
        emailNotification.emailTo(new User("Ivan Ivanov", "ivanov@ivanov.ru"));
        emailNotification.close();
    }
}
