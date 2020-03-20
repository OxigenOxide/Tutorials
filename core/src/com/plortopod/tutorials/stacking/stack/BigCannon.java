package com.plortopod.tutorials.stacking.stack;

import com.badlogic.gdx.math.Vector2;

public class BigCannon extends Stack {
    public BigCannon(int index) {
        super(
                index,
                new Layer[]{
                        new Layer("bigCannon_base",0),
                        new Layer("bigCannon_gun",22.5f,new Vector2(13.5f, 13.5f)),
                        new Layer("bigCannon_shine",20),
                        new Layer("bigCannon_trunkShine",23.5f, new Vector2(-12.5f,1.5f))
                },
                36
        );
        setShadowDisposition(10);
    }
}