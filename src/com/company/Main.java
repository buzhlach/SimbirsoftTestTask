package com.company;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args)  {
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
