package com.ease.archiecture;


import org.apache.dubbo.common.extension.SPI;

@SPI
public interface Color {

    String getColor();
}
