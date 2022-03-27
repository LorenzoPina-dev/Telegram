/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibUtil;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pina_lorenzo
 */
public class GestioneFile {
    public static void ScriviFile(String file,String testo) throws IOException{
        ScriviFile(new FileWriter(file), testo);
    }
    public static void ScriviFile(OutputStream file,String testo) throws IOException{
        ScriviFile(new OutputStreamWriter(file), testo);
    }
    public static void ScriviFile(OutputStreamWriter file,String testo) throws IOException{
        BufferedWriter sw= new BufferedWriter(file);
        sw.write(testo);
        sw.flush();
        sw.close();
    }
    public static String LeggiFile(String file) throws IOException{
        return LeggiFile(new FileReader(file));
    }
    public static String LeggiFile(InputStreamReader file) throws IOException{
        BufferedReader sr= new BufferedReader(file);
        String testo=sr.readLine(),line="";
        while((line=sr.readLine())!=null)
            testo+="\r\n"+line;
        sr.close();
        return testo;
    }
    public static String LeggiFile(InputStream file) throws IOException{
        return LeggiFile(new InputStreamReader(file));
    }
    
    public static List LeggiFileList(String file) throws IOException{
        String[] righe=GestioneFile.LeggiFile(file).split("\r\n");
        List<String> ris= new ArrayList<>();
        for(String riga:righe)
            ris.add(riga);
        return ris;
    }
    
}
