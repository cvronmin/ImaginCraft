package tk.cvrunmin.lanfasy.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import tk.cvrunmin.lanfasy.inventory.ContainerDimchanger;

public class GuiDIMChanger extends GuiContainer{
    private final InventoryPlayer playerInventory;
    public IInventory meInventory;
    private static final ResourceLocation guiTextures = new ResourceLocation("lanfasy","textures/gui/container/dispenser.png");
    public GuiDIMChanger(InventoryPlayer playerInv, IInventory meInv, World worldIn)
    {
        super(new ContainerDimchanger(playerInv, worldIn));
        this.playerInventory = playerInv;
        this.meInventory = meInv;
        this.xSize = 256;
        this.ySize = 176;
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = "block.dimchanger";
        this.fontRendererObj.drawString(I18n.format(s, new Object[0]), 10, 6, 4210752);
        String s1 = this.playerInventory.getDisplayName().getUnformattedText();
        this.fontRendererObj.drawString(s1, this.xSize - 77 - this.fontRendererObj.getStringWidth(s1) - 5 - 5, this.ySize - 10 - 3, 4210752);
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
