package tk.cvrunmin.lanfasy.client.gui;

import tk.cvrunmin.lanfasy.util.PlayerUtil;
import tk.cvrunmin.lanfasy.world.TeleporterGenatic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiTeleDIMConfirm extends GuiScreen {
	private GuiScreen menuScreen;
	private int tryDIMid;
    protected String confirmButtonText;
    protected String cancelButtonText;
    protected WorldProvider worldProvider;
    protected boolean success;
	public GuiTeleDIMConfirm(GuiScreen par1guiScreen, int par2) {
		this.menuScreen = par1guiScreen;
		this.tryDIMid = par2;
        this.confirmButtonText = I18n.format("gui.yes", new Object[0]);
        this.cancelButtonText = I18n.format("gui.no", new Object[0]);
	}
    public void initGui()
    {
        this.buttonList.add(new GuiLFOptionButton(0, this.width / 2 - 155, this.height / 6 + 96, this.confirmButtonText));
        this.buttonList.add(new GuiLFOptionButton(1, this.width / 2 - 155 + 160, this.height / 6 + 96, this.cancelButtonText));
        try{
        	worldProvider = WorldProvider.getProviderForDimension(tryDIMid);
        	if(worldProvider instanceof WorldProvider && worldProvider != null){
        		success = true;
        	}
        }
        catch(Exception e){
        	worldProvider = null;
        	success = false;
        }
            ((GuiButton)this.buttonList.get(0)).enabled = success;
    }
    protected void actionPerformed(GuiButton p_146284_1_)
    {
        switch (p_146284_1_.id)
        {
        case 0:
	        EntityPlayerMP thePlayer = PlayerUtil.getPlayerMPfromPlayer(Minecraft.getMinecraft().thePlayer);
            thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, worldProvider.getDimensionId(), new TeleporterGenatic(thePlayer.mcServer.worldServerForDimension(worldProvider.getDimensionId())));
        	break;
        case 1:
        	this.mc.displayGuiScreen(this.menuScreen);
        	break;
        }
    }
    public void drawScreen(int par1, int par2, float par3)
    {
    	this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.teledim.q1", new Object[0]), this.width / 2, 70, 16777215);
        this.drawCenteredString(this.fontRendererObj, I18n.format("world.info.dimid", new Object[0]) + " : " + (!success ? "ERROR" : worldProvider.getDimensionId()), this.width / 2, 100, 16777215);
        this.drawCenteredString(this.fontRendererObj, I18n.format("world.info.dimName", new Object[0]) + " : " + (!success ? "ERROR" : worldProvider.getDimensionName()), this.width / 2, 120, 16777215);
    	super.drawScreen(par1, par2, par3);
    }
}
