package com.fornothing.projects.magnetize.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.fornothing.projects.magnetize.utils.Animation;

public class Player {
    public static float SPEED_UP = 80;
    public static float MOVEMENT = 800;
    public static float MOVEMENT_GO = 800;
    public static final float MOVEMENT_STOP = 0;
    private static Vector3 position;
    private static Rectangle boundsBall;
    private static Texture ballTexture;
    private static Animation ballAnimation;

    public Player(float x, float y){
        position = new Vector3(x, y, 0);
        ballTexture = new Texture("animations/RedOrb.png");
        ballAnimation = new Animation(new TextureRegion(ballTexture), 2, 1f);
        boundsBall = new Rectangle(x, y, ballTexture.getWidth() *0.5f,
                ballTexture.getHeight() *0.5f);
    }

    public static void update(float delta){
        ballAnimation.update(delta);
        position.add(MOVEMENT * delta, SPEED_UP * delta, 0);
        boundsBall.setPosition(position.x, position.y);
    }

    public static Vector3 getPositionBall() { return position; }
    public static TextureRegion getTexture() {
        return ballAnimation.getFrame();
    }
    public static Texture getBallTexture() { return ballTexture; }
    public static Rectangle getBoundsBall() { return boundsBall; }

}
