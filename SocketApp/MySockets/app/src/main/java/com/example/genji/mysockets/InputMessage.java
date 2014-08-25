package com.example.genji.mysockets;

import android.app.ListFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class InputMessage extends Fragment {
    ServerAcknowledge serverAck;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState){
        serverAck = new ServerAcknowledge();

        View view = inflater.inflate(R.layout.fragment_input_message, container, false);



        return view;
    }


}
