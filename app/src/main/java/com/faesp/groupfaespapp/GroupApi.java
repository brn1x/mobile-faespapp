package com.faesp.groupfaespapp;

import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GroupApi {
	// Le os dados do JSON e Transforma em string
    private static String readStream(InputStream in){
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        StringBuilder total = new StringBuilder();
        String line;

        try{
            while((line = r.readLine()) != null) {
                total.append(line).append('\n');
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return total.toString();
    }

	// Faz o request para a API
    public static String request(String stringUrl) {
        URL url = null;
        HttpURLConnection urlConnection = null;

        try {
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy =
                        new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            url = new URL(stringUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            return readStream(in);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            urlConnection.disconnect();
        }
        return "";
    }

    public static void requestPOST(String stringURL, String json) throws IOException {
        URL url = new URL(stringURL);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-Type", "application/json");

        urlConnection.setDoOutput(true);
        OutputStream os = urlConnection.getOutputStream();
        os.write(json.getBytes());
        os.flush();
        os.close();

        int responseCode = urlConnection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in .readLine()) != null) {
                response.append(inputLine);
            } in .close();
        } else {
            System.out.println("POST NOT WORKED");
        }
    }

    public static ArrayList<Group> findGroup(String url) throws JSONException {
        String response = request(url);
        JSONArray arrayJson = new JSONArray(response);
        JSONObject obj;
        ArrayList<Group> gList = new ArrayList<Group>();
        for(int i=0; i < arrayJson.length(); i++){
             obj = arrayJson.getJSONObject(i);

            Integer id = obj.getInt("id");
            String nmGrupo = obj.getString("nmGrupo");
            Integer qtdMinP = obj.getInt("qtdMinP");
            Integer qtdMaxP = obj.getInt("qtdMaxP");
            Integer qtdEnc = obj.getInt("qtdEnc");
            String tpGrupo = obj.getString("tpGrupo");
            String descGrupo = obj.getString("descGrupo");
            String objGrupo = obj.getString("objGrupo");
            String situacao = obj.getString("situacao");

            gList.add(new Group(id, nmGrupo, qtdMinP, qtdMaxP, qtdEnc, tpGrupo, descGrupo, objGrupo, situacao));
        }

        return gList;
    }

}
