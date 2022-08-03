package com.averagejavaenjoyers.game.screen;

import com.averagejavaenjoyers.game.GameLogic;
import com.averagejavaenjoyers.game.ShadowsOfTheCrystalLandGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.averagejavaenjoyers.game.ShadowsOfTheCrystalLandGame;
import com.badlogic.gdx.utils.viewport.Viewport;

public class LevelScreen extends CustomScreen {
    private TmxMapLoader tmxMapLoader = new TmxMapLoader();
    private TiledMap map;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private GameLogic levelLogic;
    private Viewport gamePort;
    private OrthographicCamera gameCam;


    public LevelScreen(){
        tmxMapLoader = new TmxMapLoader();
        map = tmxMapLoader.load("map.tmx");
        orthogonalTiledMapRenderer = new OrthogonalTiledMapRenderer(map);    //pokazanie mapy
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);   //ustawienie pozycji kamery na srodek okna
    }


    @Override
    public void show() {

    }
    @Override
    public void render(float delta) {
        orthogonalTiledMapRenderer.render();
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
