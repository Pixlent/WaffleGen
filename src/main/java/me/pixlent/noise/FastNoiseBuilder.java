package me.pixlent.noise;

import com.github.fastnoise.FastNoise;

public class FastNoiseBuilder {
    private FastNoiseBuilder() {

    }

    public static WhiteBuilder white() {
        return new WhiteBuilder();
    }

    public static class WhiteBuilder {
        private final FastNoise fastNoise = new FastNoise("White");

        private WhiteBuilder() {
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static CheckerboardBuilder checkerboard() {
        return new CheckerboardBuilder();
    }

    public static class CheckerboardBuilder {
        private final FastNoise fastNoise = new FastNoise("Checkerboard");

        private CheckerboardBuilder() {
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static SimplexBuilder simplex() {
        return new SimplexBuilder();
    }

    public static class SimplexBuilder {
        private final FastNoise fastNoise = new FastNoise("Simplex");

        private SimplexBuilder() {
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static PerlinBuilder perlin() {
        return new PerlinBuilder();
    }

    public static class PerlinBuilder {
        private final FastNoise fastNoise = new FastNoise("Perlin");

        private PerlinBuilder() {
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static ValueBuilder value() {
        return new ValueBuilder();
    }

    public static class ValueBuilder {
        private final FastNoise fastNoise = new FastNoise("Value");

        private ValueBuilder() {
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static CellularBuilder cellular() {
        return new CellularBuilder();
    }

    public static class CellularBuilder {
        private final FastNoise fastNoise = new FastNoise("CellularValue");

        private CellularBuilder() {
        }

        public CellularBuilder jitter(float value) {
            fastNoise.set("JitterModifier", value);
            return this;
        }

        public CellularBuilder distanceFunction(String value) {
            fastNoise.set("DistanceFunction", value);
            return this;
        }

        public CellularBuilder index(int value) {
            fastNoise.set("ValueIndex", value);
            return this;
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static CellularDistanceBuilder cellularDistance() {
        return new CellularDistanceBuilder();
    }

    public static class CellularDistanceBuilder {
        private final FastNoise fastNoise = new FastNoise("CellularDistance");

        private CellularDistanceBuilder() {
        }

        public CellularDistanceBuilder jitter(float value) {
            fastNoise.set("JitterModifier", value);
            return this;
        }

        public CellularDistanceBuilder distanceFunction(String value) {
            fastNoise.set("DistanceFunction", value);
            return this;
        }

        public CellularDistanceBuilder index0(int value) {
            fastNoise.set("DistanceIndex0", value);
            return this;
        }

        public CellularDistanceBuilder index1(int value) {
            fastNoise.set("DistanceIndex1", value);
            return this;
        }

        public CellularDistanceBuilder returnType(String value) {
            fastNoise.set("ReturnType", value);
            return this;
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static CellularLookupBuilder cellularLookup(FastNoise lookup) {
        return new CellularLookupBuilder(lookup);
    }

    public static class CellularLookupBuilder {
        private final FastNoise fastNoise = new FastNoise("CellularLookup");

        private CellularLookupBuilder(FastNoise lookup) {
            fastNoise.set("Lookup", lookup);
        }

        public CellularLookupBuilder jitter(float value) {
            fastNoise.set("JitterModifier", value);
            return this;
        }

        public CellularLookupBuilder distanceFunction(String value) {
            fastNoise.set("DistanceFunction", value);
            return this;
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static DomainScaleBuilder domainScale(FastNoise source) {
        return new DomainScaleBuilder(source);
    }

    public static class DomainScaleBuilder {
        private final FastNoise fastNoise = new FastNoise("DomainScale");

        private DomainScaleBuilder(FastNoise source) {
            fastNoise.set("Source", source);
        }

        public DomainScaleBuilder scale(float scale) {
            fastNoise.set("Scale", scale);
            return this;
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static DomainOffsetBuilder offset(FastNoise source) {
        return new DomainOffsetBuilder(source);
    }

    public static class DomainOffsetBuilder {
        private final FastNoise fastNoise = new FastNoise("DomainOffset");

        private DomainOffsetBuilder(FastNoise source) {
            fastNoise.set("Source", source);
        }

        public DomainOffsetBuilder offsetX(float offset) {
            fastNoise.set("OffsetX", offset);
            return this;
        }

        public DomainOffsetBuilder offsetY(float offset) {
            fastNoise.set("OffsetY", offset);
            return this;
        }

        public DomainOffsetBuilder offsetZ(float offset) {
            fastNoise.set("OffsetZ", offset);
            return this;
        }

        public DomainOffsetBuilder offsetW(float offset) {
            fastNoise.set("OffsetW", offset);
            return this;
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static DomainRotateBuilder rotate(FastNoise source) {
        return new DomainRotateBuilder(source);
    }

    public static class DomainRotateBuilder {
        private final FastNoise fastNoise = new FastNoise("DomainRotate");

        private DomainRotateBuilder(FastNoise source) {
            fastNoise.set("Source", source);
        }

        public DomainRotateBuilder yaw(float yaw) {
            fastNoise.set("Yaw", yaw);
            return this;
        }

        public DomainRotateBuilder pitch(float pitch) {
            fastNoise.set("Pitch", pitch);
            return this;
        }

        public DomainRotateBuilder roll(float roll) {
            fastNoise.set("Roll", roll);
            return this;
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static DomainAxisScaleBuilder axisScale(FastNoise source) {
        return new DomainAxisScaleBuilder(source);
    }

    public static class DomainAxisScaleBuilder {
        private final FastNoise fastNoise = new FastNoise("DomainAxisScale");

        private DomainAxisScaleBuilder(FastNoise source) {
            fastNoise.set("Source", source);
        }

        public DomainAxisScaleBuilder scaleX(float x) {
            fastNoise.set("ScaleX", x);
            return this;
        }

        public DomainAxisScaleBuilder scaleY(float y) {
            fastNoise.set("ScaleY", y);
            return this;
        }

        public DomainAxisScaleBuilder scaleZ(float z) {
            fastNoise.set("ScaleZ", z);
            return this;
        }

        public DomainAxisScaleBuilder scaleW(float w) {
            fastNoise.set("ScaleW", w);
            return this;
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static AddDimensionBuilder addDimension(FastNoise source) {
        return new AddDimensionBuilder(source);
    }

    public static class AddDimensionBuilder {
        private final FastNoise fastNoise = new FastNoise("AddDimension");

        private AddDimensionBuilder(FastNoise source) {
            fastNoise.set("Source", source);
        }

        public AddDimensionBuilder newDimensionPosition(float position) {
            fastNoise.set("NewDimensionPosition", position);
            return this;
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static RemoveDimensionBuilder removeDimension(FastNoise source) {
        return new RemoveDimensionBuilder(source);
    }

    public static class RemoveDimensionBuilder {
        private final FastNoise fastNoise = new FastNoise("RemoveDimension");

        private RemoveDimensionBuilder(FastNoise source) {
            fastNoise.set("Source", source);
        }

        public RemoveDimensionBuilder dimension(String dimension) {
            fastNoise.set("RemoveDimension", dimension);
            return this;
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static SeedOffsetBuilder seedOffset(FastNoise source) {
        return new SeedOffsetBuilder(source);
    }

    public static class SeedOffsetBuilder {
            private final FastNoise fastNoise = new FastNoise("SeedOffset");

        private SeedOffsetBuilder(FastNoise source) {
            fastNoise.set("Source", source);
        }

        public SeedOffsetBuilder offset(int offset) {
            fastNoise.set("SeedOffset", offset);
            return this;
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static ConvertRGBA8Builder convertRGBA8(FastNoise source) {
        return new ConvertRGBA8Builder(source);
    }

    public static class ConvertRGBA8Builder {
        private final FastNoise fastNoise = new FastNoise("ConvertRGBA8");

        private ConvertRGBA8Builder(FastNoise source) {
            fastNoise.set("Source", source);
        }

        public ConvertRGBA8Builder min(float min) {
            fastNoise.set("Min", min);
            return this;
        }

        public ConvertRGBA8Builder max(float max) {
            fastNoise.set("Max", max);
            return this;
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static GeneratorCacheBuilder generatorCache(FastNoise source) {
        return new GeneratorCacheBuilder(source);
    }

    public static class GeneratorCacheBuilder {
        private final FastNoise fastNoise = new FastNoise("GeneratorCache");

        private GeneratorCacheBuilder(FastNoise source) {
            fastNoise.set("Source", source);
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static RemapBuilder remap(FastNoise source) {
        return new RemapBuilder(source);
    }

    public static class RemapBuilder {
        private final FastNoise fastNoise = new FastNoise("Remap");

        private RemapBuilder(FastNoise source) {
            fastNoise.set("Source", source);
        }

        public RemapBuilder fromMin(float from) {
            fastNoise.set("FromMin", from);
            return this;
        }

        public RemapBuilder fromMax(float from) {
            fastNoise.set("FromMax", from);
            return this;
        }

        public RemapBuilder toMin(float to) {
            fastNoise.set("ToMin", to);
            return this;
        }

        public RemapBuilder toMax(float to) {
            fastNoise.set("ToMax", to);
            return this;
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static TerraceBuilder terrace(FastNoise source) {
        return new TerraceBuilder(source);
    }

    public static class TerraceBuilder {
        private final FastNoise fastNoise = new FastNoise("Terrace");

        private TerraceBuilder(FastNoise source) {
            fastNoise.set("Source", source);
        }

        public TerraceBuilder multiplier(float multiplier) {
            fastNoise.set("Multiplier", multiplier);
            return this;
        }

        public TerraceBuilder smoothness(float smoothness) {
            fastNoise.set("Smoothness", smoothness);
            return this;
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static AddBuilder add(FastNoise source) {
        return new AddBuilder(source);
    }

    public static class AddBuilder {
        private final FastNoise fastNoise = new FastNoise("Add");

        private AddBuilder(FastNoise source) {
                fastNoise.set("LHS", source);
        }

        public AddBuilder value(float value) {
            fastNoise.set("RHS", value);
            return this;
        }

        public AddBuilder value(FastNoise value) {
            fastNoise.set("RHS", value);
            return this;
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static SubtractBuilder subtract(FastNoise source) {
        return new SubtractBuilder(source);
    }

    public static class SubtractBuilder {
        private final FastNoise fastNoise = new FastNoise("Subtract");

        private SubtractBuilder(FastNoise source) {
            fastNoise.set("LHS", source);
        }

        public SubtractBuilder value(float value) {
            fastNoise.set("RHS", value);
            return this;
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static MultiplyBuilder multiply(FastNoise source) {
        return new MultiplyBuilder(source);
    }

    public static class MultiplyBuilder {
        private final FastNoise fastNoise = new FastNoise("Multiply");

        private MultiplyBuilder(FastNoise source) {
            fastNoise.set("LHS", source);
        }

        public MultiplyBuilder value(float value) {
            fastNoise.set("RHS", value);
            return this;
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static DivideBuilder divide(FastNoise source) {
        return new DivideBuilder(source);
    }

    public static class DivideBuilder {
        private final FastNoise fastNoise = new FastNoise("Divide");

        private DivideBuilder(FastNoise source) {
            fastNoise.set("LHS", source);
        }

        public DivideBuilder value(float value) {
            fastNoise.set("RHS", value);
            return this;
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static MinBuilder min(FastNoise source) {
        return new MinBuilder(source);
    }

    public static class MinBuilder {
        private final FastNoise fastNoise = new FastNoise("Min");

        private MinBuilder(FastNoise source) {
            fastNoise.set("LHS", source);
        }

        public MinBuilder value(float value) {
            fastNoise.set("RHS", value);
            return this;
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static MaxBuilder max(FastNoise source) {
        return new MaxBuilder(source);
    }

    public static class MaxBuilder {
        private final FastNoise fastNoise = new FastNoise("Max");

        private MaxBuilder(FastNoise source) {
            fastNoise.set("LHS", source);
        }

        public MaxBuilder value(float value) {
            fastNoise.set("RHS", value);
            return this;
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static MinSmoothBuilder minSmooth(FastNoise source) {
        return new MinSmoothBuilder(source);
    }

    public static class MinSmoothBuilder {
        private final FastNoise fastNoise = new FastNoise("MinSmooth");

        private MinSmoothBuilder(FastNoise source) {
            fastNoise.set("LHS", source);
        }

        public MinSmoothBuilder value(float value) {
            fastNoise.set("RHS", value);
            return this;
        }

        public MinSmoothBuilder smoothness(float value) {
            fastNoise.set("Smoothness", value);
            return this;
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static MaxSmoothBuilder maxSmooth(FastNoise source) {
        return new MaxSmoothBuilder(source);
    }

    public static class MaxSmoothBuilder {
        private final FastNoise fastNoise = new FastNoise("MaxSmooth");

        private MaxSmoothBuilder(FastNoise source) {
            fastNoise.set("LHS", source);
        }

        public MaxSmoothBuilder value(float value) {
            fastNoise.set("RHS", value);
            return this;
        }

        public MaxSmoothBuilder smoothness(float value) {
            fastNoise.set("Smoothness", value);
            return this;
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static PowFloatBuilder powFloat() {
        return new PowFloatBuilder();
    }

    public static class PowFloatBuilder {
        private final FastNoise fastNoise = new FastNoise("PowFloat");

        private PowFloatBuilder() {
        }

        public PowFloatBuilder value(float value) {
            fastNoise.set("Value", value);
            return this;
        }

        public PowFloatBuilder pow(float value) {
            fastNoise.set("Pow", value);
            return this;
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static PowIntBuilder powInt(FastNoise source) {
        return new PowIntBuilder(source);
    }

    public static class PowIntBuilder {
        private final FastNoise fastNoise = new FastNoise("PowInt");

        private PowIntBuilder(FastNoise source) {
            fastNoise.set("Value", source);
        }

        public PowIntBuilder pow(int value) {
            fastNoise.set("Pow", value);
            return this;
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static FadeBuilder fade(FastNoise a, FastNoise b) {
        return new FadeBuilder(a, b);
    }

    public static class FadeBuilder {
        private final FastNoise fastNoise = new FastNoise("Fade");

        private FadeBuilder(FastNoise a, FastNoise b) {
            fastNoise.set("A", a);
            fastNoise.set("B", b);
        }

        public FadeBuilder fade(float value) {
            fastNoise.set("Fade", value);
            return this;
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static FBmBuilder fbm(FastNoise source) {
        return new FBmBuilder(source);
    }

    public static class FBmBuilder {
        private final FastNoise fastNoise = new FastNoise("FractalFBm");

        private FBmBuilder(FastNoise source) {
            fastNoise.set("Source", source);
        }

        public FBmBuilder octaves(int octaves) {
            fastNoise.set("Octaves", octaves);
            return this;
        }

        public FBmBuilder gain(float gain) {
            fastNoise.set("Gain", gain);
            return this;
        }

        public FBmBuilder lacunarity(float lacunarity) {
            fastNoise.set("Lacunarity", lacunarity);
            return this;
        }

        public FBmBuilder weightedStrength(float strength) {
            fastNoise.set("WeightedStrength", strength);
            return this;
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static FractalPingPongBuilder fractalPingPong(FastNoise source) {
        return new FractalPingPongBuilder(source);
    }

    public static class FractalPingPongBuilder {
        private final FastNoise fastNoise = new FastNoise("FractalPingPong");

        private FractalPingPongBuilder(FastNoise source) {
            fastNoise.set("Source", source);
        }

        public FractalPingPongBuilder octaves(int octaves) {
            fastNoise.set("Octaves", octaves);
            return this;
        }

        public FractalPingPongBuilder gain(float gain) {
            fastNoise.set("Gain", gain);
            return this;
        }

        public FractalPingPongBuilder lacunarity(float lacunarity) {
            fastNoise.set("Lacunarity", lacunarity);
            return this;
        }

        public FractalPingPongBuilder weightedStrength(float strength) {
            fastNoise.set("WeightedStrength", strength);
            return this;
        }

        public FractalPingPongBuilder pingPongStrength(float strength) {
            fastNoise.set("PingPongStrength", strength);
            return this;
        }

        public FastNoise build() {
            return fastNoise;
        }
    }

    public static FractalRidgedBuilder fractalRidged(FastNoise source) {
        return new FractalRidgedBuilder(source);
    }

    public static class FractalRidgedBuilder {
        private final FastNoise fastNoise = new FastNoise("FractalRidged");

        private FractalRidgedBuilder(FastNoise source) {
            fastNoise.set("Source", source);
        }

        public FractalRidgedBuilder octaves(int octaves) {
            fastNoise.set("Octaves", octaves);
            return this;
        }

        public FractalRidgedBuilder gain(float gain) {
            fastNoise.set("Gain", gain);
            return this;
        }

        public FractalRidgedBuilder lacunarity(float lacunarity) {
            fastNoise.set("Lacunarity", lacunarity);
            return this;
        }

        public FractalRidgedBuilder weightedStrength(float strength) {
            fastNoise.set("WeightedStrength", strength);
            return this;
        }

        public FastNoise build() {
            return fastNoise;
        }
    }
}
