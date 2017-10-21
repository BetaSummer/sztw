package betahouse.core.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Yxm on 2017/10/21.
 */
@Component
@ConfigurationProperties(prefix="Props.mailProps")
public class MailProps {
    private String sendAccount;
    private String sendPassword;
    private String SMPHost;

    public String getSendAccount() {
        return sendAccount;
    }

    public void setSendAccount(String sendAccount) {
        this.sendAccount = sendAccount;
    }

    public String getSendPassword() {
        return sendPassword;
    }

    public void setSendPassword(String sendPassword) {
        this.sendPassword = sendPassword;
    }

    public String getSMPHost() {
        return SMPHost;
    }

    public void setSMPHost(String SMPHost) {
        this.SMPHost = SMPHost;
    }
}
