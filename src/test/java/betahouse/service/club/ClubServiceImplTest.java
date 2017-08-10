package betahouse.service.club;

import betahouse.core.office.HSSF;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClubServiceImplTest {
    @Test
    public void createClub() throws Exception {
        HSSF hssf = new HSSF("demo", "demo");
        hssf.open();
        int i=1;
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

            String[] str = new String[]{clubNameDTO, userRealNameDTO, userNameDTO, telDTO, eMailDTO, reserveMoneyDTO, reserveMoneyDTO2,
                    reserveMoneyDTO3, reserveMoneyDTO4, selfMoneyDTO};
            System.out.println(str[0]);
            i++;
        }
    }
}