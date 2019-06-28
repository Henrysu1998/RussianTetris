package service;

import dto.Player;

import java.util.List;

public interface GameService {
    /**
     *上
     */
    public boolean KeyUp();
    /**
     *下
     */
    public boolean KeyDown();
    /**
     *左
     */
    public boolean KeyLeft();
    /**
     *右
     */
    public boolean KeyRight();
    /**
     *三角
     */
    public boolean KeyFunUp();
    /**
     *大叉
     */
    public boolean KeyFunDown();
    /**
     *方块
     */
    public boolean KeyFunLeft();
    /**
     *圆圈
     */
    public boolean KeyFunRight();

    /**
     * 启动主线程开始游戏
     */
    public void startGame();

    /**
     * 游戏主要行为
     */
    public void mainAction();
}
