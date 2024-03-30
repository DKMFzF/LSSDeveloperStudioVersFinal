package com.example.pidorasgames.database;

public class QuestModel {
    public String id;
    public String disc;
    public String quest;
    public String ans;
    public String var1;
    public String var2;
    public String var3;
    public int use;

    public QuestModel(String id, String disc, String quest, String ans, String var1, String var2, String var3, int use) {
        this.id = id;
        this.disc = disc;
        this.quest = quest;
        this.ans = ans;
        this.var1 = var1;
        this.var2 = var2;
        this.var3 = var3;
        this.use = use;
    }
}
