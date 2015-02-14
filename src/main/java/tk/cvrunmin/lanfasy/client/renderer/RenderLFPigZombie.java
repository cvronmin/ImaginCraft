package tk.cvrunmin.lanfasy.client.renderer;

import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cvrunmin.lanfasy.Lanfasy;
import tk.cvrunmin.lanfasy.client.model.ModelLFZombie;
import tk.cvrunmin.lanfasy.entity.LFEntityPigZombie;

@SideOnly(Side.CLIENT)
public class RenderLFPigZombie extends RenderBiped
{
    private static final ResourceLocation field_177120_j = new ResourceLocation(Lanfasy.MODID, "textures/entity/zombie_pigman.png");

    public RenderLFPigZombie(RenderManager p_i46148_1_)
    {
        super(p_i46148_1_, new ModelLFZombie(), 0.5F, 1.0F);
        this.addLayer(new LayerHeldItem(this));
        this.addLayer(new LayerBipedArmor(this)
        {
            protected void func_177177_a()
            {
                this.field_177189_c = new ModelLFZombie(0.5F, true);
                this.field_177186_d = new ModelLFZombie(1.0F, true);
            }
        });
    }

    protected ResourceLocation func_177119_a(LFEntityPigZombie p_177119_1_)
    {
        return field_177120_j;
    }

    protected ResourceLocation getEntityTexture(EntityLiving entity)
    {
        return this.func_177119_a((LFEntityPigZombie)entity);
    }

    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.func_177119_a((LFEntityPigZombie)entity);
    }
}
