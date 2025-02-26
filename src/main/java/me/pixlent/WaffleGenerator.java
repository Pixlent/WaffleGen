package me.pixlent;

import com.github.fastnoise.FastNoise;
import me.pixlent.noise.FastNoiseBuilder;
import net.minestom.server.coordinate.Point;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.generator.GenerationUnit;
import net.minestom.server.instance.generator.Generator;
import org.jetbrains.annotations.NotNull;

public class WaffleGenerator implements Generator {
    private final FastNoise fastNoise;

    public WaffleGenerator() {
        fastNoise = FastNoiseBuilder
                        .fbm(FastNoiseBuilder.simplex().build())
                        .octaves(6)
                        .gain(0.6f)
                        .lacunarity(2.4f)
                        .weightedStrength(0.45f)
                        .build();
    }

    @Override
    public void generate(@NotNull GenerationUnit unit) {
        final Point start = unit.absoluteStart();
        final Point size = unit.size();
        for (int x = 0; x < size.blockX(); x++) {
            for (int z = 0; z < size.blockZ(); z++) {
                Point bottom = start.add(x, 0, z);
                float noiseX = (start.blockX() + x) * 0.001f;
                float noiseZ = (start.blockZ() + z) * 0.001f;
                float height = fastNoise.genSingle2D(noiseX, noiseZ, 0) * 96 + 64;

                unit.modifier().fill(bottom, bottom.add(1, 0, 1).withY(height), Block.STONE);
                unit.modifier().fill(bottom.withY(height), bottom.add(1, 0, 1).withY(63), Block.WATER);
            }
        }
    }
}
