package com.ease.archiecture;

public class Red implements Color {

    private Demo demo;


    @Override
    public String getColor() {
        demo.getMessage();
        return "红色";
    }
}
