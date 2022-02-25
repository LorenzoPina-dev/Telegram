/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telegram;

/**
 *
 * @author pina_lorenzo
 */
public class Messaggio {
    public int IDFrom;
    public int IdChat;
    public String text;
    public int date;
    public Messaggio(){
    }
    public void Saluta(){
        System.out.println("hello");
    }
}
