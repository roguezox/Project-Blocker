package com.company;

import animatefx.animation.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class fxwin extends Application {
    String dir=System.getProperty("user.home");
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader=new FXMLLoader(new File(dir+"/DynamicWebBlocker/ui_files/startup.fxml").toURI().toURL());
        loader.setController(new Controller());
        Parent root = loader.load();

        primaryStage.setScene(new Scene(root,1041,753));
        primaryStage.show();
        new FadeIn(root).play();


    }


    public static void main(String[] args) {
        launch(args);
    }

}
