package betahouse.mapper;

import betahouse.model.ClubActivityStatus;
import java.util.List;

public interface ClubActivityStatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClubActivityStatus record);

    ClubActivityStatus selectByPrimaryKey(Integer id);

    List<ClubActivityStatus> selectAll();

    int updateByPrimaryKey(ClubActivityStatus record);

    ClubActivityStatus selectByFormId(Integer formId);

    int updateByFormId(ClubActivityStatus record);

    List<ClubActivityStatus> selectByLv(int lv);

    List<ClubActivityStatus> selectByFormUserId(int formUsereId);
}