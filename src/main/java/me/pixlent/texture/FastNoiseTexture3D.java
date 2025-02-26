package me.pixlent.texture;

import com.github.fastnoise.FastNoise;
import com.github.fastnoise.FloatArray;
import me.pixlent.Domain;

import java.util.function.UnaryOperator;

public class FastNoiseTexture3D extends Texture3D {
    private final Builder builder;

    private FastNoiseTexture3D(Builder builder) {
        super(builder.domain);
        this.builder = builder;

        if (builder.spacing > 1) {
            // Generate sampled noise points
            int gridWidth = (int) Math.ceil((double) builder.domain.size().x() / builder.spacing) + 1;
            int gridHeight = (int) Math.ceil((double) builder.domain.size().y() / builder.spacing) + 1;
            int gridDepth = (int) Math.ceil((double) builder.domain.size().z() / builder.spacing) + 1;
            float[][][] noiseGrid = new float[gridWidth][gridHeight][gridDepth];

            generateInterpolatedNoise(noiseGrid, gridWidth, gridHeight, gridDepth, builder.spacing);
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

        public FastNoiseTexture3D build() {
            return new FastNoiseTexture3D(this);
        }
    }

    private void generateUniformNoise() {
        int totalSize = builder.domain.size().x() * builder.domain.size().y() * builder.domain.size().z();
        FloatArray noiseOut = new FloatArray(totalSize);

        // Generate noise values
        builder.fastNoise.genUniformGrid3D(noiseOut, builder.domain.min().x(), builder.domain.min().y(), builder.domain.min().z(),
                builder.domain.size().x(), builder.domain.size().y(), builder.domain.size().z(), builder.frequency, builder.seed);

        // Store noise values in the voxel data
        int index = 0;
        for (int x = 0; x < builder.domain.size().x(); x++) {
            for (int y = 0; y < builder.domain.size().y(); y++) {
                for (int z = 0; z < builder.domain.size().z(); z++) {
                    data[x][y][z] = noiseOut.get(index++);
                }
            }
        }
    }

    private void generateInterpolatedNoise(float[][][] noiseGrid, int gridWidth, int gridHeight, int gridDepth, int spacingFactor) {
        int totalSize = gridWidth * gridHeight * gridDepth;
        FloatArray noiseOut = new FloatArray(totalSize);
        FloatArray xArray = new FloatArray(totalSize);
        FloatArray yArray = new FloatArray(totalSize);
        FloatArray zArray = new FloatArray(totalSize);

        int index = 0;
        for (int x = 0; x < gridWidth; x++) {
            for (int y = 0; y < gridHeight; y++) {
                for (int z = 0; z < gridDepth; z++) {
                    xArray.set(index, (builder.domain.min().x() + x * spacingFactor) * builder.frequency);
                    yArray.set(index, (builder.domain.min().y() + y * spacingFactor) * builder.frequency);
                    zArray.set(index, (builder.domain.min().z() + z * spacingFactor) * builder.frequency);
                    index++;
                }
            }
        }

        // Generate noise
        builder.fastNoise.genPositionArray3D(noiseOut, xArray, yArray, zArray, 0f, 0f, 0f, builder.seed);

        // Store noise in the target grid
        index = 0;
        for (int x = 0; x < gridWidth; x++) {
            for (int y = 0; y < gridHeight; y++) {
                for (int z = 0; z < gridDepth; z++) {
                    noiseGrid[x][y][z] = noiseOut.get(index++);
                }
            }
        }
    }

    private void interpolate(float[][][] noiseGrid) {
        for (int x = 0; x < builder.domain.size().x(); x++) {
            float gx = (float) x / builder.spacing;
            int x0 = (int) Math.floor(gx);
            int x1 = Math.min(x0 + 1, noiseGrid.length - 1);
            float tx = gx - x0;

            for (int y = 0; y < builder.domain.size().y(); y++) {
                float gy = (float) y / builder.spacing;
                int y0 = (int) Math.floor(gy);
                int y1 = Math.min(y0 + 1, noiseGrid[0].length - 1);
                float ty = gy - y0;

                for (int z = 0; z < builder.domain.size().z(); z++) {
                    float gz = (float) z / builder.spacing;
                    int z0 = (int) Math.floor(gz);
                    int z1 = Math.min(z0 + 1, noiseGrid[0][0].length - 1);
                    float tz = gz - z0;

                    data[x][y][z] = trilinearInterpolation(noiseGrid, x0, y0, z0, x1, y1, z1, tx, ty, tz);
                }
            }
        }
    }

    private float trilinearInterpolation(float[][][] grid,
                                         int x0, int y0, int z0,
                                         int x1, int y1, int z1,
                                         float tx, float ty, float tz) {
        return (1 - tx) * (1 - ty) * (1 - tz) * grid[x0][y0][z0] +
                tx * (1 - ty) * (1 - tz) * grid[x1][y0][z0] +
                (1 - tx) * ty * (1 - tz) * grid[x0][y1][z0] +
                tx * ty * (1 - tz) * grid[x1][y1][z0] +
                (1 - tx) * (1 - ty) * tz * grid[x0][y0][z1] +
                tx * (1 - ty) * tz * grid[x1][y0][z1] +
                (1 - tx) * ty * tz * grid[x0][y1][z1] +
                tx * ty * tz * grid[x1][y1][z1];
    }

    public Float get(int x, int y, int z) {
        return data[x][y][z];
    }

    public float getSafe(int x, int y, int z) {
        if (x >= 0 && x < data.length &&
                y >= 0 && y < data[0].length &&
                z >= 0 && z < data[0][0].length) {
            return data[x][y][z];
        } else {
            return builder.fastNoise.genSingle3D(builder.domain.min().x() + x,
                    builder.domain.min().y() + y, builder.domain.min().z() + z, builder.seed);
        }
    }

    public void set(int x, int y, int z, Float value) {
        data[x][y][z] = value;
    }

    public void apply(UnaryOperator<Float> operator) {
        for (int x = 0; x < builder.domain.size().x(); x++) {
            for (int y = 0; y < builder.domain.size().y(); y++) {
                for (int z = 0; z < builder.domain.size().z(); z++) {
                    data[x][y][z] = operator.apply(data[x][y][z]);
                }
            }
        }
    }
}
