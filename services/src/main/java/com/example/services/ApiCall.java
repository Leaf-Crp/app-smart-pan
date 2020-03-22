package com.example.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//import org.json.JSONObject;

public class ApiCall {
    public static void main(String[] args) {
        try {
            ApiCall.call_me();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *test appel API node
     * Factoriser la classe pour la rendre accessible depuis les différents services/repos
     * @throws Exception
     */
    private static void call_me() throws Exception {
        String url = "http://localhost:3001/users";

        URL obj = new URL(url);
        System.out.println(obj);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //print in String
        System.out.println(response.toString());
        //Read JSON response and print
        //JSONObject myResponse = new JSONObject(response.toString());
    }
}
