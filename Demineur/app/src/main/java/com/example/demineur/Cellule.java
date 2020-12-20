package com.example.demineur;

import android.widget.ImageView;

public class Cellule {
    private int ClickOrNot;
    private int BombeOrNot;
    private int BombeNextTo =0;

    Cellule(int ClickOrNot,int BombeOrNot){
        this.ClickOrNot = ClickOrNot;
        this.BombeOrNot = BombeOrNot;
    }

    public int getClickOrNot(){return ClickOrNot;}

    public void setClickOrNot(int click){this.ClickOrNot = click;}

    public int getBombeNextTo(){return BombeNextTo;}

    public void setBombeNextTo(int nextTo){this.BombeNextTo = nextTo;}

    public int getBombeOrNot(){return BombeOrNot;}

}
