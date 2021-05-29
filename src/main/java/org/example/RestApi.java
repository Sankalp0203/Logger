package org.example;

public class RestApi implements DestinationInterface{
    private final String host;
    private final Integer port;
    private final String apiPath;

    public RestApi(String host, Integer port, String apiPath) {
        this.host = host;
        this.port = port;
        this.apiPath = apiPath;
    }

    @Override
    public void print(String message){
        System.out.println("Printing to URL at: " +host+":"+port.toString()+" "+message);
    }
}
