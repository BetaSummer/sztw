package betahouse.core.mail;

import betahouse.core.props.MailProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Yxm on 2017/10/21.
 */
@Service
public class MailCore {
    /**
     * MailProps
     * -SMPHost 邮箱SMTP服务器
     * -SendAccount 邮箱账号
     * -SendPassword 授权码
     */
    @Autowired
    private MailProps mailProps;
    /**
     * 发送邮件
     * @param mail
     * @return 0:成功 -1:邮件创建失败 -2:连接失败 -3:发送失败
     */
    public Integer sendMail(Mail mail){
        //创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", mailProps.getSMPHost());   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
        /*
        final String smtpPort = "465";
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
         */
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

        // 创建一封邮件
        MimeMessage message = null;
        try {
            message = createMimeMessage(session, mailProps.getSendAccount(), mail);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        // 根据 Session 获取邮件传输对象
        Transport transport = null;
        try {
            transport = session.getTransport();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            return -2;
        }
        try {
            transport.connect(mailProps.getSendAccount(), mailProps.getSendPassword());
        } catch (MessagingException e) {
            e.printStackTrace();
            return -2;
        }
        // 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        try {
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
            return -3;
        }
        return 0;
    }

    /**
     *
     * @param session 和服务器交互的会话
     * @param mail 邮件内容
     * @return
     * @throws Exception
     */
    private static MimeMessage createMimeMessage(Session session, String SendAccount, Mail mail) throws Exception {
        MimeMessage message = new MimeMessage(session);
        //From: 发件人
        message.setFrom(new InternetAddress(SendAccount, mail.getPersonal(), "UTF-8"));
        // To: 收件人
        message.setRecipients(MimeMessage.RecipientType.TO, mail.getAddresses());
        // Subject: 邮件主题
        message.setSubject(mail.getSubject(), "UTF-8");
        // Content: 邮件正文（可以使用html标签）
        message.setContent(mail.getContext(), "text/html;charset=UTF-8");
        // 设置发件时间
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }

}
