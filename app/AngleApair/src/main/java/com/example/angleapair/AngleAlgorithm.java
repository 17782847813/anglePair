package com.example.angleapair;


import com.example.angleapair.bean.AngleBean;

import java.util.List;

public class AngleAlgorithm implements IAngleAlgorithm {

    @Override
    public float getValue(float key, List<AngleBean> values) {
        return getAndleValue(key, values);
    }

    private float getAndleValue(float fYaw, List<AngleBean> values) {
        if (null == values || values.size() == 0) return fYaw;

        int length = values.size();

        float[] mgts = new float[length];
        float[] phones = new float[length];

        for (int i = 0; i < length; i++) {
            AngleBean angleBean = values.get(i);
            mgts[i] = angleBean.ciValue;
            phones[i] = angleBean.phoneValue;
        }

        float firstPhone = phones[0];
        float firstMgt = mgts[0];

        float endPhone = phones[length - 1];
        float endMgt = mgts[length - 1];

        for (int i = 0; i < length - 1; i++) {

            float phone1 = phones[i];
            float phone2 = phones[i + 1];
            float mgt1 = mgts[i];
            float mgt2 = mgts[i + 1];

            if (phones[i] == fYaw) return mgts[i];

            if (phone1 <= fYaw && fYaw <= phone2)
                return mgt1 + ((mgt2 - mgt1) / (phone2 - phone1)) * (fYaw - phone1);
            else {
                if (fYaw >= endPhone && fYaw <= firstPhone || (fYaw >= endPhone &&
                        fYaw <= firstPhone + 360 && endPhone >= firstPhone) || (fYaw + 360 >= endPhone
                        && fYaw < firstPhone && endPhone >= firstPhone)) {
                    if (fYaw >= endPhone && fYaw <= firstPhone)
                        return firstMgt / (firstPhone - endPhone) * (fYaw - endPhone);
                    else if (fYaw >= endPhone && fYaw <= firstPhone + 360)
                        return firstMgt / (firstPhone + 360 - endPhone) * (fYaw - endPhone);
                    else return firstMgt / (firstPhone + 360 - endPhone) * (fYaw + 360 - endPhone);
                } else {
                    if (phone1 > phone2 && ((fYaw <= phone1 && fYaw <= phone2) || (fYaw >= phone1 && fYaw <= 360))) {
                        if (fYaw < phone1) {
                            fYaw = fYaw + 360;
                        }
                        return mgt1 + (mgt2 - mgt1) / (phone2 + 360 - phone1) * (fYaw - phone1);
                    }
                }
            }
        }
        return fYaw;
    }
}
