package com.plortopod.tutorials;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.plortopod.tutorials.stacking.Stacking;

public class Main extends ApplicationAdapter {
    SpriteBatch batch;
    Scene currentScene;
    Scene[] scenes;
    ShapeRenderer sr;

    public static float test_float=1;
    public static OrthographicCamera cam;
    public static float dt;

    @Override
    public void create() {
        ShaderProgram.pedantic = false;

        batch = new SpriteBatch();
        sr = new ShapeRenderer();
        cam = new OrthographicCamera();

        scenes = new Scene[]{new Stacking()};
        currentScene = scenes[0];
    }

    @Override
    public void render() {
        dt = Gdx.graphics.getDeltaTime();

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT_BRACKET))
            test_float-=dt;
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT_BRACKET))
            test_float+=dt;

        currentScene.update();

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();
        batch.setProjectionMatrix(cam.combined);
        sr.setProjectionMatrix(Main.cam.combined);

        currentScene.render(batch, sr);
    }

    @Override
    public void dispose() {
        batch.dispose();
        sr.dispose();
        for (Scene scene : scenes)
            scene.dispose();
    }
}
