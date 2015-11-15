package tk.cvrunmin.lanfasy.client.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cvrunmin.lanfasy.Lanfasy;
import tk.cvrunmin.lanfasy.client.model.ModelLFGhast;
import tk.cvrunmin.lanfasy.entity.LFEntityGhast;

@SideOnly(Side.CLIENT)
public class RenderLFGhast extends RenderLiving
{
    private static final ResourceLocation ghastTextures = new ResourceLocation(Lanfasy.MODID, "textures/entity/ghast/ghast.png");
    private static final ResourceLocation ghastShootingTextures = new ResourceLocation(Lanfasy.MODID, "textures/entity/ghast/ghast_shooting.png");

    public RenderLFGhast()
    {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelLFGhast(), 0.5F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(LFEntityGhast par1EntityGhast)
    {
        return par1EntityGhast.func_110182_bF() ? ghastTextures : ghastShootingTextures;
    }

    protected void preRenderCallback(LFEntityGhast p_77041_1_, float p_77041_2_)
    {
        float f1 = 1.0F;
        float f2 = (8.0F + f1) / 2.0F;
        float f3 = (8.0F + 1.0F / f1) / 2.0F;
        GlStateManager.scale(f3, f2, f3);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.preRenderCallback((LFEntityGhast)par1EntityLivingBase, par2);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getEntityTexture((LFEntityGhast)par1Entity);
    }
}