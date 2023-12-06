package com.example.angleapair;



import com.example.angleapair.bean.AngleBean;

import java.util.List;

public class AngleTransition implements IAngleTransition {

    private static volatile IAngleTransition angleTransition;

    public static IAngleTransition getInstance() {
        if (null == angleTransition) {
            synchronized (AngleTransition.class) {
                if (null == angleTransition) {
                    angleTransition = new AngleTransition();
                }
            }
        }
        return angleTransition;
    }

    private AngleTransition() {
    }

    private IAngleValue angleValue;

    @Override
    public void parseFile(String path) {
        if (null != this.angleValue) {
            this.angleValue.parseFile(path);
        }
    }

    @Override
    public List<AngleBean> getAngleData() {
        if (null != this.angleValue)
            return this.angleValue.getAngleData();
        else
            return null;
    }

    @Override
    public void setAngleValue(IAngleValue angleValue) {
        this.angleValue = angleValue;
    }

    @Override
    public void putValue(float key, float value) {
        if (null != this.angleValue) {
            this.angleValue.putValue(key, value);
        }
    }

    @Override
    public float getValue(float key) {
        if (null != this.angleValue)
            return this.angleValue.getValue(key);
        else
            return -1;
    }
}
