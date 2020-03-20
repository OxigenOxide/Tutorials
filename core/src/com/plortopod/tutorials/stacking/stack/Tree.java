package com.plortopod.tutorials.stacking.stack;

import com.badlogic.gdx.math.Vector2;

public class Tree extends Stack {
    public Tree(int index) {
        super(
                index,
                new Layer[]{
                        new Layer("tree_trunk",0),
                        new Layer("tree_leaves_0",19).setRotateCenter(),
                        new Layer("tree_leaves_1",22).setRotateCenter(),
                        new Layer("tree_leaves_2",25).setRotateCenter()
                },
                35
        );
        setShadowDisposition(2);
    }
}