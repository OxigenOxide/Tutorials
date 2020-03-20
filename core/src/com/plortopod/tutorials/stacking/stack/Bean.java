package com.plortopod.tutorials.stacking.stack;

import com.badlogic.gdx.math.Vector2;

public class Bean extends Stack {
    public Bean(int index) {
        super(
                index,
                new Layer[]{
                        new Layer("bean", 6, new Vector2(13, 6)),
                        new Layer("bean_shine", 7.5f, new Vector2(8, 2.5f)),
                },
                12
        );

        setShadowDisposition(10);
    }
}
