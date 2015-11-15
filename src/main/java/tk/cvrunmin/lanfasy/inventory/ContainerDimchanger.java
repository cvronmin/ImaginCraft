package tk.cvrunmin.lanfasy.inventory;

import tk.cvrunmin.lanfasy.item.hec.crafting.HECT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;

public class ContainerDimchanger extends Container{
	private InventoryInori craftmatrix = new InventoryInori(this);
	private IInventory craftResult = new InventoryCraftResult();
//    private IInventory Inventory;
    private World worldObj;
    public ContainerDimchanger(InventoryPlayer playerInventory, IInventory Inventory, World worldIn)
    {
//    	this.Inventory = Inventory;
        this(playerInventory, worldIn);
    }
    public ContainerDimchanger(InventoryPlayer playerInv, World worldIn) {
        int i;
        int j;
        this.worldObj = playerInv.player.worldObj;
        this.addSlotToContainer(new SlotIroni(playerInv.player, this.craftmatrix, this.craftResult, 0, 17 + 8 + 18 - 4 + 18 * 1 + 9 - 1, 62 + 0 * 18 - 8 + 18 + 9 - 1));
        this.addSlotToContainer(new Slot(this.craftmatrix, 1, 17 + 8 + 18 - 4 - 1         , 62 + 0 * 18 - 8 + 18 + 9 - 1));
        this.addSlotToContainer(new Slot(this.craftmatrix, 2, 17 + 8 + 18 - 4 + 18 * 3 - 1, 62 + 0 * 18 - 8 + 18 + 9 - 1));
        
        this.addSlotToContainer(new Slot(this.craftmatrix, 3, 17 + 2 * 18 + 8 - 3 - 2, 62 - 1 * 18 - 10 + 1));
        this.addSlotToContainer(new Slot(this.craftmatrix, 4, 17 + 2 * 18 + 8 + 15 - 2, 62 - 1 * 18 - 10 + 1));
        
        this.addSlotToContainer(new Slot(this.craftmatrix, 5, 17 + 8 + 18 - 4 - 1 - 18, 62 + 0 * 18 - 8 + 18 + 9 - 1 - 9 + 1));
        this.addSlotToContainer(new Slot(this.craftmatrix, 6, 17 + 8 + 18 - 4 - 1 - 18, 62 + 0 * 18 - 8 + 18 + 9 - 1 + 9 + 1));
        
        this.addSlotToContainer(new Slot(this.craftmatrix, 7, 17 + 8 + 18 - 4 + 18 * 3 - 1 + 18, 62 + 0 * 18 - 8 + 18 + 9 - 1 - 9 + 1));
        this.addSlotToContainer(new Slot(this.craftmatrix, 8, 17 + 8 + 18 - 4 + 18 * 3 - 1 + 18, 62 + 0 * 18 - 8 + 18 + 9 - 1 + 9 + 1));
        
        this.addSlotToContainer(new Slot(this.craftmatrix, 9, 17 + 2 * 18 + 8 - 3 - 2, 62 - 1 * 18 - 10 + 1 + 18 * 5));
        this.addSlotToContainer(new Slot(this.craftmatrix, 10, 17 + 2 * 18 + 8 + 15 - 2, 62 - 1 * 18 - 10 + 1 + 18 * 5));
        for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 84 + 8 + i * 18 + 80, 4 + 144 - j * 18 + 2));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(playerInv, i, 142 + 8 + 80, 4 + 144 - i * 18 + 2));
        }
		this.onCraftMatrixChanged(this.craftmatrix);
	}
	public void onContainerClosed(EntityPlayer playerIn)
    {
        super.onContainerClosed(playerIn);

        if (!this.worldObj.isRemote)
        {
            for (int i = 0; i < 11; ++i)
            {
				final ItemStack slot = this.craftmatrix.getStackInSlotOnClosing(i);

				if (slot != null)
				{
					playerIn.entityDropItem(slot, 0.0F);
				}
            }
        }
    }
    public void onCraftMatrixChanged(IInventory inventoryIn)
    {
        this.craftResult.setInventorySlotContents(0, HECT.getInstance().findCoreRecipe(this.craftmatrix));
    }
	@Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
//        return this.Inventory.isUseableByPlayer(playerIn);
		return true;
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

            if (index < 11)
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
