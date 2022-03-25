/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibUtil;

import java.io.IOException;
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
    public static Element ParseXml(String file) throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Element root, element;
        factory=DocumentBuilderFactory.newInstance();
        builder=factory.newDocumentBuilder();
        Document document=builder.parse(file);
        root=document.getDocumentElement();
        return root; 
    }
}
