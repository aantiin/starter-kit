package com.antin.kit.common.controller;


import com.antin.kit.common.service.BaseVoService;
import com.antin.kit.common.util.RestUtil;
import com.antin.kit.common.vo.BaseVo;
import com.antin.kit.common.vo.ResultPageVO;
import com.antin.kit.common.vo.ResultVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public abstract class AbstractBaseController<V extends BaseVo> implements RestController<V, ResultVO> {

    protected abstract BaseVoService<V> getDomainService();

    @Override
    public ResponseEntity<ResultVO> delete(
            @PathVariable("id") String id,
            @RequestHeader String Authorization) {
        return RestUtil.getJsonResponse(null, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    public ResponseEntity<ResultVO> add(@RequestBody V vo) {
        return RestUtil.getJsonResponse(null, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    public ResponseEntity<ResultVO> edit(@RequestBody V vo) {
        return RestUtil.getJsonResponse(null, HttpStatus.METHOD_NOT_ALLOWED);
    }


    @Override
    public ResponseEntity<ResultVO> detail(@PathVariable("id") String id) {
        return RestUtil.getJsonResponse(null, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    public ResponseEntity<ResultPageVO> list(@RequestParam(value = "page", required = true, defaultValue = "0") Integer page,
                                             @RequestParam(value = "limit", required = true, defaultValue = "10") Integer limit,
                                             @RequestParam(value = "sortBy", required = false) String sortBy,
                                             @RequestParam(value = "direction", required = false) String direction,
                                             @RequestParam(value = "searchBy", required = false) String searchBy,
                                             @RequestParam(value = "searchVal", required = false) String searchVal) {
        return RestUtil.getJsonResponse(null, HttpStatus.METHOD_NOT_ALLOWED);
    }

    protected ResponseEntity<ResultPageVO> constructListResult(Map<String, Object> pageMap) {
        return AbstractRequestHandler.constructListResult(pageMap);
    }

    protected ResponseEntity<ResultPageVO> constructMapResult(Map<String, Object> pageMap) {
        return AbstractRequestHandler.constructMapResult(pageMap);
    }

}
