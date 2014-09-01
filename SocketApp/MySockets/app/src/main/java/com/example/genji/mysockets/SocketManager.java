package com.example.genji.mysockets;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Ack;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

public class SocketManager{
    private Socket socket;
    private Ack ack;

    public SocketManager(){
        try {
            init();
            System.out.println("###################################################");
            System.out.println("Connection successful.");
        } catch (Exception e){
            System.out.println("###################################################");
            System.out.println("Wrong url.");
        }
    }

    public void init() throws Exception {
        ack = new Ack(){
            @Override
            public void call(Object...args){
                System.out.println("ACKACKACKACKACKACKACKACKACKACKACKACKACKACKACKACKACKACKACKACKACKACKACKv");
                System.out.println(args);
            }
        };
        socket = IO.socket("http://192.168.1.9:3000");
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                socket.emit("foo", "hi");
                socket.disconnect();
            }

        }).on("event", new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                System.out.println(args);
            }

        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
            }

        });
        socket.connect();
    }

    public boolean sendTestMessage(String s){
        JSONObject json = new JSONObject();
        try {
            json.put("AndroidMessage", s);
            socket.emit("test", json, ack);
            System.out.println("###############################################################################");
            System.out.println(json);
            return true;
        } catch (JSONException e){
            System.out.println("##############################################################################3");
            System.out.println("erp (socketmanager)");
            e.printStackTrace();
        }
        return false;
    }

}


/*
import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;


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
*/