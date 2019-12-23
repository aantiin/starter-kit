package com.antin.kit.common.controller;

import com.antin.kit.common.vo.ResultPageVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public interface RestPageController {

    /**
     *
     * @param page
     *            : page of
     * @param limit
     *            : limit query
     * @param sortBy
     *            : sort by
     * @param direction
     *            : direction {asc:desc}
     * @param searchBy
     * @param searchVal
     * @return Collection of VO, Total-Count & Total Pages on response header
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResultPageVO> list(@RequestParam(value = "page", required = true, defaultValue = "0") Integer page,
                                             @RequestParam(value = "limit", required = true, defaultValue = "10") Integer limit,
                                             @RequestParam(value = "sortBy", required = false) String sortBy,
                                             @RequestParam(value = "direction", required = false) String direction,
                                             @RequestParam(value = "searchBy", required = false) String searchBy,
                                             @RequestParam(value = "searchVal", required = false) String searchVal);

    @RequestMapping(value = "/history/{id}", method = RequestMethod.GET)
    @ResponseBody
    public abstract ResponseEntity<ResultPageVO> getHistory(@PathVariable("id") String id);
}
