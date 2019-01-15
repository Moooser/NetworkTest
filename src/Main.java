public class Main {
    public static void main(String[] args) {


        Client client = new Client(5050, "192.168.178.48");
        if(client.handlePrepare()){
            client.startGame();
        }
        }
    }



