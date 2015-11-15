package tk.cvrunmin.lanfasy.client.renderer.crystal;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cvrunmin.lanfasy.client.model.ModelCrystal;
import tk.cvrunmin.lanfasy.entity.item.EntityAFCrystal;
import tk.cvrunmin.lanfasy.entity.item.EntityRedstal;

@SideOnly(Side.CLIENT)
public class RenderRedstal extends Render{
    private static final ResourceLocation redstalTextures = new ResourceLocation("lanfasy", "textures/entity/afcrystal/redstal.png");
    private static final ResourceLocation redstalBeamTextures = new ResourceLocation("lanfasy", "textures/entity/afcrystal/redstal_beam.png");
    private ModelBase modelCrystal = new ModelCrystal(0.0F, true);

    public RenderRedstal()
    {
        super(Minecraft.getMinecraft().getRenderManager());
        this.shadowSize = 0.5F;
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity>) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doe
     */
    public void doRender(EntityRedstal entity, double x, double y, double z, float p_76986_8_, float partialTicks)
    {
        float f2 = (float)entity.innerRotation + partialTicks;
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)z);
        this.bindTexture(this.getCrystalTextures(entity));
        float f3 = MathHelper.sin(f2 * 0.2F) / 2.0F + 0.5F;
        f3 += f3 * f3;
        this.modelCrystal.render(entity, 0.0F, f2 * 3.0F, f3 * 0.2F, 0.0F, 0.0F, 0.0625F);
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, p_76986_8_, partialTicks);
        if (entity.target != null)
        {
            this.drawRechargeRay(entity, x, y, z, partialTicks);
        }
    }
    protected void drawRechargeRay(EntityRedstal entity, double x, double y, double z, float tick)
    {
        float f1 = (float)entity.innerRotation + tick;
        float f2 = MathHelper.sin(f1 * 0.2F) / 2.0F + 0.5F;
        f2 = (f2 * f2 + f2) * 0.2F;
//        float f3 = (float)(entity.target.posX - entity.posX - (entity.prevPosX - entity.posX) * (double)(1.0F - tick));
        float f3 = (float)(entity.target.posX - entity.posX - (entity.prevPosX - entity.posX) * (double)(1.0F - tick) * -1d);
        float f4 = (float)((double)f2 + entity.target.posY - 1.5D - entity.posY - (entity.prevPosY - entity.posY) * (double)(1.0F - tick));
//        float f5 = (float)(entity.target.posZ - entity.posZ - (entity.prevPosZ - entity.posZ) * (double)(1.0F - tick));
        float f5 = (float)(entity.target.posZ - entity.posZ - (entity.prevPosZ - entity.posZ) * (double)(1.0F - tick) * -1d);
        float f6 = MathHelper.sqrt_float(f3 * f3 + f5 * f5);
        float f7 = MathHelper.sqrt_float(f3 * f3 + f4 * f4 + f5 * f5);
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y + 1.0F, (float)z);
        GlStateManager.rotate((float)(-Math.atan2((double)f5, (double)f3)) * 180.0F / (float)Math.PI - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate((float)(-Math.atan2((double)f6, (double)f4)) * 180.0F / (float)Math.PI - 90.0F, 1.0F, 0.0F, 0.0F);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableCull();
        this.bindTexture(redstalBeamTextures);
        GlStateManager.shadeModel(7425);
        float f8 = 0.0F - ((float)entity.ticksExisted + tick) * 0.01F;
        float f9 = MathHelper.sqrt_float(f3 * f3 + f4 * f4 + f5 * f5) / 32.0F - ((float)entity.ticksExisted + tick) * 0.01F;
        worldrenderer.startDrawing(5);
        byte b0 = 8;

        for (int i = 0; i <= b0; ++i)
        {
            float f10 = MathHelper.sin((float)(i % b0) * (float)Math.PI * 2.0F / (float)b0) * 0.75F;
            float f11 = MathHelper.cos((float)(i % b0) * (float)Math.PI * 2.0F / (float)b0) * 0.75F;
            float f12 = (float)(i % b0) * 1.0F / (float)b0;
            worldrenderer.setColorOpaque_I(0);
            worldrenderer.addVertexWithUV((double)(f10 * 0.2F), (double)(f11 * 0.2F), 0.0D, (double)f12, (double)f9);
            worldrenderer.setColorOpaque_I(16777215);
            worldrenderer.addVertexWithUV((double)f10, (double)f11, (double)f7, (double)f12, (double)f8);
        }

        tessellator.draw();
        GlStateManager.enableCull();
        GlStateManager.shadeModel(7424);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.popMatrix();
    }
    protected ResourceLocation getCrystalTextures(EntityRedstal p_180554_1_)
    {
        return redstalTextures;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getCrystalTextures((EntityRedstal)entity);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity>) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doe
     */
    public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks)
    {
        this.doRender((EntityRedstal)entity, x, y, z, p_76986_8_, partialTicks);
    }
}
