package com.example.honda.android_edittext_demo;

public class Mask {

    public String maskText(String text) {

        int unMaskTextCount = 3;
        String maskCharacter = "*";

        StringBuilder stringBuilder = new StringBuilder();

        if (text == null){
            return text;
        }

        if (text.length() < unMaskTextCount) {
            return text;
        }

        int range = text.length() - unMaskTextCount;
        String substring = text.substring(range);

        for (int i=0; i < range; i++) {
            stringBuilder.append(maskCharacter);
        }

        String maskedText = stringBuilder.toString();

        return maskedText + substring;
    }
}
