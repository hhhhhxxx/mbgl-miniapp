package com.hhhhhx.mbgl.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class ChartData {
    List<String> DataX;
    List<Integer> DataY;
}
