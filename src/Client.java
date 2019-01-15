import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import com.google.gson.*;


public class Client {
    ServerSocket host;
    Socket client;
    OutputStream outputStream;
    InputStream inputStream;
    BufferedWriter out;
    BufferedReader in;
    Gson gson;
    signal Signal;

    public Client(int portNumber, String IPADress) {
        this.gson = new Gson();
        try {
            this.client = new Socket(IPADress, portNumber);
            this.outputStream = this.client.getOutputStream();
            this.inputStream = this.client.getInputStream();
            this.out = new BufferedWriter(new OutputStreamWriter(this.outputStream));
            this.in = new BufferedReader(new InputStreamReader(this.inputStream));

        } catch (Exception ex) {
            // was macht hier Sinn
            System.out.println(ex);

        }


    }

    public boolean startGame() {
        System.out.println("Startgame");
        Signal = new signal();
        Signal.Sysmessage = "Start";
        String response = this.gson.toJson(Signal);
        try{
            this.out.write(response);
            this.out.newLine();
            this.out.flush();
        }catch (Exception ex){

        }
        return true;
    }

    public boolean handlePrepare(){
        String line;
        String JSON = "";
        boolean waiting = true;
        signal Signal;
        while(waiting){
            System.out.println("Waiting");
            try {
                /*
                while ((line = in.readLine()) != null) {
                    JSON = JSON + line;
                } */
                System.out.println("here");
                JSON = in.readLine();
                Signal = gson.fromJson(JSON, signal.class);
                System.out.println("########################");
                System.out.println(Signal.Sysmessage);
                System.out.println("########################");
                if (Signal.Sysmessage.equals("Prepare")) {
                    waiting = false;
                    System.out.println("HERE");
                }
            } catch (Exception ex) {
                System.out.println(ex);
                continue;
            }
        }
        System.out.println("finished");
        return true;
    }

}

