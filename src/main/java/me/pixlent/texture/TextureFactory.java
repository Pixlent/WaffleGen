package me.pixlent.texture;

import me.pixlent.Domain;

@FunctionalInterface
public interface TextureFactory<T extends Texture> {
    T create(Domain domain);
}
