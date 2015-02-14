package tk.cvrunmin.lanfasy.client.gui;

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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiLFIngameMenuT2 extends GuiScreen {
	int waitForOpenGuiNo;
    private int field_146445_a;
    private int field_146444_f;
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
        this.buttonList.add(new GuiMenuButton(1, this.width / 2 - 200, this.height / 4 + 124 + b0, 20, 20, EnumChatFormatting.BLACK + I18n.format("menu.returnToMenu", new Object[0]), 8));
        if (!this.mc.isIntegratedServerRunning())
        {
            ((GuiLFButton)this.buttonList.get(0)).displayString = EnumChatFormatting.BLACK + I18n.format("menu.disconnect", new Object[0]);
        }

        this.buttonList.add(new GuiMenuButton(4, this.width / 2 - 200, this.height / 4 + 24 + b0, 20, 20, EnumChatFormatting.BLACK + I18n.format("menu.returnToGame", new Object[0]), 5));
        this.buttonList.add(new GuiMenuButton(0, this.width / 2 - 200, this.height / 4 + 104 + b0, 20, 20, EnumChatFormatting.BLACK + I18n.format("menu.options", new Object[0]), 2));
        GuiMenuButton guibutton;
        this.buttonList.add(guibutton = new GuiMenuButton(7, this.width / 2 - 200, this.height / 4 + 84 + b0, 20, 20, EnumChatFormatting.BLACK + I18n.format("menu.shareToLan", new Object[0]), 1));
        this.buttonList.add(new GuiMenuButton(5, this.width / 2 - 200, this.height / 4 + 44 + b0, 20, 20, EnumChatFormatting.BLACK + I18n.format("gui.achievements", new Object[0]), 3));
        this.buttonList.add(new GuiMenuButton(6, this.width / 2 - 200, this.height / 4 + 64 + b0, 20, 20, EnumChatFormatting.BLACK + I18n.format("gui.stats", new Object[0]), 4));
        this.buttonList.add(new GuiMenuButton(100, this.width - 100, this.height / 4 + 64 + b0, null, 7));
        this.buttonList.add(new GuiMenuButton(101, this.width - 100, this.height / 4 + 84 + b0, null, 6));
        guibutton.enabled = this.mc.isSingleplayer() && !this.mc.getIntegratedServer().getPublic();
    }
    protected void actionPerformed(GuiButton p_146284_1_)
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
        this.drawString(this.fontRendererObj,I18n.format("menu.shareToLan"), this.width / 2 - 200 + 25, this.height / 4 + 84 + b0 + 5, 16777215);
        if (!this.mc.isIntegratedServerRunning())
        {
    		this.drawString(this.fontRendererObj,I18n.format("menu.disconnect"), this.width / 2 - 200 + 25, this.height / 4 + 124 + b0 + 5, 16777215);
        }
        else{
		    this.drawString(this.fontRendererObj,I18n.format("menu.returnToMenu"), this.width / 2 - 200 + 25, this.height / 4 + 124 + b0 + 5, 16777215);
        }
		this.drawString(this.fontRendererObj,I18n.format("menu.returnToGame"), this.width / 2 - 200 + 25, this.height / 4 + 24 + b0 + 5, 16777215);
        this.drawString(this.fontRendererObj,I18n.format("menu.options"), this.width / 2 - 200 + 25, this.height / 4 + 104 + b0 + 5, 16777215);
		this.drawString(this.fontRendererObj,I18n.format("gui.achievements"), this.width / 2 - 200 + 25, this.height / 4 + 44 + b0 + 5, 16777215);
		this.drawString(this.fontRendererObj,I18n.format("gui.stats"), this.width / 2 - 200 + 25, this.height / 4 + 64 + b0 + 5, 16777215);
        super.drawScreen(par1, par2, par3);
    }
    protected void renderDefaultBackground(){
        this.drawGradientRect(0, 0, this.width, this.height, -1072689136, -804253680);
        this.drawGradientRect(0, 0, this.width / 4, this.height, -1072689136, -804253680);
        this.drawGradientRect(this.width / 4 * 3, 0, this.width, this.height, -1072689136, -804253680);
    }
}
