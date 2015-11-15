package tk.cvrunmin.lanfasy.client.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cvrunmin.lanfasy.Lanfasy;
import tk.cvrunmin.lanfasy.client.model.ModelLFSkeleton;
import tk.cvrunmin.lanfasy.entity.LFEntitySkeleton;

@SideOnly(Side.CLIENT)
public class RenderLFSkeleton extends RenderBiped
{
    private static final ResourceLocation skeletonTextures = new ResourceLocation(Lanfasy.MODID, "textures/entity/skeleton/skeleton.png");
    private static final ResourceLocation witherSkeletonTextures = new ResourceLocation(Lanfasy.MODID, "textures/entity/skeleton/wither_skeleton.png");
    public RenderLFSkeleton()
    {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelLFSkeleton(), 0.5F);
        this.addLayer(new LayerHeldItem(this));
        this.addLayer(new LayerBipedArmor(this)
        {
            protected void func_177177_a()
            {
                this.field_177189_c = new ModelLFSkeleton(0.5F, true);
                this.field_177186_d = new ModelLFSkeleton(1.0F, true);
            }
        });
    }

    protected void preRenderCallback(LFEntitySkeleton p_77041_1_, float p_77041_2_)
    {
        if (p_77041_1_.getSkeletonType() == 1)
        {
            GlStateManager.scale(1.2F, 1.2F, 1.2F);
        }
    }

    public void func_82422_c()
    {
        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
    }

    protected ResourceLocation func_180577_a(LFEntitySkeleton p_180577_1_)
    {
        return p_180577_1_.getSkeletonType() == 1 ? witherSkeletonTextures : skeletonTextures;
    }

    protected ResourceLocation getEntityTexture(EntityLiving entity)
    {
        return this.func_180577_a((LFEntitySkeleton)entity);
    }

    protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_)
    {
        this.preRenderCallback((LFEntitySkeleton)p_77041_1_, p_77041_2_);
    }

    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.func_180577_a((LFEntitySkeleton)entity);
    }
}