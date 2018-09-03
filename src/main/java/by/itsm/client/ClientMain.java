package by.itsm.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ClientMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ClientConfig.class);
        Runnable starter = context.getBean(Runnable.class);

        starter.run();

    }
}
