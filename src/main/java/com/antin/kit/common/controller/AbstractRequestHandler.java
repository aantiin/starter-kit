package com.antin.kit.common.controller;


import com.antin.kit.common.exception.ServiceException;
import com.antin.kit.common.util.Constants;
import com.antin.kit.common.util.RestUtil;
import com.antin.kit.common.util.StatusCode;
import com.antin.kit.common.vo.ResultPageVO;
import com.antin.kit.common.vo.ResultVO;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public abstract class AbstractRequestHandler {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AbstractRequestHandler.class);

    public ResponseEntity<ResultVO> getResult() {
        ResultVO result = new ResultVO();
        try {
            Object obj = processRequest();
            if (obj != null) {
                result.setSuccess(true);
                result.setData(obj);

            } else {
                result.setSuccess(true);
                result.setData(null);
            }
        } catch (ServiceException e) {
            result.setSuccess(false);
            result.setError(e.getMessage());

        } catch (DataIntegrityViolationException e){
            LOGGER.error(e.toString());
            result.setSuccess(false);
            result.setError("FAILED to proceed! "+ e.getCause().getCause().getMessage());

        } catch (TransactionSystemException e) {
            LOGGER.error(e.toString());
            result.setSuccess(false);
            result.setError("FAILED to proceed! "+ e.getMessage());

        } catch (Exception e) {
            LOGGER.error(e.toString());
            result.setSuccess(false);
            result.setError("FAILED to proceed!");
        }
        return RestUtil.getJsonResponse(result);
    }

    public abstract Object processRequest() throws InterruptedException, ExecutionException, IOException, ParseException;

    public static ResponseEntity<ResultPageVO> constructListResult(Map<String, Object> pageMap) {
        ResultPageVO result = new ResultPageVO();
        try {
            Collection list = constructPageResult(pageMap, result);
            result.setData(list);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setError(e.getMessage());
        }
        return RestUtil.getJsonResponse(result);
    }

    public static ResponseEntity<ResultPageVO> constructMapResult(Map<String, Object> pageMap) {
        ResultPageVO result = new ResultPageVO();
        try {
            Map map = constructPageMapResult(pageMap, result);
            result.setData(map);
        } catch (Exception e) {
            result.setSuccess(true);
            result.setError(e.getMessage());
        }
        return RestUtil.getJsonResponse(result);
    }

    public static Collection constructPageResult(Map<String, Object> map, ResultPageVO result) {
        if (map == null) {
            result.setPages("0");
            result.setElements("0");
            result.setError(StatusCode.DATA_NOT_FOUND.name());
            result.setSuccess(true);

            return null;
        } else {
            Collection vos = (Collection) map.get(Constants.PageParameter.LIST_DATA);
            result.setPages(String.valueOf(map.get(Constants.PageParameter.TOTAL_PAGES)));
            result.setElements(String.valueOf(map.get(Constants.PageParameter.TOTAL_ELEMENTS)));
            result.setSuccess(true);
            return vos;
        }
    }

    public static Map constructPageMapResult(Map<String, Object> map, ResultPageVO result) {
        if (map == null) {
            result.setPages("0");
            result.setElements("0");
            result.setError(StatusCode.DATA_NOT_FOUND.name());
            result.setSuccess(true);
            return null;
        } else {
            Map vos = (Map) map.get(Constants.PageParameter.LIST_DATA);
            result.setPages(String.valueOf(map.get(Constants.PageParameter.TOTAL_PAGES)));
            result.setElements(String.valueOf(map.get(Constants.PageParameter.TOTAL_ELEMENTS)));
            result.setSuccess(true);
            return vos;
        }
    }
}
