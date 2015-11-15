package tk.cvrunmin.lanfasy.client.gui;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiShareToLan;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.HoverChecker;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiLFIngameMenuT2 extends GuiScreen {
	int waitForOpenGuiNo;
    private int field_146445_a;
    private int field_146444_f;
    protected HoverChecker HoverChecker100;
    protected HoverChecker HoverChecker101;
    protected static final ResourceLocation background = new ResourceLocation("Lanfasy", "textures/gui/background/background.png");
    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        this.field_146445_a = 0;
        this.buttonList.clear();
        byte b0 = -16;
        boolean flag = true;
        this.buttonList.add(new GuiMenuButton(1, 10, this.height / 4 + 124 + b0, 20, 20, EnumChatFormatting.BLACK + I18n.format("menu.returnToMenu", new Object[0]), 8));
        this.buttonList.add(new GuiMenuButton(4, 10, this.height / 4 + 24 + b0, 20, 20, EnumChatFormatting.BLACK + I18n.format("menu.returnToGame", new Object[0]), 5));
        this.buttonList.add(new GuiMenuButton(0, 10, this.height / 4 + 104 + b0, 20, 20, EnumChatFormatting.BLACK + I18n.format("menu.options", new Object[0]), 2));
        GuiMenuButton guibutton;
        this.buttonList.add(guibutton = new GuiMenuButton(7, 10, this.height / 4 + 84 + b0, 20, 20, EnumChatFormatting.BLACK + I18n.format("menu.shareToLan", new Object[0]), 1));
        this.buttonList.add(new GuiMenuButton(5, 10, this.height / 4 + 44 + b0, 20, 20, EnumChatFormatting.BLACK + I18n.format("gui.achievements", new Object[0]), 3));
        this.buttonList.add(new GuiMenuButton(6, 10, this.height / 4 + 64 + b0, 20, 20, EnumChatFormatting.BLACK + I18n.format("gui.stats", new Object[0]), 4));
        GuiMenuButton button100;
        GuiMenuButton button101;
        this.buttonList.add(button100 = new GuiMenuButton(100, this.width / 3 * 2 + 10, this.height / 4 + 64 + b0, null, 7));
        this.buttonList.add(button101 = new GuiMenuButton(101, this.width / 3 * 2 + 10, this.height / 4 + 84 + b0, null, 6));
        guibutton.enabled = this.mc.isSingleplayer() && !this.mc.getIntegratedServer().getPublic();
        if (!this.mc.isIntegratedServerRunning())
        {
            ((GuiMenuButton)this.buttonList.get(0)).displayString = EnumChatFormatting.BLACK + I18n.format("menu.disconnect", new Object[0]);
            button100.enabled = false;
            button101.enabled = false;
        }
        this.HoverChecker100 = new HoverChecker(button100, 200);
        this.HoverChecker101 = new HoverChecker(button101, 200);
    }
    protected void actionPerformed(GuiButton p_146284_1_) throws IOException
    {
        switch (p_146284_1_.id)
        {
            case 0:
                this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
                break;
            case 1:
                p_146284_1_.enabled = false;
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
//                FMLClientHandler.instance().showInGameModOptions(this);
                break;
            case 100:
                this.mc.displayGuiScreen(new GuiTeleDIM(this));
            	break;
            case 101:
            	this.mc.displayGuiScreen(new GuiTeleDIMConfirm(this, 0));
            	break;
        }
    }
    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        super.updateScreen();
        ++this.field_146444_f;
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
//        this.drawDefaultBackground();
//    	this.renderBackground(this.width, this.height);
    	this.renderDefaultBackground();
        int b0 = -16;
        this.drawCenteredString(this.fontRendererObj,"Game menu", this.width / 2, 20, 16777215);
        this.drawString(this.fontRendererObj,I18n.format("menu.shareToLan"), 10 + 25, this.height / 4 + 84 + b0 + 5, 16777215);
        if (!this.mc.isIntegratedServerRunning())
        {
    		this.drawString(this.fontRendererObj,I18n.format("menu.disconnect"), 10 + 25, this.height / 4 + 124 + b0 + 5, 16777215);
        }
        else{
		    this.drawString(this.fontRendererObj,I18n.format("menu.returnToMenu"), 10 + 25, this.height / 4 + 124 + b0 + 5, 16777215);
        }
		this.drawString(this.fontRendererObj,I18n.format("menu.returnToGame"), 10 + 25, this.height / 4 + 24 + b0 + 5, 16777215);
        this.drawString(this.fontRendererObj,I18n.format("menu.options"), 10 + 25, this.height / 4 + 104 + b0 + 5, 16777215);
		this.drawString(this.fontRendererObj,I18n.format("gui.achievements"), 10 + 25, this.height / 4 + 44 + b0 + 5, 16777215);
		this.drawString(this.fontRendererObj,I18n.format("gui.stats"), 10 + 25, this.height / 4 + 64 + b0 + 5, 16777215);
		this.drawString(this.fontRendererObj,I18n.format("gui.teledim"), this.width / 3 * 2 + 10 + 25, this.height / 4 + 64 + b0 + 5, 16777215);
		this.drawString(this.fontRendererObj,I18n.format("gui.teledim.overworld"), this.width / 3 * 2 + 10 + 25, this.height / 4 + 84 + b0 + 5, 16777215);
        super.drawScreen(par1, par2, par3);
        if (this.HoverChecker100.checkHover(par1, par2))
            this.drawHoveringText(this.mc.fontRendererObj.listFormattedStringToWidth(I18n.format("gui.teledim.warn"), 150), par1, par2);
        if (this.HoverChecker101.checkHover(par1, par2))
            this.drawHoveringText(this.mc.fontRendererObj.listFormattedStringToWidth(I18n.format("gui.teledim.warn"), 150), par1, par2);
    }
    protected void renderDefaultBackground(){
        this.drawGradientRect(0, 0, this.width, this.height, -1072689136, -804253680);
        this.drawGradientRect(0, 0, this.width / 3, this.height, -1072689136, -804253680);
        this.drawGradientRect(this.width / 3 * 2, 0, this.width, this.height, -1072689136, -804253680);
    }
}
