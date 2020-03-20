package com.plortopod.tutorials.stacking.stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Vector2;
import com.plortopod.tutorials.Main;
import com.plortopod.tutorials.Res;
import com.plortopod.tutorials.stacking.Stacking;

public class Stack {

    Vector2 pos;
    int index;
    Layer[] layers;
    float ang;

    boolean rotate;
    float height;
    float dy_shadow;
    int spaceBetweenStacks = 60;

    static FrameBuffer buffer_shadow;

    static float x = Stacking.WIDTH / 2f;

    static {
        buffer_shadow = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getHeight() * 2, Gdx.graphics.getHeight() * 2, true);
    }

    public Stack(int index, Layer[] layers) {
        this.index = index;
        this.layers = layers;
        construct();
    }

    public Stack(int index, Layer[] layers, float height) {
        this.index = index;
        this.layers = layers;
        this.height = height;
        construct();
    }

    void construct() {
        Layer broadestLayer = layers[0];
        for (int i = 1; i < layers.length; i++) {
            if (layers[i].sprite.getWidth() > broadestLayer.sprite.getWidth())
                broadestLayer = layers[i];
        }

        if (index != 0) x += spaceBetweenStacks + broadestLayer.sprite.getWidth() / 2;

        pos = new Vector2(x, Stacking.HEIGHT / 2f - height / 2);
        //pos = new Vector2(x, Stacking.HEIGHT / 2f);

        x += broadestLayer.sprite.getWidth() / 2;
    }

    public void update() {
        if (rotate)
            ang = (ang + Main.dt) % (2 * (float) Math.PI);

        for (Layer layer : layers) {
            layer.setParentPos(pos);
            layer.setAng(ang);
            layer.update();
        }
    }

    public void toggleRotate() {
        rotate = !rotate;
    }

    public static Texture createShadow(SpriteBatch batch, Stack[] stacks) {

        buffer_shadow.begin();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        for (Stack stack : stacks)
            stack.drawShadow(batch);

        batch.end();

        buffer_shadow.end();

        Texture tex = buffer_shadow.getColorBufferTexture();
        tex.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        return tex;
    }

    void setShadowDisposition(float dy) {
        dy_shadow = dy;
        for (Layer layer : layers)
            layer.dy_shadow = dy_shadow;
    }

    void drawShadow(SpriteBatch batch) {
        for (Layer layer : layers)
            layer.renderShadow(batch);
    }

    public void render(SpriteBatch batch) {
        for (Layer layer : layers) layer.render(batch);
    }

    public Vector2 getPos() {
        return pos;
    }
}
