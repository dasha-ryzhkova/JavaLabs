package com;

import java.util.*;
import java.io.*;

public class Task {
    char del;
    char newdel;

    public Task(char del, char newdel) {
        this.del = del;
        this.newdel = newdel;
    }

    public void file(String path, String newpath) throws IOException {
        BufferedReader rd = new BufferedReader(new FileReader(path));
        BufferedWriter wr = new BufferedWriter(new FileWriter(newpath));
        String str;

        while ((str = rd.readLine()) != null) {
            String newstr = new String();
            System.out.println(str);
            for (int i = 0; i < str.length(); i++) {
                if(str.charAt(i) == '\uFEFF'){
                    i++;
                }
                String word = new String();
                if (str.charAt(i) != '"') {
                    do {
                        word += str.charAt(i);
                        i++;
                        if (i > str.length() - 1) {
                            break;
                        }
                    } while (str.charAt(i) != del);
                    if (i < str.length() - 1) {
                        newstr += word.length() + "" + newdel;
                    } else {
                        newstr += word.length();
                    }
                } else {
                    i++;
                    do {
                        word += str.charAt(i);
                        i++;
                        if (i == str.length() - 1) {
                            break;
                        }
                    } while (str.charAt(i) != '"' || str.charAt(i + 1) != del);
                    i++;
                    if (i < str.length() - 1) {
                        newstr += word.length() + "" + newdel;
                    } else {
                        newstr += word.length();
                    }
                }
            }

            wr.write(newstr + "\n");
        }
        rd.close();
        wr.close();
    }
}
