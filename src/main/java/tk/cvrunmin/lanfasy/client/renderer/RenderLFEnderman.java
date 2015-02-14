package tk.cvrunmin.lanfasy.client.renderer;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cvrunmin.lanfasy.Lanfasy;
import tk.cvrunmin.lanfasy.client.model.ModelLFEnderman;
import tk.cvrunmin.lanfasy.client.renderer.layers.LayerLFEndermanEyes;
import tk.cvrunmin.lanfasy.client.renderer.layers.LayerLFHeldBlock;
import tk.cvrunmin.lanfasy.entity.LFEntityEnderman;

@SideOnly(Side.CLIENT)
public class RenderLFEnderman extends RenderLiving
{
    private static final ResourceLocation endermanTextures = new ResourceLocation(Lanfasy.MODID, "textures/entity/enderman/enderman.png");
    /** The model of the enderman */
    private ModelLFEnderman endermanModel;
    private Random rnd = new Random();

    public RenderLFEnderman(RenderManager renderManager)
    {
        super(renderManager, new ModelLFEnderman(0.0F), 0.5F);
        this.endermanModel = (ModelLFEnderman)super.mainModel;
        this.addLayer(new LayerLFEndermanEyes(this));
        this.addLayer(new LayerLFHeldBlock(this));
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(LFEntityEnderman entity, double x, double y, double z, float p_76986_8_, float partialTicks)
    {
        this.endermanModel.isCarrying = entity.func_175489_ck().getBlock().getMaterial() != Material.air;
        this.endermanModel.isAttacking = entity.isScreaming();

        if (entity.isScreaming())
        {
            double d3 = 0.02D;
            x += this.rnd.nextGaussian() * d3;
            z += this.rnd.nextGaussian() * d3;
        }

        super.doRender((EntityLiving)entity, x, y, z, p_76986_8_, partialTicks);
    }
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(LFEntityEnderman par1EntityEnderman)
    {
        return endermanTextures;
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.doRender((LFEntityEnderman)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
    {
        return this.shouldRenderPass((LFEntityEnderman)par1EntityLivingBase, par2, par3);
    }

    protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.renderEquippedItems((LFEntityEnderman)par1EntityLivingBase, par2);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(EntityLivingBase par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.doRender((LFEntityEnderman)par1Entity, par2, par4, par6, par8, par9);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getEntityTexture((LFEntityEnderman)par1Entity);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.doRender((LFEntityEnderman)par1Entity, par2, par4, par6, par8, par9);
    }
}