package com.southwind.layui.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Masir Description:存储查询到的数据
 * IO:
 */
@Data
public class PieVO {
    @JsonProperty("name")
    private String STATUS;
    @JsonProperty("value")
    private Double CON;
}
