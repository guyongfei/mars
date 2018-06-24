package com.witshare.mars;

import com.alibaba.fastjson.JSON;
import com.witshare.mars.pojo.dto.TokenDistributeBean;
import com.witshare.mars.pojo.util.ResponseBean;
import com.witshare.mars.service.TokenDistributeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TokenDistributeServiceTest extends BaseSpringTest {


    @Autowired
    private TokenDistributeService tokenDistributeService;

    @Test
    public void progress() throws Exception {
        ResponseBean res = tokenDistributeService.getTokenDistributeProgress("bf648cb3a3904bcdb4f4c083ce68a823");
        System.out.print(JSON.toJSONString(res));
    }

    @Test
    public void distribute() throws Exception {
        ResponseBean res = tokenDistributeService.execTokenDistribute(new TokenDistributeBean() {{
            setPassword("1234");
        }});
        System.out.print(JSON.toJSONString(res));
    }
}
