package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class usagetimehelper {
    Timer timer;
    public usagetimehelper(int seconds){

        timer=new Timer();
        timer.schedule(new task(),seconds*1000);
    }
    class task extends TimerTask{
        @Override
        public void run(){
            System.out.print("starting proxy");
            String[] args = new String[0];
            try {
                usagetimerun.main(args);

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            timer.cancel();
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.print("\ntime keeping\n");
        String dir=System.getProperty("user.home");

        FileReader reader=new FileReader(dir+"/DynamicWebBlocker/info/timet");
        BufferedReader br=new BufferedReader(reader);
        int timef=Integer.parseInt(br.readLine());
        usagetimehelper usagetimehelper=new usagetimehelper(timef);



    }



}
