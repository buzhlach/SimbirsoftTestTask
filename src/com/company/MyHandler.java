package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyHandler {

    private String site;
    public MyHandler(String site){
        this.site=site;
    }
    public Map<String,Integer> MyParse() throws IOException {
        URL siteURL = new URL(site);
        Map<String,Integer> result=new HashMap<String, Integer>();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(siteURL.openStream()));

        String inputLine;
        StringBuffer stringBuffer=new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            Pattern pattern = Pattern.compile(">[^<]+(</script)|" +
                    ">[^<]+(</style)|" +
                    ">[^<]+< |" +
                    ">[^<]+<=|" +
                    ">[^<]+<");
            Matcher matcher = pattern.matcher(inputLine);
            while (matcher.find()) {
                String subInputLine=inputLine.substring(matcher.start(), matcher.end());
                if ((!subInputLine.contains("/script"))&&
                        (!subInputLine.contains("< "))&&
                        (!subInputLine.contains("<="))&&
                        (!subInputLine.contains("/style"))){
                    stringBuffer.append(subInputLine+"\n");
                    subInputLine=subInputLine.substring(1,subInputLine.length()-1);
                    for (String word:subInputLine.split("[ ,.!?\";:\\]\\[()\n\r\t+\\-=*/]+")){
                        if (!word.equals("")) {
                            int count = (result.get(word.toUpperCase()) == null ? 0 : result.get(word.toUpperCase()).intValue());
                            result.put(word.toUpperCase(), count + 1);
                        }
                    }
                }
            }
        }
        in.close();
        return result;
    }
}
