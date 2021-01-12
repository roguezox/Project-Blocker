package com.company;

import animatefx.animation.*;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

import java.io.*;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class blockcontrol implements Initializable {
    String directory=System.getProperty("user.home");
    ExecutorService coreblock= Executors.newFixedThreadPool(1);
    ExecutorService strblock=Executors.newFixedThreadPool(1);
    ExecutorService modblock=Executors.newFixedThreadPool(1);
    ExecutorService ubblock=Executors.newFixedThreadPool(1);
    @FXML
    Button startb,start,dblock,ublock,sproxy,iaccess,pcontrol,stopb,ddisable,udisable,rotator;
    @FXML
    Button blockk,keycontents,keyclear,urlblock,urlcontents,urlclear;
    @FXML
    TextField keyword;
    @FXML
    TextArea urls;
    @FXML
    CheckBox penable;
    @FXML
    ToggleButton moderate,strict;
    @FXML
     Label plabel,selmode;
    @FXML
    Pane bpane;
    @FXML
    Button settings;
    String condition= Files.readString(Paths.get(directory+"/DynamicWebBlocker/info/pcontrol"));
    String condition2=Files.readString(Paths.get(directory+"/DynamicWebBlocker/info/pmode"));

    public blockcontrol() throws IOException {

    }
    @FXML
    public void initialize(URL url,ResourceBundle resourceBundle){
        new RotateIn(start).setSpeed(0.5).play();
        new RotateIn(dblock).setSpeed(0.5).play();
        new RotateIn(ublock).setSpeed(0.5).play();
        new RotateIn(pcontrol).setSpeed(0.5).play();
        new RotateIn(iaccess).setSpeed(0.5).play();
        new RotateIn(sproxy).setSpeed(0.5).play();
        new RotateIn(rotator).setSpeed(0.5).play();
        new RotateIn(settings).setSpeed(0.5).play();
        selmode.setVisible(false);
        moderate.setVisible(false);
        strict.setVisible(false);
        penable.setVisible(false);
        urls.setVisible(false);
        urlblock.setVisible(false);
        urlclear.setVisible(false);
        urlcontents.setVisible(false);
        blockk.setVisible(false);
        keycontents.setVisible(false);
        keyclear.setVisible(false);
        ddisable.setVisible(false);
        udisable.setVisible(false);
        keyword.setVisible(false);
        startb.setVisible(false);
        stopb.setVisible(false);

        if(condition.equals("true")) {
            plabel.setVisible(true);
            dblock.setVisible(false);
            ublock.setVisible(false);
            ddisable.setVisible(true);
            udisable.setVisible(true);
            penable.setSelected(true);
        }
        if(condition.equals("false")){
            plabel.setVisible(false);
            ublock.setVisible(true);
            dblock.setVisible(true);
            ddisable.setVisible(false);
            udisable.setVisible(false);
            penable.setSelected(false);
        }
        if(condition2.equals("modtrue")){moderate.setSelected(true); strict.setSelected(false);}
        if(condition2.equals("strtrue")){moderate.setSelected(false); strict.setSelected(true);}
        new RotateIn(ddisable).setSpeed(0.3).play();
        new RotateIn(udisable).setSpeed(0.3).play();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                try {
                    String in=Files.readString(Paths.get(directory+"/DynamicWebBlocker/info/monuse"));
                    if(in!=null){
                        System.out.print("reset");
                        Files.writeString(Paths.get(directory+"/DynamicWebBlocker/info/monuse"),"");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        try {
            Base64 base64=new Base64();
            String entimess=Files.readString(Paths.get(directory+"/DynamicWebBlocker/info/times"));
            String Timeserverr = "time.google.com";
            NTPUDPClient timeClientt = new NTPUDPClient();
            InetAddress inetAddresss = InetAddress.getByName(Timeserverr);

            TimeInfo timeInfoo = timeClientt.getTime(inetAddresss);
            long returnTimee = timeInfoo.getReturnTime();
            Date timee = new Date(returnTimee);
            String timese = timee.toString().substring(4, 10);
            System.out.print(timese);
            String timess=new String(base64.decode(entimess));
            if(timess.equals("null")|| !timess.equals(timese)) {
                String Timeserver = "time.google.com";
                NTPUDPClient timeClient = new NTPUDPClient();
                InetAddress inetAddress = InetAddress.getByName(Timeserver);

                TimeInfo timeInfo = timeClient.getTime(inetAddress);
                long returnTime = timeInfo.getReturnTime();
                Date time = new Date(returnTime);
                String times = time.toString().substring(4, 10);
                System.out.print(times);

                String entime=new String(base64.encode(times.getBytes()));
                Files.writeString(Paths.get(directory+"/DynamicWebBlocker/info/times"),entime);
            }
            else {
                String ti=Files.readString(Paths.get(directory+"/DynamicWebBlocker/info/times"));
                String Timeserver = "time.google.com";
                NTPUDPClient timeClient = new NTPUDPClient();
                InetAddress inetAddress = InetAddress.getByName(Timeserver);

                TimeInfo timeInfo = timeClient.getTime(inetAddress);
                long returnTime = timeInfo.getReturnTime();
                Date time = new Date(returnTime);
                String times = time.toString().substring(4, 10);
                System.out.print(times);
                if(!ti.equals(times)){

                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public void rotator(){

        if(rotator.getRotate()==45){
            RotateTransition rt=new RotateTransition(Duration.millis(900),rotator);
            new FadeOutUp(startb).play();
            new FadeOutUp(stopb).play();
            rt.setFromAngle(45);
            rt.setToAngle(0);
            rt.play();
        }
        else {
            startb.setVisible(true);
            stopb.setVisible(true);
            int i = 0;
            new FadeInDown(startb).play();
            new FadeInDown(stopb).play();
            RotateTransition rt = new RotateTransition(Duration.millis(900), rotator);

            rt.setFromAngle(0);
            rt.setToAngle(45);
            rt.play();
        }

    }

    public void startButton(ActionEvent event) {
        moderate.setVisible(false);
        strict.setVisible(false);
        penable.setVisible(false);
        keyword.setVisible(false);
        keyclear.setVisible(false);
        keycontents.setVisible(false);
        urls.setVisible(false);
        urlblock.setVisible(false);
        urlcontents.setVisible(false);
        urlclear.setVisible(false);
        blockk.setVisible(false);
       rotator.setVisible(true);
        new RotateIn(rotator).play();
        settings.setVisible(true);
        System.out.print("hello");
    }
    public void setStartb(ActionEvent event) throws IOException {
        String condition= Files.readString(Paths.get(directory+"/DynamicWebBlocker/info/pcontrol"));
        String condition2=Files.readString(Paths.get(directory+"/DynamicWebBlocker/info/pmode"));
        Runtime rs = Runtime.getRuntime();
        if (condition.equals("true")) {
            if (condition2.equals("modtrue")) {
                ZipFile zipFile = new ZipFile(directory + "/DynamicWebBlocker/pcon/pcon.zip", "yahoo@123".toCharArray());
                try {
                    rs.exec("killall -9 proxy");
                    zipFile.extractFile("proxy", directory + "/DynamicWebBlocker/pcon");
                    zipFile.extractFile("pconmod.py", directory + "/DynamicWebBlocker/pcon");
                    zipFile.extractFile("pconmodrun.py", directory + "/DynamicWebBlocker/pcon");
                    rs.exec("killall -9 proxy");
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
                    rs.exec("rm -f " + directory + "/DynamicWebBlocker/pcon/pconmodrun.py");
                    rs.exec("rm -f " + directory + "/DynamicWebBlocker/pcon/proxy");
                    rs.exec("notify-send "+"Running_on_moderate_parental_mode "+"-i "+directory+"/DynamicWebBlocker/ui_files/icon.png");
                    DataOutputStream dos = new DataOutputStream(new FileOutputStream(directory + "/DynamicWebBlocker/info/mode"));
                    dos.write("mod".getBytes());
                    dos.close();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }

            }
            if (condition2.equals("strtrue")) {
                ZipFile zipFile = new ZipFile(directory + "/DynamicWebBlocker/pcon/pcon.zip", "yahoo@123".toCharArray());

                try {
                    zipFile.extractFile("proxy", directory + "/DynamicWebBlocker/pcon");
                    zipFile.extractFile("pconst.py", directory + "/DynamicWebBlocker/pcon");
                    zipFile.extractFile("pconstrun.py", directory + "/DynamicWebBlocker/pcon");
                    rs.exec("killall -9 proxy");
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
                    rs.exec("rm -f " + directory + "/DynamicWebBlocker/pcon/pconstrun.py");
                    rs.exec("rm -f " + directory + "/DynamicWebBlocker/pcon/proxy");
                    rs.exec("notify-send "+"Running_on_strict_parental_mode "+"-i "+directory+"/DynamicWebBlocker/ui_files/icon.png");
                    DataOutputStream dos = new DataOutputStream(new FileOutputStream(directory + "/DynamicWebBlocker/info/mode"));
                    dos.write("str".getBytes());
                    dos.close();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if(condition.equals("false")){

            try {
                DataOutputStream dos = new DataOutputStream(new FileOutputStream(directory + "/DynamicWebBlocker/info/mode"));
                dos.write("1".getBytes());
                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ZipFile zipFile = new ZipFile(directory + "/DynamicWebBlocker/block_functions/block_functions.zip", "yahoo@123".toCharArray());
            try {
                zipFile.extractAll(directory + "/DynamicWebBlocker/block_functions");
            } catch (ZipException e) {
                e.printStackTrace();
            }

            try {
                rs.exec("killall -9 proxy");
                rs.exec("python3 "+directory+"/DynamicWebBlocker/block_functions/helper.py");
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
                rs.exec("rm -f " + directory + "/DynamicWebBlocker/block_functions/proxy");
                rs.exec("rm -f " + directory + "/DynamicWebBlocker/block_functions/helper.py");
                rs.exec("rm -f " + directory + "/DynamicWebBlocker/block_functions/ubproxy.py");
                rs.exec("rm -f " + directory + "/DynamicWebBlocker/block_functions/proxyrun.py");
                rs.exec("notify-send Started_Dynamic_Blocking -i "+directory+"/DynamicWebBlocker/ui_files/icon.png");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void setStopb(ActionEvent event){
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(directory+"/DynamicWebBlocker/info/mode"));
            dos.write("2".getBytes());
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Runtime rs= Runtime.getRuntime();
        try {
            rs.exec("killall -9 proxy");
            rs.exec("rm -f "+directory+"/DynamicWebBlocker/block_functions/core_script.py");
            ZipFile zipFile=new ZipFile(directory+"/DynamicWebBlocker/block_functions/block_functions.zip","yahoo@123".toCharArray());
            zipFile.extractFile("proxy",directory+"/DynamicWebBlocker/block_functions");
            zipFile.extractFile("ubproxy.py",directory+"/DynamicWebBlocker/block_functions");
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
            rs.exec("rm -f "+directory+"/DynamicWebBlocker/block_functions/proxy");
            rs.exec("rm -f "+directory+"/DynamicWebBlocker/block_functions/ubproxy.py");
            rs.exec("notify-send Stopped_Blocking -i "+directory+"/DynamicWebBlocker/ui_files/icon.png");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void setSettings() throws IOException {
        String dir=System.getProperty("user.home");
        FXMLLoader loader=new FXMLLoader(new File(dir+"/DynamicWebBlocker/ui_files/settings.fxml").toURI().toURL());
        loader.setController(new settings());
        Pane spane=loader.load();
        new FadeIn(spane).play();
        bpane.getChildren().setAll(spane);
    }
    public void setdblock(ActionEvent event){
        rotator.setVisible(false);

        moderate.setVisible(false);
        strict.setVisible(false);
        penable.setVisible(false);
        startb.setVisible(false);
        stopb.setVisible(false);
        urls.setVisible(false);
        urlcontents.setVisible(false);
        urlclear.setVisible(false);
        urlblock.setVisible(false);
        keyword.setVisible(true);
        blockk.setVisible(true);
        keycontents.setVisible(true);
        keyclear.setVisible(true);
        new FadeInDown(keyword).play();
        new FadeInUp(blockk).play();
        new FadeInUp(keycontents).play();
        new FadeInUp(keyclear).play();
    }
    public void setBlockk(){
        try {
            String blockerkeywords = keyword.getText();
            blockerkeywords = blockerkeywords + ",";

            Base64 base64=new Base64();
            String encrypted=new String(base64.encode(blockerkeywords.getBytes()));
            System.out.print("original text "+blockerkeywords+"\n");
            System.out.print("encrypted text "+encrypted+"\n");

            DataOutputStream dos=new DataOutputStream(new FileOutputStream(directory+"/DynamicWebBlocker/info/blockerinfo"));
            dos.write(encrypted.getBytes());
            dos.close();


        }catch (IOException e){e.printStackTrace();}

    }
    public void setKeycontents() throws IOException {
        try {

            FileReader fr= new FileReader(directory+"/DynamicWebBlocker/info/blockerinfo");
        BufferedReader br = new BufferedReader(fr);
        String encoded= br.readLine();
        br.close();
        fr.close();
        Base64 base64=new Base64();
        String decrypted= new String(base64.decode(encoded));
        System.out.print(decrypted);
        keyword.setText(decrypted);

    } catch (IOException e) {
        e.printStackTrace();
      }
    }
    public void setKeyclear(){
        String encoded=" ";
        DataOutputStream dos= null;
        try {
            keyword.setText(" ");
            dos = new DataOutputStream(new FileOutputStream(directory+"/DynamicWebBlocker/info/blockerinfo"));

            dos.write(encoded.getBytes());
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setSproxy(){
        Runtime rs=Runtime.getRuntime();
        try {
            rs.exec("killall -9 proxy");
            rs.exec("rm -f "+directory+"/DynamicWebBlocker/block_functions/core_script.py");
            rs.exec("rm -f "+directory+"/DynamicWebBlocker/block_functions/ubproxy.py");
            rs.exec("rm -f "+directory+"/DynamicWebBlocker/mode/dmode.py");
            rs.exec("rm -f "+directory+"/DynamicWebBlocker/mode/proxyf.py");
            rs.exec("rm -f "+directory+"/DynamicWebBlocker/pcon/pconst.py");
            rs.exec("rm -f "+directory+"/DynamicWebBlocker/pcon/pconmod.py");
            rs.exec("notify-send Proxy_Stopped -i "+directory+"/DynamicWebBlocker/ui_files/icon.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setUblock(){
        rotator.setVisible(false);

        moderate.setVisible(false);
        strict.setVisible(false);
        penable.setVisible(false);
        startb.setVisible(false);
        stopb.setVisible(false);
        keyword.setVisible(false);
        keyclear.setVisible(false);
        keycontents.setVisible(false);
        blockk.setVisible(false);
        urls.setVisible(true);
        urlblock.setVisible(true);
        urlcontents.setVisible(true);
        urlclear.setVisible(true);
        new FadeIn(urls).play();
        new FadeInUp(urlblock).play();
        new FadeInUp(urlcontents).play();
        new FadeInUp(urlclear).play();
    }
    public void setUrlblock(){
        try {
            String blockerkeywords = urls.getText();
            blockerkeywords = blockerkeywords + ",";

            Base64 base64=new Base64();
            String encrypted=new String(base64.encode(blockerkeywords.getBytes()));
            System.out.print("original text "+blockerkeywords+"\n");
            String dec=new String(base64.decode(encrypted));
            System.out.print("decrypted "+dec);
            DataOutputStream dos=new DataOutputStream(new FileOutputStream(directory+"/DynamicWebBlocker/info/urls"));
            dos.write(encrypted.getBytes());
            dos.close();


        }catch (IOException e){e.printStackTrace();}

    }
    public void setUrlcontents(){
        FileReader fr= null;
        try {
            fr = new FileReader(directory+"/DynamicWebBlocker/info/urls");

            BufferedReader br = new BufferedReader(fr);
            String encoded= br.readLine();
            br.close();

            Base64 base64=new Base64();
            String decrypted= new String(base64.decode(encoded));
            System.out.print(decrypted);
            urls.setText(decrypted);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setUrlclear(){
        String encoded=" ";
        DataOutputStream dos= null;
        try {
            urls.setText(" ");
            dos = new DataOutputStream(new FileOutputStream(directory+"/DynamicWebBlocker/info/urls"));

            dos.write(encoded.getBytes());
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setPcontrol(){
        rotator.setVisible(false);

      startb.setVisible(false);
      stopb.setVisible(false);
      urlblock.setVisible(false);
      urlclear.setVisible(false);
      urlcontents.setVisible(false);
      blockk.setVisible(false);
      keycontents.setVisible(false);
      keyclear.setVisible(false);
      keyword.setVisible(false);
      urls.setVisible(false);
      moderate.setVisible(true);
      strict.setVisible(true);
      penable.setVisible(true);
      new FadeInDown(moderate).play();
      new FadeInDown(strict).play();
      new FadeInUp(penable).play();
    }
    public void setPenable(){
        if (moderate.isSelected() || strict.isSelected()){
            boolean active = penable.isSelected();
            if (!active) {
                try {
                    Files.writeString(Paths.get(directory + "/DynamicWebBlocker/info/pcontrol"), "false");
                    Files.writeString(Paths.get(directory + "/DynamicWebBlocker/info/wblock"), "false");
                    new FadeOutRight(plabel).play();
                    ublock.setVisible(true);
                    dblock.setVisible(true);
                    ddisable.setVisible(false);
                    udisable.setVisible(false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    plabel.setVisible(true);
                    new FadeInRight(plabel).play();
                    dblock.setVisible(false);
                    ublock.setVisible(false);
                    ddisable.setVisible(true);
                    udisable.setVisible(true);
                    Files.writeString(Paths.get(directory + "/DynamicWebBlocker/info/pcontrol"), "true");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            penable.setSelected(false);
            selmode.setVisible(true);
            new FadeInLeft(selmode).play();
            Timer timer=new Timer();
            TimerTask task=new TimerTask() {
                @Override
                public void run() {
                    new FadeOutLeft(selmode).play();
                    timer.cancel();
                }
            };
            timer.schedule(task,3*1000);

        }
    }
    public void setModerate(){
        try {

            strict.setSelected(false);
            if(!moderate.isSelected()){
                Files.writeString(Paths.get(directory+"/DynamicWebBlocker/info/pcontrol"),"false");
                penable.setSelected(false);
                plabel.setVisible(false);
                ddisable.setVisible(false);
                udisable.setVisible(false);
                ublock.setVisible(true);
                dblock.setVisible(true);
            }
            else {
                Files.writeString(Paths.get(directory+"/DynamicWebBlocker/info/pmode"),"modtrue");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setStrict(){
        try {

            moderate.setSelected(false);
            if (!strict.isSelected()){
                Files.writeString(Paths.get(directory+"/DynamicWebBlocker/info/pcontrol"),"false");
                penable.setSelected(false); plabel.setVisible(false);
                ddisable.setVisible(false);
                udisable.setVisible(false);
                dblock.setVisible(true);
                ublock.setVisible(true);
            }
            else {
                Files.writeString(Paths.get(directory + "/DynamicWebBlocker/info/pmode"), "strtrue");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void setIaccess(ActionEvent event) throws Exception {
        String dir=System.getProperty("user.home");
        FXMLLoader loader=new FXMLLoader(new File(dir+"/DynamicWebBlocker/ui_files/iaccess.fxml").toURI().toURL());
        loader.setController(new InternetAccessControl());
        Pane ipane = loader.load();
        bpane.getChildren().setAll(ipane);


    }

}




