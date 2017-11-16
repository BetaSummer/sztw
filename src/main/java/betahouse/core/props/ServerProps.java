package betahouse.core.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Yxm on 2017/11/16.
 */
@Component
@ConfigurationProperties(prefix="server")
public class ServerProps {
    private String port;
    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
