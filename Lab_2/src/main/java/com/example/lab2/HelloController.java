package com.example.lab2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class HelloController {
    @FXML
    private Label label;
    @FXML
    private TextField text;
    @FXML
    private TextField key;
    @FXML
    private TextArea textArea;

    @FXML
    protected void Encrypt(){
        String text = this.text.getText();
        String key = this.key.getText();

        char[] letters = "абвгдеєжзиіїйклмнопрстуфхцчшщьюя".toCharArray();//32

        int error = 0;

        if (text.isEmpty()){
            this.text.setText("");
            this.text.setPromptText("Введіть текст");
            error++;
        }
        if (key.isEmpty()){
            this.key.setText("");
            this.key.setPromptText("Введіть ключ");
            error++;
        }

        if (error == 0){
            String cipherText = "";
            text = text.toLowerCase();
            key = key.toLowerCase();

            key += key.repeat(text.length()/key.length());

            for (int i = 0; i < text.length(); i ++){
                char c = text.charAt(i), k = key.charAt(i);
                int x = 0, y = 0;

                for (int j = 0; j < 32; j ++){
                    if (c == letters[j]){
                        x = j + 1;
                    }
                }
                for (int j = 0; j < 32; j ++){
                    if (k == letters[j]){
                        y = j + 1;
                    }
                }
                int sum = x + y;
                sum = sum % 32;
                if (sum % 32 == 0){
                    sum = 32;
                }
                cipherText += letters[sum - 1];
            }
            textArea.setText(cipherText);
        }
    }
    @FXML
    protected void AddInFile(){
        String path = "";
        try {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Виберіть файл");
            File file = chooser.showOpenDialog(null);
            path = file.getAbsolutePath();

            FileWriter writer = new FileWriter(path, false);
            writer.write(textArea.getText());
            writer.flush();
            label.setText("Зашифрований текст записано в файл");
        }catch (Exception e) {
            label.setText("Виберіть файл");
        }
    }
    //Tab 2

    @FXML
    private TextField key2;
    @FXML
    private TextArea textArea2;
    @FXML
    private Label label2;

    @FXML
    protected void ChooseFile(){
        try{
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Виберіть файл");
            File file = chooser.showOpenDialog(null);
            label2.setText(file.getAbsolutePath());
        }catch (Exception e){
            label2.setText("Виберіть файл");
        }
    }
    @FXML
    protected void DecryptFromFile(){
        String text = "",ciphertext = "", key = key2.getText(), path = label2.getText();
        int error = 0;

        if (path.isEmpty()){
            label2.setText("Виберіть файл");
            error++;
        }
        if (key.isEmpty()){
            key2.setText("");
            key2.setPromptText("Введіть ключ");
            error++;
        }
        if (error == 0) {
            Scanner scan;
            try (FileReader reader = new FileReader(path)) {
                scan = new Scanner(reader);
                while (scan.hasNextLine()) {
                    text += scan.nextLine();
                }
                scan.close();

                key = key.toLowerCase();

                key += key.repeat(text.length() / key.length());
                char[] letters = "абвгдеєжзиіїйклмнопрстуфхцчшщьюя".toCharArray();//32

                for (int i = 0; i < text.length(); i++) {
                    int r = 0;
                    int x = 0;

                    char c = text.charAt(i);
                    char c1 = key.charAt(i);

                    if (c == c1) {
                        ciphertext += ' ';
                    }

                    for (int j = 0; j < 32; j++) {
                        if (c == letters[j]) {
                            r = j + 1;
                        }
                    }

                    for (int j = 0; j < 32; j++) {
                        if (c1 == letters[j]) {
                            x = j + 1;
                        }
                    }

                    int sum = r - x;
                    if (sum < 0) {
                        sum += 32;
                    }

                    for (int j = 0; j < 32; j++) {
                        if (sum % 32 == j + 1) {
                            ciphertext += letters[j];
                        }
                    }
                }
                textArea2.setText(ciphertext);
            }catch (Exception e){
                label2.setText("Виберіть файл");
            }
        }
    }
}