package com.antin.kit.message.controller;

import com.antin.kit.common.controller.AbstractRequestHandler;
import com.antin.kit.common.controller.BaseController;
import com.antin.kit.common.service.BaseVoService;
import com.antin.kit.common.vo.ResultPageVO;
import com.antin.kit.common.vo.ResultVO;
import com.antin.kit.message.service.MessageService;
import com.antin.kit.message.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/messages")
public class MessageController extends BaseController<MessageVo> {

    @Autowired
    MessageService messageService;

    @Override
    protected BaseVoService<MessageVo> getDomainService() {
        return messageService;
    }

    @Override
    public ResponseEntity<ResultPageVO> getHistory(String id) {
        return null;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<ResultPageVO> list(
            @RequestParam(value = "page", required = true, defaultValue = "0") Integer page,
            @RequestParam(value = "limit", required = true, defaultValue = "10000") Integer limit,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "direction", required = false) String direction
    ) {
        Map<String, Object> pageMap = messageService.getList(page, limit, sortBy, direction);
        return constructListResult(pageMap);
    }
}
