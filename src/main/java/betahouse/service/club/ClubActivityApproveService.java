package betahouse.service.club;

import betahouse.model.ClubActivityApprove;
import betahouse.model.User;
import betahouse.model.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by x1654 on 2017/7/4.
 */
public interface ClubActivityApproveService {

    int saveApprove(UserInfo userInfo, int isApprove, int formId, String comment, int applySelfMoney, int applyReserveMoney);

    List<ClubActivityApprove> listApproveByLv(int lv);

    ClubActivityApprove getApproveByLvAndFormId(int lv, int formId);

    List<ClubActivityApprove> listApproveByFormId(int formId);

}
