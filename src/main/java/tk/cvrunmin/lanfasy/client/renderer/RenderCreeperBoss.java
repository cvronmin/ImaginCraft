package tk.cvrunmin.lanfasy.client.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import tk.cvrunmin.lanfasy.Lanfasy;
import tk.cvrunmin.lanfasy.client.model.ModelCreeperBoss;
import tk.cvrunmin.lanfasy.entity.LFEntityCreeper;
import tk.cvrunmin.lanfasy.entity.LFEntityCreeperBoss;

public class RenderCreeperBoss extends RenderLiving{
    private static final ResourceLocation creeperTextures = new ResourceLocation(Lanfasy.MODID, "textures/entity/creeper/creeperBoss.png");
	public RenderCreeperBoss() {
		super(Minecraft.getMinecraft().getRenderManager(), new ModelCreeperBoss(), 0.75f);
		// TODO Auto-generated constructor stub
	}
    public void doRender(LFEntityCreeperBoss p_177124_1_, double p_177124_2_, double p_177124_4_, double p_177124_6_, float p_177124_8_, float p_177124_9_)
    {
        BossStatus.setBossStatus(p_177124_1_, true);
        super.doRender((EntityLiving)p_177124_1_, p_177124_2_, p_177124_4_, p_177124_6_, p_177124_8_, p_177124_9_);
    }
    protected void preRenderCallback(LFEntityCreeperBoss p_180570_1_, float p_180570_2_)
    {
        GlStateManager.scale(2F, 2F, 2F);
        float f1 = p_180570_1_.getCreeperFlashIntensity(p_180570_2_);
        float f2 = 1.0F + MathHelper.sin(f1 * 100.0F) * f1 * 0.01F;
        f1 = MathHelper.clamp_float(f1, 0.0F, 1.0F);
        f1 *= f1;
        f1 *= f1;
        float f3 = (1.0F + f1 * 0.4F) * f2;
        float f4 = (1.0F + f1 * 0.1F) / f2;
        GlStateManager.scale(f3, f4, f3);
    }

    protected int getColorMultiplier(LFEntityCreeperBoss p_180571_1_, float p_180571_2_, float p_180571_3_)
    {
        float f2 = p_180571_1_.getCreeperFlashIntensity(p_180571_3_);

        if ((int)(f2 * 10.0F) % 2 == 0)
        {
            return 0;
        }
        else
        {
            int i = (int)(f2 * 0.2F * 255.0F);
            i = MathHelper.clamp_int(i, 0, 255);
            return i << 24 | 16777215;
        }
    }
	protected ResourceLocation getEntityTexture(LFEntityCreeperBoss entity) {
		// TODO Auto-generated method stub
		return creeperTextures;
	}
    protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_)
    {
        this.preRenderCallback((LFEntityCreeperBoss)p_77041_1_, p_77041_2_);
    }
    public void doRender(EntityLiving entity, double x, double y, double z, float p_76986_8_, float partialTicks)
    {
        this.doRender((LFEntityCreeperBoss)entity, x, y, z, p_76986_8_, partialTicks);
    }

    public void doRender(EntityLivingBase entity, double x, double y, double z, float p_76986_8_, float partialTicks)
    {
        this.doRender((LFEntityCreeperBoss)entity, x, y, z, p_76986_8_, partialTicks);
    }

    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getEntityTexture((LFEntityCreeperBoss)entity);
    }
    protected int getColorMultiplier(EntityLivingBase p_77030_1_, float p_77030_2_, float p_77030_3_)
    {
        return this.getColorMultiplier((LFEntityCreeperBoss)p_77030_1_, p_77030_2_, p_77030_3_);
    }

    public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks)
    {
        this.doRender((LFEntityCreeperBoss)entity, x, y, z, p_76986_8_, partialTicks);
    }
}
