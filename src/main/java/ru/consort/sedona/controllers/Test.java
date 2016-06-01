package ru.consort.sedona.controllers;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.util.*;

/**
 * Created by DaH4uk on 15.05.2016.
 */
public class Test {
    public static void main(String[] args) throws ParseException {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        client.addFilter(new HTTPBasicAuthFilter("admin", ""));
        WebResource service = client.resource(UriBuilder.fromUri("http://192.168.1.40:5555/").build());
        // getting JSON data
        String json = service.path("json").accept(MediaType.APPLICATION_JSON).get(String.class);
        JSONParser level1Parser = new JSONParser();
        Object obj = level1Parser.parse(json);
        JSONArray obj1 = (JSONArray) ((JSONObject) ((JSONObject) obj).get("obj")).get("ref");

//        List<Object> level1Hrefs = new ArrayList<>();
//        List<Object> level2Hrefs = new ArrayList<>();
//        List<Object> level3Hrefs = new ArrayList<>();
//
//        //parsing 1 level of tree
//        for (Object level1Object : obj1) {
//            Object level1Str = ((JSONObject) level1Object).get("href");
//            level1Hrefs.add(level1Str);
////            System.out.println(level1Str);
//
//            //parameters for 2 level parsing
//            String json1 = service.path("json/" + level1Str).accept(MediaType.APPLICATION_JSON).get(String.class);
//            JSONParser level2Parser = new JSONParser();
//            Object obj2 = level2Parser.parse(json1);
//
//            //if для избежания null pointer exception
//            if (((JSONObject) obj2).containsKey("obj") && ((JSONObject) ((JSONObject) obj2).get("obj")).containsKey("ref")) {
//                JSONArray obj3 = (JSONArray) ((JSONObject) ((JSONObject) obj2).get("obj")).get("ref");
//                //parsing 2 level of tree
//                for (Object ob : obj3) {
//                    Object level2Str = ((JSONObject) ob).get("href");
//                    level2Hrefs.add(level1Str + "" + level2Str);
////                    System.out.println(level1Str + "" + level2Str);
//
//                    String json2 = service.path("json/" + level1Str + level2Str).accept(MediaType.APPLICATION_JSON).get(String.class);
//                    JSONParser level3Parser = new JSONParser();
//                    Object obj4 = level3Parser.parse(json2);
//
//                    if (((JSONObject) obj4).containsKey("obj") && ((JSONObject) ((JSONObject) obj4).get("obj")).containsKey("ref")) {
//                        JSONArray obj5 = (JSONArray) ((JSONObject) ((JSONObject) obj2).get("obj")).get("ref");
//
//                        for (Object obect : obj5) {
//                            Object level3Str = ((JSONObject) obect).get("href");
//                            level3Hrefs.add(level1Str + "" + level2Str + level3Str);
////                            System.out.println(level1Str + "" + level2Str + level3Str);
//                        }
//
//                    }
//                }
//
//            }
//
//
//
//        }

        Set set = new TreeSet<>();
        for (Object o: obj1) {
            ((JSONObject) o).keySet().forEach(set::add);

        }

        for (Object o : obj1) {
            for (Object setObj: set) {
                System.out.println(((JSONObject) o).get(setObj));
            }
        }

    }
}




