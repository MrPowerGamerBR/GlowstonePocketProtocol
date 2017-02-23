package net.pocketdreams.sequinland.util;

import java.util.ArrayList;

import com.google.gson.Gson;

public class MessageUtils {
    public static String translate(String json) {
        StringBuilder sb = new StringBuilder();
        Gson gson = new Gson();
        MessageBase mb = gson.fromJson(json, MessageBase.class);
        translateMessageBase(sb, mb);
        return sb.toString();
    }
    
    public static StringBuilder translateMessageBase(StringBuilder sb, MessageBase mb) {
        sb.append(mb.text);
        if (mb.extra != null) {
            for (MessageBase messBase : mb.extra) {
                sb = translateMessageBase(sb, messBase);
            }
        }
        return sb;
    }
    
    public static class MessageBase {
        String text;
        ArrayList<MessageBase> extra = new ArrayList<MessageBase>();
        String color;
    }
}
