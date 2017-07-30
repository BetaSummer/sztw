package betahouse.service.place;

import betahouse.mapper.FormPublicUtilityMapper;
import betahouse.model.FormPublicUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FormPublicUtilityServiceImpl implements FormPublicUtilityService {

    @Autowired
    private FormPublicUtilityMapper formPublicUtilityMapper;

    @Override
    public int insert(int formId, String defendComment, String electricComment, String water,
                      String electric, String start, String end) {
        FormPublicUtility FormPublicUtilityDTO = new FormPublicUtility();
        FormPublicUtilityDTO.setFormId(formId);
        FormPublicUtilityDTO.setDefendComment(defendComment);
        FormPublicUtilityDTO.setElectricComment(electricComment);
        FormPublicUtilityDTO.setWater(water);
        FormPublicUtilityDTO.setElectric(electric);
        FormPublicUtilityDTO.setStart(start);
        FormPublicUtilityDTO.setEnd(end);
        return formPublicUtilityMapper.insert(FormPublicUtilityDTO);
    }
}
