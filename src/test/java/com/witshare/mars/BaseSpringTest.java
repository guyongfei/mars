package com.witshare.mars;


import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class BaseSpringTest {

    protected static Logger log = LoggerFactory.getLogger(BaseSpringTest.class);

    private Long startTimeMillis = 0l;

    @Before
    public void runBeforeAll() throws Exception {
        startTimeMillis = System.currentTimeMillis();
    }

    @After
    public void runAfterAll() throws Exception {
        log.info("total use timeMillis=>{}", System.currentTimeMillis() - startTimeMillis);
    }



}
