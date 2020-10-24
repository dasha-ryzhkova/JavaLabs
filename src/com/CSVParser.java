package com;


import java.io.*;
import java.util.ArrayList;

public class CSVParser {
    String inputFile;
    String outputFile;
    char delimiter;
    char combiner;
    char newdel;
    ArrayList<ArrayList<String>> linesArr = new ArrayList<>();
    ArrayList<String> wordsOfLine = new ArrayList<>();

    public CSVParser(String inputFile, String outputFile, char delimiter, char combiner, char newdel) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.delimiter = delimiter;
        this.combiner = combiner;
        this.newdel = newdel;
    }

    public void readCSVFile() {

        BufferedReader rd = null;
        try {
            rd = new BufferedReader(new FileReader(inputFile));
            String str;

            while ((str = rd.readLine()) != null) {
                parseStringFromCSV(str);
            }
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        //BufferedWriter wr = new BufferedWriter(new FileWriter(newpath));

        for (ArrayList<String> l:linesArr) {
            for (int i = 0; i < l.size(); i++) {
                if(i < l.size() - 1){
                    System.out.print(l.get(i) + "" + newdel);
                } else {
                    System.out.print(l.get(i));
                }
            }
            System.out.println();
        }

    }

    public void parseStringFromCSV(String nextString) {
//        System.out.println(nextString);
        String cleanedline = deleteQuotes(nextString);

        while (cleanedline.length() > 0) {
            cleanedline = extractWordFromLine(cleanedline);
            cleanedline = deleteQuotes(cleanedline);
        }
        linesArr.add(wordsOfLine);
        wordsOfLine = new ArrayList<>();
//        for (int i = 0; i < wordsOfLine.size(); i++) {
//            if(i < wordsOfLine.size() - 1){
//                System.out.print(wordsOfLine.get(i) + "/");
//            } else {
//                System.out.print(wordsOfLine.get(i));
//            }
//        }
    }

    public String extractWordFromLine(String line) {
        int i = 0;
        String word = "";
        if (line.charAt(0) == combiner) {
            while (!(line.charAt(i) == delimiter &&  line.charAt(i - 1) == combiner )) {
                if(line.charAt(i) == '\uFEFF'){
                    i++;
                }
                //if (line.charAt(i) != combiner ){
                    word += line.charAt(i);
                //}
                i++;
                if (i == line.length()) {
                    break;
                }
            }
            //i++;
            word = deleteQuotes(word);

            wordsOfLine.add(word);
            i++;
            if (i < line.length()) {
                line = line.substring(i);
            } else {
                line = "";
            }
            return line;
        } else {
            do {
                if(line.charAt(i) == '\uFEFF'){
                    i++;
                }
                //if (line.charAt(i) != combiner){
                    word += line.charAt(i);
               // }
                i++;
                if (i == line.length()) {
                    i++;
                    break;
                }
            } while (line.charAt(i) != delimiter);
            word = deleteQuotes(word);
            wordsOfLine.add(word);
            i++;
            if (i < line.length()) {
                line = line.substring(i);
            } else {
                line = "";
            }
        }
        return line;
    }


    public String deleteQuotes(String str) {
        String result = str;
        while (result.length() > 1 && result.charAt(0) == combiner && result.charAt(result.length() - 1) == combiner) {
            result = result.substring(1, result.length() - 1);
        }
        return result;
    }
}
