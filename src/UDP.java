import java.util.Scanner;

public class UDP {
    public static void main(String[] args) {
        int serverDoor = 8888;
        int bufferSize = 256;
        String localAdress = "localhost";
        String mensagem;
        Scanner scanner = new Scanner(System.in);

        ServerUDP server = new ServerUDP(serverDoor, bufferSize);
        Client client = new Client(localAdress, serverDoor);

        do {
            if (!server.isAlive()) {
                server.start();
            }
            menu();
            mensagem = scanner.nextLine();
            String response = client.send(mensagem);
            serverResponse(response);
        } while (mensagem != "end");

        scanner.close();
    }

    private static void menu() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Escreva uma mensagem ou escreva 'end' para encerrar\n");
        stringBuilder.append("Mensagem: ");
        System.out.print(stringBuilder.toString());
    }

    private static void serverResponse(String response) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Resposta do servidor: ");
        stringBuilder.append(response);
        stringBuilder.append("\n");
        System.out.print(stringBuilder.toString());
    }
}
