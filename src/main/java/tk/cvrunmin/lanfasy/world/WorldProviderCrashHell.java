package tk.cvrunmin.lanfasy.world;

import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cvrunmin.lanfasy.world.biome.LFBiomeBase;
import tk.cvrunmin.lanfasy.world.gen.ChunkProviderCrashHell;

public class WorldProviderCrashHell extends WorldProvider{
    public void registerWorldChunkManager(){
        this.worldChunkMgr = new WorldChunkManagerHell(LFBiomeBase.LFhell, 0.0F);
        this.isHellWorld = true;
        this.hasNoSky = true;
        this.dimensionId = -1011;
    }

    @SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float p_76562_1_, float p_76562_2_)
    {
        return new Vec3(0.20000000298023224D, 0.029999999329447746D, 0.029999999329447746D);
    }

    public IChunkProvider createChunkGenerator(){
        return new ChunkProviderCrashHell(this.worldObj, true, this.worldObj.getSeed());
    }

    public boolean isSurfaceWorld(){
        return false;
    }

    public boolean canCoordinateBeSpawn(int par1, int par2){
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int par1, int par2)
    {
        return true;
    }

    public String getDimensionName(){
        return "Nthree";
    }
    public float calculateCelestialAngle(long par1, float par3)
    {
        return 0.5F;
    }
    @Override 
    protected void generateLightBrightnessTable()
    {
        float f = 0.1F;

        for (int i = 0; i <= 15; ++i)
        {
            float f1 = 1.0F - (float)i / 15.0F;
            this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
        }
    }
    public String getInternalNameSuffix()
    {
        return "_nthree";
    }

    public WorldBorder getWorldBorder()
    {
        return new WorldBorder()
        {
            public double getCenterX()
            {
                return super.getCenterX() / 8.0D;
            }
            public double getCenterZ()
            {
                return super.getCenterZ() / 8.0D;
            }
        };
    }
}
