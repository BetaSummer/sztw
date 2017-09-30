package betahouse.service.information;

import betahouse.mapper.MessageMapper;
import betahouse.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;


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
    public int sendMessage(int userId, String title, String comment, String toId, int fileId) {
        Message messageDTO = new Message();
        messageDTO.setFromId(userId);
        messageDTO.setTitle(title);
        messageDTO.setComment(comment);
        messageDTO.setToId(toId);
        Date dateDTO = new Date();
        SimpleDateFormat sdfDTO = new SimpleDateFormat("yyyy年MM月dd日");
        messageDTO.setDate(sdfDTO.format(dateDTO));
        messageDTO.setStatus(1);
        messageDTO.setFileId(fileId);
        return messageMapper.insert(messageDTO);
    }

    @Override
    public int saveMessage(int id, int userId, String title, String comment, String toId, int fileId) {
        Message messageDTO = new Message();
        messageDTO.setFromId(userId);
        messageDTO.setTitle(title);
        messageDTO.setComment(comment);
        messageDTO.setToId(toId);
        Date dateDTO = new Date();
        SimpleDateFormat sdfDTO = new SimpleDateFormat("yyyy年MM月dd日");
        messageDTO.setDate(sdfDTO.format(dateDTO));
        messageDTO.setStatus(0);
        messageDTO.setFileId(fileId);
        if(id==0){
            return messageMapper.insert(messageDTO);
        }
        messageDTO.setId(id);
        return messageMapper.updateByPrimaryKey(messageDTO);
    }
}
