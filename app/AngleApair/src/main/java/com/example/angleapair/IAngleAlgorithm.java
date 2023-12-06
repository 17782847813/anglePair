package com.example.angleapair;


import com.example.angleapair.bean.AngleBean;

import java.util.List;

public interface IAngleAlgorithm {
    float getValue(float key, List<AngleBean> values);
}
