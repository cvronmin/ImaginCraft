package tk.cvrunmin.lanfasy.client.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cvrunmin.lanfasy.Lanfasy;
import tk.cvrunmin.lanfasy.client.renderer.layers.LayerLFSlimeGel;
import tk.cvrunmin.lanfasy.entity.LFEntitySlime;

@SideOnly(Side.CLIENT)
public class RenderLFSlime extends RenderLiving
{
    private static final ResourceLocation slimeTextures = new ResourceLocation(Lanfasy.MODID, "textures/entity/slime/slime.png");

    public RenderLFSlime(ModelBase par2ModelBase, float par3)
    {
        super(Minecraft.getMinecraft().getRenderManager(), par2ModelBase, par3);
        this.addLayer(new LayerLFSlimeGel(this));
    }

    public void doRender(LFEntitySlime p_177124_1_, double p_177124_2_, double p_177124_4_, double p_177124_6_, float p_177124_8_, float p_177124_9_)
    {
        this.shadowSize = 0.25F * (float)p_177124_1_.getSlimeSize();
        super.doRender((EntityLiving)p_177124_1_, p_177124_2_, p_177124_4_, p_177124_6_, p_177124_8_, p_177124_9_);
    }

    protected void preRenderCallback(LFEntitySlime p_77041_1_, float p_77041_2_)
    {
        float f1 = (float)p_77041_1_.getSlimeSize();
        float f2 = (p_77041_1_.prevSquishFactor + (p_77041_1_.squishFactor - p_77041_1_.prevSquishFactor) * p_77041_2_) / (f1 * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        GlStateManager.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
    }
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(LFEntitySlime par1EntitySlime)
    {
        return slimeTextures;
    }

    public void doRender(EntityLiving entity, double x, double y, double z, float p_76986_8_, float partialTicks)
    {
        this.doRender((LFEntitySlime)entity, x, y, z, p_76986_8_, partialTicks);
    }

    protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_)
    {
        this.preRenderCallback((LFEntitySlime)p_77041_1_, p_77041_2_);
    }

    public void doRender(EntityLivingBase entity, double x, double y, double z, float p_76986_8_, float partialTicks)
    {
        this.doRender((LFEntitySlime)entity, x, y, z, p_76986_8_, partialTicks);
    }

    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getEntityTexture((LFEntitySlime)entity);
    }

    public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks)
    {
        this.doRender((LFEntitySlime)entity, x, y, z, p_76986_8_, partialTicks);
    }
}