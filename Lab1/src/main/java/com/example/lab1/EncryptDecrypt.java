package com.example.lab1;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;

public class EncryptDecrypt {
    public static String ChipterText(String text, int key){

        String cipherText = "";
        String letters = "абвгдеєжзиіїйклмнопрстуфхцчшщьюя";
        String Letter = "АБВГДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯ";


        String l = "";

        for (int i = 0; i < text.length(); i ++){
            if (       text.charAt(i) == ' '
                    || text.charAt(i) == '!'
                    || text.charAt(i) == '?'
                    || text.charAt(i) == '.'
                    || text.charAt(i) == ','
                    || text.charAt(i) == '\''
                    || text.charAt(i) == '\n')
            {
                cipherText += text.charAt(i);
            }

            else {
                if (isUpperCase(text.charAt(i))){
                    l = Letter;
                }
                else if (isLowerCase(text.charAt(i))){
                    l = letters;
                }

                for (int j = 0; j < l.length(); j++) {
                    if (text.charAt(i) == l.charAt(j)){
                        int index = j + key;
                        if (index == 32){
                            index = 0;
                        }
                        else if (index > 32){
                            index = index % 32;
                        }
                        cipherText += l.charAt(index);
                    }
                }
            }
        }
        return cipherText;
    }
    public static String DecryptText(String text, int key){
        String cipherText = "";
        String Letter = "АБВГДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯ";
        String letters = "абвгдеєжзиіїйклмнопрстуфхцчшщьюя";

        String l = "";

        for (int i = 0; i < text.length(); i ++){
            if (       text.charAt(i) == ' '
                    || text.charAt(i) == '!'
                    || text.charAt(i) == '?'
                    || text.charAt(i) == '.'
                    || text.charAt(i) == ','
                    || text.charAt(i) == '\''
                    || text.charAt(i) == '\n')
            {
                cipherText += text.charAt(i);
            }

            else {

                if (isUpperCase(text.charAt(i))){
                    l = Letter;
                }
                else if (isLowerCase(text.charAt(i))){
                    l = letters;
                }

                for (int j = 0; j < l.length(); j++) {
                    if (text.charAt(i) == l.charAt(j)){
                        int index = j - key;
                        if (index < 0){
                            index = 32 + index;
                        }
                        cipherText += l.charAt(index);
                    }
                }
            }
        }
        return cipherText;
    }
}
