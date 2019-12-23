package com.antin.kit.message.service;

import com.antin.kit.BaseIntegrationTest;
import com.antin.kit.message.vo.MessageVo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class MessageServiceTest extends BaseIntegrationTest {
    @Autowired
    MessageService messageService;

    MessageVo vo;
    @Before
    public void setupMock() {
        vo = new MessageVo();
        vo.setMessage("Message 1");
        vo.setCreatedBy("SYSTEM - TEST");
    }

    @Test
    public void testAll() {
        // create
        MessageVo result = messageService.add(vo);
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getId());
        Assert.assertNotNull(result.getMessage());
        Assert.assertNotNull(result.getCreatedAt());

        Assert.assertEquals("Message 1", result.getMessage());
        Assert.assertEquals(true, result.getIsActive());

        // get Detail
        MessageVo detail = messageService.findById(result.getId());
        Assert.assertNotNull(detail);
        Assert.assertNotNull(detail.getId());
        Assert.assertNotNull(detail.getMessage());
        Assert.assertNotNull(detail.getCreatedAt());

        Assert.assertEquals("Message 1", detail.getMessage());
        Assert.assertEquals(true, detail.getIsActive());

        // all
        Map<String, Object> list = messageService.getList(0, 10, null, null);
        Assert.assertNotNull(list);
        Assert.assertNotNull(list.get("listData"));
        Assert.assertNotNull(list.get("totalPages"));
        Assert.assertNotNull(list.get("totalElements"));

        Assert.assertEquals(1, ((List)list.get("listData")).size());
        Assert.assertEquals(1, ((Integer)list.get("totalPages")).intValue());
        Assert.assertEquals(1, ((Long)list.get("totalElements")).intValue());

    }
}
