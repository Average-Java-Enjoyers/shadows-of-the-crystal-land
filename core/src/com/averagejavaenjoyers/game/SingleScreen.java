package com.averagejavaenjoyers.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;

import static com.averagejavaenjoyers.game.ShadowsOfTheCrystalLandGame.SCREEN_HEIGHT;
import static com.averagejavaenjoyers.game.ShadowsOfTheCrystalLandGame.SCREEN_WIDTH;

public class SingleScreen implements Screen {

    final com.averagejavaenjoyers.game.ShadowsOfTheCrystalLandGame game;

    public OrthographicCamera camera;
    public Stage stage;

    public SingleScreen(final com.averagejavaenjoyers.game.ShadowsOfTheCrystalLandGame game){
        this.game = game;
        this.stage = new Stage();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        camera.update();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        this.resize(800, 480);
        ScreenUtils.clear(0.5f,0.66f,0.2f,1);
        camera.update();
        game.batch.begin();
        game.font.draw(game.batch, "New", SCREEN_WIDTH - SCREEN_WIDTH * 9/10,SCREEN_HEIGHT - game.font.getXHeight() * 1.5f);
        game.font.setColor(0.854f, 0.572f, 0.043f, 1);
        game.batch.end();
        camera.update();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
