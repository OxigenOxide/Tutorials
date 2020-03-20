package com.plortopod.tutorials.stacking.stack;

import com.badlogic.gdx.math.Vector2;

public class Cannon extends Stack {
    public Cannon(int index) {
        super(
                index,
                new Layer[]{
                        new Layer("cannon_base",0),
                        new Layer("cannon_gun",10.5f,new Vector2(6.5f, 6.5f)),
                        new Layer("cannon_shine",10)
                },
                17
        );
        setShadowDisposition(5);
    }
}
