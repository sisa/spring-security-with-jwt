package io.sisa.core.security.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author isaozturk
 */

@Data
@ConfigurationProperties("jwt")
public class JwtProperties {

    private String header;
    private String secret;
    private Long expiration;
    private String authServerName;
}
