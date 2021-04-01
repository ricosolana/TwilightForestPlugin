package com.crazicrafter1.tfplugin.boss;

import java.util.Objects;

public class Point {

    private int x, z;

    public Point(int x, int z) {
        this.x = x;
        this.z = z;
    }

    public Point(Integer[] loc) {
        this.x = loc[0];
        this.z = loc[1];
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    public Integer[] getLoc() {
        return new Integer[] {x, z};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                z == point.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, z);
    }
}
