package bootcamp.testtask.rest;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "bootcamp.testtask.rest")
@Data
public class RestProperties {

    private int pageSize = 10;
}
