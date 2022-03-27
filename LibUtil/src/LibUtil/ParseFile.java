/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibUtil;

import java.io.IOException;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
/**
 *
 * @author pina_lorenzo
 */
public class ParseFile {
    public static Element ParseXml(String url) throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Element root, element;
        factory=DocumentBuilderFactory.newInstance();
        builder=factory.newDocumentBuilder();
        Document document=builder.parse(url);
        root=document.getDocumentElement();
        return root; 
    }
    
    public static JSONObject ParseJSON(String testo) throws IOException{
        String json=testo;
        if(testo.contains("http")||testo.contains("https"))
            json=GestioneUrl.LeggiResponse(testo);
        else if(testo.contains(".json"))
            json=GestioneFile.LeggiFile(testo);
        return new JSONObject(json);
    }

    
}
