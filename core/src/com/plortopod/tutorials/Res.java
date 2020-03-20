package com.plortopod.tutorials;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class Res {
    public static ShaderProgram shader_pixelate = new ShaderProgram(Gdx.files.internal("shaders/shader_pixelate.vert"), Gdx.files.internal("shaders/shader_pixelate.frag"));
    public static ShaderProgram shader_c = new ShaderProgram(Gdx.files.internal("shaders/shader_c.vert"), Gdx.files.internal("shaders/shader_c.frag"));
    public static ShaderProgram shader_a = new ShaderProgram(Gdx.files.internal("shaders/shader_a.vert"), Gdx.files.internal("shaders/shader_a.frag"));
}
