package com.averagejavaenjoyers.game.screen;

import com.averagejavaenjoyers.game.SingleScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

import static com.averagejavaenjoyers.game.ShadowsOfTheCrystalLandGame.SCREEN_HEIGHT;
import static com.averagejavaenjoyers.game.ShadowsOfTheCrystalLandGame.SCREEN_WIDTH;

public class StartMenuScreen extends CustomScreen {
    OrthographicCamera camera;

    final int buttonNumber = 5;

    private ArrayList<Texture> buttonTextures;
    private ArrayList<Rectangle> buttonShapes;
    private String [] texts = {new String("Single"), new String("Multi"), new String("Coop"), new String("Authors"), new String("Close")};
    private BitmapFont buttonFont;


    FreeTypeFontGenerator fontGenerator;
    FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    public void createFonts(){
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Minecraft.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 40;
        fontParameter.color.set(1f, 1f, 1f, 1);
        buttonFont = fontGenerator.generateFont(fontParameter);
    }

    public void renderFonts(){

    }

    public void createButtons(){
        buttonTextures = new ArrayList<Texture>();
        buttonShapes = new ArrayList<Rectangle>();
        for(int i = 0; i < buttonNumber; i++){
            buttonTextures.add(new Texture(Gdx.files.internal("button.png")));
            buttonShapes.add(new Rectangle());
            buttonShapes.get(i).width = 200;
            buttonShapes.get(i).height = 100;
            buttonShapes.get(i).x = SCREEN_WIDTH - SCREEN_WIDTH/6 - buttonShapes.get(i).width/2;
            buttonShapes.get(i).y = SCREEN_HEIGHT - buttonShapes.get(i).height * 2f - i * buttonShapes.get(i).height * 1.5f;
        }
    }

    public void renderButtons(){
        for(int i = 0; i < buttonNumber; i++) {
            game.batch.draw(buttonTextures.get(i), buttonShapes.get(i).x, buttonShapes.get(i).y);
            GlyphLayout layout = new GlyphLayout(); //dont do this every frame! Store it as member
            layout.setText(buttonFont, texts[i]);
            float width = layout.width;// contains the width of the current set text
            float height = layout.height; // contains the height of the current set text

            buttonFont.draw(game.batch, texts[i],  buttonShapes.get(i).x - width/2 + buttonShapes.get(i).width/2, buttonShapes.get(i).y + buttonShapes.get(i).height - height);
        }

        //System.out.println(buttonFont.getBounds(String str).width);
        //System.out.println(buttonFont.getRegion().getRegionHeight());
    }

    public void detectTouchOnButton(){
        Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPos);

        if (    touchPos.x >= buttonShapes.get(0).getX() &&
                touchPos.x <= buttonShapes.get(0).getX() + buttonShapes.get(0).width &&
                touchPos.y >= buttonShapes.get(0).getY() &&
                touchPos.y <= buttonShapes.get(0).getY() + buttonShapes.get(0).height)
        {
            Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
            //fontParameter.color.set(0.854f, 0.572f, 0.043f, 1);
            buttonFont.setColor(0.854f, 0.572f, 0.043f, 1);
            //camera.update();
            if (Gdx.input.isTouched()) {
                //game.font.setColor(0.043f, 0.149f, 0.854f, 1);
                game.setScreen(new SingleScreen(game));
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }
        }
        else{
            Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            buttonFont.setColor(1f, 1f, 1f, 1);
        }

    }
    public StartMenuScreen(){
        createButtons();
        createFonts();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
        camera.update();
    }
    
    @Override
    public void show() {
    
    }
    
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0.2f,1);
        camera.update();
        game.batch.begin();
        game.batch.draw(new Texture(Gdx.files.internal("Padia_pixeled.png")), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.font.draw(game.batch, "Shadow Of The Crystal Land", SCREEN_WIDTH - SCREEN_WIDTH * 9/10,SCREEN_HEIGHT - game.font.getXHeight() * 1.5f);
        //game.font.setColor(0.854f, 0.572f, 0.043f, 1);
        //game.font.getData().setScale(2.5f);
        renderButtons();
        game.batch.end();
        camera.update();

        /*if(Gdx.input.isTouched()){
            //game.setScreen(new GameScreen(game));
            dispose();
        }*/

        detectTouchOnButton();
    }
}
