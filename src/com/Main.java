package com;
import java.util.Scanner;
import java.io.*;


public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner (System.in);

        System.out.println("Enter file path:");
        String path = scanner.next();
        File f = new File(path);
        if (f.exists())
        {


            try
            {
                System.out.println("Enter delimiter:");
                String del = scanner.next();

                System.out.println("Enter new delimiter:");
                String newdel = scanner.next();

                System.out.println("Enter path for new file:");
                String newpath = scanner.next();

                char[] del1 = del.toCharArray();
                FileReader fr = new FileReader(path);
                Scanner sc = new Scanner(fr);
                FileWriter fw = new FileWriter(newpath);

                String line;
                char[] chars;
                int counter;
                String res;


                while (sc.hasNextLine())
                {
                    line = sc.nextLine();
                    chars = line.toCharArray();
                    for(int i = 0; i < chars.length; i++) //i = 0 -> \uFEFF
                    {
                        counter = 0;

                        if(chars[i] == '\uFEFF')
                        {
                            i++;
                            if(chars[i] == '"') //chars[i] == '“' || chars[i] == '”' ||
                            {
                                i++;
                                while(i < chars.length) //i < chars.length ||
                                {
                                    if(chars[i] == '"')
                                        break;

                                    counter++;
                                    i++;

                                }
                                res = Integer.toString(counter);
                                fw.write(res);
                                if (i < chars.length - 1)
                                    fw.write(newdel);
                                else
                                    fw.write("\n");
                            }
                            else
                            {
                                while(i < chars.length  && chars[i] != del1[0] && chars[i] != '"')
                                {
                                    if(chars[i] == '\uFEFF')
                                        i++;
                                    counter++;
                                    i++;
                                }
                                res = Integer.toString(counter);
                                fw.write(res);
                                if (i < chars.length - 1)
                                    fw.write(newdel);
                                else
                                    fw.write("\n");
                            }

                        }
                        else {
                            if(chars[i] == '"') //chars[i] == '“' || chars[i] == '”' ||
                            {
                                i++;
                                while(i < chars.length) //i < chars.length ||
                                {
                                    if(chars[i] == '"')
                                        break;

                                    counter++;
                                    i++;

                                }

                                res = Integer.toString(counter);
                                fw.write(res);
                                if (i < chars.length - 1)
                                    fw.write(newdel);
                                else
                                    fw.write("\n");
                            }

                            else
                            {
                                while (i < chars.length && chars[i] != del1[0]) {
                                    if (chars[i] == '\uFEFF')
                                        i++;
                                    counter++;
                                    i++;
                                }
                                res = Integer.toString(counter);
                                fw.write(res);
                                if (i < chars.length - 1)
                                    fw.write(newdel);
                                else
                                    fw.write("\n");
                            }
                        }

                    }
                }

                fr.close();
                sc.close();
                fw.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        else
            System.out.println("File does not exist.\n");

        scanner.close();
    }
}
// C:\Users\VivoBook\Desktop\lab1.csv
// C:\Users\VivoBook\Desktop\result.txt