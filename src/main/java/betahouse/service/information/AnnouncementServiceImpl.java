package betahouse.service.information;

import betahouse.mapper.AnnouncementMapper;
import betahouse.model.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by x1654 on 2017/7/12.
 */
@Service
public class AnnouncementServiceImpl implements AnnouncementService{

    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public Map<String, String> listAll() {
        Map<String, String> mapDTO = new HashMap<>();
        List<Announcement> listDTO = announcementMapper.selectAll();
        for(Announcement a: listDTO){
            mapDTO.put(a.getTime(), a.getTitle());
        }
        return mapDTO;
    }
}
