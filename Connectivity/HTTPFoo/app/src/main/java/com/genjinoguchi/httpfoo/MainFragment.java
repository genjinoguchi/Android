package com.genjinoguchi.httpfoo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class MainFragment extends Fragment {
    TextView txtvw;
    URL root;
    String targetURL = "http://192.168.1.9:5000/sendtext";
    HttpURLConnection connection;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        txtvw = (TextView) view.findViewById(R.id.view_status);

        new HttpStuff().execute();

        return view;

    }

    private class HttpStuff extends AsyncTask<URL, String, String> {
        protected String doInBackground(URL... urls){
            //http jazz
            try {
                String urlParameters =
                        "name=" + URLEncoder.encode("Genji Noguchi", "UTF-8") +
                                "&phoneNumber=" + URLEncoder.encode("9174350162", "UTF-8");

                root = new URL(targetURL);
                connection = (HttpURLConnection) root.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type","application/x-www-urlencoded");

                connection.setRequestProperty("Content-Length", "" +
                        Integer.toString(urlParameters.getBytes().length));
                connection.setRequestProperty("Content-Language", "en-US");

                connection.setUseCaches (false);
                connection.setDoInput(true);
                connection.setDoOutput(true);

                //Post Request
                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                out.writeBytes(urlParameters);
                out.flush();
                out.close();
                //Get Response
                InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response = new StringBuffer();
                while((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\r');
                }
                rd.close();
                System.out.println(response.toString());

                txtvw.setText("Awaiting text message");

            } catch (Exception e){
                e.printStackTrace();
                txtvw.setText("fk");
            } finally {
                if(connection != null) {
                    connection.disconnect();
                }
            }

            return "";

        }
    }



}
