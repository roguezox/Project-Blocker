package com.company;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.text.ParseException;
import java.util.Base64;
import java.util.Timer;
import java.util.TimerTask;

import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

public class Controller {
    public static int once;
    @FXML
    Button startup,forpass,verify,back,startelse;
    @FXML
    TextField password;
    @FXML
    ProgressIndicator progress;
    @FXML
    AnchorPane stpane;
    String passwordd;
    @FXML
    Label wrong,change,passnot,inkey;
    @FXML
    public void initialize() throws IOException {
        String dir=System.getProperty("user.home");
        Runtime rs=Runtime.getRuntime();
        System.out.print("hello");


        String encrypted= Files.readString(Paths.get(dir+"/DynamicWebBlocker/info/accesscon"));
        String decrypted=decrypt(encrypted,"blocker@123");
        String hashed=sha256Hex("roguezox");
        System.out.println(decrypted);
        if(decrypted.contains(hashed)){
            password.setVisible(false);
            startup.setVisible(false);
            passnot.setVisible(true);
            progress.setVisible(false);
            forpass.setVisible(false);
        }
        else {startelse.setVisible(false); passnot.setVisible(false);}
        inkey.setVisible(false);
        change.setVisible(false);
        wrong.setVisible(false);
        verify.setVisible(false);
        back.setVisible(false);


    }

     public void handleButtonAction(ActionEvent event) throws IOException, ParseException {
         String dir=System.getProperty("user.home");
         passwordd=password.getText();
         System.out.print(passwordd);
         String encrypted= Files.readString(Paths.get(dir+"/DynamicWebBlocker/info/accesscon"));
         System.out.println("testing: "+encrypted);

         if(passwordd!=null&& !encrypted.equals("")){


             String pass=decrypt(encrypted,"blockerzox@123");
             String hashedpassword=sha256Hex(passwordd);
             if(hashedpassword.contains(pass)){
                 progress.setProgress(100);
                 Stage bstage=new Stage();
                 FXMLLoader loader=new FXMLLoader(new File(dir+"/DynamicWebBlocker/ui_files/blocker.fxml").toURI().toURL());
                 loader.setController(new blockcontrol());
                 Pane bpane=loader.load();
                 bstage.setScene(new Scene(bpane,1294,868));
                 bstage.show();
                 Stage stage=(Stage) startup.getScene().getWindow();
                 stage.close();

             }
             else {
                 wrong.setVisible(true);
                 TimerTask task=new TimerTask() {
                     @Override
                     public void run() {
                         wrong.setVisible(false);
                     }
                 };
                 Timer timer=new Timer();
                 timer.schedule(task,3*1000);
             }
         }
         System.out.print(passwordd);

     }
     public void setForpass(){
        password.setText("Enter Your Recovery Key");
        change.setVisible(true);
         forpass.setVisible(false);
         verify.setVisible(true);

         back.setVisible(true);

     }
     public void setVerify() throws IOException {
         String dir=System.getProperty("user.home");
        if(verify.getText().contains("Enter Code")){
            String activation_code=password.getText();
            String enactivationcode=Files.readString(Paths.get(dir+"/DynamicWebBlocker/info/cehelper"));
            String code=decrypt(enactivationcode,"blockerzox@123");
            System.out.print("read code: "+code+"\n");
            System.out.print("entered code: "+activation_code+"\n");
            if(activation_code.equals(code)) {
                Files.writeString(Paths.get(dir + "/DynamicWebBlocker/info/match"), "true");
                System.out.print("marked");
                startelse.setVisible(true);
                passnot.setVisible(true);
                password.setVisible(false);
                verify.setText("Verify");
                verify.setVisible(false);
                passnot.setText("Blocker Is Not Password Protected!");
            }
        }
        else {
            String encryptedkey = Files.readString(Paths.get(dir + "/DynamicWebBlocker/info/reinfo"));
            String hashkey = decrypt(encryptedkey, "blocker@123");
            String hashpass=sha256Hex(password.getText());
            if (hashpass.equals(hashkey)) {
                String hashrog=sha256Hex("roguezox");
                String encryptedhashrog=encrypt(hashrog,"blocker@123");
                Files.writeString(Paths.get(dir + "/DynamicWebBlocker/info/accesscon"), encryptedhashrog);
                progress.setProgress(100);
                Stage bstage = new Stage();
                FXMLLoader loader = new FXMLLoader(new File(dir + "/DynamicWebBlocker/ui_files/blocker.fxml").toURI().toURL());
                loader.setController(new blockcontrol());
                Pane bpane = loader.load();
                bstage.setScene(new Scene(bpane, 1294, 868));
                bstage.show();
                Stage stage = (Stage) startup.getScene().getWindow();
                stage.close();

            } else {
                change.setVisible(false);
                inkey.setVisible(true);
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        inkey.setVisible(false);
                        change.setVisible(true);
                    }
                };
                Timer timer = new Timer();
                timer.schedule(task, 3 * 1000);
            }
        }
     }
     public void setBack(){
        password.setText("");
        verify.setVisible(false);
        change.setVisible(false);
        forpass.setVisible(true);
        back.setVisible(false);

     }

     public void setStartelse() throws IOException {
         String dir=System.getProperty("user.home");
         Stage bstage=new Stage();
         FXMLLoader loader=new FXMLLoader(new File(dir+"/DynamicWebBlocker/ui_files/blocker.fxml").toURI().toURL());
         loader.setController(new blockcontrol());
         Pane bpane=loader.load();
         bstage.setScene(new Scene(bpane,1294,868));
         bstage.show();
         Stage stage=(Stage) startup.getScene().getWindow();
         stage.close();
     }
    public static String encrypt(String strToEncrypt, String secret)
    {

        String salt = "ssshhhhhhhhhhh!!!!";
        try
        {
            String secretkey = "roguezox@123";
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secretkey.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
    public static String decrypt(String strToDecrypt, String secret) {
        String secretkey = "roguezox@123";
        String salt = "ssshhhhhhhhhhh!!!!";
        try
        {
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secretkey.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }


}
