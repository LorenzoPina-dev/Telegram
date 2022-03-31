/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibUtil;

import java.util.Map;

/**
 *
 * @author user
 */
public class Generiche {
    public static String MapToString(Map mappa,String separatoreKeyVal,String separatorerighe){
        String ris="";
        for(var key: mappa.keySet())
            ris+=key+separatoreKeyVal+mappa.get(key)+separatorerighe;
        return ris.substring(0, ris.length()-separatorerighe.length());
    }
}
