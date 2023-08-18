package com.top.quotageneratorclient;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Controller {
    @FXML
    private TextField ipAddressServer;
    @FXML
    private TextField portServer;
    @FXML
    private TextArea outQuote;
    @FXML
    private Button connectButton;

    private Socket socketServer;
    private Sender sender=null;
    private boolean flag=true;




    @FXML
    private void connectToServer() throws IOException {
        ConnectToServer startClient = new ConnectToServer();
        Thread threadStartClient = new Thread(startClient);
        if (flag) {
            connectButton.setText("Disconnect");
            threadStartClient.start();
            flag=false;
        }else if (!flag){
            sender.send("exit");
            flag=true;
            connectButton.setText("Connect");
        }
    }
    @FXML
    private void generateQuote() throws IOException {
        String messageByServer="quota";
        sender.send(messageByServer);
    }
   public class ConnectToServer implements Runnable{

        @Override
        public void run() {
            Receiver receiver=null;
            outQuote.setWrapText(true);

            try{
                socketServer=new Socket(InetAddress.getByName(ipAddressServer.getText()),Integer.parseInt(portServer.getText()));
                System.out.println("Создан сокет для подключения к серверу: "+ipAddressServer.getText()+":"+portServer.getText());

                sender=new Sender(socketServer);
                receiver=new Receiver(socketServer);
                String server=ipAddressServer.getText()+":"+portServer.getText()+">";
                String quota;

                while (!flag){
                    quota = receiver.receive();    // считать сообщение
                    outQuote.appendText(server+" "+quota+"\n"); //вывод сообщения в поле TextArea
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }finally {
                try {
                    if (socketServer != null && !socketServer.isClosed()) {
                        socketServer.close();
                    }
                    if (sender != null) {
                        sender.close();
                    }
                    if (receiver != null) {
                        receiver.close();
                    }
                }
                catch (Exception ex) {
                    System.out.println("Клиент > что-то пошло не так в finally: " + ex.getMessage());
                }
            }
        }
    }
}