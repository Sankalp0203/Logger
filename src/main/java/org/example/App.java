package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Logger logger = Logger.getInstance();
        String targetConfigString = "{\"host\":\"abcd\",\"port\":\"1234\", \"connector\":\"jdbc\"}";
        logger.setConfiguration("DEBUG","dd-mm-yyyy hh:mm:ss", "db", targetConfigString);
        logger.info("INFO message", "Logger App");
        logger.warn("WARN message", "Logger App");
        logger.error("Error message", "Logger App");
    }
}
