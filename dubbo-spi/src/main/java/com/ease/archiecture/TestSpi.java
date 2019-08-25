package com.ease.archiecture;

import org.apache.dubbo.common.extension.ExtensionLoader;

public class TestSpi {



    public static void main(String[] args) {
        ExtensionLoader<Color> extensionLoader = ExtensionLoader.getExtensionLoader(Color.class);
        System.out.println(extensionLoader.getExtension("red").getColor());
    }
}
