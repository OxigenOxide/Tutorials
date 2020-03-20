package com.plortopod.tutorials.stacking.stack;

import com.badlogic.gdx.math.Vector2;

public class BigBean extends Stack{
    public BigBean(int index) {
        super(
                index,
                new Layer[]{
                        new Layer("bigBean", 11.5f).setRotateCenter(),
                        new Layer("bigBean_shine", 15).setRotateCenter(),
                },
                23
        );

        setShadowDisposition(18);
    }
}
