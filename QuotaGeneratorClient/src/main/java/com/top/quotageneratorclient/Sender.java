package com.top.quotageneratorclient;
import java.io.*;
import java.net.Socket;

// Sender - класс, обеспечивающий отправку строковых через поток сокета
public class Sender implements Closeable {
    private PrintWriter out; // поток записи

    // конструктор
    public Sender(Socket socket) {
        // создать поток из сокета
        try {
            out=new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())),true);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
    // метод отправки строки в поток сокета
    public void send(String message) throws IOException {
        if (out==null){
            // если поток был закрыт ранее
            throw new IOException("stream 'out' is null");
        }
        out.println(message);


    }

    @Override
    public void close() throws IOException {
        if (out !=null){
            out.close();
            out=null;
        }
    }
}
