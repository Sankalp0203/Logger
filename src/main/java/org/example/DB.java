package org.example;

public class DB implements DestinationInterface {
    private final String host;
    private final Integer port;
    private final String connector;

    public DB(String host, Integer port, String connector) {
        this.host = host;
        this.port = port;
        this.connector = connector;
    }

    @Override
    public void print(String message){
        System.out.println("Printing to DB at: " +host+":"+port.toString()+" "+message);
    }
}
