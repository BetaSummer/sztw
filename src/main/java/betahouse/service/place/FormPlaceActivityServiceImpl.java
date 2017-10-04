package betahouse.service.place;

import betahouse.mapper.*;
import betahouse.model.*;
import betahouse.model.VO.PlaceActivityTableVO;
import betahouse.service.organization.OrganizationMemberService;
import betahouse.service.organization.OrganizationTimesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class FormPlaceActivityServiceImpl implements FormPlaceActivityService{

    @Autowired
    private FormPlaceActivityMapper formPlaceActivityMapper;

    @Autowired
    private StatusPlaceActivityService statusPlaceActivityService;

    @Autowired
    private FormPublicUtilityService formPublicUtilityService;

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private OrganizationMemberMapper organizationMemberMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private ApprovePlaceActivityService approvePlaceActivityService;

    @Autowired
    private OrganizationTimesServices organizationTimesServices;

    @Autowired
    private OrganizationMemberService organizationMemberService;

    @Autowired
    private FormPublicUtilityMapper formPublicUtilityMapper;

    @Override
    public int commitForm(int formUserId, String userTel, String activityName, String activityPlace, String activityDate, String list,
                          String content, String budget, String method, int resourcesStatus) {
        FormPlaceActivity formPlaceActivityDTO = new FormPlaceActivity();
        formPlaceActivityDTO.setUserId(formUserId);
        formPlaceActivityDTO.setUserTel(userTel);
        formPlaceActivityDTO.setActivityName(activityName);
        formPlaceActivityDTO.setActivityPlace(activityPlace);
        formPlaceActivityDTO.setActivityDate(activityDate);
        formPlaceActivityDTO.setList(list);
        formPlaceActivityDTO.setContent(content);
        formPlaceActivityDTO.setBudget(Float.parseFloat(budget));
        formPlaceActivityDTO.setMethod(method);
        Date dateDTO = new Date();
        SimpleDateFormat sdfDTO  = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        formPlaceActivityDTO.setDate(sdfDTO.format(dateDTO));
        int organizationIdDTO = organizationMemberService.getOrganizationByUserId(formUserId).getOrganizationId();
        OrganizationTimes organizationTimesDTO = organizationTimesServices.getTimeByOrganizationId(organizationIdDTO);
        if(organizationTimesDTO==null){
            organizationTimesServices.insert(organizationIdDTO, 1);
            formPlaceActivityDTO.setNumber(1);
        }else {
            formPlaceActivityDTO.setNumber(organizationTimesDTO.getTimes()+1);
            organizationTimesServices.addTime(organizationTimesDTO.getId());
        }
        formPlaceActivityMapper.insert(formPlaceActivityDTO);
        int idDTO = formPlaceActivityDTO.getId();
        statusPlaceActivityService.insert(idDTO, formUserId, resourcesStatus, 0);
        return 0;
    }

    @Override
    public int commitForm(int formUserId, String userTel, String activityName, String activityPlace, String activityDate,
                          String list, String content, String budget, String method, int resourcesStatus,
                          String water, String electric,
                          String start, String end) {
        FormPlaceActivity formPlaceActivityDTO = new FormPlaceActivity();
        formPlaceActivityDTO.setUserId(formUserId);
        formPlaceActivityDTO.setUserTel(userTel);
        formPlaceActivityDTO.setActivityName(activityName);
        formPlaceActivityDTO.setActivityPlace(activityPlace);
        formPlaceActivityDTO.setActivityDate(activityDate);
        formPlaceActivityDTO.setList(list);
        formPlaceActivityDTO.setContent(content);
        formPlaceActivityDTO.setBudget(Float.parseFloat(budget));
        formPlaceActivityDTO.setMethod(method);
        Date dateDTO = new Date();
        SimpleDateFormat sdfDTO  = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        formPlaceActivityDTO.setDate(sdfDTO.format(dateDTO));
        int organizationIdDTO = organizationMemberService.getOrganizationByUserId(formUserId).getOrganizationId();
        OrganizationTimes organizationTimesDTO = organizationTimesServices.getTimeByOrganizationId(organizationIdDTO);
        if(organizationTimesDTO==null){
            organizationTimesServices.insert(organizationIdDTO, 1);
            formPlaceActivityDTO.setNumber(1);
        }else {
            formPlaceActivityDTO.setNumber(organizationTimesDTO.getTimes()+1);
            organizationTimesServices.addTime(organizationTimesDTO.getId());
        }
        formPlaceActivityMapper.insert(formPlaceActivityDTO);
        int idDTO = formPlaceActivityDTO.getId();
        statusPlaceActivityService.insert(idDTO, formUserId, resourcesStatus, 1);
        formPublicUtilityService.insert(idDTO, water, electric, start, end);
        return 0;
    }

    @Override
    public PlaceActivityTableVO getPlaceActivityTableVOById(int id) {
        FormPlaceActivity formPlaceActivityDTO = formPlaceActivityMapper.selectByPrimaryKey(id);
        int organizationIdDTO = organizationMemberMapper.selectByUserId(formPlaceActivityDTO.getUserId()).getOrganizationId();
        String organizationDTO = organizationMapper.selectByPrimaryKey(organizationIdDTO).getOrganizationName();
        UserInfo userInfoDTO = userInfoMapper.selectByPrimaryKey(formPlaceActivityDTO.getUserId());
        List<ApprovePlaceActivity> listDTO = approvePlaceActivityService.listApproveByFormId(id);

        PlaceActivityTableVO placeActivityTableVO = new PlaceActivityTableVO();
        placeActivityTableVO.setId(id);
        placeActivityTableVO.setNumber(formPlaceActivityDTO.getNumber());
        placeActivityTableVO.setOrganization(organizationDTO);
        placeActivityTableVO.setUsername(userInfoDTO.getRealName());
        placeActivityTableVO.setTel(formPlaceActivityDTO.getUserTel());
        placeActivityTableVO.setActivityName(formPlaceActivityDTO.getActivityName());
        placeActivityTableVO.setActivityPlace(formPlaceActivityDTO.getActivityPlace());
        placeActivityTableVO.setActivityDate(formPlaceActivityDTO.getActivityDate());
        placeActivityTableVO.setList(formPlaceActivityDTO.getList());
        placeActivityTableVO.setContent(formPlaceActivityDTO.getContent());
        placeActivityTableVO.setBudget(formPlaceActivityDTO.getBudget().toString());
        placeActivityTableVO.setMethod(formPlaceActivityDTO.getMethod());
        for(ApprovePlaceActivity a: listDTO){
            String approverName = userInfoMapper.selectByPrimaryKey(a.getApproveUserId()).getRealName();
            switch (a.getLv()){
                case 2:
                    placeActivityTableVO.setOrganizationComment(a.getComment());
                    placeActivityTableVO.setLeaderName(approverName);
                    placeActivityTableVO.setOrganizationApproveDate(a.getDate());
                    break;
                case 3:
                    placeActivityTableVO.setTeacherComment(a.getComment());
                    placeActivityTableVO.setTeacherName(approverName);
                    placeActivityTableVO.setTeacherApproveDate(a.getDate());
                    break;
                case 4:
                    placeActivityTableVO.setCommitteeComment(a.getComment());
                    placeActivityTableVO.setCommitteeName(approverName);
                    placeActivityTableVO.setCommitteeApproveDate(a.getDate());
                    break;
                case 5:
                    placeActivityTableVO.setEducationComment(a.getComment());
                    placeActivityTableVO.setEducationName(approverName);
                    placeActivityTableVO.setEducationApproveDate(a.getDate());
                    break;
                case 6:
                    placeActivityTableVO.setDefendComment(a.getComment());
                    placeActivityTableVO.setDefendName(approverName);
                    placeActivityTableVO.setElectricComment(a.getComment());
                    placeActivityTableVO.setElectricName(approverName);
            }
        }
        FormPublicUtility formPublicUtilityDTO = formPublicUtilityMapper.selectByFormId(id);
        placeActivityTableVO.setWater(formPublicUtilityDTO.getWater());
        placeActivityTableVO.setElectric(formPublicUtilityDTO.getElectric());
        placeActivityTableVO.setStart(formPublicUtilityDTO.getStart());
        placeActivityTableVO.setEnd(formPublicUtilityDTO.getEnd());
        return placeActivityTableVO;
    }

    @Override
    public FormPlaceActivity getFormById(int id) {
        return formPlaceActivityMapper.selectByPrimaryKey(id);
    }
}
