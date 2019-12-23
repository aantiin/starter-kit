package com.antin.kit.common.controller;

import com.antin.kit.common.vo.BaseVo;
import com.antin.kit.common.vo.ResultVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public interface RestController<V extends BaseVo, R extends ResultVO> extends RestPageController {
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public abstract ResponseEntity<R> add(@RequestBody V vo);

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public abstract ResponseEntity<R> edit(@RequestBody V vo);

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public abstract ResponseEntity<R> delete(@PathVariable("id") String id, @RequestHeader String Authorization);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public abstract ResponseEntity<R> detail(@PathVariable("id") String id);
}
