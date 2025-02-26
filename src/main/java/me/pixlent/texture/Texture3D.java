package me.pixlent.texture;

import me.pixlent.Domain;

public class Texture3D implements Texture {
    public final float[][][] data;

    Texture3D(Domain domain) {
        data = new float[domain.size().x()][domain.size().y()][domain.size().z()];
    }
}
