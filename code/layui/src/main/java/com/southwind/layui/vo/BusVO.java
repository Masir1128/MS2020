package com.southwind.layui.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BusVO {
    @JsonProperty("name")
    private String projectName;
    @JsonProperty("value")
    private Double pingtime;
    @JsonProperty("rem")
    private String rem;

}
