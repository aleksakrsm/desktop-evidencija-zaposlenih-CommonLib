/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.communication;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author aleks
 */
public class Sender {
    private Socket socket;
    private ObjectOutputStream out;
    public Sender(Socket socket) throws IOException {
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
    }
    public void send(Object object) throws IOException{
        out.writeObject(object);
//        System.out.println("Poruka poslata: "+object);
        out.flush();
    }
    public void close() throws IOException{
        out.close();
    }
}
