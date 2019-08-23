package com.ease.archiecture;

import java.util.ServiceLoader;

public class TestSPI {

    public static void main(String[] args) {
        ServiceLoader<Color> serviceLoader = ServiceLoader.load(Color.class);
        System.out.println("Java SPI");
        for (Color color : serviceLoader) {
            System.out.println(color.getColor());
        }
    }
}
