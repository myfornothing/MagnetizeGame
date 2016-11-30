package com.fornothing.projects.magnetize.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.fornothing.projects.magnetize.MainClass;
import com.fornothing.projects.magnetize.entities.Lightning;
import com.fornothing.projects.magnetize.entities.Player;
import com.fornothing.projects.magnetize.entities.RedLine;
import com.fornothing.projects.magnetize.utils.CameraStyles;

import static com.fornothing.projects.magnetize.MainClass.batch;
import static com.fornothing.projects.magnetize.MainClass.gamecam;
import static com.fornothing.projects.magnetize.MainClass.handleInput;
import static com.fornothing.projects.magnetize.MainClass.shapeRen;
import static com.fornothing.projects.magnetize.MainClass.stage;
import static com.fornothing.projects.magnetize.MainClass.world;
import static com.fornothing.projects.magnetize.entities.Player.MOVEMENT;
import static com.fornothing.projects.magnetize.entities.Player.MOVEMENT_STOP;
import static com.fornothing.projects.magnetize.entities.RedLine.LINE_SPACE_BETWEEN;

public class GameScreen implements Screen {
    public static Player redBall;
    public Rectangle boundsWallLeft, boundsWallRight;
    public Vector2 target;

    public static Array<Lightning> lightningHArray;
    public static int LIGHTNING_HORIZ_COUNT = 5;

    public static Array<RedLine> lineLeftArray;
    public static Array<RedLine> lineRightArray;
    public static final int LINE_LEFT_COUNT = 2;
    public static final int LINE_RIGHT_COUNT = 2;

    public GameScreen(final MainClass main) {
        stage = new Stage(new FitViewport(MainClass.V_WIDTH /2, MainClass.V_HEIGHT /2, gamecam));
        gamecam.setToOrtho(false, MainClass.V_WIDTH, MainClass.V_HEIGHT);
    }

    @Override
    public void show() {
        System.out.println("GameScreen show...");
        Gdx.input.setInputProcessor(stage);
        stage.clear();
        // Camera
        target = new Vector2(0, 0);
        // Player
        redBall = new Player(gamecam.viewportWidth * 0.5f,
                gamecam.viewportHeight * 0.15f);
        // LIGHTNING ARRAY
        lightningHArray = new Array<Lightning>();
        for (int i = 1; i <= LIGHTNING_HORIZ_COUNT; i++){
            lightningHArray.add(new Lightning(i *(Lightning.LIGHTNING_SPACE_BETWEEN)));
        }
        // LEFT REDLINE ARRAY
        lineLeftArray = new Array<RedLine>();
        for (int i = 1; i <= LINE_LEFT_COUNT; i++){
            lineLeftArray.add(new RedLine(i * (LINE_SPACE_BETWEEN)));
        }
        // RIGHT REDLINE ARRAY
        lineRightArray = new Array<RedLine>();
        for (int i = 1; i <= LINE_RIGHT_COUNT; i++){
            lineRightArray.add(new RedLine(i * (LINE_SPACE_BETWEEN)));
        }
        // Wall Bounds
        boundsWallRight = new Rectangle(
                gamecam.viewportWidth *0.989f,  //x
                gamecam.viewportHeight *0.02f,  //y
                4f, gamecam.viewportHeight *200f);
        boundsWallLeft = new Rectangle(
                1f,                            //x
                gamecam.viewportHeight *0.02f, //y
                4f, gamecam.viewportHeight *200f);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(gamecam.combined);
        shapeRen.setProjectionMatrix(gamecam.combined);
    // Wall Right
        shapeRen.begin(ShapeRenderer.ShapeType.Filled);
        shapeRen.setColor(Color.GREEN);
        shapeRen.rect(gamecam.viewportWidth *0.99f, //x
                gamecam.viewportHeight *0.02f,      //y
                4, gamecam.viewportHeight *200f);
        shapeRen.end();
    // Wall Left
        shapeRen.begin(ShapeRenderer.ShapeType.Filled);
        shapeRen.setColor(Color.GREEN);
        shapeRen.rect(0, //x
                gamecam.viewportHeight *0.02f,      //y
                4, gamecam.viewportHeight *200f);
        shapeRen.end();
        // Camera
        target = new Vector2(gamecam.viewportWidth /2,
                Player.getPositionBall().y + gamecam.viewportHeight *0.25f);

        batch.begin();
        batch.draw(Player.getTexture(), Player.getPositionBall().x,
                Player.getPositionBall().y);
        for (Lightning lightHA : lightningHArray){
            batch.draw(lightHA.getTextureHoriz(), lightHA.getPositionLightningHoriz().x,
                    lightHA.getPositionLightningHoriz().y);
        }
        for (RedLine lineLArray : lineLeftArray){
            batch.draw(lineLArray.getLINE_TEXTURE(), lineLArray.getLINE_POS_LEFT().x,
                    lineLArray.getLINE_POS_LEFT().y);
        }
        for (RedLine lineRArray : lineRightArray){
            batch.draw(lineRArray.getLINE_TEXTURE(), lineRArray.getLINE_POS_RIGHT().x,
                    lineRArray.getLINE_POS_RIGHT().y);
        }

        batch.end();

        stage.act(delta);
        stage.draw();
        update(delta);
        cameraUpdate(delta);

    }

    private void cameraUpdate(float delta){
        CameraStyles.lockOnTarget(gamecam, target);
    }

    private void update(float delta) {
        world.step(1f / MainClass.APP_FPS, 6, 2);
        batch.setProjectionMatrix(gamecam.combined);
        handleInput(delta);
        stage.act(delta);
        Player.update(delta);
        Lightning.update(delta);
        RedLine.update(delta);

        if ((RedLine.BOUNDS_LEFT).overlaps(Player.getBoundsBall()) &&
                Player.MOVEMENT < 0){
            MOVEMENT = MOVEMENT_STOP;
        }
        if ((RedLine.BOUNDS_RIGHT.overlaps(Player.getBoundsBall()) &&
                Player.MOVEMENT > 0)){
            MOVEMENT = MOVEMENT_STOP;
        }

        // Wall & Line Collisions
        if ((boundsWallLeft.overlaps(Player.getBoundsBall()) && Player.MOVEMENT < 0)){
            MOVEMENT = MOVEMENT_STOP;
        }
        if ((boundsWallRight.overlaps(Player.getBoundsBall()) && Player.MOVEMENT > 0)){
            MOVEMENT = MOVEMENT_STOP;
        }
    }

    public Array<RedLine> getLineLeftArray() { return lineLeftArray; }
    public Array<RedLine> getLineRightArray() { return lineRightArray; }

    @Override
    public void resize(int width, int height) {  }

    @Override
    public void pause() {  }

    @Override
    public void resume() {  }

    @Override
    public void hide() {  }

    @Override
    public void dispose() {
        System.out.println("GameScreen disposed...");
        shapeRen.dispose();
    }
}
