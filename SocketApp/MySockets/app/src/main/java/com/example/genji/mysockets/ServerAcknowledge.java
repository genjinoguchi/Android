package com.example.genji.mysockets;

import io.socket.IOAcknowledge;

/**
 * Created by gnoguchi on 8/24/14.
 */
public class ServerAcknowledge implements IOAcknowledge{

    @Override
    public void ack(Object... args) {
        System.out.println("Server acknowledges this package.");
        for(Object o : args)
            System.out.println(o.toString());
    }


}
