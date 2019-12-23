package com.antin.kit.message.converter;

import com.antin.kit.common.converter.BaseListConverter;
import com.antin.kit.common.converter.BaseVoConverter;
import com.antin.kit.common.util.ExtendedSpringBeanUtil;
import com.antin.kit.message.persistence.domain.Message;
import com.antin.kit.message.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageVoConverter extends BaseListConverter<MessageVo, Message> {
    private static final String[] VO_PROPS = new String[]{
            "message"
    };

    @Autowired
    BaseVoConverter baseVoConverter;

    @Override
    public Message transferVoToModel(MessageVo vo, Message model) {
        if ( model == null ) {
            model = new Message();
        }
        baseVoConverter.transferVoToModel(vo, model);
        ExtendedSpringBeanUtil.copySpecificProperties(vo, model, VO_PROPS);
        return model;
    }

    @Override
    public MessageVo transferModelToVO(Message model, MessageVo vo) {
        if ( vo == null ) {
            vo = new MessageVo();
        }
        baseVoConverter.transferModelToVO(model , vo) ;
        ExtendedSpringBeanUtil.copySpecificProperties(model, vo, VO_PROPS);
        return vo;
    }
}
