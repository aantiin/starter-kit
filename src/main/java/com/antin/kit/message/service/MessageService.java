package com.antin.kit.message.service;

import com.antin.kit.common.converter.IBaseVoConverter;
import com.antin.kit.common.persistence.repository.BaseRepository;
import com.antin.kit.common.service.BaseVoServiceImpl;
import com.antin.kit.message.converter.MessageVoConverter;
import com.antin.kit.message.persistence.domain.Message;
import com.antin.kit.message.persistence.repository.MessageRepository;
import com.antin.kit.message.vo.MessageVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class MessageService extends BaseVoServiceImpl<Message, MessageVo> {
    static Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    MessageVoConverter messageVoConverter;

    @Override
    protected BaseRepository<Message> getJpaRepository() {
        return messageRepository;
    }

    @Override
    protected IBaseVoConverter<MessageVo, Message> getVoConverter() {
        return messageVoConverter;
    }

    public Map<String, Object> getList(Integer page, Integer limit, String sortBy, String direction) {

        sortBy = sortBy == null ? "createdAt" : sortBy;
        direction = direction == null ? "DESC" : direction;

        Pageable pageable = PageRequest.of(page, limit, getSortBy(sortBy, direction));

        Page<Message> resultPage = messageRepository.findAll(pageable);
        List<Message> models = resultPage.getContent();

        Collection<MessageVo> vos = messageVoConverter.transferListOfModelToListOfVO(models, null);

        return constructMapReturn(vos, resultPage.getTotalElements(), resultPage.getTotalPages());
    }
}
