package sca;

import javax.xml.ws.Endpoint;

public class WSServer {
    public static void main(String[] args) {
        String url = "http://localhost:8080/wssca";
        System.out.println("Iniciando WSServer em: " + url);
        Endpoint.publish(url, new WSSCAImpl());
        System.out.println("WSServer publicado com sucesso! WSDL disponível em: " + url + "?wsdl");
    }
}