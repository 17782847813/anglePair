package com.example.AngleApair;

import androidx.annotation.NonNull;


import com.example.AngleApair.bean.AngleBean;

import java.util.List;

public interface IAngleFile{
    void parseFile(@NonNull String path);

    List<AngleBean> getAngleData();
}
