package com.southwind.layui.vo;

import lombok.Data;

import java.util.List;

@Data
public class BusTVO {
    private List<String> names;
    private List<Double> values;
    private List<String> rem;
}
