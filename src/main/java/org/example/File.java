package org.example;

public class File implements DestinationInterface{
    private final String filePath;

    public File(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void print(String message){
        System.out.println("Printing to File at: " +filePath+" "+message);
    }
}
