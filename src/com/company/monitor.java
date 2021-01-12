package com.company;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import net.lingala.zip4j.ZipFile;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;


public class monitor {
    @FXML
    TextArea terminal;
    @FXML
    Button realtime,dump;

    public void setRealtime(){
        String dir=System.getProperty("user.home");
        Runtime rs=Runtime.getRuntime();
        ZipFile zipFile = new ZipFile(dir + "/DynamicWebBlocker/block_functions/block_functions.zip", "yahoo@123".toCharArray());
        try {
            rs.exec("killall -9 proxy");
            zipFile.extractFile("proxy",dir+"/DynamicWebBlocker/block_functions");
            Process process=rs.exec(dir+"/DynamicWebBlocker/block_functions/proxy -p 1330");
            Thread.sleep(5000);
            rs.exec("rm -f "+dir+"/DynamicWebBlocker/block_functions/proxy");
            InputStreamReader isr=new InputStreamReader(process.getInputStream());
            BufferedReader br =new BufferedReader(isr);
            final int[] i = {0};
            String line;

            Thread monitor=new Thread(new Runnable() {
                @Override
                public void run() {
                    Timer timer=new Timer();
                    TimerTask task=new TimerTask() {
                        @Override
                        public void run() {
                            try {
                                String info = br.readLine();
                                if(info!=null){
                                    terminal.appendText(info+"\n");
                                }
                            }
                            catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                    };
                    timer.schedule(task,0,130);

                }
            });
            monitor.start();



        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void setDump() throws IOException {
        String dir=System.getProperty("user.home");
        FileWriter writter=null;

        writter=new FileWriter(dir+"/output");

        BufferedWriter wrte=new BufferedWriter(writter);
        try {
            wrte.write(" ");
            wrte.close();
            writter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ZipFile zipFile = new ZipFile(dir + "/DynamicWebBlocker/block_functions/block_functions.zip", "yahoo@123".toCharArray());
        Runtime rs=Runtime.getRuntime();
        rs.exec("killall -9 proxy");
        try {
            zipFile.extractFile("proxy",dir+"/DynamicWebBlocker/block_functions");

            Process process=rs.exec(dir+"/DynamicWebBlocker/block_functions/proxy -p 1330");
            Thread.sleep(5000);
            InputStreamReader isr=new InputStreamReader(process.getInputStream());
            BufferedReader br= new BufferedReader(isr);
            final int[] i = {0};

            TimerTask task=new TimerTask() {
                @Override
                public void run() {
                    try {
                       FileWriter writer = new FileWriter(dir+"/output",true);
                        BufferedWriter bw=new BufferedWriter(writer);
                        if(br.readLine()!=null){
                            System.out.print("hello");
                            bw.write(br.readLine()+"\n");
                            bw.close();
                            writer.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            Timer timer=new Timer();
            timer.scheduleAtFixedRate(task,0,130);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
