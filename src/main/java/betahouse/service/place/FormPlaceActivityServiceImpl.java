package betahouse.service.place;

import betahouse.mapper.FormPlaceActivityMapper;
import betahouse.model.FormPlaceActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormPlaceActivityServiceImpl implements FormPlaceActivityService{

    @Autowired
    private FormPlaceActivityMapper formPlaceActivityMapper;

    @Override
    public int commitForm(String activityName, String activityPlace, String activityDate, String list,
                          String content, String budget, String method) {
        FormPlaceActivity formPlaceActivityDTO = new FormPlaceActivity();
        formPlaceActivityDTO.setActivityName(activityName);
        formPlaceActivityDTO.setActivityPlace(activityPlace);
        formPlaceActivityDTO.setActivityDate(activityDate);
        formPlaceActivityDTO.setList(list);
        formPlaceActivityDTO.setContent(content);
        formPlaceActivityDTO.setBudget(budget);
        formPlaceActivityDTO.setMethod(method);
        return 0;
    }
}
