package tk.cvrunmin.lanfasy.client.gui;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiShareToLan;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.client.config.HoverChecker;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiIngameMenuVan extends GuiIngameMenu{
    private int field_146445_a;
    private int field_146444_f;
    protected HoverChecker HoverChecker1024;
    public void initGui()
    {
    	super.initGui();
        this.field_146445_a = 0;
        byte b0 = -16;
        GuiLFButton button1;
        this.buttonList.add(button1 = new GuiLFButton(1024, this.width / 2 - 100, this.height / 4 + 142 + b0, I18n.format("gui.teledim", new Object[0])));
        if (!this.mc.isIntegratedServerRunning())
        {
            button1.enabled = false;
        }
        this.HoverChecker1024 = new HoverChecker(button1, 200);
    }

    protected void actionPerformed(GuiButton button) throws IOException
    {
        switch (button.id)
        {
            case 0:
                this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
                break;
            case 1:
                button.enabled = false;
                this.mc.theWorld.sendQuittingDisconnectingPacket();
                this.mc.loadWorld((WorldClient)null);
                this.mc.displayGuiScreen(new GuiMainMenu());
            case 2:
            case 3:
            default:
                break;
            case 4:
                this.mc.displayGuiScreen((GuiScreen)null);
                this.mc.setIngameFocus();
                break;
            case 5:
                if (this.mc.thePlayer != null)
                this.mc.displayGuiScreen(new GuiAchievements(this, this.mc.thePlayer.getStatFileWriter()));
                break;
            case 6:
                if (this.mc.thePlayer != null)
                this.mc.displayGuiScreen(new GuiStats(this, this.mc.thePlayer.getStatFileWriter()));
                break;
            case 7:
                this.mc.displayGuiScreen(new GuiShareToLan(this));
                break;
            case 12:
                net.minecraftforge.fml.client.FMLClientHandler.instance().showInGameModOptions(this);
                break;
            case 1024:
                this.mc.displayGuiScreen(new GuiTeleDIM(this));
            	break;
        }
    }

    public void updateScreen()
    {
        super.updateScreen();
        ++this.field_146444_f;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
//        this.drawDefaultBackground();
//        this.drawCenteredString(this.fontRendererObj, I18n.format("menu.game", new Object[0]), this.width / 2, 40, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
        if (this.HoverChecker1024.checkHover(mouseX, mouseY))
            this.drawHoveringText(this.mc.fontRendererObj.listFormattedStringToWidth(I18n.format("gui.teledim.warn"), 150), mouseX, mouseY);
    }
}
