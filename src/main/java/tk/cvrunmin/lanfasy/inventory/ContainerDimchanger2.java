package tk.cvrunmin.lanfasy.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerDimchanger2 extends Container{
    private IInventory Inventory;
    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
    public IInventory craftResult = new InventoryCraftResult();
    public ContainerDimchanger2(IInventory playerInventory, IInventory inventoryIn)
    {
        this.Inventory = inventoryIn;
        int i;
        int j;
        this.addSlotToContainer(new Slot(inventoryIn, 0, 17 + 0 * 18 + 8, 62 + 0 * 18 - 8));
        this.addSlotToContainer(new Slot(inventoryIn, 1, 17 + 1 * 18 + 8, 62 + 0 * 18 - 8));
        this.addSlotToContainer(new Slot(inventoryIn, 2, 17 - 1 * 18 + 8, 62 + 1 * 18 - 8));
        this.addSlotToContainer(new Slot(inventoryIn, 3, 17 + 2 * 18 + 8, 62 + 1 * 18 - 8));
        this.addSlotToContainer(new Slot(inventoryIn, 4, 17 - 1 * 18 + 8, 62 + 2 * 18 - 8));
        this.addSlotToContainer(new Slot(inventoryIn, 5, 17 + 2 * 18 + 8, 62 + 2 * 18 - 8));
        this.addSlotToContainer(new Slot(inventoryIn, 6, 17 + 0 * 18 + 8, 62 + 3 * 18 - 8));
        this.addSlotToContainer(new Slot(inventoryIn, 7, 17 + 1 * 18 + 8, 62 + 3 * 18 - 8));
        for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 8, 84 + 8 + i * 18, 4 + j * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(playerInventory, i, 142 + 8, 4 + i * 18));
        }
    }

	@Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.Inventory.isUseableByPlayer(playerIn);
    }
	@Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < 8)
            {
                if (!this.mergeItemStack(itemstack1, 8, 45, true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, 8, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(playerIn, itemstack1);
        }

        return itemstack;
    }
}
