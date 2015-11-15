package tk.cvrunmin.lanfasy.client.renderer;

import java.util.List;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelGuardian;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import tk.cvrunmin.lanfasy.Lanfasy;
import tk.cvrunmin.lanfasy.client.model.ModelZombieBoss;
import tk.cvrunmin.lanfasy.entity.LFEntityZombie;
import tk.cvrunmin.lanfasy.entity.LFEntityZombieBoss;

import com.google.common.collect.Lists;

public class RenderZombieBoss extends RenderLiving{
    private static final ResourceLocation zombieTextures = new ResourceLocation(Lanfasy.MODID, "textures/entity/zombie/zombieBoss.png");
	private ModelZombieBoss field_82434_o;

    public RenderZombieBoss()
    {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelZombieBoss(), 0.5F);
        this.addLayer(new LayerHeldItem(this));
    }

    public void func_180579_a(LFEntityZombieBoss p_180579_1_, double p_180579_2_, double p_180579_4_, double p_180579_6_, float p_180579_8_, float p_180579_9_)
    {
        BossStatus.setBossStatus(p_180579_1_, true);
        super.doRender(p_180579_1_, p_180579_2_, p_180579_4_, p_180579_6_, p_180579_8_, p_180579_9_);
    }

    protected ResourceLocation func_180578_a(LFEntityZombieBoss entity)
    {
        return zombieTextures;
    }

    private void func_82427_a(LFEntityZombieBoss p_82427_1_)
    {
            this.mainModel = this.field_82434_o;
    }

    protected ResourceLocation getEntityTexture(EntityLiving entity)
    {
        return this.func_180578_a((LFEntityZombieBoss)entity);
    }

    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.func_180578_a((LFEntityZombieBoss)entity);
    }
    public void doRender(Entity entity, double par2, double par4, double par6, float par8, float par9){
    	this.func_180579_a((LFEntityZombieBoss)entity, par2, par4, par6, par8, par9);
    }
}
