package utils;

import java.io.File;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class FileReaderUtil {
    // get file path
    private static String getFilePath(String fileName) {
        // Assuming file is located in a folder named "dataFiles" relative to your project's root directory
        String absolutePath = "src" +File.separator + "dataFiles" + File.separator + fileName;
        return new File(absolutePath).getPath();
    }

    // read all data in a file
    public static ArrayList<String[]> readAllLines(String fileName) {
        String[] data;
        String currentLine;
        ArrayList<String[]> allFileData = new ArrayList<>();

        try {
            String filePath = getFilePath(fileName);
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // While reading that file, if the reader contact "," it will then break,
            while ((currentLine = bufferedReader.readLine()) != null) {
                if (!currentLine.trim().startsWith("//")) {
                data = currentLine.split(",");
                allFileData.add(data);
            }}
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return allFileData;
    }

    // read data in a specific column
    public static String[] readColString(int col, String fileName, String delimiter) throws IOException {
        String[] data;
        String currentLine;
        ArrayList<String> colData = new ArrayList<>();

        try {
            String filePath = getFilePath(fileName);
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // while reading that file, if the reader contact a symbol that is set in another method that this method supported
            // it will then break, skip all other columns except for one column that is asked to read.
            while ((currentLine = bufferedReader.readLine()) != null) {
                if (!currentLine.trim().startsWith("//")) {
                data = currentLine.split(delimiter);
                colData.add(data[col]);
            }}
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return colData.toArray(new String[0]);
    }

    // read data in a specific row
    public static String[] readSpecificLine( String row, String fileName, String delimiter) throws IOException {
        String[] data;
        String currentLine;

        try {
            String filePath = getFilePath(fileName);
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // while reading that file, if the reader contact a symbol that is set in another method that this method supported
            // it will then break, skip all other columns except for one column that is asked to read.
            while ((currentLine = bufferedReader.readLine()) != null) {
                if (!currentLine.trim().startsWith("//")) {
                data = currentLine.split(delimiter);
                if (data[0].equals(row)) {
                   return data;
                }}
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return new String[0];
    }

    // display return data from readAllLines method
    public static void displayAllLines(String fileName) {
        readAllLines(fileName).forEach((line) -> {
            System.out.println(Arrays.toString(line));
        });
    }
    // display return data from readColString method
    public static void displaySingleLine(int col, String fileName, String delimiter) throws IOException {
        System.out.println(Arrays.toString(readColString(col, fileName, delimiter)));
    }

    // display return data from readSpecificLine method
    public static void displaySpecificLine(String row, String fileName, String delimiter) throws IOException {
        System.out.println(Arrays.toString(readSpecificLine(row, fileName, delimiter)));
    }

    public static void main(String[] args) throws IOException {
        displayAllLines("test.txt");
        displaySingleLine(1, "test.txt", ",");
        displaySpecificLine("1", "test.txt", ",");

    }
}
