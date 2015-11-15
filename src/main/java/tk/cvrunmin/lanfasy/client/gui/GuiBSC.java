package tk.cvrunmin.lanfasy.client.gui;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.util.EnumChatFormatting;
import tk.cvrunmin.fansy.api.client.gui.FGuiScreen;

public class GuiBSC extends FGuiScreen{
	public boolean isHardcore;
	public int count;
    public void initGui()
    {
        this.buttonList.clear();
        count = 200;
        this.isHardcore = this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled();
    }
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawGradientRect(0, 0, this.width, this.height, 0, 0, 0, 255, 0, 0, 0, 255);
        GlStateManager.pushMatrix();
        GlStateManager.scale(4.0F, 4.0F, 4.0F);
//        this.drawString(this.fontRendererObj, ":(", this.width / 3 / 4 / 2 - 10, this.height / 3 / 4 - 10, 16777215);
        this.drawString(this.fontRendererObj, ":(", this.width / 3 / 4 / 2 - 10, this.height / 3 / 4 - 10, 255, 255, 255, 255);
        GlStateManager.popMatrix();
        this.drawString(this.fontRendererObj, "The death screen is replace by me \\(^o^)/,", this.width / 3 / 2 - 40, this.height / 3 + 10, 255, 255, 255, 255);
        String s = "and now it is revolting me.";
        this.drawString(this.fontRendererObj, this.isHardcore ? EnumChatFormatting.STRIKETHROUGH + s : s, this.width / 3 / 2 - 40, this.height / 3 + 20, 255, 255, 255, 255);
        if(isHardcore){
            this.drawString(this.fontRendererObj, "Okay,although it revolt me,you cannot respawn in Hardcore Mode!", this.width / 3 / 2 - 40, this.height / 3 + 30, 255, 255, 255, 255);
        }
        this.drawString(this.fontRendererObj, "It will show again in " + count / 20 + " seconds", this.width / 3 / 2 - 40, this.height / 3 + 40, 255, 255, 255, 255);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    public boolean doesGuiPauseGame()
    {
        return false;
    }
    public void updateScreen()
    {
    	--count;
    	if(count == 0){
            this.mc.displayGuiScreen(new GuiLFGameOver());
    	}
    }
}
