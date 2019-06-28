package ui;


import java.awt.*;

public class LayerBackground extends Layer {

    public  LayerBackground(int x, int y, int w, int h) {
        super(x, y, w, h);
    }



    public void paint(Graphics g){
        int bgIdx=this.dto.getNowlevel()%Img.BG_LIST.size();
        g.drawImage(Img.BG_LIST.get(bgIdx),0,0,1162,654,null);
    }
}
