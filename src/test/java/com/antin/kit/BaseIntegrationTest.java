package com.antin.kit;

import com.antin.kit.common.service.ApplicationService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class BaseIntegrationTest {

    @Autowired
    ApplicationService applicationService;

    @Before
    public void contextLoads() {
        String vers = applicationService.getVersion();
        assertNotNull(vers);
        assertEquals("0.0.1-DEVELOPMENT-test", vers);
    }
}
