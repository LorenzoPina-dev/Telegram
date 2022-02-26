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
    public int id;
    public int date;
    public Chat chat;
    public User from;
    public String text;
    public Messaggio(JSONObject messaggio){
        id=messaggio.getInt("message_id");
        date=messaggio.getInt("date");
        text=messaggio.getString("text");
        chat=new Chat(messaggio.getJSONObject("chat"));
        from=new User(messaggio.getJSONObject("from"));
        
        System.out.println(messaggio);
    }
    public String ToCsv(){
        return id+";"+date+";"+text+";"+chat.ToCsv()+";"+from.ToCsv();
    }
}
