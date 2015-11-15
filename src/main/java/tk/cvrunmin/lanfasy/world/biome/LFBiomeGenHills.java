package tk.cvrunmin.lanfasy.world.biome;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import tk.cvrunmin.lanfasy.init.LFBlocks;


public class LFBiomeGenHills extends LFBiomeBase{
    private int field_150635_aE;
    private int field_150636_aF;
    private int field_150637_aG;
    private int field_150638_aH;

    public LFBiomeGenHills(int p_i45373_1_, boolean p_i45373_2_)
    {
        super(p_i45373_1_);
        this.field_150635_aE = 0;
        this.field_150636_aF = 1;
        this.field_150637_aG = 2;
        this.field_150638_aH = this.field_150635_aE;

        if (p_i45373_2_)
        {
            this.theBiomeDecorator.treesPerChunk = 3;
            this.field_150638_aH = this.field_150636_aF;
        }
    }
    public void decorate(World worldIn, Random p_180624_2_, BlockPos p_180624_3_)
    {
        super.decorate(worldIn, p_180624_2_, p_180624_3_);
    }

    public void genTerrainBlocks(World worldIn, Random p_180622_2_, ChunkPrimer p_180622_3_, int p_180622_4_, int p_180622_5_, double p_180622_6_)
    {
        this.topBlock = LFBlocks.textureless.getDefaultState();
        this.fillerBlock = LFBlocks.lightless.getDefaultState();

        if ((p_180622_6_ < -1.0D || p_180622_6_ > 2.0D) && this.field_150638_aH == this.field_150637_aG)
        {
            this.topBlock = LFBlocks.dremic.getDefaultState();
            this.fillerBlock = LFBlocks.textureless.getDefaultState();
        }
        else if (p_180622_6_ > 1.0D && this.field_150638_aH != this.field_150636_aF)
        {
            this.topBlock = LFBlocks.dremic.getDefaultState();
            this.fillerBlock = LFBlocks.lightless.getDefaultState();
        }

        this.generateBiomeTerrain(worldIn, p_180622_2_, p_180622_3_, p_180622_4_, p_180622_5_, p_180622_6_);
    }

    public LFBiomeGenHills mutateHills(BiomeGenBase p_150633_1_)
    {
        this.field_150638_aH = this.field_150637_aG;
        this.func_150557_a(p_150633_1_.color, true);
        this.setBiomeName(p_150633_1_.biomeName + " M");
        this.setHeight(new BiomeGenBase.Height(p_150633_1_.minHeight, p_150633_1_.maxHeight));
        this.setTemperatureRainfall(p_150633_1_.temperature, p_150633_1_.rainfall);
        return this;
    }

    public BiomeGenBase createMutatedBiome(int p_180277_1_)
    {
        return (new LFBiomeGenHills(p_180277_1_, false)).mutateHills(this);
    }
}
