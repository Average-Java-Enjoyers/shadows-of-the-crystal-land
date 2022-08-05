package com.averagejavaenjoyers.game.screen;

import com.averagejavaenjoyers.game.ShadowsOfTheCrystalLandGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.im.spi.InputMethod;
import java.util.ArrayList;
import java.util.List;

import static com.averagejavaenjoyers.game.ShadowsOfTheCrystalLandGame.SCREEN_HEIGHT;
import static com.averagejavaenjoyers.game.ShadowsOfTheCrystalLandGame.SCREEN_WIDTH;

public class StartMenuScreen extends CustomScreen {

    public Viewport viewport;
    public Stage stage;
    public Table table;
    public OrthographicCamera camera;

    private ImageTextButton single;
    private ImageTextButton multi;
    private ImageTextButton coop;
    private ImageTextButton authors;
    private ImageTextButton exit;
    private Label title;
    private Skin skin;
    private Window popup, exitPopup;

    public ImageTextButton createButton(String txt, Skin skn){
        ImageTextButton btn = new ImageTextButton(txt, skn);
        btn.getLabelCell().padLeft(btn.getLabel().getWidth() - btn.getWidth());
        return btn;
    }

    public Window createPopup(Skin skin){
        Window popup = new Window("", skin);
        popup.setWidth(400);
        popup.setHeight(200);
        popup.setPosition(400, SCREEN_HEIGHT / 2 - popup.getHeight());
        popup.setVisible(false);
        return popup;
    }

    public void renderButton(ImageTextButton btn, Window win, boolean flag){
        if(btn.isPressed() && !flag) {win.setVisible(!win.isVisible()); flag = true;}
        else if(!btn.isPressed()) flag = false;
    }

    public StartMenuScreen(){
        viewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT);
        stage = new Stage(viewport, game.batch);
        camera = new OrthographicCamera();

        table = new Table();
        table.setPosition(SCREEN_WIDTH * 4/5, SCREEN_HEIGHT - 200);

        skin = new Skin(Gdx.files.internal("Skins/StartMenu/StartMenuSkin.json"));

        title = new Label("Shadow Of The Crystal Lands", skin);
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/SteamwreckBold-e3Xp.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 100;
        fontParameter.borderColor.set(Color.BLACK);
        fontParameter.borderWidth = 5;
        BitmapFont titleFont = fontGenerator.generateFont(fontParameter);
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = titleFont;
        title.setStyle(labelStyle);
        title.setPosition(150, 750);
        stage.addActor(title);

        /*popup = new Window("", skin);
        exitPopup = new Window("", skin);
        popup.setWidth(400);
        popup.setHeight(200);
        popup.setPosition(400, Sotcl.HEIGHT / 2 - popup.getHeight());
        popup.setVisible(false);*/

        popup = createPopup(skin);
        exitPopup = createPopup(skin);


        single = createButton("Single", skin);
        multi = createButton( "Multi", skin);
        coop = createButton( "Coop", skin);
        authors = createButton("Authors", skin);
        exit = createButton("Exit", skin);
        /*single = new ImageTextButton("Single", skin);
        multi = new ImageTextButton("Multi", skin);
        coop = new ImageTextButton("Coop", skin);
        authors = new ImageTextButton("Authors", skin);
        exit = new ImageTextButton("exit", skin);
        single.getLabelCell().padLeft(single.getLabel().getWidth() - single.getWidth());
        multi.getLabelCell().padLeft(multi.getLabel().getWidth() - multi.getWidth());
        coop.getLabelCell().padLeft(coop.getLabel().getWidth() - coop.getWidth());
        authors.getLabelCell().padLeft(authors.getLabel().getWidth() - authors.getWidth());
        exit.getLabelCell().padLeft(exit.getLabel().getWidth() - exit.getWidth());*/
        //title = new Label("Shadows Of The Crystal Lands", buttonSkin);

        //table.add(title);
        table.padTop(400);
        table.add(single).expandX().padTop(40); table.row();
        table.add(multi).expandX().padTop(40); table.row();
        table.add(coop).expandX().padTop(40); table.row();
        table.add(authors).expandX().padTop(40); table.row();
        table.add(exit).expandX().padTop(40); table.row();
        //table.add(popup);
        stage.addActor(popup);
        stage.addActor(exitPopup);

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    public void detectTouchOnButton(ImageTextButton itBtn){
        Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPos);

        if (    touchPos.x >= itBtn.getX() &&
                touchPos.x <= itBtn.getX() + itBtn.getWidth() &&
                touchPos.y >= itBtn.getY() &&
                touchPos.y <= itBtn.getY() + itBtn.getHeight())
        {
            Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
            if (Gdx.input.isTouched())
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
        }
        else Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
    }

    @Override
    public void show() {

    }

    boolean fs = false, fe = false;
    @Override
    public void render(float delta) {

        stage.getBatch().begin();
        stage.getBatch().draw(new Texture(Gdx.files.internal("Images/Padia_pixeled.png")), 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage.getBatch().end();
        stage.draw();

        if(single.isPressed() && !fs) {game.setScreen(new LevelScreen()); fs = true;}
        else if(!single.isPressed()) fs = false;

        if(exit.isPressed() && !fe) {exitPopup.setVisible(!exitPopup.isVisible()); fe = true;}
        else if(!exit.isPressed()) fe = false;
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
