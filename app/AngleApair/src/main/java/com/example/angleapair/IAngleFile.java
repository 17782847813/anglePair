package com.example.angleapair;

import androidx.annotation.NonNull;


import com.example.angleapair.bean.AngleBean;

import java.util.List;

public interface IAngleFile{
    void parseFile(@NonNull String path);

    List<AngleBean> getAngleData();
}
