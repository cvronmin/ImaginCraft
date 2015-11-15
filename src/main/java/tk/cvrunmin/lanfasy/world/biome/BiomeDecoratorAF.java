package tk.cvrunmin.lanfasy.world.biome;

import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenSpikes;
import net.minecraft.world.gen.feature.WorldGenerator;
import tk.cvrunmin.lanfasy.init.LFBlocks;

public class BiomeDecoratorAF extends BiomeDecorator
{
    public BiomeDecoratorAF()
    {
    }

    protected void genDecorations(BiomeGenBase p_150513_1_)
    {
        this.generateOres();

/*        if (this.field_180294_c.getX() == 0 && this.field_180294_c.getZ() == 0)
        {
           EntityDragon entitydragon = new EntityDragon(this.currentWorld);
            entitydragon.setLocationAndAngles(0.0D, 128.0D, 0.0D, this.randomGenerator.nextFloat() * 360.0F, 0.0F);
            this.currentWorld.spawnEntityInWorld(entitydragon);
        }*/
    }
}