package com.antin.kit.common.controller;

import com.antin.kit.BaseIntegrationTest;
import com.antin.kit.common.util.RestUtil;
import com.antin.kit.common.vo.ResultVO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class PingControllerTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void pingTest() throws Exception {
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/ping").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        ResultVO vo = RestUtil.jsonToObject(contentAsString, ResultVO.class);
        Assert.assertNotNull(vo);
        Assert.assertEquals(vo.getSuccess(), true);
        Assert.assertEquals(vo.getError(), null);
        Assert.assertNotNull(vo.getData());
    }
}
