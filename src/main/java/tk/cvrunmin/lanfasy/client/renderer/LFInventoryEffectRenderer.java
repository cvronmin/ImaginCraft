package tk.cvrunmin.lanfasy.client.renderer;

import java.util.Collection;
import java.util.Iterator;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cvrunmin.lanfasy.client.gui.inventory.LFGuiContainer;

@SideOnly(Side.CLIENT)
public abstract class LFInventoryEffectRenderer extends LFGuiContainer
{
    /** True if there is some potion effect to display */
    private boolean hasActivePotionEffects;

    public LFInventoryEffectRenderer(Container p_i1089_1_)
    {
        super(p_i1089_1_);
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        super.initGui();
        this.updateActivePotionEffects();
    }

    protected void updateActivePotionEffects()
    {
        if (!this.mc.thePlayer.getActivePotionEffects().isEmpty())
        {
            this.guiLeft = (this.width - this.xSize);
            this.hasActivePotionEffects = true;
        }
        else
        {
            this.guiLeft = (this.width - this.xSize);
            this.hasActivePotionEffects = false;
        }
    }

    /**
     * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        super.drawScreen(mouseX, mouseY, partialTicks);

        if (this.hasActivePotionEffects)
        {
            this.drawActivePotionEffects();
        }
    }

    /**
     * Display the potion effects list
     */
    private void drawActivePotionEffects()
    {
        int i = this.guiLeft - 124;
        i = 0;
        int j = this.height - 32;
        boolean flag = true;
        Collection collection = this.mc.thePlayer.getActivePotionEffects();

        if (!collection.isEmpty())
        {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableLighting();
            int k = 33;

            if (collection.size() > 5)
            {
                k = (132 + 100) / (collection.size() - 1);
            }

            for (Iterator iterator = this.mc.thePlayer.getActivePotionEffects().iterator(); iterator.hasNext(); i += k)
            {
                PotionEffect potioneffect = (PotionEffect)iterator.next();
                Potion potion = Potion.potionTypes[potioneffect.getPotionID()];
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                this.mc.getTextureManager().bindTexture(inventoryBackground);
                this.drawTexturedModalRect(i, j, 0, 166, 140, 32);

                if (potion.hasStatusIcon())
                {
                    int l = potion.getStatusIconIndex();
                    this.drawTexturedModalRect(i + 6, j + 7, 0 + l % 8 * 18, 198 + l / 8 * 18, 18, 18);
                }

                potion.renderInventoryEffect(i, j, potioneffect, mc);
                if (!potion.shouldRenderInvText(potioneffect)) continue;
                String s1 = I18n.format(potion.getName(), new Object[0]);

/*                if (potioneffect.getAmplifier() == 1)
                {
                    s1 = s1 + " " + I18n.format("enchantment.level.2", new Object[0]);
                }
                else if (potioneffect.getAmplifier() == 2)
                {
                    s1 = s1 + " " + I18n.format("enchantment.level.3", new Object[0]);
                }
                else if (potioneffect.getAmplifier() == 3)
                {
                    s1 = s1 + " " + I18n.format("enchantment.level.4", new Object[0]);
                }*/
                if(potioneffect.getAmplifier() >= 1 && potioneffect.getAmplifier() < 25){
                    s1 = s1 + " " + I18n.format("enchantment.level." + Integer.toString(potioneffect.getAmplifier() + 1), new Object[0]);
                }
                else if (potioneffect.getAmplifier() >= 25){
                	s1 = s1 + " " + Integer.toString(potioneffect.getAmplifier() + 1);
                }

                this.fontRendererObj.drawStringWithShadow(s1, (float)(i + 10 + 18), (float)(j + 6), 16777215);
                String s = Potion.getDurationString(potioneffect);
                this.fontRendererObj.drawStringWithShadow(s, (float)(i + 10 + 18 - 18 - 5), (float)(j + 6 + 10 - 32 + 10), 8355711);
            }
        }
    }
}