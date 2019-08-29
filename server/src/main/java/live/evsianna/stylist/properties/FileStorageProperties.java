package live.evsianna.stylist.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Rustam Mamedov
 */

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {

    @Getter
    @Setter
    private String uploadDir;

}
