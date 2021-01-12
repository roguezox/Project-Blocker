package com.company;

import animatefx.animation.FadeIn;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


public class updates {
    @FXML
    Label checkup,upavailable,updone,newver;
    @FXML
    Button checkbutton,upnow,back;

    @FXML
    Pane uppane;

    @FXML
    public void initialize(){
        checkup.setVisible(false);
        upavailable.setVisible(false);
        upnow.setVisible(false);
        updone.setVisible(false);


    }

    public void setCheckbutton() throws IOException, InterruptedException {
        checkup.setVisible(true);
        String dir =System.getProperty("user.home");
         Runtime rs =Runtime.getRuntime();
         String av=null;
         Process process=rs.exec("python3 "+dir+"/DynamicWebBlocker/up/upcheck/uprun.py");
        InputStreamReader isr = new InputStreamReader(process.getInputStream());
        BufferedReader br = new BufferedReader(isr);
        while (br.readLine()!=null) {
            String info =br.readLine();
            System.out.print("\n"+info+"\n");
            if (info.equals("available")) {
                checkbutton.setVisible(false);
                upnow.setVisible(true);
                checkup.setVisible(false);
                upavailable.setVisible(true);
                av = br.readLine();
                break;

            }
            else if(info.equals("new version available")){
                upnow.setVisible(false);
                checkup.setVisible(false);
                checkbutton.setVisible(false);
                newver.setVisible(true);
                break;
            }
            else {
                checkup.setText("No Available Updates");
                break;
            }
        }


    }
    public void setUpnow() throws IOException {
        String dir =System.getProperty("user.home");
        upavailable.setText("Downloading Updates");
        back.setVisible(false);
        Runtime rs = Runtime.getRuntime();
          Process process=rs.exec("python3 "+dir+"/DynamicWebBlocker/up/down/downrun.py");
          InputStreamReader isr =new InputStreamReader(process.getInputStream());
          BufferedReader br = new BufferedReader(isr);
          while(br.readLine()!=null){
              System.out.print(br.readLine());
          }
          updone.setVisible(true);

          
    }
    public void setBack() throws IOException {
        String dir =System.getProperty("user.home");
        Stage stage=new Stage();
        FXMLLoader loader=new FXMLLoader(new File(dir + "/DynamicWebBlocker/ui_files/settings.fxml").toURI().toURL());
        loader.setController(new settings());
        Pane spane=loader.load();
        stage.setScene(new Scene(spane,1298,868));
        stage.show();
        new FadeIn(spane).play();
        Stage curstage=(Stage) uppane.getScene().getWindow();
        curstage.close();
    }

}
