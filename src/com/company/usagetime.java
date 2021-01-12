package com.company;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

import java.io.*;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class usagetime extends Thread{
    String dir=System.getProperty("user.home");
    public void run(){

        try {
            timefrom();
            timeto();
            String now=time();

            time_errorfix(now);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static String time() throws IOException {
        String Timeserver= "time.google.com";
        NTPUDPClient timeClient = new NTPUDPClient();
        InetAddress inetAddress = InetAddress.getByName(Timeserver);

        TimeInfo timeInfo = timeClient.getTime(inetAddress);
        long returnTime = timeInfo.getReturnTime();
        Date time = new Date(returnTime);
        int i=0;
        String times=time.toString().replace(time.toString().substring(0,11),"");
        String ttimes=times.replace(times.substring(8),"");
        ttimes=ttimes.replace(ttimes.substring(8),"");
        return ttimes;
    }
    public static void restrictinit() throws IOException, InterruptedException {
        String dir=System.getProperty("user.home");

        Runtime rs=Runtime.getRuntime();
        String[] args = new String[0];
        String condition= Files.readString(Paths.get(dir+"/DynamicWebBlocker/info/wblock"));

        if(condition.equals("false")) {usagetimerun.main(args);}
        if(condition.equals("true")) {usagetimebrun.main(args);}

    }
    public static void timefrom() throws IOException {
        String dir=System.getProperty("user.home");
        FileReader fr= null;
        int hourf = 0,minutef = 0,hourt = 0,minutet = 0;
        try {
            fr = new FileReader(dir+"/DynamicWebBlocker/block_functions/timer/hoursf");
            BufferedReader br = new BufferedReader(fr);
            hourf=Integer.parseInt(br.readLine());
            fr = new FileReader(dir+"/DynamicWebBlocker/block_functions/timer/minutesf");
            BufferedReader brr = new BufferedReader(fr);
            minutef=Integer.parseInt(brr.readLine());
            fr = new FileReader(dir+"/DynamicWebBlocker/block_functions/timer/hourst");
            BufferedReader brrr = new BufferedReader(fr);
            hourt=Integer.parseInt(brrr.readLine());
            fr = new FileReader(dir+"/DynamicWebBlocker/block_functions/timer/minutest");
            BufferedReader brrrr=new BufferedReader(fr);
            minutet=Integer.parseInt(brrrr.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }


        LocalDateTime localNow = LocalDateTime.now();
        ZoneId currentZone = ZoneId.systemDefault();
        ZonedDateTime timef=ZonedDateTime.of(localNow,currentZone);
        timef=timef.withHour(hourf).withMinute(minutef);
        ZonedDateTime timet=timef.withHour(hourt).withMinute(minutet);
        if(timef.compareTo(timet) > 0)
            timet = timet.plusDays(1);
        Duration duration= Duration.between(timef,timet);
        int seconds=(int) duration.getSeconds();
        FileWriter writer=new FileWriter(dir+"/DynamicWebBlocker/info/timef");
        BufferedWriter bw=new BufferedWriter(writer);
        bw.write(String.valueOf(seconds));
        bw.close();
        writer.close();
    }
    public static void timeto() throws IOException {
        String dir=System.getProperty("user.home");
        FileReader fr= null;
        int hourf = 0,minutef = 0,hourt = 0,minutet = 0;
        try {
            fr = new FileReader(dir+"/DynamicWebBlocker/block_functions/timer/hoursf");
            BufferedReader br = new BufferedReader(fr);
            hourf=Integer.parseInt(br.readLine());
            fr = new FileReader(dir+"/DynamicWebBlocker/block_functions/timer/minutesf");
            BufferedReader brr = new BufferedReader(fr);
            minutef=Integer.parseInt(brr.readLine());
            fr = new FileReader(dir+"/DynamicWebBlocker/block_functions/timer/hourst");
            BufferedReader brrr = new BufferedReader(fr);
            hourt=Integer.parseInt(brrr.readLine());
            fr = new FileReader(dir+"/DynamicWebBlocker/block_functions/timer/minutest");
            BufferedReader brrrr=new BufferedReader(fr);
            minutet=Integer.parseInt(brrrr.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }


        LocalDateTime localNow = LocalDateTime.now();
        ZoneId currentZone = ZoneId.systemDefault();
        ZonedDateTime timef=ZonedDateTime.of(localNow,currentZone);
        timef=timef.withHour(hourf).withMinute(minutef);
        ZonedDateTime timet=timef.withHour(hourt).withMinute(minutet);
        if(timef.compareTo(timet) < 0)
            timet = timet.plusDays(1);
        Duration duration= Duration.between(timet,timef);
        int seconds=(int) Math.abs(duration.getSeconds());
        FileWriter writer=new FileWriter(dir+"/DynamicWebBlocker/info/timet");
        BufferedWriter bw=new BufferedWriter(writer);
        bw.write(String.valueOf(seconds));
        bw.close();
        writer.close();
    }
    private static void time_errorfix(String now) throws IOException {
        String dir=System.getProperty("user.home");
        FileReader fr= null;
        int hourf = 0,minutef = 0,hourt = 0,minutet = 0,secondf=0;
        try {
            fr = new FileReader(dir+"/DynamicWebBlocker/block_functions/timer/hoursf");
            BufferedReader br = new BufferedReader(fr);
            hourt=Integer.parseInt(br.readLine());
            fr = new FileReader(dir+"/DynamicWebBlocker/block_functions/timer/minutesf");
            BufferedReader brr = new BufferedReader(fr);
            minutet=Integer.parseInt(brr.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
        hourf=Integer.parseInt(now.substring(0,2));
        minutef=Integer.parseInt(now.substring(3,5));
        secondf=Integer.parseInt(now.substring(6));

        LocalDateTime localNow = LocalDateTime.now();
        ZoneId currentZone = ZoneId.systemDefault();
        ZonedDateTime timef=ZonedDateTime.of(localNow,currentZone);
        timef=timef.withHour(hourf).withMinute(minutef).withSecond(secondf);
        ZonedDateTime timet=timef.withHour(hourt).withMinute(minutet).withSecond(00);
        if(timef.compareTo(timet) > 0)
            timet = timet.plusDays(1);
        Duration duration= Duration.between(timef,timet);
        System.out.print(duration.getSeconds());
        int seconds= (int) duration.getSeconds();
        if(seconds>0){
            Runtime rs=Runtime.getRuntime();
            rs.exec("killall -9 proxy");
        }
        Timer timer=new Timer();
        timer.schedule(new task(),seconds*1000);

    }
  static class task extends TimerTask{
    @Override
    public void run(){
        try {
            restrictinit();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
  }

}
