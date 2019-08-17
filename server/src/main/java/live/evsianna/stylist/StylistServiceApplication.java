package live.evsianna.stylist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class StylistServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StylistServiceApplication.class, args);
    }
}
