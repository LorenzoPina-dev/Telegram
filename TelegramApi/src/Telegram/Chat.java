/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telegram;

import org.json.JSONObject;

/**
 *
 * @author user
 */
public class Chat {
    public int id;
    public String type;
    public String title;
    public Chat(JSONObject Chat) {
        id=Chat.getInt("id");
        type=Chat.getString("type");
        if(Chat.has("title"))
            title=Chat.getString("title");
    }
    public String ToCsv(){
        return id+";"+type+";"+title;
    }
}
