package com.fornothing.projects.magnetize;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.fornothing.projects.magnetize.entities.Player;
import com.fornothing.projects.magnetize.screens.GameScreen;

import static com.fornothing.projects.magnetize.entities.Player.MOVEMENT;
import static com.fornothing.projects.magnetize.entities.Player.MOVEMENT_GO;

public class MainClass extends Game {
	public static String APP_TITLE = "Magnetize";
	public static double APP_VERSION = 0.1;
	public static int APP_DESKTOP_WIDTH = 1000;
	public static int APP_DESKTOP_HEIGHT = 1500;
	public static int APP_FPS = 60;
	// Game Variables
	public static int V_WIDTH = 420;
	public static int V_HEIGHT = 720;
	// Shared resources
	public static World world;
	public static Stage stage;
	public static OrthographicCamera gamecam;
	// Batches
	public static SpriteBatch batch;
	public static ShapeRenderer shapeRen;
	public static AssetManager assets;

    public GameScreen gameScreen;
	
	@Override
	public void create () {
		assets = new AssetManager();
		gamecam = new OrthographicCamera();
		gamecam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		batch = new SpriteBatch();
		shapeRen = new ShapeRenderer();
		world = new World(new Vector2(0f, 0f), true);

        //Screens
        gameScreen = new GameScreen(this);
        this.setScreen(gameScreen);

    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(.01f, .01f, .01f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();

		batch.begin();
		batch.end();
	}

    // Touch & Spacebar Input
    public static void handleInput(float delta){
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            //this.setScreen(gameScreen);
            Gdx.app.exit();
        }
        if((Gdx.input.justTouched()) || (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))) {
            if (MOVEMENT == 0 && Player.getPositionBall().x < gamecam.viewportWidth /2 ){
                MOVEMENT = MOVEMENT_GO;
            }else if (MOVEMENT == 0 && Player.getPositionBall().x > gamecam.viewportWidth /2){
                MOVEMENT = -MOVEMENT_GO;
            }
            Player.update(delta);
        }
    }
	
	@Override
	public void dispose () {
        super.dispose(); System.out.println("super dispose");
        batch.dispose(); System.out.println("batch dispose");
        shapeRen.dispose(); System.out.println("shape dispose");
        stage.dispose(); System.out.println("stage dispose");
        world.dispose(); System.out.println("world dispose");
        assets.dispose(); System.out.println("assets dispose");
	}
}
