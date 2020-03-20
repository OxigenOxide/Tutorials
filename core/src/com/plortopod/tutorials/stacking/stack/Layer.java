package com.plortopod.tutorials.stacking.stack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Layer {

    Sprite sprite;
    Sprite sprite_shadow;
    float dy;
    Vector2 origin;
    boolean doRotate;
    Vector2 pos_parent;
    float ang;
    float dy_shadow;

    public Layer(String image, float dy) {
        this.dy = dy;
        sprite = new Sprite(new Texture("stacking/" + image + ".png"));
        sprite_shadow=new Sprite(sprite.getTexture());
        origin = new Vector2(sprite.getWidth() / 2, 0);
        sprite.setOrigin(origin.x, origin.y);
        sprite_shadow.setOrigin(origin.x, origin.y);
        sprite_shadow.setFlip(false,true);
    }

    public Layer(String image, float dy, Vector2 origin) {
        this.dy = dy;
        sprite = new Sprite(new Texture("stacking/" + image + ".png"));
        sprite_shadow=new Sprite(sprite.getTexture());
        sprite_shadow.setFlip(false,true);
        setRotate(origin);
    }

    public void setRotate(Vector2 origin) {
        doRotate = true;
        this.origin = origin;
        sprite.setOrigin(origin.x, origin.y);
        sprite_shadow.setOrigin(origin.x, origin.y);
    }

    public Layer setRotateCenter() {
        doRotate = true;
        origin.set(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setOrigin(origin.x, origin.y);
        sprite_shadow.setOrigin(origin.x, origin.y);
        return this;
    }

    public void setParentPos(Vector2 pos) {
        pos_parent = pos;
    }

    public void setAng(float ang) {
        if (doRotate)
            this.ang = ang;
    }

    public void update() {
        sprite.setPosition((int) (pos_parent.x - origin.x), (int) (pos_parent.y + dy - origin.y));
        sprite.setRotation((float) Math.toDegrees(ang));

        sprite_shadow.setPosition((int) (pos_parent.x - origin.x), (int) (pos_parent.y - sprite_shadow.getHeight() - dy + origin.y + dy_shadow));
        sprite_shadow.setRotation(sprite.getRotation());
    }


    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void renderShadow(SpriteBatch batch) {
        sprite_shadow.draw(batch);
    }
}
