package betahouse.service.information;

import betahouse.model.Message;

import java.util.List;

/**
 * Created by x1654 on 2017/7/13.
 */
public interface MessageBoxService {

    List<Message> listAllByUserId(int userId);

}
