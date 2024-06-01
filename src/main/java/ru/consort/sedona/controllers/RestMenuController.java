package ru.consort.sedona.controllers;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.util.*;

/**
 * Created by DaH4uk on 15.05.2016.
 */

@Controller
public class RestMenuController {

    @GetMapping( "/rest/*")
    public ModelAndView main(HttpServletRequest request) throws ParseException {
        ModelAndView modelAndView = new ModelAndView();


        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        client.addFilter(new HTTPBasicAuthFilter("admin", ""));
        WebResource service = client.resource(UriBuilder.fromUri("http://192.168.1.40:5555/").build());
        // getting JSON data
        String json = service.path("json").accept(MediaType.APPLICATION_JSON).get(String.class);



        JSONParser parser = new JSONParser();
        Object obj = parser.parse(json);
        JSONArray obj1 = (JSONArray) ((JSONObject) ((JSONObject) obj).get("obj")).get("ref");

        StringBuilder str = new StringBuilder();


        List<Object> level1Hrefs = new ArrayList<>();
        List<Object> level2Hrefs = new ArrayList<>();

        //parsing 1 level of tree
        for (Object level1Object : obj1) {
            Object level1Str = ((JSONObject) level1Object).get("href");
            level1Hrefs.add(level1Str);
            str.append("<li>\n" +
                    "<a href=\" " + level1Str + "\">" + ((JSONObject) level1Object).get("display"));


            //parameters for 2 level parsing
            String json1 = service.path("json/" + level1Str).accept(MediaType.APPLICATION_JSON).get(String.class);
            JSONParser level2Parser = new JSONParser();
            Object obj2 = level2Parser.parse(json1);

            //if для избежания null pointer exception
            if (((JSONObject) obj2).containsKey("obj") && ((JSONObject) ((JSONObject) obj2).get("obj")).containsKey("ref")) {
                JSONArray obj3 = (JSONArray) ((JSONObject) ((JSONObject) obj2).get("obj")).get("ref");
                str.append("<span class=\"fa arrow\"></span></a>\n" +
                        "<ul class=\"nav nav-third-level\">");
                //parsing 2 level of tree
                for (Object level2Object : obj3) {
                    Object level2Str = ((JSONObject) level1Object).get("href");
                    level2Hrefs.add(level1Str + "" + ((JSONObject) level2Object).get("href"));
                    str.append("<li>\n" +
                            "<a href=\"" + level1Str + level2Str + "\">" + ((JSONObject) level2Object).get("display") + "</a>\n");

                }
                str.append("</ul>\n" +
                        "<!-- /.nav-third-level -->\n" +
                        "</li>");
            } else {
                str.append("</a>\n" +
                        "</li>");
            }

        }


        String string = str.toString();


        StringBuilder theadStrBldr = new StringBuilder();

        Set<Object> set = new TreeSet<>();
        for (Object o : obj1) {
            ((JSONObject) o).keySet().forEach(set::add);

        }
        for (Object o : set) {
            theadStrBldr.append("<th>\n" +
                    "                                                " + o + "\n" +

                    "                                            </th>");
        }
        String theadTable = theadStrBldr.toString();

        StringBuilder bodyStrBldr = new StringBuilder();

        for (Object o : obj1) {
            bodyStrBldr.append("<tr>");
            for (Object setObj : set) {
                String parameter = (String) ((JSONObject) o).get(setObj);
                if (parameter == null) {
                    parameter = "";
                }

                if (setObj.equals("href"))
                {
                    parameter = "<a href=\"/rest/" + parameter + "\"> " + parameter+"</a>";
                }
                bodyStrBldr.append("<td>\n" +
                        " " + parameter + "\n" +
                        "</td>");
            }
            bodyStrBldr.append("</tr>");
        }


        String bodyTable = bodyStrBldr.toString();

        modelAndView.addObject("mapping", string);
        modelAndView.addObject("theadTable", theadTable);
        modelAndView.addObject("tableBody", bodyTable);

        System.out.println(request.getRequestURI());


        modelAndView.setViewName("/rest/json");
        return modelAndView;
    }



}
