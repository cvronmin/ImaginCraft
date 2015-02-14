package tk.cvrunmin.lanfasy.world;

import tk.cvrunmin.lanfasy.world.biome.LFBiomeBase;
import tk.cvrunmin.lanfasy.world.gen.ChunkProviderLF;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderLF extends WorldProvider {

    public void registerWorldChunkManager(){
        this.worldChunkMgr = new WorldChunkManagerHell(LFBiomeBase.MLextremeHills, 0.3f);
        this.isHellWorld = false;
        this.hasNoSky = false;
        this.dimensionId = 1010;
    }

    @SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float p_76562_1_, float p_76562_2_)
    {
        float f2 = MathHelper.cos(p_76562_1_ * (float)Math.PI * 2.0F) * 2.0F + 0.5F;
        f2 = MathHelper.clamp_float(f2, 0.0F, 1.0F);
        float f3 = 0.7529412F;
        float f4 = 0.84705883F;
        float f5 = 1.0F;
        f3 *= f2 * 0.94F + 0.06F;
        f4 *= f2 * 0.94F + 0.06F;
        f5 *= f2 * 0.91F + 0.09F;
        return new Vec3((double)f3, (double)f4, (double)f5);
    }

    public IChunkProvider createChunkGenerator(){
        return new ChunkProviderGenerate(this.worldObj, getSeed(), true, "");
    }

    public boolean isSurfaceWorld(){
        return true;
    }

    public boolean canCoordinateBeSpawn(int par1, int par2){
        return false;
    }

    public boolean canRespawnHere(){
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int par1, int par2){
        return false;
    }

    public String getDimensionName(){
        return "Fantasy World";
    }

    @Override 
    protected void generateLightBrightnessTable() {
/*	    	  float f = 0.5F;
  	  for(int i = 0; i <= 15; ++i) {
  	  float f1 = 1.0F - (float) i / 15.0F;this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
    }*/
        float f = 0.0F;

        for (int i = 0; i <= 15; ++i)
        {
            float f1 = 1.0F - (float)i / 15.0F;
            this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
        }
    }

	@Override
	public String getInternalNameSuffix() {
		return "_lfdim";
	}

}
