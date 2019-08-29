package live.evsianna.stylist;

import live.evsianna.stylist.properties.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class StylistServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StylistServiceApplication.class, args);
    }
}
