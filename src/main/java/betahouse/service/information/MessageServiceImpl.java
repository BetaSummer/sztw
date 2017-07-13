package betahouse.service.information;

import betahouse.mapper.MessageMapper;
import betahouse.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by x1654 on 2017/7/13.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public Message getMessageById(int id) {
        return messageMapper.selectByPrimaryKey(id);
    }

    @Override
    public int sendMessage(int userId, String title, String comment, String toId) {
        Message messageDTO = new Message();
        messageDTO.setFromId(userId);
        messageDTO.setTitle(title);
        messageDTO.setComment(comment);
        messageDTO.setToId(toId);
        Date dateDTO = new Date();
        SimpleDateFormat sdfDTO = new SimpleDateFormat("yyyy/MM/dd");
        messageDTO.setDate(sdfDTO.format(dateDTO));
        return messageMapper.insert(messageDTO);
    }
}
