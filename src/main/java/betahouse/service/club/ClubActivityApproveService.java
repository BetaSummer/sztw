package betahouse.service.club;

import betahouse.model.ClubActivityApprove;
import betahouse.model.User;

import java.util.List;

/**
 * Created by x1654 on 2017/7/4.
 */
public interface ClubActivityApproveService {

    int saveApprove(User user, int isApprove, int formId, String comment, int clubId, int applySelfMoney,
                    int applyReserveMoney);

    List<ClubActivityApprove> listApproveByLv(int lv);

    List<ClubActivityApprove> listApproveByLvAndFormId(int lv, int formId);

    List<ClubActivityApprove> listApproveByFormId(int formId);

}
