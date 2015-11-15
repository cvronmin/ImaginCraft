package tk.cvrunmin.lanfasy.client.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cvrunmin.lanfasy.Lanfasy;
import tk.cvrunmin.lanfasy.client.model.ModelLFSpider;
import tk.cvrunmin.lanfasy.client.renderer.layers.LayerLFSpiderEyes;
import tk.cvrunmin.lanfasy.entity.LFEntitySpider;

@SideOnly(Side.CLIENT)
public class RenderLFSpider extends RenderLiving
{
    private static final ResourceLocation spiderTextures = new ResourceLocation(Lanfasy.MODID, "textures/entity/spider/spider.png");

    public RenderLFSpider()
    {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelLFSpider(), 1.0F);
        this.addLayer(new LayerLFSpiderEyes(this));
    }

    protected float getDeathMaxRotation(LFEntitySpider p_77037_1_)
    {
        return 180.0F;
    }

    protected ResourceLocation getEntityTexture(LFEntitySpider entity)
    {
        return spiderTextures;
    }

    protected float getDeathMaxRotation(EntityLivingBase p_77037_1_)
    {
        return this.getDeathMaxRotation((LFEntitySpider)p_77037_1_);
    }

    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getEntityTexture((LFEntitySpider)entity);
    }
}
