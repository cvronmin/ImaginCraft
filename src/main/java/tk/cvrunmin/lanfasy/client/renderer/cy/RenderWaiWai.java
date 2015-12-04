package tk.cvrunmin.lanfasy.client.renderer.cy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.layers.LayerArrow;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import tk.cvrunmin.lanfasy.Lanfasy;
import tk.cvrunmin.lanfasy.entity.cy.CYBossStatus;
import tk.cvrunmin.lanfasy.entity.cy.EntityWaiWai;

public class RenderWaiWai extends RenderLiving {
	private final static ResourceLocation texture = new ResourceLocation(Lanfasy.MODID, "textures/entity/cy/chw.png");
	private final static ResourceLocation texture2 = new ResourceLocation(Lanfasy.MODID, "textures/entity/cy/chw2.png");
	public RenderWaiWai() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelPlayer(0f,false), 0.5F);
        this.addLayer(new LayerHeldItem(this));
        this.addLayer(new LayerBipedArmor(this));
        this.addLayer(new LayerArrow(this));
	}
	protected ResourceLocation getEntityTexture(EntityWaiWai entity) {
		return entity.getMode() == 2 ? texture2 : (entity.getMode() == 3 ? texture2 : (entity.getMode() == 1 ? texture : texture));
	}
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return this.getEntityTexture((EntityWaiWai)entity);
	}
    protected void preRenderCallback(EntityWaiWai entitylivingbaseIn, float partialTickTime)
    {
        float f1 = 0.9375F;
        GlStateManager.scale(f1, f1, f1);
    }
    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLivingBase entitylivingbaseIn, float partialTickTime)
    {
        this.preRenderCallback((EntityWaiWai)entitylivingbaseIn, partialTickTime);
    }
    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity>) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doe
     *  
     * @param entityYaw The yaw rotation of the passed entity
     */
    public void doRender(EntityLivingBase entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        this.doRender((EntityWaiWai)entity, x, y, z, entityYaw, partialTicks);
    }
    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity>) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doe
     *  
     * @param entityYaw The yaw rotation of the passed entity
     */
    public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        this.doRender((EntityWaiWai)entity, x, y, z, entityYaw, partialTicks);
    }
    public void doRender(EntityWaiWai entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
    	CYBossStatus.setBossStatus(entity, true);
    	super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}
