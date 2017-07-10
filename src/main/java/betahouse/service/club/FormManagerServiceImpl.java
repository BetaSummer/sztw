package betahouse.service.club;

import betahouse.mapper.FormManagerMapper;
import betahouse.model.FormManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by x1654 on 2017/7/10.
 */
@Service
public class FormManagerServiceImpl implements FormManagerService{

    @Autowired
    private FormManagerMapper formManagerMapper;

    @Override
    public FormManager getFormManagerByApprover(int approverId) {
        return formManagerMapper.selectFormManagerByApprover(approverId);
    }
}
