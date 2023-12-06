package com.example.AngleApair;



import com.example.AngleApair.bean.AngleBean;

import java.util.List;

public class AngleValue implements IAngleValue {

    private final IAngleFile angleFile;

    private final IAngleAlgorithm angleAlgorithm;

    public AngleValue(IAngleFile angleFile, IAngleAlgorithm angleAlgorithm) {
        this.angleFile = angleFile;
        this.angleAlgorithm = angleAlgorithm;
    }

    @Override
    public void putValue(float key, float value) {
        if (null != this.angleFile) {
            this.angleFile.getAngleData().add(new AngleBean(key, value));
        }
    }

    @Override
    public float getValue(float key) {
        if (null != this.angleFile)
            return this.angleAlgorithm.getValue(key, this.angleFile.getAngleData());
        else
            return -1;
    }

    @Override
    public void parseFile(String path) {
        this.angleFile.parseFile(path);
    }

    @Override
    public List<AngleBean> getAngleData() {
        return this.angleFile.getAngleData();
    }
}
