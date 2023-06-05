package praktikum_asd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileTeks {
    private String namaFile;
    
    public FileTeks(String namaFile){
        this.namaFile=namaFile;
    }
    
    public void tulis(String teks){
        try{
            FileWriter myWriter = new FileWriter(this.namaFile);
            myWriter.write(teks);
            myWriter.close();
        }catch(IOException e){
            System.out.println("An error Occured" + e.getMessage());
            
        }
    }
    
    public String baca() throws FileNotFoundException{
        String hasil="";
        try{
            File myFile = new File(this.namaFile);
            Scanner filereader = new Scanner(myFile);
            while(filereader.hasNextLine()){
                hasil = hasil + filereader.nextLine()+"\n";
        }
    }catch(FileNotFoundException e){
        System.out.println("File : " + this.namaFile + "tidak ada!!!");
    }
        return hasil;
    }
        
        
    public String[] bacaBaris(){
        String s = "";
        try{
            File myObj = new File(namaFile);
            Scanner myreader = new Scanner(myObj);
            while(myreader.hasNextLine()){
                s += myreader.nextLine() + "\n";
            }
        }catch(FileNotFoundException e){
            System.out.println("File : " + this.namaFile);
        }
        return s.split("\n");
            
    }
        
       
}