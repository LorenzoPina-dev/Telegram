/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.Map;
import LibUtil.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
/**
 *
 * @author user
 */
public class GestioneService {
    private String key;
    
    public static String InviaRequest(String urlbase,Map<String,String> parametri) throws IOException
    {
        return GestioneUrl.LeggiResponse(CreateUrl(urlbase,parametri));
    }
    
    public static String CreateUrl(String urlbase,Map<String,String> parametri)
    {
        String urlCompleto=urlbase+"?";
        for(String key:parametri.keySet())
            urlCompleto+=key+"="+URLEncoder.encode(parametri.get(key), StandardCharsets.UTF_8)+"&";
        urlCompleto = urlCompleto.substring(0, urlCompleto.length()-1);
        return urlCompleto;
    }
}
