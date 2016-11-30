package com.fornothing.projects.magnetize.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import static com.fornothing.projects.magnetize.MainClass.gamecam;
import static com.fornothing.projects.magnetize.screens.GameScreen.LINE_LEFT_COUNT;
import static com.fornothing.projects.magnetize.screens.GameScreen.LINE_RIGHT_COUNT;
import static com.fornothing.projects.magnetize.screens.GameScreen.lineLeftArray;
import static com.fornothing.projects.magnetize.screens.GameScreen.lineRightArray;

public class RedLine {

    private final Texture LINE_TEXTURE;
    private final Vector3 LINE_POS_LEFT;
    private final Vector3 LINE_POS_RIGHT;
    public static Rectangle BOUNDS_LEFT;
    public static Rectangle BOUNDS_RIGHT;
    public static final int LINE_SPACE_BETWEEN = 600;
    private static final float SPEED_DOWN = -100;
    private static final float SPEED_UP = (-SPEED_DOWN *2) + Player.SPEED_UP;

    public RedLine(float y) {
        LINE_TEXTURE = new Texture("images/redLine.png");

        LINE_POS_LEFT = new Vector3(gamecam.viewportWidth * 0.1f,
                y + LINE_SPACE_BETWEEN, 0);
        LINE_POS_RIGHT = new Vector3(gamecam.viewportWidth * 0.9f,
                y - LINE_SPACE_BETWEEN, 0);

        BOUNDS_LEFT = new Rectangle(
                LINE_POS_LEFT.x, LINE_POS_LEFT.y,
                LINE_TEXTURE.getWidth() *2f, LINE_TEXTURE.getHeight());
        BOUNDS_RIGHT = new Rectangle(
                LINE_POS_RIGHT.x - (LINE_TEXTURE.getWidth() *2f),
                LINE_POS_RIGHT.y,
                LINE_TEXTURE.getWidth() *2f, LINE_TEXTURE.getHeight());
    }

    public void repoRedLines(float y) {
        LINE_POS_LEFT.set(LINE_POS_LEFT.x, y, 0);
        LINE_POS_RIGHT.set(LINE_POS_RIGHT.x, y, 0);
    }


//        BOUNDS_LEFT.set(LINE_POS_LEFT.x, LINE_POS_LEFT.y,
//                LINE_TEXTURE.getWidth() *2f, LINE_TEXTURE.getHeight());
//        BOUNDS_RIGHT.set(LINE_POS_RIGHT.x - (LINE_TEXTURE.getWidth() *2f),
//                LINE_POS_RIGHT.y,
//                LINE_TEXTURE.getWidth() *2f, LINE_TEXTURE.getHeight());

//        BOUNDS_LEFT.setPosition(lineLeftArr.getLINE_POS_LEFT().x,
//                lineLeftArr.getLINE_POS_LEFT().y);
//        BOUNDS_RIGHT.setPosition(lineRightArr.getLINE_POS_RIGHT().x,
//                lineRightArr.getLINE_POS_RIGHT().y);



    public static void update(float delta) {
        //Reposition LEFT Line
        for (int i = 0; i < lineLeftArray.size; i++) {
            RedLine lineLeftArr = lineLeftArray.get(i);
            lineLeftArr.getLINE_POS_LEFT().add(0, SPEED_DOWN * delta, 0);

            if (gamecam.position.y - (gamecam.viewportHeight /2) >
                    lineLeftArr.getLINE_POS_LEFT().y) {
                lineLeftArr.repositionLeft(lineLeftArr.getLINE_POS_LEFT().y +
                        (LINE_SPACE_BETWEEN * LINE_LEFT_COUNT));
            }
        }
        //Reposition RIGHT Line
        for (int j = 0; j < lineRightArray.size; j++) {
            RedLine lineRightArr = lineRightArray.get(j);
            lineRightArr.getLINE_POS_RIGHT().add(0, SPEED_UP * delta, 0);

            if (lineRightArr.getLINE_POS_RIGHT().y >
                    gamecam.position.y + (gamecam.viewportHeight /2)) {
                lineRightArr.repositionRight(lineRightArr.getLINE_POS_RIGHT().y -
                        LINE_SPACE_BETWEEN * LINE_RIGHT_COUNT);
            }
        }
    }


    private void repositionLeft(float y){
        LINE_POS_LEFT.set(LINE_POS_LEFT.x , y, 0);
    }
    private void repositionRight(float y){
        LINE_POS_RIGHT.set(LINE_POS_RIGHT.x, y, 0);
    }

    public Texture getLINE_TEXTURE() { return LINE_TEXTURE; }
    public Vector3 getLINE_POS_LEFT() { return LINE_POS_LEFT; }
    public Vector3 getLINE_POS_RIGHT() { return LINE_POS_RIGHT; }

    public Rectangle getBOUNDS_LEFT() { return BOUNDS_LEFT; }
    public Rectangle getBOUNDS_RIGHT() { return BOUNDS_RIGHT; }

    public boolean collidesLineLeft(Rectangle player){
        return player.overlaps(BOUNDS_LEFT); }
    public boolean collidesLineRight(Rectangle player){
        return player.overlaps(BOUNDS_RIGHT); }

}
