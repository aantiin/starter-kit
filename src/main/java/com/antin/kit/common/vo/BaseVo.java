package com.antin.kit.common.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BaseVo {

    private String id;

    private String createdBy;
    private Long createdAt;

    private String updatedBy;
    private Long updatedAt;

    private Boolean isActive;
}
