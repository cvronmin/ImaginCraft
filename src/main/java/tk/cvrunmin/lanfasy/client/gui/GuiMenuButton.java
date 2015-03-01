package tk.cvrunmin.lanfasy.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiMenuButton extends GuiButton{
    protected static final ResourceLocation buttonTextures = new ResourceLocation("lanfasy", "textures/gui/menuButton.png");
    /** Button width in pixels */
    protected int width;
    /** Button height in pixels */
    protected int height;
    /** The x position of this control. */
    public int xPosition;
    /** The y position of this control. */
    public int yPosition;
    public int id;
    /** True if this control is enabled, false to disable. */
    public boolean enabled;
    /** Hides the button completely if false. */
    public boolean visible;
    protected boolean field_146123_n;
    public int packedFGColour;
    protected int menuId;

    public GuiMenuButton(int par1, int par2, int par3, String par4Str, int menuId)
    {
        this(par1, par2, par3, 20, 20, par4Str, menuId);
    }

    public GuiMenuButton(int par1, int par2, int par3, int par4, int par5, String par6Str, int menuId)
    {
    	super(par1, par2, par3, 20, 20, par6Str);
        this.width = 20;
        this.height = 20;
        this.enabled = true;
        this.visible = true;
        this.id = par1;
        this.xPosition = par2;
        this.yPosition = par3;
        this.width = par4;
        this.height = par5;
        this.displayString = par6Str;
        this.menuId = menuId;
    }

    /**
     * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over this button and 2 if it IS hovering over
     * this button.
     */
    protected int getHoverState(boolean p_146114_1_)
    {
        byte b0 = 1;

        if (!this.enabled)
        {
            b0 = 0;
        }
        else if (p_146114_1_)
        {
            b0 = 2;
        }

        return b0;
    }

    /**
     * Draws this button to the screen.
     */
    @Override
    public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_)
    {
        if (this.visible)
        {
            p_146112_1_.getTextureManager().bindTexture(buttonTextures);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.field_146123_n = p_146112_2_ >= this.xPosition && p_146112_3_ >= this.yPosition && p_146112_2_ < this.xPosition + this.width && p_146112_3_ < this.yPosition + this.height;
            int k = this.getHoverState(this.field_146123_n);
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, menuId * 20, k * 20, this.width, this.height);
//            this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 20 - this.width / 2,k * 20, this.width / 2, this.height);
            this.mouseDragged(p_146112_1_, p_146112_2_, p_146112_3_);
        }
    }

    /**
     * Fired when the mouse button is dragged. Equivalent of MouseListener.mouseDragged(MouseEvent e).
     */
    protected void mouseDragged(Minecraft p_146119_1_, int p_146119_2_, int p_146119_3_) {
    	super.mouseDragged(p_146119_1_, p_146119_2_, p_146119_3_);
    }

    /**
     * Fired when the mouse button is released. Equivalent of MouseListener.mouseReleased(MouseEvent e).
     */
    public void mouseReleased(int p_146118_1_, int p_146118_2_) {
    	super.mouseReleased(p_146118_1_, p_146118_2_);
    }

    /**
     * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent
     * e).
     */
    public boolean mousePressed(Minecraft p_146116_1_, int p_146116_2_, int p_146116_3_)
    {
    	return super.mousePressed(p_146116_1_, p_146116_2_, p_146116_3_);
//        return this.enabled && this.visible && p_146116_2_ >= this.xPosition && p_146116_3_ >= this.yPosition && p_146116_2_ < this.xPosition + this.width && p_146116_3_ < this.yPosition + this.height;
    }
    public boolean func_146115_a()
    {
        return this.field_146123_n;
    }
    public void func_146111_b(int p_146111_1_, int p_146111_2_) {}
    public void func_146113_a(SoundHandler p_146113_1_)
    {
        p_146113_1_.playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
    }
    public int getButtonWidth()
    {
        return this.width;
    }
}
