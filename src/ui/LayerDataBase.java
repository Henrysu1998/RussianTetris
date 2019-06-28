package ui;

import java.awt.*;

/**
 * 数据库窗体
 */
public class LayerDataBase extends LayerData {
    //TODO 配置文件



    public LayerDataBase(int x, int y, int w, int h) {
        super(x, y, w, h);

    }

    public  void paint(Graphics g) {
        this.createWindow(g);
        this.showData(Img.DB,this.dto.getDbRecode(),g);
    }
}
