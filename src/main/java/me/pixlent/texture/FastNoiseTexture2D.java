package me.pixlent.texture;

import com.github.fastnoise.FastNoise;
import com.github.fastnoise.FloatArray;
import me.pixlent.Domain;

import java.util.function.UnaryOperator;

public class FastNoiseTexture2D extends Texture2D {
    private final Builder builder;

    private FastNoiseTexture2D(Builder builder) {
        super(builder.domain);
        this.builder = builder;

        if (builder.spacing > 1) {
            // Generate sampled noise points
            int gridWidth = (int) Math.ceil((double) builder.domain.size().x() / builder.spacing) + 1;
            int gridDepth = (int) Math.ceil((double) builder.domain.size().z() / builder.spacing) + 1;
            float[][] noiseGrid = new float[gridWidth][gridDepth];

            generateInterpolatedNoise(noiseGrid, gridWidth, gridDepth, builder.spacing);
            interpolate(noiseGrid);
        } else {
            generateUniformNoise();
        }
    }

    public static Builder builder(Domain domain, FastNoise fastNoise) {
        return new Builder(domain, fastNoise);
    }

    public static class Builder {
        private final Domain domain;
        private final FastNoise fastNoise;

        private int spacing = 1;
        private int seed = 0;
        private float frequency = 1.0f;

        private Builder(Domain domain, FastNoise fastNoise) {
            this.domain = domain;
            this.fastNoise = fastNoise;
        }

        public Builder interpolate(int spacing) {
            this.spacing = spacing;
            return this;
        }

        public Builder seed(int seed) {
            this.seed = seed;
            return this;
        }

        public Builder frequency(float frequency) {
            this.frequency = frequency;
            return this;
        }

        public FastNoiseTexture2D build() {
            return new FastNoiseTexture2D(this);
        }
    }

    private void generateUniformNoise() {
        int totalSize = builder.domain.size().x() * builder.domain.size().z();
        FloatArray noiseOut = new FloatArray(totalSize);

        // Generate noise values
        builder.fastNoise.genUniformGrid2D(noiseOut, builder.domain.min().x(), builder.domain.min().z(),
                builder.domain.size().x(), builder.domain.size().z(), builder.frequency, builder.seed);

        // Store noise values in the voxel data
        int index = 0;
        for (int x = 0; x < builder.domain.size().x(); x++) {
            for (int z = 0; z < builder.domain.size().z(); z++) {
                data[x][z] = noiseOut.get(index++);
            }
        }
    }

    private void generateInterpolatedNoise(float[][] noiseGrid, int gridWidth, int gridDepth, int spacingFactor) {
        int totalSize = gridWidth * gridDepth;
        FloatArray noiseOut = new FloatArray(totalSize);
        FloatArray xArray = new FloatArray(totalSize);
        FloatArray zArray = new FloatArray(totalSize);

        int index = 0;
        for (int x = 0; x < gridWidth; x++) {
            for (int z = 0; z < gridDepth; z++) {
                xArray.set(index, (builder.domain.min().x() + x * spacingFactor) * builder.frequency);
                zArray.set(index, (builder.domain.min().z() + z * spacingFactor) * builder.frequency);
                index++;
            }
        }

        // Generate noise
        builder.fastNoise.genPositionArray2D(noiseOut, xArray, zArray, 0f, 0f, builder.seed);

        // Store noise in the target grid
        index = 0;
        for (int x = 0; x < gridWidth; x++) {
            for (int z = 0; z < gridDepth; z++) {
                noiseGrid[x][z] = noiseOut.get(index++);
            }
        }
    }

    private void interpolate(float[][] noiseGrid) {
        for (int x = 0; x < builder.domain.size().x(); x++) {
            float gx = (float) x / builder.spacing;
            int x0 = (int) Math.floor(gx);
            int x1 = Math.min(x0 + 1, noiseGrid.length - 1);
            float tx = gx - x0;

            for (int z = 0; z < builder.domain.size().z(); z++) {
                float gz = (float) z / builder.spacing;
                int z0 = (int) Math.floor(gz);
                int z1 = Math.min(z0 + 1, noiseGrid[0].length - 1);
                float tz = gz - z0;

                data[x][z] = bilinearInterpolation(noiseGrid, x0, z0, x1, z1, tx, tz);
            }
        }
    }

    private float bilinearInterpolation(float[][] grid, int x0, int z0, int x1, int z1, float tx, float tz) {
        float c00 = grid[x0][z0];
        float c10 = grid[x1][z0];
        float c01 = grid[x0][z1];
        float c11 = grid[x1][z1];

        return (c00 * (1 - tx) + c10 * tx) * (1 - tz) +
                (c01 * (1 - tx) + c11 * tx) * tz;
    }

    public Float get(int x, int z) {
        return data[x][z];
    }

    public float getSafe(int x, int z) {
        if (x >= 0 && x < data.length &&
                z >= 0 && z < data[0].length) {
            return data[x][z];
        } else {
            return builder.fastNoise.genSingle2D(builder.domain.min().x() + x, builder.domain.min().z() + z, builder.seed); // Generate noise if out of bounds
        }
    }

    public void set(int x, int z, Float value) {
        data[x][z] = value;
    }

    public void apply(UnaryOperator<Float> operator) {
        for (int x = 0; x < builder.domain.size().x(); x++) {
            for (int z = 0; z < builder.domain.size().z(); z++) {
                data[x][z] = operator.apply(data[x][z]);
            }
        }
    }
}
