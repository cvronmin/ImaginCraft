package tk.cvrunmin.lanfasy.client.gui;


import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.input.Keyboard;

import tk.cvrunmin.fansy.api.client.gui.FGuiScreen;

@SideOnly(Side.CLIENT)
public class GuiTeleDIM extends FGuiScreen{
    private GuiScreen menuScreen;
    private GuiTextField textField;
	public GuiTeleDIM(GuiScreen par1GuiScreen) {
        this.menuScreen = par1GuiScreen;
	}
    public void initGui()
    {
        Keyboard.enableRepeatEvents(true);
    	this.buttonList.clear();
        this.buttonList.add(new GuiLFButton(0, this.width / 2 - 100 + 50, this.height / 6 + 168, 100, 20, I18n.format("gui.back", new Object[0])));
        GuiLFButton button;
        this.buttonList.add(button = new GuiLFButton(1, this.width / 2 + 100 - 50, this.height / 3 * 2 + 10, 100, 20, I18n.format("gui.connect", new Object[0])));
        button.enabled = false;
        this.textField = new GuiTextField(2, this.fontRendererObj, this.width / 2 - 200 + 50, this.height / 3 * 2 + 10, 100, 20);
        this.textField.setFocused(true);
    }
    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
    }
    protected void actionPerformed(GuiButton p_146284_1_) throws IOException
    {
    	switch (p_146284_1_.id)
        {
    	case 0:
            this.mc.displayGuiScreen(this.menuScreen);
        	break;
    	case 1:
    		this.mc.displayGuiScreen(new GuiTeleDIMConfirm(this.menuScreen, Integer.parseInt(this.textField.getText())));
        }
        
    }
    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char par1, int par2) throws IOException
    {
    	boolean isNum = (par2 >= 2 && par2 <= 11) || (par2 >= 71 && par2 <= 73) || (par2 >= 75 && par2 <= 77) || (par2 >= 79 && par2 <= 82);
    	boolean isArrow = par2 == Keyboard.KEY_UP || par2 == Keyboard.KEY_LEFT || par2 == Keyboard.KEY_RIGHT || par2 == Keyboard.KEY_DOWN;
    	if(((isNum || isArrow || par2 == 14 || par2 == 211) || ((par2 == Keyboard.KEY_MINUS || par2 == Keyboard.KEY_SUBTRACT) && this.textField.getText().trim().length() == 0)) && !isShiftKeyDown()){
        this.textField.textboxKeyTyped(par1, par2);
    	}
        ((GuiButton)this.buttonList.get(1)).enabled = this.textField.getText().length() > 0;

        if ((par2 == 28 || par2 == 156) && ((GuiButton)this.buttonList.get(1)).enabled == true)
        {
            this.actionPerformed((GuiButton)this.buttonList.get(1));
        }
    }

    /**
     * Called when the mouse is clicked.
     * @throws IOException 
     */
    protected void mouseClicked(int par1, int par2, int par3) throws IOException
    {
        super.mouseClicked(par1, par2, par3);
        this.textField.mouseClicked(par1, par2, par3);
    }
    public void drawScreen(int par1, int par2, float par3)
    {
    	this.renderDefaultBackground();
        this.textField.drawTextBox();
        super.drawScreen(par1, par2, par3);
    }
	protected void renderDefaultBackground(){
        this.drawGradientRect(0, this.height / 3 * 2, this.width, this.height, 0, 0, 0, 127, 255, 255, 255, 127);
    }
}
