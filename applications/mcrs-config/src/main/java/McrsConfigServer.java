import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by kinggrass on 27.04.15.
 */
@EnableAutoConfiguration
@EnableConfigServer
public class McrsConfigServer {

    public static void main(String[] args) {
        SpringApplication.run(McrsConfigServer.class, args);
    }
}
