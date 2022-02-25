/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Json;
import org.json.*;

/**
 *
 * @author pina_lorenzo
 */
public class ParseFile {
    public static void Parsa(String testo){
        String jsonString = "{nome:'mario',messaggi:['ciao','mondo']}" ; //assign your JSON String here
        JSONObject obj = new JSONObject(jsonString);
        String pageName = obj.getString("nome");

        JSONArray arr = obj.getJSONArray("messaggi"); // notice that `"posts": [...]`
        for (int i = 0; i < arr.length(); i++)
        {
            System.out.println(arr.get(i));
        }
    }
}
