package tk.cvrunmin.lanfasy.entity.item;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cvrunmin.lanfasy.util.DamageSourceLF;

public class EntityRedstal extends EntityAFCrystal{
    /** Used to create the rotation animation when rendering the crystal. */
    public int innerRotation;
    public int health;
    public EntityPlayer target;

    public EntityRedstal(World worldIn)
    {
        super(worldIn);
        this.preventEntitySpawning = true;
        this.setSize(2.0F, 2.0F);
        this.health = 5;
        this.innerRotation = this.rand.nextInt(100000);
    }
    @SideOnly(Side.CLIENT)
    public EntityRedstal(World worldIn, double x, double y, double z)
    {
        this(worldIn);
        this.setPosition(x, y, z);
    }
    protected boolean canTriggerWalking()
    {
        return false;
    }

    protected void entityInit()
    {
        this.dataWatcher.addObject(8, Integer.valueOf(this.health));
    }
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        ++this.innerRotation;
        this.dataWatcher.updateObject(8, Integer.valueOf(this.health));
        if(!this.isDead){
        	checkPlayer();
        }
    }
    private void checkPlayer(){
        if(target != null){
            if (this.target.isDead)
            {
                this.target = null;
            }
            else if (this.ticksExisted % 10 == 0)
            {
                target.attackEntityFrom(DamageSourceLF.AFProtecting, 1);
            }
        }
        if (this.rand.nextInt(10) == 0)
        {
        	EntityPlayer entity = null;
        	List list = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().expand(5D, 5D, 5D));
            Iterator iterator = list.iterator();
            double d0 = Double.MAX_VALUE;
            while (iterator.hasNext())
            {
                EntityPlayer entity1 = (EntityPlayer)iterator.next();
                double d1 = entity1.getDistanceSqToEntity(this);

                if (d1 < d0)
                {
                    d0 = d1;
                    entity = entity1;
                }
            }
            this.target = entity;
        }
    }
	protected void readEntityFromNBT(NBTTagCompound tagCompund) {

	}
	protected void writeEntityToNBT(NBTTagCompound tagCompound) {

	}
    public boolean canBeCollidedWith()
    {
        return true;
    }
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else
        {
            if (!this.isDead && !this.worldObj.isRemote)
            {
                this.health -= 1;

                if (this.health <= 0)
                {
                    this.setDead();

                    if (!this.worldObj.isRemote)
                    {
                        this.worldObj.createExplosion((Entity)null, this.posX, this.posY, this.posZ, 6.0F, false);
                    }
                }
            }

            return true;
        }
    }
}
