package betahouse.service.club;

import betahouse.core.Base.SimpleMD5;
import betahouse.core.office.HSSF;
import betahouse.mapper.ClubMapper;
import betahouse.mapper.UserInfoMapper;
import betahouse.mapper.UserMapper;
import betahouse.model.Club;
import betahouse.model.ClubActivityForm;
import betahouse.model.User;
import betahouse.model.UserInfo;
import betahouse.service.financial.ClubFinancialFlowService;
import betahouse.service.form.FormManagerService;
import betahouse.service.power.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by x1654 on 2017/7/4.
 */
@Service
public class ClubServiceImpl implements ClubService {

    @Autowired
    private ClubMapper clubMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ClubFinancialFlowService clubFinancialFlowService;

    @Autowired
    private ClubActivityFormService clubActivityFormService;

    @Autowired
    private ClubActivityStatusService clubActivityStatusService;

    @Autowired
    private ClubActivityApproveService clubActivityApproveService;

    @Autowired
    private FormManagerService formManagerService;

    @Autowired
    private PowerService powerService;

    @Override
    public Club getClubByUserId(int userId) {
        return clubMapper.selectByUserId(userId);
    }

    @Override
    public List<Club> listAll() {
        return clubMapper.selectAll();
    }

    @Override
    public Map<String, UserInfo> listClubAndChief() {
        Map<String, UserInfo> mapDTO = new HashMap<>();
        List<Club> listDTO = clubMapper.selectAll();
        for(Club c: listDTO){
            UserInfo userInfoDTO = userInfoMapper.selectByPrimaryKey(c.getUserId());
            mapDTO.put(c.getClubName(), userInfoDTO);
        }
        return mapDTO;
    }

    @Override
    public int updateMoneyById(int id, int change, int selfReserve, float money) {
        Club clubDTO = clubMapper.selectByPrimaryKey(id);
        if(-1==change){
            money = -money;
        }
        if(1==selfReserve){
            float reserveMoneyDTO = clubDTO.getReserveMoney()+money;
            if(reserveMoneyDTO<0){
                return -1;
            }
            clubDTO.setReserveMoney(reserveMoneyDTO);
            return clubMapper.updateByPrimaryKey(clubDTO);
        }else if(2==selfReserve){
            float selfMoneyDTO = clubDTO.getSelfMoney()+money;
            if(selfMoneyDTO<0){
                return -1;
            }
            clubDTO.setSelfMoney(selfMoneyDTO);
            return clubMapper.updateByPrimaryKey(clubDTO);
        }
        return -1;
    }

    @Override
    public int createClub(String folderName, String fileName) {
        HSSF hssf = new HSSF("demo", "demo");
        hssf.open();
        int i=1;
        int idDTO = 0;
        while (!"".equals(hssf.get(0,i,1))){
            String clubNameDTO = hssf.get(0, i,1);
            String userRealNameDTO = hssf.get(0, i,2);
            String userNameDTO = hssf.get(0,i,3);
            String telDTO = hssf.get(0,i,4);
            String eMailDTO = hssf.get(0,i,6);
            String reserveMoneyDTO = hssf.get(0,i,7);
            String reserveMoneyDTO2 = hssf.get(0,i,8);
            String reserveMoneyDTO3 = hssf.get(0,i,9);
            String reserveMoneyDTO4 = hssf.get(0,i,10);
            String selfMoneyDTO = hssf.get(0,i,11);

            if(!"".equals(userNameDTO)){
                User userDTO = new User();
                userDTO.setUsername(userNameDTO);
                userDTO.setPassword(SimpleMD5.MD5(userNameDTO.substring(userNameDTO.length()-4)));
                idDTO = userMapper.insert(userDTO);

                UserInfo userInfoDTO = new UserInfo();
                userInfoDTO.setId(idDTO);
                userInfoDTO.seteMail(eMailDTO);
                userInfoDTO.setSchoolId(userNameDTO);
                userInfoDTO.setRealName(userRealNameDTO);
                userInfoDTO.setTel(telDTO);
                userInfoMapper.insert(userInfoDTO);
            }

            Club clubDTO = new Club();
            clubDTO.setClubName(clubNameDTO);
            clubDTO.setReserveMoney(Float.parseFloat(reserveMoneyDTO4));
            clubDTO.setSelfMoney(Float.parseFloat(selfMoneyDTO));
            clubDTO.setUserId(idDTO);
            int idDTO2 = clubMapper.insert(clubDTO);

            clubFinancialFlowService.insert(idDTO2, "2015年剩余预留金额", idDTO, 1, Integer.parseInt(reserveMoneyDTO));
            clubFinancialFlowService.insert(idDTO2, "2016年上交预留金额", idDTO, 1, Integer.parseInt(reserveMoneyDTO2));
            clubFinancialFlowService.insert(idDTO2, "2016学年预留经费使用", idDTO, -1, Integer.parseInt(reserveMoneyDTO3));

            i++;
        }
        return 0;
    }

