/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 *
 * @author aleks
 */
public class Receiver {
    private Socket socket;
    private ObjectInputStream in;

    public Receiver(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new ObjectInputStream(socket.getInputStream());
    }
    
    public Object receive() throws Exception{
        return in.readObject();
    }
    public void close() throws IOException{
        in.close();
    }
    
}
