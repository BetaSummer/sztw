package betahouse.mapper;

import betahouse.model.Club;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ClubMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Club record);

    Club selectByPrimaryKey(Integer id);

    List<Club> selectAll();

    int updateByPrimaryKey(Club record);

    Club selectByUserId(Integer userId);

}