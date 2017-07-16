package betahouse.mapper;

import betahouse.model.Message;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    Message selectByPrimaryKey(Integer id);

    List<Message> selectAll();

    int updateByPrimaryKey(Message record);

    Message selectByFromId(int fromId);
}