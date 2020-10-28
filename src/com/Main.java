
package com;
import java.util.Scanner;
import java.io.*;


public class Main
{


    public static void main(String[] args)throws IOException
    {
        Scanner scanner = new Scanner (System.in);

        System.out.println("Enter file path:");
        String path = scanner.next();;
        File f = new File(path);
        if (f.exists())
        {
            System.out.println("Enter delimiter:");
            String del1 = scanner.next();

            System.out.println("Enter new delimiter:");
            String newdel1 = scanner.next();

            System.out.println("Enter path for new file:");
            String newpath = scanner.next();;
            char del = del1.charAt(0);
            char newdel = newdel1.charAt(0);

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


