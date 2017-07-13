package betahouse.service.information;

import betahouse.model.Message;

/**
 * Created by x1654 on 2017/7/13.
 */
public interface MessageService {

    Message getMessageById(int id);

    int sendMessage(int userId, String title, String comment, String toId);

}
