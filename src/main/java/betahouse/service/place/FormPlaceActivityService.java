package betahouse.service.place;

public interface FormPlaceActivityService {

    int commitForm(int formUserId,String activityName, String activityPlace, String activityDate, String list, String content,
                   String budget, String method, int resourcesStatus);

    int commitForm(int formUserId,String activityName, String activityPlace, String activityDate, String list, String content,
                   String budget, String method, int resourcesStatus, String defendComment, String electricComment,
                   String water, String electric, String start, String end);



}
