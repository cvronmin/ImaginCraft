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
public class GuiLFIngameMenuT3 extends GuiScreen {
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
//        this.buttonList.add(new GuiLFButton(1, 10, this.height / 4 + 124 + b0, 20, 20, EnumChatFormatting.BLACK + I18n.format("menu.returnToMenu", new Object[0])));
        this.buttonList.add(new GuiLFButton(1, 0, this.height / 4 + 124 + b0, this.width / 3, 20, EnumChatFormatting.BLUE + I18n.format("menu.exit", new Object[0])));
        if (!this.mc.isIntegratedServerRunning())
        {
            ((GuiLFButton)this.buttonList.get(0)).displayString = EnumChatFormatting.DARK_BLUE + I18n.format("menu.disconnect", new Object[0]);
        }

        this.buttonList.add(new GuiLFButton(4, 0, this.height / 4 + 24 + b0, this.width / 3, 20, EnumChatFormatting.BLUE + I18n.format("menu.returnToGame", new Object[0])));
        this.buttonList.add(new GuiLFButton(0, 0, this.height / 4 + 104 + b0, this.width / 3, 20, EnumChatFormatting.BLUE + I18n.format("menu.options", new Object[0])));
        GuiLFButton guibutton;
        this.buttonList.add(guibutton = new GuiLFButton(7, 0, this.height / 4 + 84 + b0, this.width / 3, 20, EnumChatFormatting.BLUE + I18n.format("menu.shareToLan", new Object[0])));
        this.buttonList.add(new GuiLFButton(5, 0, this.height / 4 + 44 + b0, this.width / 3, 20, EnumChatFormatting.BLUE + I18n.format("gui.achievements", new Object[0])));
        this.buttonList.add(new GuiLFButton(6, 0, this.height / 4 + 64 + b0, this.width / 3, 20, EnumChatFormatting.BLUE + I18n.format("gui.stats", new Object[0])));
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
                this.mc.displayGuiScreen(new GuiExitConfirm(this));
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
        super.drawScreen(par1, par2, par3);
    }
    protected void renderDefaultBackground(){
        this.drawGradientRect(0, 0, this.width, this.height, -1072689136, -804253680);
        this.drawGradientRect(0, 0, this.width / 3, this.height, -1072689136, -804253680);
    }
    class GuiExitConfirm extends GuiScreen{
    	private GuiScreen parentScreen;
		public GuiExitConfirm(GuiScreen parentScreen){
    		this.parentScreen = parentScreen;
    	}
    	public void initGui(){
            this.buttonList.clear();
            byte b0 = -16;
            boolean flag = true;
            this.buttonList.add(new GuiLFButton(1, this.width / 3 * 2, this.height / 3 + this.height / 3 / 4 * 2, this.width / 3, this.height / 3 / 4, EnumChatFormatting.BLUE + I18n.format("menu.returnToMenu", new Object[0])));
            if (!this.mc.isIntegratedServerRunning())
            {
                ((GuiLFButton)this.buttonList.get(0)).displayString = EnumChatFormatting.BLACK + I18n.format("menu.disconnect", new Object[0]);
            }
            this.buttonList.add(new GuiLFButton(2, this.width / 3 * 2, this.height / 3, this.width / 3, this.height / 3 / 4, EnumChatFormatting.BLUE + I18n.format("gui.teledim", new Object[0])));
            this.buttonList.add(new GuiLFButton(3, this.width / 3 * 2, this.height / 3 + this.height / 3 / 4, this.width / 3, this.height / 3 / 4, EnumChatFormatting.BLUE + I18n.format("gui.teledim.overworld", new Object[0])));
            this.buttonList.add(new GuiLFButton(4, this.width / 3 * 2, this.height / 3 + this.height / 3 / 4 * 3, this.width / 3, this.height / 3 / 4, EnumChatFormatting.BLUE + I18n.format("menu.return", new Object[0])));
    	}
        protected void actionPerformed(GuiButton p_146284_1_)
        {
            switch (p_146284_1_.id)
            {
            case 1:
                p_146284_1_.enabled = false;
                this.mc.theWorld.sendQuittingDisconnectingPacket();
                this.mc.loadWorld((WorldClient)null);
                this.mc.displayGuiScreen(new GuiMainMenu());
                break;
            case 2:
                this.mc.displayGuiScreen(new GuiTeleDIM(parentScreen));
            	break;
            case 3:
            	this.mc.displayGuiScreen(new GuiTeleDIMConfirm(parentScreen, 0));
            	break;
            case 4:
                this.mc.displayGuiScreen(this.parentScreen);
                break;
            }
            
        }
        public void drawScreen(int par1, int par2, float par3)
        {
//            this.drawDefaultBackground();
//        	this.renderBackground(this.width, this.height);
        	this.renderDefaultBackground();
            int b0 = -16;
            this.drawString(this.fontRendererObj,I18n.format("menu.exitconfirm.l1"), 10 + 25, this.height / 3 + 10, 16777215);
            super.drawScreen(par1, par2, par3);
        }
        protected void renderDefaultBackground(){
            this.drawGradientRect(0, this.height / 3, this.width, this.height / 3 * 2, -1072689136, -804253680);
        }
    }
}
