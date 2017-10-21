package betahouse.core.mail;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Yxm on 2017/10/21.
 */
public class Mail {
    private String Subject;
    private String Personal;
    private String Context;
    private InternetAddress[] addresses;

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getPersonal() {
        return Personal;
    }

    public void setPersonal(String personal) {
        Personal = personal;
    }

    public String getContext() {
        return Context;
    }

    public void setContext(String context) {
        Context = context;
    }

    public InternetAddress[] getAddresses() {
        return addresses;
    }

    public void setAddresses(String[] addressList, String[] receiverNames) throws UnsupportedEncodingException {
        this.addresses = new InternetAddress[addressList.length];
        for(int i =0;i<addressList.length;i++){
            addresses[i] = new InternetAddress(addressList[i], receiverNames[i], "UTF-8");
        }
    }

    public void setAddresses(String addressList, String receiverName) throws UnsupportedEncodingException {
        this.addresses = new InternetAddress[1];
        addresses[0] = new InternetAddress(addressList, receiverName, "UTF-8");
    }

    public void setAddresses(List<String> addressList,List<String> receiverNames) throws UnsupportedEncodingException {
        this.addresses = new InternetAddress[addressList.size()];
        for(int i =0;i<addressList.size();i++){
            addresses[i] = new InternetAddress(addressList.get(i), receiverNames.get(i), "UTF-8");
        }
    }
}
