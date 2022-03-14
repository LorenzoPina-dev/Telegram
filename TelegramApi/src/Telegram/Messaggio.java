/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telegram;

import org.json.*;

/**
 *
 * @author pina_lorenzo
 */
public class Messaggio {
    public long id;
    public int date;
    public Chat chat;
    public User from;
    public String text;
    public Messaggio(JSONObject messaggio){
        id=messaggio.getLong("message_id");
        date=messaggio.getInt("date");
        if(messaggio.has("text"))
        text=messaggio.getString("text");
        chat=new Chat(messaggio.getJSONObject("chat"));
        from=new User(messaggio.getJSONObject("from"));
        
    }
    public String ToCsv(){
        return id+";"+date+";"+text+";"+chat.ToCsv()+";"+from.ToCsv();
    }
}
