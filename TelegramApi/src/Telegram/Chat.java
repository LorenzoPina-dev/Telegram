/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telegram;

import java.math.BigInteger;
import org.json.JSONObject;

/**
 *
 * @author user
 */
public class Chat {
    public long id;
    public String type;
    public String title,username,first_name,last_name;
    public String bio,description,invite_link;
    public Chat(JSONObject Chat) {
        id=Chat.getLong("id");
        type=Chat.getString("type");
        if(Chat.has("title"))
            title=Chat.getString("title");
        if(Chat.has("username"))
            username=Chat.getString("username");
        if(Chat.has("first_name"))
            first_name=Chat.getString("first_name");
        if(Chat.has("last_name"))
            last_name=Chat.getString("last_name");
        if(Chat.has("bio"))
            bio=Chat.getString("bio");
        if(Chat.has("description"))
            description=Chat.getString("description");
        if(Chat.has("invite_link"))
            description=Chat.getString("invite_link");
    }
    public String ToCsv(){
        return id+";"+type+";"+title;
    }
}
