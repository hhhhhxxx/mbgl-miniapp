package com.hhhhhx.mbgl.param.doctor;

import com.hhhhhx.mbgl.param.BasePage;
import lombok.Data;

@Data
public class DoctorPageVM extends BasePage {
    private String key;
    private Integer patientUserId;
}
