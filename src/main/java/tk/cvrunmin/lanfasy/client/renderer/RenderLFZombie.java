package tk.cvrunmin.lanfasy.client.renderer;

import java.util.List;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.model.ModelZombieVillager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.layers.LayerVillagerArmor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cvrunmin.lanfasy.Lanfasy;
import tk.cvrunmin.lanfasy.client.model.ModelLFZombie;
import tk.cvrunmin.lanfasy.entity.LFEntityZombie;

import com.google.common.collect.Lists;

@SideOnly(Side.CLIENT)
public class RenderLFZombie extends RenderBiped
{
    private static final ResourceLocation zombieTextures = new ResourceLocation(Lanfasy.MODID, "textures/entity/zombie/zombie.png");
    private final ModelBiped field_82434_o;
    private final List field_177121_n;
    private final List field_177122_o;

    public RenderLFZombie(RenderManager p_i46127_1_)
    {
        super(p_i46127_1_, new ModelLFZombie(), 0.5F, 1.0F);
        LayerRenderer layerrenderer = (LayerRenderer)this.layerRenderers.get(0);
        this.field_82434_o = this.modelBipedMain;
        this.addLayer(new LayerHeldItem(this));
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
        {
            protected void func_177177_a()
            {
                this.field_177189_c = new ModelLFZombie(0.5F, true);
                this.field_177186_d = new ModelLFZombie(1.0F, true);
            }
        };
        this.addLayer(layerbipedarmor);
        this.field_177122_o = Lists.newArrayList(this.layerRenderers);

        if (layerrenderer instanceof LayerCustomHead)
        {
            this.removeLayer(layerrenderer);
        }

        this.removeLayer(layerbipedarmor);
        this.addLayer(new LayerVillagerArmor(this));
        this.field_177121_n = Lists.newArrayList(this.layerRenderers);
    }

    public void func_180579_a(LFEntityZombie p_180579_1_, double p_180579_2_, double p_180579_4_, double p_180579_6_, float p_180579_8_, float p_180579_9_)
    {
        this.func_82427_a(p_180579_1_);
        super.doRender(p_180579_1_, p_180579_2_, p_180579_4_, p_180579_6_, p_180579_8_, p_180579_9_);
    }

    protected ResourceLocation func_180578_a(LFEntityZombie p_180578_1_)
    {
        return zombieTextures;
    }

    private void func_82427_a(LFEntityZombie p_82427_1_)
    {
            this.mainModel = this.field_82434_o;
            this.layerRenderers = this.field_177122_o;
        this.modelBipedMain = (ModelBiped)this.mainModel;
    }

    protected void rotateCorpse(LFEntityZombie p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_)
    {
        if (p_77043_1_.isConverting())
        {
            p_77043_3_ += (float)(Math.cos((double)p_77043_1_.ticksExisted * 3.25D) * Math.PI * 0.25D);
        }

        super.rotateCorpse(p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
    }

    protected ResourceLocation getEntityTexture(EntityLiving entity)
    {
        return this.func_180578_a((LFEntityZombie)entity);
    }

    public void doRender(EntityLiving entity, double x, double y, double z, float p_76986_8_, float partialTicks)
    {
        this.func_180579_a((LFEntityZombie)entity, x, y, z, p_76986_8_, partialTicks);
    }

    protected void rotateCorpse(EntityLivingBase p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_)
    {
        this.rotateCorpse((LFEntityZombie)p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
    }

    public void doRender(EntityLivingBase entity, double x, double y, double z, float p_76986_8_, float partialTicks)
    {
        this.func_180579_a((LFEntityZombie)entity, x, y, z, p_76986_8_, partialTicks);
    }

    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.func_180578_a((LFEntityZombie)entity);
    }

    public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks)
    {
        this.func_180579_a((LFEntityZombie)entity, x, y, z, p_76986_8_, partialTicks);
    }
}