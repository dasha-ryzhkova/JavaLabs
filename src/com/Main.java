
package com;
import java.util.Scanner;
import java.io.*;


public class Main
{


    public static void main(String[] args)throws IOException
    {
        Scanner scanner = new Scanner (System.in);

        System.out.println("Enter file path:");
        String path = new String("C:\\Users\\VivoBook\\Desktop\\lab1.csv");
        File f = new File(path);
        if (f.exists())
        {
            System.out.println("Enter delimiter:");
            char del = ',';

            System.out.println("Enter new delimiter:");
            char newdel = '+';

            System.out.println("Enter path for new file:");
            String newpath = new String("C:\\Users\\VivoBook\\Desktop\\result.txt");   //scanner.next().CharAt();

//            Task task = new Task(del, newdel);
//            task.file(path,newpath);
            CSVParser parser = new CSVParser(path, newpath, del, '"', newdel);
            parser.readCSVFile();

        }
        else
            System.out.println("File does not exist.\n");

        scanner.close();
    }
}
// C:\Users\VivoBook\Desktop\lab1.csv
// C:\Users\VivoBook\Desktop\result.txt


