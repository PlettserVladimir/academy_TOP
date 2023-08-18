package com.top.quotageneratorclient;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

// Receiver - класс для чтения строковых сообщений из потока сокета
public class Receiver implements Closeable {
    private BufferedReader in;  // поток чтения

    public Receiver(Socket socket) throws IOException{
        // создать поток из сокета
        in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    public String receive() throws IOException{
        if (in==null){
            throw new IOException("stream 'in' is null");
        }
        return in.readLine();
    }


    @Override
    public void close() throws IOException {
        if (in !=null){
            in.close();
            in=null;
        }
    }
}
