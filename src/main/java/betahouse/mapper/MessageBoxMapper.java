package betahouse.mapper;

import betahouse.model.MessageBox;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface MessageBoxMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MessageBox record);

    MessageBox selectByPrimaryKey(Integer id);

    List<MessageBox> selectAll();

    int updateByPrimaryKey(MessageBox record);

    MessageBox selectByUserId(Integer userId);
}