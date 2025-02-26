package me.pixlent.texture;

import me.pixlent.Domain;

public class Texture2D implements Texture {
    public final float[][] data;

    Texture2D(Domain domain) {
        data = new float[domain.size().x()][domain.size().z()];
    }
}
