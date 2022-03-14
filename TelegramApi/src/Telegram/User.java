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
public class User {
    public long id;
    public boolean is_bot,can_join_groups,can_read_all_group_messages,supports_inline_queries;
    public String first_name;
    public String last_name;
    public String username;
    public String language_code;
    public User(JSONObject User) {
        id=User.getLong("id");
        is_bot=User.getBoolean("is_bot");
        first_name=User.getString("first_name");
        if(User.has("last_name"))
            last_name=User.getString("last_name");
        if(User.has("username"))
            username=User.getString("username");
        if(User.has("language_code"))
            language_code=User.getString("language_code");
        if(User.has("can_join_groups"))
            can_join_groups=User.getBoolean("can_join_groups");
        if(User.has("can_read_all_group_messages"))
            can_read_all_group_messages=User.getBoolean("can_read_all_group_messages");
        if(User.has("supports_inline_queries"))
            supports_inline_queries=User.getBoolean("supports_inline_queries");
    }
    public String ToCsv(){
        return id+";"+is_bot+";"+first_name+";"+last_name+";"+username+";"+language_code;
    }
    
}
