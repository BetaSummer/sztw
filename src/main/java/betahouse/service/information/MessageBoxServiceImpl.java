package betahouse.service.information;

import betahouse.mapper.MessageBoxMapper;
import betahouse.mapper.MessageMapper;
import betahouse.model.Message;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by x1654 on 2017/7/13.
 */
@Service
public class MessageBoxServiceImpl implements MessageBoxService{

    @Autowired
    private MessageBoxMapper messageBoxMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public List<Message> listAllByUserId(int userId) {
        String messageDTO = null;
        List<Message> messageListDTO = new ArrayList<>();
        try {
            messageDTO = messageBoxMapper.selectByUserId(userId).getMessageList();
            List<Integer> listDTO = JSON.parseArray(messageDTO, Integer.class);
            for(int id: listDTO){
                messageListDTO.add(messageMapper.selectByPrimaryKey(id));
            }
            return messageListDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messageListDTO;
    }
}
