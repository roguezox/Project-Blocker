package com.company;

import animatefx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

public class settings {
    String dir=System.getProperty("user.home");
    @FXML
    Button change,password,back,newkey,updates,pswd,about;

    @FXML
    PasswordField passold,passnew;
    @FXML
    Label passlab,confirm,old,neww,geninfo,keylab,version,dwb;

    @FXML
    Pane spane;

    @FXML
    public void initialize() throws IOException {

        String dir=System.getProperty("user.home");
        String encryptedhashpass= Files.readString(Paths.get(dir+"/DynamicWebBlocker/info/accesscon"));
        String hashpass=decrypt(encryptedhashpass,"blocker@123");
        geninfo.setVisible(false);
        keylab.setVisible(false);
        String hashh=sha256Hex("roguezox");
        if(hashpass.contains(hashh)){
            System.out.print("\n"+hashpass+"\n"+"trip");
            old.setVisible(false);
            neww.setVisible(false);
            passlab.setVisible(true);
            confirm.setVisible(true);
            change.setText("Set Password");
        }
        else {
            passlab.setVisible(false);
            confirm.setVisible(false);
            old.setVisible(true);
            neww.setVisible(true);
            change.setText("Change Password");
        }





        version.setVisible(false);
        dwb.setVisible(false);

    }
    public void setPswd() throws IOException {
        String pass= Files.readString(Paths.get(dir+"/DynamicWebBlocker/info/accesscon"));
        if(pass.contains("roguezox")){
            passlab.setVisible(true);
            confirm.setVisible(true);
            new FadeInLeft(passlab).play();
            new FadeInLeft(confirm).play();
        }
        else {
            old.setVisible(true);
            neww.setVisible(true);
            new FadeInLeft(old).play();
            new FadeInLeft(neww).play();
        }
        passold.setVisible(true);
        passnew.setVisible(true);
        newkey.setVisible(true);
        change.setVisible(true);
        new FadeInRight(passold).play();
        new FadeInRight(passnew).play();
        new FadeInUp(newkey).play();
        new FadeInUp(change).play();



        version.setVisible(false);
        dwb.setVisible(false);

    }
    public void setUpdates() throws IOException {
        Stage stage=new Stage();
        FXMLLoader loader=new FXMLLoader(new File(dir + "/DynamicWebBlocker/ui_files/updater.fxml").toURI().toURL());
        loader.setController(new updates());
        Pane uppane=loader.load();
        stage.setScene(new Scene(uppane,818,610));
        stage.show();
        new FadeIn(uppane).play();
        Stage curstage=(Stage) spane.getScene().getWindow();
        curstage.close();
    }


    public void setChange() throws IOException {
        String encryptedhash_pass= Files.readString(Paths.get(dir+"/DynamicWebBlocker/info/accesscon"));
        String hashedpass=decrypt(encryptedhash_pass,"blocker@123");
        String enrogue=sha256Hex("roguezox");
        if(hashedpass.contains(enrogue)){
            if(passold.getText().equals(passnew.getText())){
                System.out.print("orig password "+passnew.getText());
                String hash=sha256Hex(passnew.getText());

                String encypt=encrypt(hash,"blockerzox@123");
                try {
                    Files.writeString(Paths.get(dir+"/DynamicWebBlocker/info/accesscon"),encypt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            String decryptedpass=decrypt(encryptedhash_pass,"blockerzox@123");
            String hashed=sha256Hex(passold.getText());
            if(decryptedpass.equals(hashed)){
                System.out.print("old pass "+decryptedpass);
                String hash2=sha256Hex(passnew.getText());
                String encypt=encrypt(hash2,"blockerzox@123");
                Files.writeString(Paths.get(dir+"/DynamicWebBlocker/info/accesscon"),encypt);
            }
        }
    }
    public void setBack() throws IOException {
        String dir=System.getProperty("user.home");
        FXMLLoader loader=new FXMLLoader(new File(dir+"/DynamicWebBlocker/ui_files/blocker.fxml").toURI().toURL());
        loader.setController(new blockcontrol());
        Pane bpane=loader.load();
        new FadeIn(bpane).play();
        spane.getChildren().setAll(bpane);
    }

    public void setNewkey() throws IOException {
        char[] chars = "a@@$##!@@YFE!ydf2y3d23ty124e12@DFD%$^ascv(*()*(*(*(".toCharArray();
        StringBuilder sb = new StringBuilder(20);
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 20; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        System.out.println(output);
        geninfo.setVisible(true);
        keylab.setText(output);
        keylab.setVisible(true);
        StringSelection selection = new StringSelection(output);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
        String hashkey=sha256Hex(output);
        String encryptedkey=encrypt(hashkey,"blocker@123");
        System.out.print("\nencryptedkey "+encryptedkey);
        Files.writeString(Paths.get(dir+"/DynamicWebBlocker/info/reinfo"),encryptedkey);

    }



    public void setAbout(){



        passlab.setVisible(false);
        old.setVisible(false);
        neww.setVisible(false);
        confirm.setVisible(false);
        passold.setVisible(false);
        passnew.setVisible(false);
        geninfo.setVisible(false);
        keylab.setVisible(false);
        newkey.setVisible(false);
        change.setVisible(false);
        version.setVisible(true);
        dwb.setVisible(true);
        new FadeInDown(dwb).play();
        new FadeInUp(version).play();
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
