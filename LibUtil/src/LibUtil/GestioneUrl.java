/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 *
 * @author pina_lorenzo
 */
public class GestioneUrl {
    
    public static String LeggiResponse(String u) throws IOException
    {
        return GestioneFile.LeggiFile(new URL(u).openStream());
    }
}
