package com.company;


import animatefx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class InternetAccessControl implements Initializable {
    String dir=System.getProperty("user.home");
    String condition2=Files.readString(Paths.get(dir+"/DynamicWebBlocker/info/pcontrol"));
    usagetime usagetime = new usagetime();
    Thread thread = new Thread(usagetime);
    ScheduledExecutorService graph=Executors.newSingleThreadScheduledExecutor();
    ExecutorService downloadmod=Executors.newFixedThreadPool(1);
    ExecutorService coreblock= Executors.newFixedThreadPool(1);
    ExecutorService strblock=Executors.newFixedThreadPool(1);
    ExecutorService modblock=Executors.newFixedThreadPool(1);
    ExecutorService ubblock=Executors.newFixedThreadPool(1);
    ExecutorService proxyoff=Executors.newFixedThreadPool(1);
    @FXML
    Button downdis,downloadmode,restrict,usetime,back,on,off,turn,monitor,usagedura;
    @FXML
    CheckBox wblock;
    @FXML
    Label parcontrol,in,from,to;
    @FXML
    Spinner<Integer> hoursf,minutesf,hourst,minutest;
    @FXML
    Pane ipane;


    public InternetAccessControl() throws IOException {
    }
    @FXML
    public void initialize(URL url,ResourceBundle resourceBundle){
        String condition= null;
        try {
            on.setVisible(false);
            off.setVisible(false);
            in.setVisible(false);

            new FadeInDown(usetime).setSpeed(0.5).play();
            new FadeInDown(turn).setSpeed(0.5).play();
            new FadeInDown(monitor).setSpeed(0.5).play();
            new FadeInDown(downloadmode).setSpeed(0.5).play();



            String dir=System.getProperty("user.home");
            String condition2=Files.readString(Paths.get(dir+"/DynamicWebBlocker/info/pcontrol"));
            condition = Files.readString(Paths.get(dir+"/DynamicWebBlocker/info/wblock"));
            System.out.print(condition);
            if(condition.contains("false")){
                wblock.setSelected(false);}
            if(condition.contains("true")){ wblock.setSelected(true);}
            if(condition2.contains("false")){wblock.setVisible(true); downdis.setVisible(false); parcontrol.setVisible(false);}
            if(condition2.contains("true")){wblock.setVisible(false); downdis.setVisible(true); parcontrol.setVisible(true);}
        } catch (IOException e) {
            e.printStackTrace();
        }
        new FadeInLeft(hoursf).setSpeed(0.5).play();
        new FadeInLeft(minutesf).setSpeed(0.5).play();
        new FadeInRight(hourst).setSpeed(0.5).play();
        new FadeInRight(minutest).setSpeed(0.5).play();

    }
    public void setUsetime(){
        back.setVisible(true);




        hoursf.setVisible(true);
        minutesf.setVisible(true);
        hourst.setVisible(true);
        minutest.setVisible(true);
        restrict.setVisible(true);
        from.setVisible(true);
        to.setVisible(true);
        new FadeInRight(hoursf).play();
        new FadeInRight(minutesf).play();
        new FadeInLeft(hourst).play();
        new FadeInLeft(minutest).play();
        new FadeInUp(restrict).play();
        new FadeInUp(wblock).play();
        new FadeInDown(from).play();
        new FadeInDown(to).play();
        on.setVisible(false);
        off.setVisible(false);
        if(condition2.equals("true")){wblock.setVisible(false);}
        if(condition2.equals("false")){wblock.setVisible(true);}
        try {
            String hourf= Files.readString(Paths.get(dir+"/DynamicWebBlocker/block_functions/timer/hoursf"));
            String minutef= Files.readString(Paths.get(dir+"/DynamicWebBlocker/block_functions/timer/minutesf"));
            String hourt= Files.readString(Paths.get(dir+"/DynamicWebBlocker/block_functions/timer/hourst"));
            String minutet= Files.readString(Paths.get(dir+"/DynamicWebBlocker/block_functions/timer/minutest"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void setRestrict(){
        in.setVisible(false);
        DataOutputStream dos = null;
        int hourf = hoursf.getValue();
        int minutef = minutesf.getValue();
        int hourt = hourst.getValue();
        int minutet = minutest.getValue();
        String hourff, minuteff, hourtt, minutett;
        if (hourt > hourf) {
            try {
                dos = new DataOutputStream(new FileOutputStream(dir + "/DynamicWebBlocker/block_functions/timer/hoursf"));
                if (hourf < 10)
                    hourff = "0" + hourf;
                else
                    hourff = String.valueOf(hourf);
                dos.write(hourff.getBytes());
                dos.close();
                dos = new DataOutputStream(new FileOutputStream(dir + "/DynamicWebBlocker/block_functions/timer/minutesf"));
                if (minutef < 10)
                    minuteff = "0" + minutef;
                else
                    minuteff = String.valueOf(minutef);
                dos.write(minuteff.getBytes());
                dos.close();
                dos = new DataOutputStream(new FileOutputStream(dir + "/DynamicWebBlocker/block_functions/timer/hourst"));
                if (hourt < 10)
                    hourtt = "0" + hourt;
                else
                    hourtt = String.valueOf(hourt);
                dos.write(hourtt.getBytes());
                dos.close();
                dos = new DataOutputStream(new FileOutputStream(dir + "/DynamicWebBlocker/block_functions/timer/minutest"));
                if (minutet < 10)
                    minutett = "0" + minutet;
                else
                    minutett = String.valueOf(minutet);
                dos.write(minutett.getBytes());
                dos.close();

            } catch (IOException e) {
                e.printStackTrace();
            }


            thread.start();
        }
                else if(hourt==hourf&&minutet>minutef){
                try {
                    dos = new DataOutputStream(new FileOutputStream(dir + "/DynamicWebBlocker/block_functions/timer/hoursf"));
                    if (hourf < 10)
                        hourff = "0" + hourf;
                    else
                        hourff = String.valueOf(hourf);
                    dos.write(hourff.getBytes());
                    dos.close();
                    dos = new DataOutputStream(new FileOutputStream(dir + "/DynamicWebBlocker/block_functions/timer/minutesf"));
                    if (minutef < 10)
                        minuteff = "0" + minutef;
                    else
                        minuteff = String.valueOf(minutef);
                    dos.write(minuteff.getBytes());
                    dos.close();
                    dos = new DataOutputStream(new FileOutputStream(dir + "/DynamicWebBlocker/block_functions/timer/hourst"));
                    if (hourt < 10)
                        hourtt = "0" + hourt;
                    else
                        hourtt = String.valueOf(hourt);
                    dos.write(hourtt.getBytes());
                    dos.close();
                    dos = new DataOutputStream(new FileOutputStream(dir + "/DynamicWebBlocker/block_functions/timer/minutest"));
                    if (minutet < 10)
                        minutett = "0" + minutet;
                    else
                        minutett = String.valueOf(minutet);
                    dos.write(minutett.getBytes());
                    dos.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }


                thread.start();
            }
            else {
                in.setVisible(true);
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        in.setVisible(false);
                    }
                };
                Timer timer= new Timer();
                timer.schedule(task,3*1000);
            }
        }
        public void setWblock(){
            boolean active= wblock.isSelected();
            if(!active){
                try {
                    Files.writeString(Paths.get(dir+"/DynamicWebBlocker/info/wblock"),"false");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    Files.writeString(Paths.get(dir+"/DynamicWebBlocker/info/wblock"),"true");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        public void goback() throws IOException {
            String dir=System.getProperty("user.home");
            FXMLLoader loader=new FXMLLoader(new File(dir+"/DynamicWebBlocker/ui_files/blocker.fxml").toURI().toURL());
            loader.setController(new blockcontrol());
            Pane bpane = loader.load();
            ipane.getChildren().setAll(bpane);
        }
        public void setDownloadmode(){
            String dir=System.getProperty("user.home");
            try {
                DataOutputStream dos=new DataOutputStream(new FileOutputStream(dir+"/DynamicWebBlocker/info/mode"));
                dos.write("3".getBytes());
                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Runtime rs = Runtime.getRuntime();
            ZipFile zipFile=new ZipFile(dir+"/DynamicWebBlocker/mode/mode.zip","yahoo@123".toCharArray());
            try {
                zipFile.extractFile("proxy",dir+"/DynamicWebBlocker/mode");
                zipFile.extractFile("dmoderun.py",dir+"/DynamicWebBlocker/mode");
                zipFile.extractFile("dmode.py",dir+"/DynamicWebBlocker/mode");

            } catch (ZipException e) {
                e.printStackTrace();
            }
            try {
                rs.exec("killall -9 proxy");
                downloadmod.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String directory=System.getProperty("user.home");
                            CommandLine com=new CommandLine("python3");
                            com.addArgument(" "+directory+"/DynamicWebBlocker/mode/dmoderun.py");
                            PumpStreamHandler stream=new PumpStreamHandler();
                            DefaultExecutor exec=new DefaultExecutor();
                            exec.setStreamHandler(stream);
                            exec.execute(com);
                        }
                        catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                });
                Thread.sleep(5000);
                rs.exec("rm -f "+dir+"/DynamicWebBlocker/mode/proxy");
                rs.exec("rm -f "+dir+"/DynamicWebBlocker/mode/dmoderun.py");
                rs.exec("notify-send Running_Download_Mode -i "+dir+"/DynamicWebBlocker/ui_files/icon.png");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }


         }
         public void setTurn(){
        back.setVisible(true);




            from.setVisible(false);
            to.setVisible(false);
            hoursf.setVisible(false);
            minutesf.setVisible(false);
            hourst.setVisible(false);
            minutest.setVisible(false);
            restrict.setVisible(false);
            wblock.setVisible(false);
            on.setVisible(true);

            off.setVisible(true);
            new FadeInRight(on).play();
            new FadeInLeft(off).play();
         }
         public void setOn() throws IOException {
             String condition3=Files.readString(Paths.get(dir+"/DynamicWebBlocker/info/pmode"));
             Runtime rs = Runtime.getRuntime();
             FileReader fr= null;
             try {
                 rs.exec("killall -9 proxy");
                 fr = new FileReader(dir+"/DynamicWebBlocker/info/mode");

                 BufferedReader br = new BufferedReader(fr);
                 String mode=br.readLine();
                 if(mode.equals("1")){
                     ZipFile zipFile=new ZipFile(dir+"/DynamicWebBlocker/block_functions/block_functions.zip","yahoo@123".toCharArray());
                     try {
                         zipFile.extractAll(dir+"/DynamicWebBlocker/block_functions");
                     } catch (ZipException e) {
                         e.printStackTrace();
                     }

                     try {
                         coreblock.execute(new Runnable() {
                             @Override
                             public void run() {
                                 try {
                                     String directory=System.getProperty("user.home");
                                     CommandLine com=new CommandLine("python3");
                                     com.addArgument("  "+directory+"/DynamicWebBlocker/block_functions/proxyrun.py");
                                     PumpStreamHandler stream=new PumpStreamHandler();
                                     DefaultExecutor exec=new DefaultExecutor();
                                     exec.setStreamHandler(stream);
                                     exec.execute(com);
                                 }
                                 catch (IOException e){
                                     e.printStackTrace();
                                 }
                             }
                         });
                         Thread.sleep(5000);
                         rs.exec("rm -f "+dir+"/DynamicWebBlocker/block_functions/proxy");
                         rs.exec("rm -f "+dir+"/DynamicWebBlocker/block_functions/proxyrun.py");
                         rs.exec("notify-send Started_Dynamic_Blocking -i "+dir+"/DynamicWebBlocker/ui_files/icon.png");
                     } catch (IOException | InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
                 if(mode.equals("2")){
                     try {
                         rs.exec("killall -9 proxy");
                         rs.exec("rm -f "+dir+"/DynamicWebBlocker/block_functions/core_script.py");
                         ZipFile zipFile=new ZipFile(dir+"/DynamicWebBlocker/block_functions/block_functions.zip","yahoo@123".toCharArray());
                         zipFile.extractFile("proxy",dir+"/DynamicWebBlocker/block_functions");
                         zipFile.extractFile("ubproxy.py",dir+"/DynamicWebBlocker/block_functions");
                         ubblock.execute(new Runnable() {
                             @Override
                             public void run() {
                                 try {
                                     String directory=System.getProperty("user.home");
                                     CommandLine com=new CommandLine("python3");
                                     com.addArgument(" "+directory+"/DynamicWebBlocker/block_functions/ubproxy.py");
                                     PumpStreamHandler stream=new PumpStreamHandler();
                                     DefaultExecutor exec=new DefaultExecutor();
                                     exec.setStreamHandler(stream);
                                     exec.execute(com);
                                 }
                                 catch (IOException e){
                                     e.printStackTrace();
                                 }
                             }
                         });
                         Thread.sleep(5000);
                         rs.exec("notify-send Stopped_Blocking -i "+dir+"/DynamicWebBlocker/ui_files/icon.png");
                         rs.exec("rm -f "+dir+"/DynamicWebBlocker/block_functions/proxy");
                         rs.exec("rm -f "+dir+"/DynamicWebBlocker/block_functions/ubproxy.py");
                     } catch (IOException | InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
                 if (mode.equals("3")){
                     ZipFile zipFile=new ZipFile(dir+"/DynamicWebBlocker/mode/mode.zip","yahoo@123".toCharArray());
                     try {
                         zipFile.extractFile("proxy",dir+"/DynamicWebBlocker/mode");
                         zipFile.extractFile("dmoderun.py",dir+"/DynamicWebBlocker/mode");
                         zipFile.extractFile("dmode.py",dir+"/DynamicWebBlocker/mode");

                     } catch (ZipException e) {
                         e.printStackTrace();
                     }
                     try {
                         rs.exec("killall -9 proxy");
                         downloadmod.execute(new Runnable() {
                             @Override
                             public void run() {
                                 try {
                                     String directory=System.getProperty("user.home");
                                     CommandLine com=new CommandLine("python3");
                                     com.addArgument(" "+directory+"/DynamicWebBlocker/mode/dmoderun.py");
                                     PumpStreamHandler stream=new PumpStreamHandler();
                                     DefaultExecutor exec=new DefaultExecutor();
                                     exec.setStreamHandler(stream);
                                     exec.execute(com);
                                 }
                                 catch (IOException e){
                                     e.printStackTrace();
                                 }
                             }
                         });
                         Thread.sleep(5000);
                         rs.exec("rm -f "+dir+"/DynamicWebBlocker/mode/proxy");
                         rs.exec("rm -f "+dir+"/DynamicWebBlocker/mode/dmoderun.py");
                         rs.exec("notify-send Running_Download_Mode -i "+dir+"/DynamicWebBlocker/ui_files/icon.png");
                     } catch (IOException | InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
                 if (condition2.equals("true")) {
                     if (condition3.equals("modtrue")) {
                         ZipFile zipFile = new ZipFile(dir + "/DynamicWebBlocker/pcon/pcon.zip", "yahoo@123".toCharArray());
                         try {
                             zipFile.extractFile("proxy", dir + "/DynamicWebBlocker/pcon");
                             zipFile.extractFile("pconmod.py", dir + "/DynamicWebBlocker/pcon");
                             zipFile.extractFile("pconmodrun.py", dir + "/DynamicWebBlocker/pcon");
                             modblock.execute(new Runnable() {
                                 @Override
                                 public void run() {
                                     try {
                                         String directory=System.getProperty("user.home");
                                         CommandLine com=new CommandLine("python3");
                                         com.addArgument(" "+directory + "/DynamicWebBlocker/pcon/pconmodrun.py");
                                         PumpStreamHandler stream=new PumpStreamHandler();
                                         DefaultExecutor exec=new DefaultExecutor();
                                         exec.setStreamHandler(stream);
                                         exec.execute(com);
                                     }
                                     catch (IOException e){
                                         e.printStackTrace();
                                     }
                                 }
                             });
                             Thread.sleep(5000);
                             rs.exec("notify-send "+"Running_on_moderate_parental_mode "+"-i "+dir+"/DynamicWebBlocker/ui_files/icon.png");
                             rs.exec("rm -f " + dir + "/DynamicWebBlocker/pcon/pconmodrun.py");
                             rs.exec("rm -f " + dir + "/DynamicWebBlocker/pcon/proxy");
                         } catch (IOException | InterruptedException e) {
                             e.printStackTrace();
                         }

                     }
                     if (condition3.equals("strtrue")) {
                         ZipFile zipFile = new ZipFile(dir + "/DynamicWebBlocker/pcon/pcon.zip", "yahoo@123".toCharArray());

                         try {
                             zipFile.extractFile("proxy", dir + "/DynamicWebBlocker/pcon");
                             zipFile.extractFile("pconst.py", dir + "/DynamicWebBlocker/pcon");
                             zipFile.extractFile("pconstrun.py", dir + "/DynamicWebBlocker/pcon");
                             strblock.execute(new Runnable() {
                                 @Override
                                 public void run() {
                                     try {
                                         String directory=System.getProperty("user.home");
                                         CommandLine com=new CommandLine("python3");
                                         com.addArgument(" "+directory + "/DynamicWebBlocker/pcon/pconstrun.py");
                                         PumpStreamHandler stream=new PumpStreamHandler();
                                         DefaultExecutor exec=new DefaultExecutor();
                                         exec.setStreamHandler(stream);
                                         exec.execute(com);
                                     }
                                     catch (IOException e){
                                         e.printStackTrace();
                                     }
                                 }
                             });
                             Thread.sleep(5000);
                             rs.exec("notify-send "+"Running_on_strict_parental_mode "+"-i "+dir+"/DynamicWebBlocker/ui_files/icon.png");
                             rs.exec("rm -f " + dir + "/DynamicWebBlocker/pcon/pconstrun.py");
                             rs.exec("rm -f " + dir + "/DynamicWebBlocker/pcon/proxy");
                         } catch (IOException | InterruptedException e) {
                             e.printStackTrace();
                         }
                     }
                 }
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
         public void setOff(){
             Runtime rs=Runtime.getRuntime();
             ZipFile zipFile=new ZipFile(dir+"/DynamicWebBlocker/block_functions/block_functions.zip","yahoo@123".toCharArray());
             ZipFile mode=new ZipFile(dir+"/DynamicWebBlocker/mode/mode.zip","yahoo@123".toCharArray());

             try {
                 rs.exec("killall -9 proxy");
                 zipFile.extractFile("proxy",dir+"/DynamicWebBlocker/block_functions");
                 mode.extractFile("proxyf.py",dir+"/DynamicWebBlocker/mode");
                 mode.extractFile("proxyfrun.py",dir+"/DynamicWebBlocker/mode");
                 proxyoff.execute(new Runnable() {
                     @Override
                     public void run() {
                         try {
                             String directory=System.getProperty("user.home");
                             CommandLine com=new CommandLine("python3");
                             com.addArgument(" "+directory+"/DynamicWebBlocker/block_functions/ubproxy.py");
                             PumpStreamHandler stream=new PumpStreamHandler();
                             DefaultExecutor exec=new DefaultExecutor();
                             exec.setStreamHandler(stream);
                             exec.execute(com);
                         }
                         catch (IOException e) {
                             e.printStackTrace();
                         }
                     }
                 });

                 Thread.sleep(5000);
                 rs.exec("notify-send Turned_Off -i "+dir+"/DynamicWebBlocker/ui_files/icon.png");
                 rs.exec("rm -f "+dir+"/DynamicWebBlocker/block_functions/proxy");
                 rs.exec("rm -f "+dir+"/DynamicWebBlocker/mode/proxyfrun.py");
             } catch (IOException | InterruptedException e) {
                 e.printStackTrace();
             }
         }
         public void setMonitor() throws IOException {
             Stage monitors =new Stage();
             FXMLLoader loader=new FXMLLoader(new File(dir+"/DynamicWebBlocker/ui_files/monitor.fxml").toURI().toURL());
             loader.setController(new monitor());
             Pane mpane=loader.load(); 
             monitors.setScene(new Scene(mpane,1294,868));
             monitors.show();
             new FadeIn(mpane).play();
         }
         public void setUsagedura() throws IOException {
             String drop=Files.readString(Paths.get(dir+"/DynamicWebBlocker/info/mon"));

             if(drop.equals("")) {
                 System.out.print(drop);

                 from.setVisible(false);
                 to.setVisible(false);
                 hoursf.setVisible(false);
                 minutesf.setVisible(false);
                 hourst.setVisible(false);
                 minutest.setVisible(false);
                 restrict.setVisible(false);
                 wblock.setVisible(false);
                 on.setVisible(false);
                 off.setVisible(false);



             }
             else {

                 Base64 base64=new Base64();

                 back.setVisible(false);
                 String enuse=Files.readString(Paths.get(dir+"/DynamicWebBlocker/info/mon"));
                 String timings=Files.readString(Paths.get(dir+"/DynamicWebBlocker/info/times"));

                 String use=new String(base64.decode(enuse));
                 int element=StringUtils.countMatches(use,",");
                 String[] monitored=new String[element];
                 StringTokenizer token=new StringTokenizer(use);
                 int j=0;
                 while (token.hasMoreTokens()){
                     monitored[j]=token.nextToken();
                     j++;
                 }
                 j=0;

                 String encodedinfo=Files.readString(Paths.get(dir+"/DynamicWebBlocker/info/usedur"));
                 String info = new String(base64.decode(encodedinfo));
                 System.out.print(info+"\n");
                 int res= StringUtils.countMatches(info,",");
                 String[] arr=new String[res];
                 int i=0;
                 StringTokenizer st=new StringTokenizer(info,",");
                 while(st.hasMoreTokens()){
                     String val=st.nextToken();
                     arr[i]=val;
                     i++;
                 }
                 i=0;
                 int percent;
                 int hour=0;
                 String[] result=new String[element];
                 int[] percents=new int[element];
                 for(int k=0;k<monitored.length;k++){
                     for(int h=0;h<arr.length;h++){
                         if(monitored[k].contains(arr[i])){
                           hour++;
                         }
                     }
                     percent=(hour/arr.length)*100;
                     percents[k]=percent;
                     result[k]=monitored[k]+": "+percent;

                 }
                 TimerTask task=new TimerTask() {
                     @Override
                     public void run() {
                         try {
                             for (int value : percents) {
                                 Files.writeString(Paths.get(dir + "/DynamicWebBlocker/info/times"), value + ",");
                             }
                             Files.writeString(Paths.get(dir+"/DynamicWebBlocker/info/usedur"),"");
                         }
                         catch (IOException e){
                             e.printStackTrace();
                         }
                     }
                 };

                 graph.scheduleAtFixedRate(task,0,600000, TimeUnit.MILLISECONDS);



             }

         }

         public void setClearmon() throws IOException {
             Files.writeString(Paths.get(dir + "/DynamicWebBlocker/info/mon"), "");
         }



}
    

