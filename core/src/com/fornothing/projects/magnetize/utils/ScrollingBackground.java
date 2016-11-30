package com.fornothing.projects.magnetize.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fornothing.projects.magnetize.MainClass;

public final class ScrollingBackground {

    private static final int DEFAULT_SPEED = 80;
    private static final int ACCELERATION = 50;
    private static final int GOAL_REACH_ACCELERATION = 200;

    private Texture image;
    private float y1, y2;
    private int speed;//In pixels / second
    private int goalSpeed;
    private float imageScale;
    private boolean speedFixed;

    public ScrollingBackground () {
        image = new Texture("images/bluestars_background.png");

        y1 = 0;
        y2 = image.getHeight();
        speed = 0;
        goalSpeed = DEFAULT_SPEED;
        imageScale = MainClass.gamecam.viewportWidth / image.getWidth();
        speedFixed = true;
    }

    public void updateAndRender (float deltaTime, SpriteBatch batch) {
        //Speed adjustment to reach goal
        if (speed < goalSpeed) {
            speed += GOAL_REACH_ACCELERATION * deltaTime;
            if (speed > goalSpeed)
                speed = goalSpeed;
        } else if (speed > goalSpeed) {
            speed -= GOAL_REACH_ACCELERATION * deltaTime;
            if (speed < goalSpeed)
                speed = goalSpeed;
        }

        if (!speedFixed)
            speed += ACCELERATION * deltaTime;

        y1 -= speed * deltaTime;
        y2 -= speed * deltaTime;

        //if image reached the bottom of screen and is not visible, put it back on top
        if (y1 + image.getHeight() * imageScale <= 0)
            y1 = y2 + image.getHeight() * imageScale;

        if (y2 + image.getHeight() * imageScale <= 0)
            y2 = y1 + image.getHeight() * imageScale;

        //Render
        batch.draw(image, 0, y1, MainClass.gamecam.viewportWidth, image.getHeight() * imageScale);
        batch.draw(image, 0, y2, MainClass.gamecam.viewportWidth, image.getHeight() * imageScale);
    }

    public void setSpeed (int goalSpeed) {
        this.goalSpeed = goalSpeed;
    }

    public void setSpeedFixed (boolean speedFixed) {
        this.speedFixed = speedFixed;
    }





}
