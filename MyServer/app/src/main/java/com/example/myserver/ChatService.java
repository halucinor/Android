package com.example.myserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatService extends Service {
    public ChatService() {


    }

    @Override
    public void onCreate() {
        super.onCreate();
        ServerThread serverThread = new ServerThread();
        serverThread.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    class ServerThread extends Thread {
        public void run(){
            int port = 5001;

            try {
                ServerSocket server = new ServerSocket(port);
                Log.d("serverthread", " 서버가 실행됨.");

                while(true){
                    Socket socket = server.accept();

                    ObjectInputStream instream = new ObjectInputStream(socket.getInputStream());

                    Object input = instream.readObject();
                    Log.d("serverthread","input : "+ input);

                    ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream());
                    outstream.writeObject(input + "from server.");
                    outstream.flush();

                    Log.d("serverthread","output 보냄.");
                    socket.close();

                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
