package com.averagejavaenjoyers.game.screen;

import com.averagejavaenjoyers.game.ShadowsOfTheCrystalLandGame;
import com.badlogic.gdx.Screen;

public abstract class CustomScreen implements Screen {
    
    protected final ShadowsOfTheCrystalLandGame game = ShadowsOfTheCrystalLandGame.instance();
    
    @Override
    public abstract void show();
    
    @Override
    public abstract void render(float delta);
    
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
