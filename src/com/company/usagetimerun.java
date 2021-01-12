package com.company;

import net.lingala.zip4j.ZipFile;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class usagetimerun {
    Timer timer;

    public usagetimerun(int seconds){

        timer=new Timer();
        timer.schedule(new task(),seconds*1000);
    }
    class task extends TimerTask{
        @Override
        public void run(){
            System.out.print("completed");
            String[] args = new String[0];
            try {
                Runtime rs=Runtime.getRuntime();
                rs.exec("killall -9 proxy");
                usagetimehelper.main(args);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            timer.cancel();
        }
    }
    static ExecutorService nbblock= Executors.newFixedThreadPool(1);
    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.print("\nbout to start\n");
        String dir=System.getProperty("user.home");

        FileReader reader=new FileReader(dir+"/DynamicWebBlocker/info/timef");
        BufferedReader br=new BufferedReader(reader);
        int timef=Integer.parseInt(br.readLine());
        ZipFile zipFile=new ZipFile(dir+"/DynamicWebBlocker/block_functions/block_functions.zip","yahoo@123".toCharArray());
        zipFile.extractFile("proxy",dir+"/DynamicWebBlocker/block_functions");
        zipFile.extractFile("ubproxy.py",dir+"/DynamicWebBlocker/block_functions");
        Runtime rs=Runtime.getRuntime();
        rs.exec("killall -9 proxy");
        nbblock.execute(new Runnable() {
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
        usagetimerun usagetimerun=new usagetimerun(timef);
        Thread.sleep(5000);
        rs.exec("rm -f "+dir+"/DynamicWebBlocker/block_functions/proxy");
        rs.exec("rm -f "+dir+"/DynamicWebBlocker/block_functions/ubproxy.py");
        rs.exec("notify-send Restricted");


    }



}
