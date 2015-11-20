package tk.cvrunmin.lanfasy.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tk.cvrunmin.fansy.api.client.gui.FGui;
import tk.cvrunmin.lanfasy.Lanfasy;
import tk.cvrunmin.lanfasy.entity.cy.CYBossStatus;
import tk.cvrunmin.lanfasy.init.LFHecItems;
import tk.cvrunmin.lanfasy.util.LogHelper;

public class GuiBossBarOverlay extends FGui {
	private static final ResourceLocation baricons = new ResourceLocation(Lanfasy.MODID, "textures/gui/baricons.png");
	Minecraft mc;
	public GuiBossBarOverlay(Minecraft mc){
		super();
		this.mc = mc;
	}
	  @SubscribeEvent
	  public void onRender(RenderGameOverlayEvent event){
		    if(event.isCancelable())
		    {
		      return;
		    }
	        if (CYBossStatus.bossName != null && CYBossStatus.statusBarTime > 0)
	        {
	            --CYBossStatus.statusBarTime;
	            ScaledResolution scaledresolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
	            int i = scaledresolution.getScaledWidth();
	            GlStateManager.enableBlend();
	            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
	            short short1 = 182;
	            int j = i / 2 - short1 / 2;
	            int k = (int)(CYBossStatus.healthScaleDivided * (float)(short1 + 1));
	            LogHelper.info(CYBossStatus.healthScaleDivided + " And " + k);
	            byte b0 = 12;
	            this.drawTexturedModalRect(j, b0, 0, 0, short1, 10);
//	            drawRect(j, b0, (j + short1) * scaledresolution.getScaleFactor(), scaledresolution.getScaleFactor() * (b0 + 10), 0x000000);
	            this.drawTexturedModalRect(j, b0, 0, 0, short1, 10);
	            
	            if (k > 0)
	            {
//		            drawRect(j, b0, (j + k) * scaledresolution.getScaleFactor(), scaledresolution.getScaleFactor() * (b0 + 10), 0x00FF00);
	                this.drawTexturedModalRect(j, b0, 0, 10, k, 10);
	            }

	            String s = CYBossStatus.bossName;
	            String s1 = "x" + CYBossStatus.healthBarDivide;
	            this.getFontRenderer().drawStringWithShadow(s, (float)(i / 2 - this.getFontRenderer().getStringWidth(s) / 2), (float)(b0 + 1), 16777215);
	            this.getFontRenderer().drawStringWithShadow(s1, (float)(j + short1 - this.getFontRenderer().getStringWidth(s1) - 5), (float)(b0 + 1), 16777215);
	            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	            this.mc.getTextureManager().bindTexture(baricons);
	            GlStateManager.disableBlend();
	        }
	  }
	    public FontRenderer getFontRenderer()
	    {
	        return this.mc.fontRendererObj;
	    }
}
