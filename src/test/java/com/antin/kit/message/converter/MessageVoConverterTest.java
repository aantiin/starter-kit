package com.antin.kit.message.converter;

import com.antin.kit.BaseIntegrationTest;
import com.antin.kit.message.persistence.domain.Message;
import com.antin.kit.message.vo.MessageVo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;


public class MessageVoConverterTest extends BaseIntegrationTest {
    @Autowired
    MessageVoConverter messageVoConverter;

    private Message model = null;
    private MessageVo vo = null;
    private static final String MESSAGE_1 = "Message 1";
    private static final String MESSAGE_2 = "Message 2";


    @Before
    public void setUp(){
        model = new Message();
        model.setUuid(UUID.randomUUID().toString());
        model.setMessage(MESSAGE_1);
        model.setCreatedAt(new Date());

        vo = new MessageVo();
        vo.setId(UUID.randomUUID().toString());
        vo.setMessage(MESSAGE_2);
        vo.setCreatedAt((new Date().getTime()));
    }

    @Test
    public void transferVoToModelTest() {
        Message modelData = messageVoConverter.transferVoToModel(vo, null);

        Assert.assertNotNull(modelData);
        Assert.assertNotNull(modelData.getUuid());
        Assert.assertNotNull(modelData.getMessage());

        Assert.assertEquals(modelData.getMessage(), MESSAGE_2);
        Assert.assertEquals(true, (modelData.getCreatedAt() instanceof Date));
    }

    @Test
    public void transferModelToVOTest() {
        MessageVo voData = messageVoConverter.transferModelToVO(model, null);

        Assert.assertNotNull(voData);
        Assert.assertNotNull(voData.getId());
        Assert.assertNotNull(voData.getMessage());

        Assert.assertEquals(voData.getMessage(), MESSAGE_1);
        Assert.assertEquals(true, (voData.getCreatedAt() instanceof Long));

    }

    public void transferListOfModelToListOfVO() {
        List<Message> models = new ArrayList<>();
        models.add(model);

        Collection<MessageVo> vos = messageVoConverter.transferListOfModelToListOfVO(models, null);
        Assert.assertNotNull(vos);
        Assert.assertEquals(1, vos.size());

        MessageVo voData = vos.iterator().next();
        Assert.assertNotNull(voData);
        Assert.assertNotNull(voData.getId());
        Assert.assertNotNull(voData.getMessage());

        Assert.assertEquals(voData.getMessage(), MESSAGE_1);
    }
}
