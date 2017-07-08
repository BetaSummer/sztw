package betahouse.service.club;

import betahouse.mapper.ClubActivityFormMapper;
import betahouse.model.ClubActivityForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by x1654 on 2017/7/4.
 */
@Service
public class ClubActivityFormServiceImpl implements ClubActivityFormService {

    @Autowired
    private ClubActivityFormMapper clubActivityFormMapper;

    @Override
    public int commitForm(String club, String chiefName, String activityName, String activityPlace,
                          String activityTime, String activityPeople, String isApplyFine, String activityInfo,
                          String applySelfMoney, String applyReserveMoney,int clubId, int fileId) {
        int isApplyFineDTO = Integer.parseInt(isApplyFine);
        int applySelfMoneyDTO = Integer.parseInt(applySelfMoney);
        int applyReserveMoneyDTO = Integer.parseInt(applyReserveMoney);
        ClubActivityForm clubActivityFormDTO = new ClubActivityForm();
        clubActivityFormDTO.setClub(club);
        clubActivityFormDTO.setChiefName(chiefName);
        clubActivityFormDTO.setActivityName(activityName);
        clubActivityFormDTO.setActivityPlace(activityPlace);
        clubActivityFormDTO.setActivityTime(activityTime);
        clubActivityFormDTO.setActivityPeople(activityPeople);
        clubActivityFormDTO.setIsApplyFine(isApplyFineDTO);
        clubActivityFormDTO.setActivityInfo(activityInfo);
        clubActivityFormDTO.setApplySelfMoney(applySelfMoneyDTO);
        clubActivityFormDTO.setReserveMoney(applyReserveMoneyDTO);
        clubActivityFormDTO.setClubId(clubId);
        clubActivityFormDTO.setFileId(fileId);
        return clubActivityFormMapper.insert(clubActivityFormDTO);
    }

    @Override
    public ClubActivityForm getFormById(int id) {
        return clubActivityFormMapper.selectByPrimaryKey(id);
    }

    @Override
    public ClubActivityForm getLastForm() {
        return clubActivityFormMapper.selectAll().get(0);
    }

    @Override
    public int deleteFormById(int id) {
        return clubActivityFormMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int getLastInsertId() {
        return clubActivityFormMapper.selectLastInsertId();
    }
}
