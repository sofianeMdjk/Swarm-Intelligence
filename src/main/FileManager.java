package main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class FileManager
{
    private static PrintWriter writer = null;

    public static void createFile(String fileName) throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter(fileName,"UTF-8");
    }

    public static void writeInFile(String line)
    {
        writer.println(line);
    }

    public static void closeFile()
    {
        writer.close();
    }
}
