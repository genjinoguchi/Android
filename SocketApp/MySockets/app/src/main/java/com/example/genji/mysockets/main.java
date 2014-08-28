package com.example.genji.mysockets;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class main extends ActionBarActivity {
    private SocketManager ioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String username = intent.getStringExtra(LoginPage.USERNAME_EXTRA);

        try {
            ioManager = new SocketManager();
            System.out.println("############################################################maintest1");
            ioManager.sendTestMessage("I'm here!");
            System.out.println("############################################################maintest2");

        } catch (Exception e){
            System.out.println("################################################################33333333333333");
            System.out.println("There was an error in the connection (main)");
        }

        setContentView(R.layout.activity_main);
    }
}
