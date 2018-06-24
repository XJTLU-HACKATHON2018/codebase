package com.hstone.mkey;

import android.util.Log;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

import static android.content.ContentValues.TAG;

public class Client extends Socket {
    String textAll = "";
    Socket socket ;
    public void connect(String text) {
                    try {
                        //创建一个流套接字并将其连接到指定主机上的指定端口号
                        socket = new Socket("10.10.20.23", 38848);
                       // Thread.sleep(1000);//读取服务器端数据
                        DataInputStream input = new DataInputStream(socket.getInputStream());
                        //向服务器端发送数据
                        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                        //String str = "send";
                        out.writeUTF("jjjjjjjjjjjjjjjjjjjjjjjjj");

                        final String ret = input.readUTF();
                        out.close();
                        input.close();
                    } catch (Exception e) {
                        System.out.println("客户端异常:" + e.getMessage());
                    } finally {
                        if (socket != null) {
                            try {
                                socket.close();
                            } catch (IOException e) {
                                socket = null;
                            }
                        }
                    }


    }


    public void send() {

    }

    public void received() {

    }
}
