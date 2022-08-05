package com.averagejavaenjoyers.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import org.w3c.dom.Text;

public class Character extends Sprite {
    Animation standAnimation;
    public World world;
    public Body b2body;
    public TextureRegion texture;

    public Character(World world){
        this.world = world;
        defineCharacter();
        texture =  new TextureRegion(new Texture(Gdx.files.internal("Graphics/Cedania/CedaniaLightWarrior50x75.png")));
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
    }

    public void defineCharacter(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(50,50);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        /*Image img = new Image(new Texture(Gdx.files.internal("Graphics/Cedania/CedaniaLightWarrior50x75.png")));
        img.setWidth(50);
        img.setHeight(75);*/
        CircleShape shape = new CircleShape();
        shape.setRadius(25);
        fdef.shape = shape;
        //shape
        b2body.createFixture(fdef);
    }
    
}
