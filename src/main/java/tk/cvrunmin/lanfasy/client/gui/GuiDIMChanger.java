package tk.cvrunmin.lanfasy.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class GuiDIMChanger extends GuiContainer{
    private static final ResourceLocation guiTextures = new ResourceLocation("lanfasy", "textures/gui/teledim.png");
    private GuiTextField textField;
    public GuiDIMChanger(Container p_i1072_1_) {
		super(p_i1072_1_);
        this.xSize = 250;
        this.ySize = 200;
	}
    public void initGui()
    {
        Keyboard.enableRepeatEvents(true);
    	this.buttonList.clear();
        this.buttonList.add(new GuiLFButton(0, this.width / 2 - 100 + 50, this.height / 6 + 168, 100, 20, I18n.format("gui.back", new Object[0])));
        this.buttonList.add(new GuiLFButton(1, this.width / 2 + 100 - 50, this.height / 3 * 2 + 10, 100, 20, I18n.format("gui.connect", new Object[0])));
        ((GuiButton)this.buttonList.get(1)).enabled = false;
        this.textField = new GuiTextField(2, this.fontRendererObj, this.width / 2 - 200 + 50, this.height / 3 * 2 + 10, 100, 20);
        this.textField.setFocused(true);
        super.initGui();
    }
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
		
	}
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks,
			int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(guiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}

}
