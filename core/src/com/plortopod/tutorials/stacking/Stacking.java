package com.plortopod.tutorials.stacking;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.plortopod.tutorials.Main;
import com.plortopod.tutorials.Res;
import com.plortopod.tutorials.Scene;
import com.plortopod.tutorials.stacking.stack.Bean;
import com.plortopod.tutorials.stacking.stack.BigBean;
import com.plortopod.tutorials.stacking.stack.BigCannon;
import com.plortopod.tutorials.stacking.stack.BigCannon2;
import com.plortopod.tutorials.stacking.stack.Cannon;
import com.plortopod.tutorials.stacking.stack.Stack;
import com.plortopod.tutorials.stacking.stack.Tree;

import java.util.ArrayList;

public class Stacking extends Scene {
    public Stack[] stacks;
    FrameBuffer buffer;
    Texture tex_buffer;
    Texture tex_shadow;
    Texture tex_arrow;
    int index_currentStack;

    boolean doPixelate;

    Vector2 pos_dest_cam;
    Vector2 pos_cam;

    public static final int WIDTH = 16 * 20, HEIGHT = 9 * 20;
    public static final Vector2 screenDim = new Vector2(WIDTH, HEIGHT);
    private static final Color COLOR_BACKGROUND = new Color(205 / 255f, 220 / 255f, 250 / 255f, 1);
    private static final float MARGINS = .15f;

    public Stacking() {
        buffer = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getHeight() * 2, Gdx.graphics.getHeight() * 2, true);
        Main.cam.setToOrtho(false, WIDTH, HEIGHT);

        stacks = new Stack[]{new Cannon(0), new Bean(1), new BigBean(2), new BigCannon(3), new BigCannon2(4), new Tree(5)};

        pos_dest_cam = new Vector2(WIDTH / 2, HEIGHT / 2);
        pos_cam = new Vector2(pos_dest_cam);

        tex_arrow = new Texture("stacking/arrow.png");

        focusCamOnStack(stacks[0]);
    }

    @Override
    public void update() {
        for (Stack stack : stacks) stack.update();

        if (Gdx.input.isKeyJustPressed(Input.Keys.D))
            scrollStacks(1);
        if (Gdx.input.isKeyJustPressed(Input.Keys.A))
            scrollStacks(-1);
        if (Gdx.input.isKeyJustPressed(Input.Keys.O))
            Main.cam.position.add(1, 0, 0);
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            stacks[index_currentStack].toggleRotate();
        if (Gdx.input.isKeyJustPressed(Input.Keys.S))
            doPixelate = !doPixelate;

        pos_cam.lerp(pos_dest_cam, .1f);
    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer sr) {
        Main.cam.position.set(pos_cam.x, pos_cam.y, 0);
        Main.cam.update();
        batch.setProjectionMatrix(Main.cam.combined);

        tex_shadow = Stack.createShadow(batch, stacks);

        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        buffer.begin();

        Gdx.gl.glClearColor(COLOR_BACKGROUND.r, COLOR_BACKGROUND.g, COLOR_BACKGROUND.b, COLOR_BACKGROUND.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        batch.setShader(Res.shader_c);
        Res.shader_c.setUniformf("c", 0, 0, 0, .1f);
        batch.draw(tex_shadow, pos_cam.x - WIDTH / 2, HEIGHT, WIDTH, -HEIGHT);
        batch.setShader(null);

        for (Stack stack : stacks)
            stack.render(batch);

        batch.end();

        buffer.end();

        tex_buffer = buffer.getColorBufferTexture();
        tex_buffer.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        Main.cam.position.set(WIDTH / 2, HEIGHT / 2, 0);
        Main.cam.update();
        batch.setProjectionMatrix(Main.cam.combined);

        batch.begin();
        if (doPixelate)
            batch.setShader(Res.shader_pixelate);
        Res.shader_pixelate.setUniformf("texDim", screenDim);

        batch.draw(tex_buffer, 0, HEIGHT, WIDTH, -HEIGHT);
        batch.end();
        batch.setShader(null);

        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.BLACK);
        sr.rect(0, 0, WIDTH, HEIGHT * MARGINS);
        sr.rect(0, HEIGHT * (1 - MARGINS), WIDTH, HEIGHT * MARGINS);
        sr.end();

        batch.begin();
        batch.draw(tex_arrow, WIDTH/2-5, 12);
        batch.end();
    }

    void scrollStacks(int dir) {
        index_currentStack = MathUtils.clamp(index_currentStack + dir, 0, stacks.length - 1);
        focusCamOnStack(stacks[index_currentStack]);
    }

    void focusCamOnStack(Stack stack) {
        pos_dest_cam.set((int) stack.getPos().x, HEIGHT / 2);
    }

    @Override
    public void dispose() {

    }
}
