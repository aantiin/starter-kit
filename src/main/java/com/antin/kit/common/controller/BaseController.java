package com.antin.kit.common.controller;

import com.antin.kit.common.vo.BaseVo;
import com.antin.kit.common.vo.ResultPageVO;
import com.antin.kit.common.vo.ResultVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * BaseController class
 *
 * @param <V> Base VO
 *
 */
public abstract class BaseController<V extends BaseVo> extends AbstractBaseController<V> {

    @Override
    public ResponseEntity<ResultVO> add(@RequestBody V vo) {
        AbstractRequestHandler handler = new AbstractRequestHandler() {
            @Override
            public Object processRequest() {
                V voResult = getDomainService().add(vo);
                return voResult == null ? null : voResult;
            }
        };
        return handler.getResult();
    }

    @Override
    public ResponseEntity<ResultVO> edit(@RequestBody V vo) {
        AbstractRequestHandler handler = new AbstractRequestHandler() {
            @Override
            public Object processRequest() {
                V voResult = getDomainService().edit(vo);
                return voResult == null ? null : voResult;
            }
        };
        return handler.getResult();
    }


    @Override
    public ResponseEntity<ResultVO> delete(
            @PathVariable("id") String id,
            @RequestHeader String Authorization) {
        AbstractRequestHandler handler = new AbstractRequestHandler() {
            @Override
            public Object processRequest() {
                Boolean voResult = getDomainService().delete(id, Authorization);
                return voResult == null ? null : voResult;
            }
        };
        return handler.getResult();
    }

    @Override
    public ResponseEntity<ResultVO> detail(
            @PathVariable("id") String id) {
        AbstractRequestHandler handler = new AbstractRequestHandler() {
            @Override
            public Object processRequest() {
                V voResult = getDomainService().findById(id);
                return voResult == null ? null : voResult;
            }
        };
        return handler.getResult();
    }

    @Override
    public ResponseEntity<ResultPageVO> list(
            @RequestParam(value = "page", required = true, defaultValue = "0") Integer page,
            @RequestParam(value = "limit", required = true, defaultValue = "10") Integer limit,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "direction", required = false) String direction,
            @RequestParam(value = "searchBy", required = false) String searchBy,
            @RequestParam(value = "searchVal", required = false) String searchVal) {
        return super.list(page, limit, sortBy, direction, searchBy, searchVal);
    }
}
