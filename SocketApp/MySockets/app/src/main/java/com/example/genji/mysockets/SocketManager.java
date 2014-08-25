package com.example.genji.mysockets;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;


/**
 * Created by gnoguchi on 8/24/14.
 */
public class SocketManager implements IOCallback{
    private String serverURL;
    private SocketIO socket;

    public SocketManager(String url) throws Exception{
        try {

            socket = new SocketIO(url);
            socket.connect(this);
            System.out.println("################################################################33333333333333");
            System.out.println("It worked!");

        } catch(Exception e){
            System.out.println("################################################################33333333333333");
            System.out.println("Connection error (socketmanager)");
            e.printStackTrace();
        }
    }

    public boolean setUsername(String username){
        try {
            socket.emit("add user", username);
            return true;
        } catch (Exception e){
            System.out.println("################################################################33333333333333");
            System.out.println("Exception when updating username. (Socketmanager)");

        }
        return false;
    }

    //Callbacks
    @Override
    public void onMessage(JSONObject json, IOAcknowledge ack) {
        try {
            System.out.println("Server said:" + json.toString(2));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onMessage(String data, IOAcknowledge ack) {
        System.out.println("Server said: " + data);
    }
    @Override
    public void onError(SocketIOException socketIOException) {
        System.out.println("an Error occurred");
        socketIOException.printStackTrace();
    }
    @Override
    public void onDisconnect() {
        System.out.println("Connection terminated.");
    }
    @Override
    public void onConnect() {
        System.out.println("Connection established");
    }
    @Override
    public void on(String event, IOAcknowledge ack, Object... args) {
        System.out.println("Server triggered event '" + event + "'");
    }



}
