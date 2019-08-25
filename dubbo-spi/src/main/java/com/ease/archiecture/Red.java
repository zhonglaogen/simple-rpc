package com.ease.archiecture;


import org.apache.dubbo.common.Extension;

@Extension
public class Red implements Color {


    @Override
    public String getColor() {
        return "红色";
    }
}
