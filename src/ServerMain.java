public class ServerMain {
    public static void main(String[] args){
        Server server = new Server(5050);

        if(server.waitForConnection()){
            System.out.println("Server started");
            server.Prepare();
            server.handleGameStart();
            System.out.println("Here");
        }
    }
}
