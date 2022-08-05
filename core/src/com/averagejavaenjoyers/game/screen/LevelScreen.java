package com.averagejavaenjoyers.game.screen;

import com.averagejavaenjoyers.game.GameLogic;
import com.averagejavaenjoyers.game.ShadowsOfTheCrystalLandGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.averagejavaenjoyers.game.ShadowsOfTheCrystalLandGame;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.averagejavaenjoyers.game.ShadowsOfTheCrystalLandGame.SCREEN_HEIGHT;
import static com.averagejavaenjoyers.game.ShadowsOfTheCrystalLandGame.SCREEN_WIDTH;

public class LevelScreen extends CustomScreen {
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr; //reprezentacja obiektow w swiecie



    public LevelScreen(){
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT, gameCam);     //do skalowania ekranu

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("sandBox.tmx");    //ladowanie mapy zrobionej w tiled
        renderer = new OrthogonalTiledMapRenderer(map);    //pokazanie mapy
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);   //ustawienie pozycji kamery na srodek okna

        //w konstruktorze: grawitacja i uspienie
        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();

        //zmienne lokalne do dodanie atrybutow dla kolejnych obiektow (pipes, bricks itp)
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //przejscie petla po warstwach 0-n dol-gora
        //tworzenie rur
        for(int i = 1; i <= 6 ; i++) {
            for (MapObject object : map.getLayers().get(i).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();

                bdef.type = BodyDef.BodyType.StaticBody;    //obiekty nieruchome
                bdef.position.set((rect.getX() + rect.getWidth() / 2), (rect.getY() + rect.getHeight() / 2));
                body = world.createBody(bdef);  //tworzenie ciala o konkretnych atrybutach

                shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
                fdef.shape = shape;
                body.createFixture(fdef);
            }
        }
    }


    @Override
    public void show() {

    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 1, 0, 1);

        world.step(1/60f, 6, 2);    //odswierzanie swiata
        //gameCam.position.y = player.b2body.getPosition().y;

        gameCam.update();
        renderer.setView(gameCam);

        renderer.render();  //renderowanie mapy
        b2dr.render(world, gameCam.combined);   //renderowanie obiektow na swiecie
    }
    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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
