package com.example.AngleApair;

import android.text.TextUtils;

import androidx.annotation.NonNull;


import com.example.AngleApair.bean.AngleBean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AngleFile implements IAngleFile {

    private boolean isParseding = false;

    private final List<AngleBean> angleBeans;

    public AngleFile() {
        angleBeans = new ArrayList<>();
    }

    @Override
    public void parseFile(@NonNull String path) {
        parse(path);
    }

    private void parse(String path) {
        if (TextUtils.isEmpty(path)) return;
        if (isParseding) return;
        isParseding = true;
        File file = new File(path);
        if (!file.exists()) return;
        if (file.isDirectory()) return;
        angleBeans.clear();
        new Thread(() -> {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                String line;
                while (!TextUtils.isEmpty(line = br.readLine())) {
                    String[] strs = line.split(" ");
                    if (strs.length > 1 && !TextUtils.isEmpty(strs[0]) && !TextUtils.isEmpty(strs[1])) {
                        float key = (float) Double.parseDouble(strs[0]);
                        float value = (float) Double.parseDouble(strs[1]);
                        angleBeans.add(new AngleBean(value, key));
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != br) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    br = null;
                }
                isParseding = false;
            }
        }).start();
    }

    @Override
    public List<AngleBean> getAngleData() {
        return angleBeans;
    }
}
