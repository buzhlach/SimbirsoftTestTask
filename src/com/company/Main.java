package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner console=new Scanner(System.in);
        String site=console.nextLine();
        MyHandler myHandler=new MyHandler(site);
        try {
            Map<String, Integer> words = myHandler.MyParse();

            List<Map.Entry<String,Integer>> list = new ArrayList<>(words.entrySet());
            list.sort(Map.Entry.comparingByValue());
            Collections.reverse(list);
            for(Map.Entry<String,Integer> elem:list){

                System.out.println(elem.getKey()+ ": "+elem.getValue());
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
