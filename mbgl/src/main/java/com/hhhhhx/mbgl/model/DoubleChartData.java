package com.hhhhhx.mbgl.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class DoubleChartData {
    List<String> DataX;
    List<Integer> DataY1;
    List<Integer> DataY2;
}