    @Override
    public int deleteClub(int clubId) {
        int userIdDTO = clubMapper.selectByPrimaryKey(clubId).getUserId();
        formManagerService.updateFormManagerByApprover(userIdDTO, 1, -1);
        powerService.deletePowerByUserId(userIdDTO, new int[]{3,4});

        List<ClubActivityForm> listDTO = clubActivityFormService.listFormByClubId(clubId);
        for(ClubActivityForm c: listDTO){
            clubActivityStatusService.deleteStatusByFormId(c.getId());
            clubActivityApproveService.deleteApproveByFormId(c.getId());
        }
        clubFinancialFlowService.deleteFinaceByClubId(clubId);
        clubActivityFormService.deleteFormByClubId(clubId);
        clubMapper.deleteByPrimaryKey(clubId);
        return 0;
    }

    @Override
    public int updateClub(int clubId, String clubName,int userId, String realName, String schoolId, String eMail, String tel) {
        int oldUserId = clubMapper.selectByPrimaryKey(clubId).getUserId();
        if(!"".equals(clubName)){
            Club clubDTO = new Club();
            clubDTO.setId(clubId);
            clubDTO.setClubName(clubName);
            if(oldUserId!=userId){
                clubDTO.setUserId(userId);
                powerService.deletePowerByUserId(oldUserId, new int[]{3,4});
                formManagerService.updateFormManagerByApprover(oldUserId, 1, -1);
                powerService.updatePowerByUserId(userId, 3);
                powerService.updatePowerByUserId(userId, 4);
                formManagerService.updateFormManagerByApprover(userId, 1, 1);
            }
            clubMapper.updateByPrimaryKey(clubDTO);
        }
        if(!"".equals(realName)||!"".equals(schoolId)||!"".equals(eMail)||!"".equals(tel)){
            UserInfo userInfoDTO = new UserInfo();
            userInfoDTO.setId(userId);
            userInfoDTO.setRealName(realName);
            userInfoDTO.setSchoolId(schoolId);
            userInfoDTO.seteMail(eMail);
            userInfoDTO.setTel(tel);
            userInfoMapper.updateByPrimaryKey(userInfoDTO);
        }
        return 0;
    }

    @Override
    public int updateMoneyById(int id, float applySelfMoney, float applyReserveMoney) {
        Club clubDTO = clubMapper.selectByPrimaryKey(id);
        float selfMoneyDTO = clubDTO.getSelfMoney()-applySelfMoney;
        float reserveMoneyDTO = clubDTO.getReserveMoney()-applyReserveMoney;
        if(selfMoneyDTO<0||reserveMoneyDTO<0){
            return -1;
        }
        Club clubDTO2 = new Club();
        clubDTO2.setId(id);
        clubDTO2.setSelfMoney(selfMoneyDTO);
        clubDTO2.setReserveMoney(reserveMoneyDTO);
        return clubMapper.updateByPrimaryKey(clubDTO2);
    }

    @Override
    public Club getClubById(int id) {
        return clubMapper.selectByPrimaryKey(id);
    }
}
