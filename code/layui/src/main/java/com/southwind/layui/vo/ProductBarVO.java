package com.southwind.layui.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class ProductBarVO {
    private String name;
    @JsonProperty("value")
    private Integer count;
}
