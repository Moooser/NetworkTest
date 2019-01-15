import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import com.google.gson.*;

public class Server {
    ServerSocket host;
    Socket client;
    OutputStream outputStream;
    InputStream inputStream;
    BufferedWriter out;
    BufferedReader in;
    Gson gson;

    public Server(int portNumber) {
        try {
            this.host = new ServerSocket(portNumber);
        } catch (Exception ex) {
            // was macht hier Sinn
        }
        this.gson = new Gson();

    }

    public boolean waitForConnection(){
        try {
            this.client = host.accept();
            host.getInetAddress();
            this.outputStream = client.getOutputStream();
            this.inputStream = client.getInputStream();
            this.out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            this.in =new BufferedReader(new InputStreamReader(client.getInputStream()));
        }
        catch (Exception ex){
            return false;
        }
        return true;
    }


    public boolean handleGameStart(){

        String line;
        String JSON = "";
        signal Signal;
        boolean waiting = true;
        while(waiting) {
            System.out.println("waiting");
            try {
             /*   while ((line = in.readLine()) != null) {
                    JSON = JSON + line;
                } */
                JSON = in.readLine();
                Signal = gson.fromJson(JSON, signal.class);
                System.out.println(Signal.Sysmessage);
                if(Signal.Sysmessage.equals("Start")){
                    System.out.println(Signal.Sysmessage);
                    waiting = false;
                }
            } catch (Exception ex) {
                continue;
            }
        }
        return true;
    }

    public boolean Prepare(){
        signal Signal = new signal();
        Signal.Sysmessage = "Prepare";
        String JSON = this.gson.toJson(Signal);
        try {
            out.write(JSON);
            out.newLine();
            out.flush();
            System.out.println("Written");
        }
        catch (Exception ex){
            return false;
        }
        return true;
    }


}


