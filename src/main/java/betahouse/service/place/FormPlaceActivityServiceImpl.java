package betahouse.service.place;

import betahouse.mapper.FormPlaceActivityMapper;
import betahouse.model.FormPlaceActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FormPlaceActivityServiceImpl implements FormPlaceActivityService{

    @Autowired
    private FormPlaceActivityMapper formPlaceActivityMapper;

    @Autowired
    private StatusPlaceActivityService statusPlaceActivityService;

    @Autowired
    private FormPublicUtilityService formPublicUtilityService;

    @Override
    public int commitForm(int formUserId,String activityName, String activityPlace, String activityDate, String list,
                          String content, String budget, String method, int resourcesStatus) {
        FormPlaceActivity formPlaceActivityDTO = new FormPlaceActivity();
        formPlaceActivityDTO.setUserId(formUserId);
        formPlaceActivityDTO.setActivityName(activityName);
        formPlaceActivityDTO.setActivityPlace(activityPlace);
        formPlaceActivityDTO.setActivityDate(activityDate);
        formPlaceActivityDTO.setList(list);
        formPlaceActivityDTO.setContent(content);
        formPlaceActivityDTO.setBudget(budget);
        formPlaceActivityDTO.setMethod(method);
        Date dateDTO = new Date();
        SimpleDateFormat sdfDTO  = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        formPlaceActivityDTO.setDate(sdfDTO.format(dateDTO));
        int idDTO = formPlaceActivityMapper.insert(formPlaceActivityDTO);
        statusPlaceActivityService.insert(idDTO, formUserId, resourcesStatus, 0);
        return 0;
    }

    @Override
    public int commitForm(int formUserId, String activityName, String activityPlace, String activityDate,
                          String list, String content, String budget, String method, int resourcesStatus,
                          String defendComment, String electricComment, String water, String electric,
                          String start, String end) {
        FormPlaceActivity formPlaceActivityDTO = new FormPlaceActivity();
        formPlaceActivityDTO.setUserId(formUserId);
        formPlaceActivityDTO.setActivityName(activityName);
        formPlaceActivityDTO.setActivityPlace(activityPlace);
        formPlaceActivityDTO.setActivityDate(activityDate);
        formPlaceActivityDTO.setList(list);
        formPlaceActivityDTO.setContent(content);
        formPlaceActivityDTO.setBudget(budget);
        formPlaceActivityDTO.setMethod(method);
        Date dateDTO = new Date();
        SimpleDateFormat sdfDTO  = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        formPlaceActivityDTO.setDate(sdfDTO.format(dateDTO));
        int idDTO = formPlaceActivityMapper.insert(formPlaceActivityDTO);
        statusPlaceActivityService.insert(idDTO, formUserId, resourcesStatus, 1);
        formPublicUtilityService.insert(idDTO, defendComment, electricComment,water, electric, start, end);
        return 0;
    }
}
