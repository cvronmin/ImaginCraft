package tk.cvrunmin.lanfasy.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotIroni extends Slot{
	private final IInventory craftMatrix;
	private final EntityPlayer thePlayer;
	public SlotIroni(EntityPlayer player, IInventory matrix, IInventory inventoryIn, int index, int xPosition,
			int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		this.craftMatrix = matrix;
		this.thePlayer = player;
	}
	public boolean isItemValid(ItemStack par1ItemStack)
	{
		return false;
	}
    public void onPickupFromSlot(EntityPlayer playerIn, ItemStack stack)
    {
		for (int var2 = 0; var2 < this.craftMatrix.getSizeInventory(); ++var2)
		{
			final ItemStack var3 = this.craftMatrix.getStackInSlot(var2);

			if (var3 != null)
			{
				this.craftMatrix.decrStackSize(var2, 1);
			}
		}
    }
}
