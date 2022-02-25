package link.seeyouat.take2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class Take2Application {

    public static void main(String[] args) {

        SpringApplication application =
                new SpringApplication(Take2Application.class);
        application.addListeners(new ApplicationPidFileWriter());
        application.run(args);

    }

}
