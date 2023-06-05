package praktikum_asd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileCsv {

    private String file;

    public FileCsv(String file) {
        this.file = file;
    }

    public String[] OpenFile() throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader textReader = new BufferedReader(fr);

        int numberOfLines = 3;
        String[] textData = new String[numberOfLines];

        for (int i = 0; i < numberOfLines; i++) {
            textData[i] = textReader.readLine();
        }
        textReader.close();
        return textData;
    }

    int readLines() throws IOException {
        FileReader file_to_read = new FileReader(file);
        BufferedReader bf = new BufferedReader(file_to_read);

        String aLine;
        int numberOfLines = 0;

        while ((aLine = bf.readLine()) != null) {
            numberOfLines++;
        }
        bf.close();
        return numberOfLines;
    }

}
