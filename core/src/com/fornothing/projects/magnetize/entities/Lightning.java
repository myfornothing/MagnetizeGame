package com.fornothing.projects.magnetize.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.fornothing.projects.magnetize.utils.Animation;

import static com.fornothing.projects.magnetize.MainClass.gamecam;
import static com.fornothing.projects.magnetize.screens.GameScreen.LIGHTNING_HORIZ_COUNT;
import static com.fornothing.projects.magnetize.screens.GameScreen.lightningHArray;

public class Lightning {
    public static final int LIGHTNING_SPACE_BETWEEN = 300;
    private final Texture  lightningHorizTexture;
    private final Vector3  positionHoriz;
    private static Animation  lightningHorizAnim;

    public final Rectangle boundsLightningHoriz;

    public Lightning(float y){
        lightningHorizTexture = new Texture("animations/LightningHoriz.png");
        lightningHorizAnim = new Animation(new TextureRegion(lightningHorizTexture), 8, 0.5f);
        positionHoriz = new Vector3(-10, y + LIGHTNING_SPACE_BETWEEN, 0);

        boundsLightningHoriz = new Rectangle(-10, y,
                lightningHorizTexture.getWidth() *0.25f,
                lightningHorizTexture.getHeight() *0.25f);
    }

    private void reposition(float y){
        positionHoriz.set(-10, y, 0);
        boundsLightningHoriz.set(-10, y,
                lightningHorizTexture.getWidth() *0.25f,
                lightningHorizTexture.getHeight() *0.25f);
        }

    public static void update(float delta){
        lightningHorizAnim.update(delta);
        for (int i = 0; i < lightningHArray.size; i++){
            Lightning lightningArray = lightningHArray.get(i);
            if (gamecam.position.y - (gamecam.viewportHeight/2) >
                    lightningArray.getPositionLightningHoriz().y ){
                lightningArray.reposition(lightningArray.getPositionLightningHoriz().y +
                        ((LIGHTNING_SPACE_BETWEEN) * LIGHTNING_HORIZ_COUNT));
            }
        }
    }

    public Vector3 getPositionLightningHoriz() { return positionHoriz; }
    public TextureRegion getTextureHoriz() { return lightningHorizAnim.getFrame(); }
    public Rectangle getBoundsLightningHoriz() { return boundsLightningHoriz; }

}
