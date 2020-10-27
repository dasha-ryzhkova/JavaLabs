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
        ArrayList<String>  lines = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(inputFile));){
            String str;
            while ((str = rd.readLine()) != null) {
                lines.add(str);
            }
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        parseCSV(lines);
    }

    public void parseCSV(ArrayList<String> lines) {
        for (String s : lines) {
            String cleanedline = deleteQuotesOfLine(s);
            wordsOfLine = new ArrayList<>();
            while (cleanedline.length() > 0) {
                cleanedline = extractWordFromLine(cleanedline);
            }
            linesArr.add(wordsOfLine);
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));) {
            for (ArrayList<String> l : linesArr) {
                for (int i = 0; i < l.size(); i++) {
                    if (i < l.size() - 1) {
                        writer.write(l.get(i).length() + "" + newdel);
                        //System.out.print(l.get(i).length() + "" + newdel);
                    } else {
                        //System.out.print(l.get(i).length());
                        writer.write(l.get(i).length() + "\n");
                    }
                }
                //System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String extractWordFromLine(String line) {
        int i = 0;
        String word = "";
        if(line.charAt(i) == '\uFEFF'){
            i++;
        }
        if(line.charAt(0) == combiner) {
            do{
                word += line.charAt(i);
                i++;
            }while (i != line.length() && line.charAt(i) != delimiter || line.charAt(i - 1) != combiner);
            word = deleteQuotesOfLine(word);
            word = deleteQuotesOfLine(word);
            word = deleteDoubleQuotesInWord(word);
            wordsOfLine.add(word);
            while (i < line.length() - 1 && line.charAt(i) == delimiter) {
                i++;
            }
            if (i < line.length()) {
                line = line.substring(i);
            } else {
                line = "";
            }
        } else {
            do {
                word += line.charAt(i);
                i++;
            } while (i != line.length() && line.charAt(i) != delimiter);
            word = deleteDoubleQuotesInWord(word);
            wordsOfLine.add(word);
            while (i < line.length() - 1 && line.charAt(i) == delimiter) {
                i++;
            }
            if (i < line.length()) {
                line = line.substring(i);
            } else {
                line = "";
            }
        }
        return line;
    }


    public String deleteQuotesOfLine(String str) {
        if (str.length() > 1 && str.charAt(0) == combiner && str.charAt(str.length() - 1) == combiner) {
            str = str.substring(1, str.length() - 1);
        }
        return str;
    }

    public String deleteDoubleQuotesInWord(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++){
            if (str.charAt(i) == combiner && str.charAt(i + 1) == combiner) {
                result += combiner;
                i++;
            } else {
                result += str.charAt(i);
            }
        }
        return result;
    }

//    public String extractWordFromLine(String line) {
//        int i = 0;
//        String word = "";
//        if (line.charAt(0) == combiner) {
//            while (!(line.charAt(i) == delimiter &&  line.charAt(i - 1) == combiner )) {
//                if(line.charAt(i) == '\uFEFF'){
//                    i++;
//                }
//                //if (line.charAt(i) != combiner ){
//                    word += line.charAt(i);
//                //}
//                i++;
//                if (i == line.length()) {
//                    break;
//                }
//            }
//            //i++;
//            word = deleteQuotes(word);
//
//            wordsOfLine.add(word);
//            i++;
//            if (i < line.length()) {
//                line = line.substring(i);
//            } else {
//                line = "";
//            }
//            return line;
//        } else {
//            do {
//                if(line.charAt(i) == '\uFEFF'){
//                    i++;
//                }
//                //if (line.charAt(i) != combiner){
//                    word += line.charAt(i);
//               // }
//                i++;
//                if (i == line.length()) {
//                    i++;
//                    break;
//                }
//            } while (line.charAt(i) != delimiter);
//            word = deleteQuotes(word);
//            wordsOfLine.add(word);
//            i++;
//            if (i < line.length()) {
//                line = line.substring(i);
//            } else {
//                line = "";
//            }
//        }
//        return line;
//    }
//
//
//    public String deleteQuotes(String str) {
//        String result = str;
//        while (result.length() > 1 && result.charAt(0) == combiner && result.charAt(result.length() - 1) == combiner) {
//            result = result.substring(1, result.length() - 1);
//        }
//        return result;
//    }
}
