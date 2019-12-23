package com.antin.kit.common.controller;

import com.antin.kit.common.vo.ResultVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.util.Date;

@RestController
@RequestMapping("/ping")
public class PingController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<ResultVO> ping() {

        AbstractRequestHandler abstractRequestHandler = new AbstractRequestHandler() {
            @Override
            public Object processRequest() {
                Date date = new Date();
                DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
                return dateFormat.format(date);
            }
        };
        return abstractRequestHandler.getResult();
    }
}
