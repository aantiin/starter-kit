package com.antin.kit.message.vo;

import com.antin.kit.common.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageVo extends BaseVo {
    private String message;
}
