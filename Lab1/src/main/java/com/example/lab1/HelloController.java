package com.example.lab1;

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


    //Tab 1
    @FXML
    private Label label;
    @FXML
    private TextField text;
    @FXML
    private TextField key;
    @FXML
    private TextArea textArea;

    @FXML
    protected void Encrypt() {

        char[] letters = "абвгдеєжзиіїйклмнопрстуфхцчшщьюя".toCharArray();//32
        String text = this.text.getText();
        int error = 0;
        int key = 0;

        if (text.isEmpty()) {
            this.text.setText("");
            this.text.setPromptText("Введіть текст");
            error++;
        }

        try {key = Integer.parseInt(this.key.getText());
        }catch (NumberFormatException e){
            error ++;
            this.key.setText("");
            this.key.setPromptText("Введіть число");
        }

        if (error == 0){
            textArea.setText(EncryptDecrypt.ChipterText(text, key));
        }
    }
    @FXML
    protected void AddInFile(){
        String EncryptText = textArea.getText();
        String path = "";
        try {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Виберіть файл");
            File file = chooser.showOpenDialog(null);
            path = file.getAbsolutePath();

            FileWriter writer = new FileWriter(path, false);
            writer.write(EncryptText);
            writer.flush();
            label.setText("Зашифрований текст записано в файл");
        }catch (Exception e) {
            label.setText("Виберіть файл");
        }
    }

//Tab 2
    @FXML
    private Label label2;
    @FXML
    private TextField key2;
    @FXML
    private TextArea textArea2;
    @FXML
    protected void Chosser(){
        String path = "";
        try {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Виберіть файл");
            File file = chooser.showOpenDialog(null);
            path = file.getAbsolutePath();
            label2.setText(path);
        } catch (Exception e) {
            label2.setText("Виберіть файл");
        }
    }
    @FXML
    protected void Decrypt(){
        String text = "";
        int error = 0;
        int key = 0;
        textArea2.setText("");

        try (FileReader reader = new FileReader(label2.getText())){
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()){
                text += scanner.nextLine() + "\n";
            }
            scanner.close();
        }catch (Exception e){
            label2.setText("Виберіть файл");
            error ++;
        }

        try{key = Integer.parseInt(key2.getText());
        }catch (NumberFormatException e){
            error ++;
            key2.setText("");
            key2.setPromptText("Введіть число");
        }

        if (error == 0){
            String cipherText = EncryptDecrypt.DecryptText(text, key);
            textArea2.setText(cipherText);
        }
    }

    @FXML
    private Label label3;
    @FXML
    private TextField key3;
    @FXML
    private TextArea textArea3;

    @FXML
    protected void ChoseFile2(){
        String path = "";
        try {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Виберіть файл");
            File file = chooser.showOpenDialog(null);
            path = file.getAbsolutePath();
            label3.setText(path);
        } catch (Exception e) {
            label3.setText("Виберіть файл");
        }
    }
    @FXML
    protected void Encrypt2(){
        int error = 0;
        int key = 0;

        try{key = Integer.parseInt(key3.getText());
        }catch (NumberFormatException e){
            error ++;
            key3.setText("");
            key3.setPromptText("Введіть число");
        }

        if (label3.getText().isEmpty() || label3.getText().equals("Шлях до файлу")){
            label3.setText("Виберіть файл");
            error ++;
        }
        if (error == 0){
            textArea3.setVisible(true);
            String text = "";

            try (FileReader reader = new FileReader(label3.getText())) {
                Scanner scanner = new Scanner(reader);
                while (scanner.hasNextLine()) {
                    text += scanner.nextLine() + "\n";
                }
                scanner.close();
            } catch (Exception e) {
                label3.setText("Виберіть файл");
            }

            String path = EncryptDecrypt.ChipterText(text, key);
            textArea3.setText(path);


            try (FileWriter writer = new FileWriter(label3.getText(), false)){
                writer.write(path);
                writer.flush();

                label3.setText("Зашифрований текст записано назад до файл");
            }catch (Exception e) {
                label3.setText("Виберіть файл");
            }
        }
    }
}