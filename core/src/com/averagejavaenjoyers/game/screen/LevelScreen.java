package com.averagejavaenjoyers.game.screen;

import com.averagejavaenjoyers.game.Character;
import com.averagejavaenjoyers.game.GameLogic;
import com.averagejavaenjoyers.game.ShadowsOfTheCrystalLandGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

    private Character player;



    public LevelScreen(){
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT, gameCam);     //do skalowania ekranu

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Levels/SandBox/sandBox.tmx");    //ladowanie mapy zrobionej w tiled
        renderer = new OrthogonalTiledMapRenderer(map);    //pokazanie mapy
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);   //ustawienie pozycji kamery na srodek okna

        //w konstruktorze: grawitacja i uspienie
        world = new World(new Vector2(0, 0), true);
        b2dr = new Box2DDebugRenderer();

        player = new Character(this.world);

        //zmienne lokalne do dodanie atrybutow dla kolejnych warstw obiektow
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //wyciaganie warstw obiektów z Tiled kolejność: down=0, up=n
        for(int i = 1; i <= 5 ; i++) {
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

    public void handleInput(float dt){
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            // w konstruktorze 1. kierunek przemieszczenie, 2. przemieszczenie ekranu, 3. obudzenie obiektu
            player.b2body.applyLinearImpulse(new Vector2(0, 40f), player.b2body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            // w konstruktorze 1. kierunek przemieszczenie, 2. przemieszczenie ekranu, 3. obudzenie obiektu
            player.b2body.applyLinearImpulse(new Vector2(0, -40f), player.b2body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            player.b2body.applyLinearImpulse(new Vector2(4f,0), player.b2body.getWorldCenter(), true);
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            player.b2body.applyLinearImpulse(new Vector2(-4f,0), player.b2body.getWorldCenter(), true);
    }

    public void update(float dt){
        handleInput(dt);

        world.step(1/60f, 6, 2);    //odswierzanie swiata
        player.update(dt);
        //gameCam.position.x = player.b2body.getPosition().x;
        //gameCam.position.y = player.b2body.getPosition().y;

        gameCam.update();
        renderer.setView(gameCam);
    }

    @Override
    public void show() {

    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 1, 0, 1);
        update(delta);

        world.step(1/60f, 6, 2);    //odswierzanie swiata
        //gameCam.position.y = player.b2body.getPosition().y;

        gameCam.update();
        renderer.setView(gameCam);

        renderer.render();  //renderowanie mapy
        b2dr.render(world, gameCam.combined);   //renderowanie obiektow na swiecie

        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        //TODO: DO POPRAWKI
            game.batch.draw(player.texture, player.b2body.getPosition().x - player.getWidth() / 2, player.b2body.getPosition().y - player.getHeight() / 2);
        game.batch.end();
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
