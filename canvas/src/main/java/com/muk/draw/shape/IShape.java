package com.muk.draw.shape;

public interface IShape extends IDrawAble {

    boolean isValid();
    Coordinate[] coordinates();
    Coordinate coordinate(int i);
}
