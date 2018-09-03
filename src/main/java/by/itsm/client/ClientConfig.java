package by.itsm.client;

import by.itsm.client.serv.SimpleRequest;
import by.itsm.client.serv.SimpleResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.function.Supplier;

@Configuration
public class ClientConfig {

    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }

    @Bean
    public String name() {
        return "Tom";
    }

    @Bean
    public Supplier<SimpleRequest> supplier(String name) {
        String message = "hello";
        return () -> new SimpleRequest(name, message);
    }

    @Bean
    public Runnable executor(ObjectMapper mapper, Supplier<SimpleRequest> supplier) {
        return () -> {
            try {
                Socket socket = new Socket("localhost", 8081);
                DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
                DataInputStream reader = new DataInputStream(socket.getInputStream());

                SimpleRequest request = supplier.get();
                String requestString = mapper.writeValueAsString(request);

                writer.writeUTF(requestString);
                writer.flush();

                String responseString = reader.readUTF();
                SimpleResponse response = mapper.readValue(responseString, SimpleResponse.class);

                System.out.println(response.getMessage());

                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
}
