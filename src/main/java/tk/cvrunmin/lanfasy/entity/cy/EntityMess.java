package tk.cvrunmin.lanfasy.entity.cy;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import tk.cvrunmin.lanfasy.util.LogHelper;

public abstract class EntityMess extends EntityMob{
	protected byte mode = 0;
    protected boolean forceMode = false;
    protected boolean isInvulnerable = false;
    protected boolean alwaysSkipDamage = false;
	public EntityMess(World worldIn) {
		super(worldIn);
	}
    protected void entityInit()
    {
        super.entityInit();
        /** Status of Boss (odd number to become invulnerable) **/
//        this.dataWatcher.addObject(31, Byte.valueOf((byte)0));
    }
    public void onLivingUpdate()
    {
    	if (!isAlwaysSkipDamage()) {
    	    if (getMode() % 2 == 1) {
    	    	setInvulnerable(true);
    	    }
    	    else{
    	    	setInvulnerable(false);
    	    }
    	}
    	super.onLivingUpdate();
    }
    public void readEntityFromNBT(NBTTagCompound tagCompund)
    {
        super.readEntityFromNBT(tagCompund);
        this.setMode(tagCompund.getByte("EntityMode"));
        this.lockMode(tagCompund.getBoolean("LockMode"));
        this.setInvulnerable(tagCompund.getBoolean("IsInvulnerable"));
        this.setAlwaysSkipDamage(tagCompund.getBoolean("AlwaysInvulnerable"));
    }
    public void writeEntityToNBT(NBTTagCompound tagCompound)
    {
        super.writeEntityToNBT(tagCompound);
        tagCompound.setByte("EntityMode", getMode());
        tagCompound.setBoolean("LockMode", this.isLockedMode());
        tagCompound.setBoolean("IsInvulnerable", this.isInvulnerable());
        tagCompound.setBoolean("AlwaysInvulnerable", this.isAlwaysSkipDamage());
    }
    protected void setInvulnerable(boolean flag){
    	if (!isAlwaysSkipDamage()) {
        	if (getMode() % 2 == 0 && flag) {
            	this.setMode((byte) (getMode() + 1));
            	isInvulnerable = true;
    		} 
        	else if (getMode() % 2 == 1 && !flag) {
            	this.setMode((byte) (getMode() - 1));
            	isInvulnerable = false;
    		}	
		}
    }
    public boolean isInvulnerable(){
    	LogHelper.info(isInvulnerable + "");
    	return isInvulnerable;
    }
    protected void setAlwaysSkipDamage(boolean flag){
    	alwaysSkipDamage = flag;
    }
    public boolean isAlwaysSkipDamage(){
    	return alwaysSkipDamage;
    }
    protected void setMode(byte modeid){
    	if (!isLockedMode()) {
        	this.mode = modeid;
	}
    }
    public byte getMode(){
    	return mode;
    }
    protected void lockMode(boolean flag){
    	forceMode = flag;
    }
    public boolean isLockedMode(){
    	return forceMode;
    }
}
