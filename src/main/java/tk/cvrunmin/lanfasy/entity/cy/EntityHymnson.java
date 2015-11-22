package tk.cvrunmin.lanfasy.entity.cy;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import tk.cvrunmin.lanfasy.util.LogHelper;

public class EntityHymnson extends EntityMob implements IBossDisplayData{
    protected static final IAttribute reinforcementChance = (new RangedAttribute((IAttribute)null, "cyboss.spawnReinforcements", 0.0D, 0.0D, 1.0D)).setDescription("Spawn Reinforcements Chance");
    private EntityAIAttackOnCollide attackOnCollide = new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true);
    private EntityAIWatchClosest watchClosest = new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F);
    private EntityAIMoveThroughVillage moveThroughVillage = new EntityAIMoveThroughVillage(this, 5.0D, false);
    private EntityAIHurtByTarget hurtByTarget = new EntityAIHurtByTarget(this, true, new Class[] {EntityUnknown.class});
    private EntityAINearestAttackableTarget nearestAttackableTarget = new EntityAINearestAttackableTarget(this, EntityPlayer.class, true);
    protected boolean addedModify = false;
	public EntityHymnson(World worldIn) {
		super(worldIn);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiAvoidExplodingCreepers);
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAILookIdle(this));
//        this.applyEntityBossAI();
	}
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(400D);
    }
    protected void applyEntityBossAI()
    {
        this.tasks.addTask(2, attackOnCollide);
        this.tasks.addTask(8, watchClosest);
        this.tasks.addTask(6, moveThroughVillage);
        this.targetTasks.addTask(1, hurtByTarget);
        this.targetTasks.addTask(2, nearestAttackableTarget);
    }
    protected void removeEntityBossAI()
    {
        this.tasks.removeTask(attackOnCollide);
        this.tasks.removeTask(watchClosest);
        this.tasks.removeTask(moveThroughVillage);
        this.targetTasks.removeTask(hurtByTarget);
        this.targetTasks.removeTask(nearestAttackableTarget);
    }
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
        this.dataWatcher.addObject(17, Float.valueOf(0.0F));
        this.dataWatcher.addObject(10, Byte.valueOf((byte)0));
        /** Status of Boss (0=defence,2=attack,4=escape,+1 to become invincible) **/
        this.dataWatcher.addObject(31, Byte.valueOf((byte)0));
    }
    protected void setMode(byte modeid){
    	this.dataWatcher.updateObject(31, Byte.valueOf(modeid));
    }
    protected byte getMode(){
    	return dataWatcher.getWatchableObjectByte(31);
    }
    protected void setInvincible(boolean flag){
    	if (dataWatcher.getWatchableObjectByte(31) % 2 == 0 && flag) {
        	this.dataWatcher.updateObject(31, dataWatcher.getWatchableObjectByte(31) + 1);
		} 
    	else if (dataWatcher.getWatchableObjectByte(31) % 2 == 1 && !flag) {
        	this.dataWatcher.updateObject(31, dataWatcher.getWatchableObjectByte(31) - 1);
		}
    }
    public void readEntityFromNBT(NBTTagCompound tagCompund)
    {
        super.readEntityFromNBT(tagCompund);
        this.setMode(tagCompund.getByte("EntityMode"));
    }
    public void writeEntityToNBT(NBTTagCompound tagCompound)
    {
        super.writeEntityToNBT(tagCompound);
        tagCompound.setByte("EntityMode", getMode());
    }
    public void onLivingUpdate()
    {
    	if (this.getHealth() / this.getMaxHealth() > 0.3f) {
			setMode((byte)0);
		}
    	if (this.getHealth() / this.getMaxHealth() <= 0.3f) {
			setMode((byte)2);
		}
    	if (this.getHealth() / this.getMaxHealth() <= 0.05f) {
			setMode((byte)4);
			removeEntityBossAI();
		}
    	switch (getMode()) {
		case 0:
			onDefenceMode();
			break;
		case 2:
			onAttackMode();
			break;
		case 4:
			onEscapeMode();
			break;
		case 1:
			break;
		case 3:
			break;
		case 5:
			break;
		default:
			break;
		}
    	super.onLivingUpdate();
    }
    protected void onDefenceMode(){
    	if (this.getHealth() < this.getMaxHealth()) {
			this.heal(0.05f);
		}
    	if (!worldObj.isRemote) {
            int tri = rand.nextInt(100);
            if (tri < 25) {
            	for (int x = -1; x <= 1; x++) {
                	for (int y = -1; y <= 1; y++) {
                    	for (int z = -1; z <= 1; z++) {
                    		Block blk = worldObj.getBlockState(new BlockPos(this.posX + x, this.posY + y, this.posZ + z)).getBlock();
                    		if (blk.equals(Blocks.water) && rand.nextBoolean() && (y != posY + 1 && x != posX && z != posZ)) {
        						worldObj.setBlockState(new BlockPos(this.posX + x, this.posY + y, this.posZ + z), Blocks.lava.getDefaultState());
        						break;
        					}
                		}
            		}
        		}
    		}
		}
    }
    protected void onAttackMode(){
    	if(!worldObj.isRemote){
            int tri = rand.nextInt(100);
        	if(tri < 5){
        	worldObj.addWeatherEffect(new EntityLightningBolt(worldObj, prevPosX + rand.nextDouble() * 10 * (rand.nextBoolean() ? -1 : 1), prevPosY + rand.nextDouble() * 10 * (rand.nextBoolean() ? -1 : 1), prevPosZ + rand.nextDouble() * 10 * (rand.nextBoolean() ? -1 : 1)));
        	}
        	if (tri < 5) {
            	for (int x = -5; x <= 5; x++) {
                	for (int y = -5; y <= 5; y++) {
                    	for (int z = -5; z <= 5; z++) {
                    		Block blk = worldObj.getBlockState(new BlockPos(this.posX + x, this.posY + y, this.posZ + z)).getBlock();
                    		if (blk.equals(Blocks.water) && rand.nextBoolean() && (y != posY + 1 && x != posX && z != posZ)) {
                    	    	for (int x1 = -1; x1 <= 1; x1++) {
                    	    		for (int z1 = -1; z1 <= 1; z1++) {
                    	    			Block blk2 = worldObj.getBlockState(new BlockPos(this.posX + x + x1, this.posY + y, this.posZ + z + z1)).getBlock();
                    	    			if (blk2.equals(Blocks.water) && (y != posY + 1 && x != posX && z != posZ)) {
                    	    				worldObj.setBlockState(new BlockPos(this.posX + x + x1, this.posY + y, this.posZ + z + z1), Blocks.lava.getDefaultState());
                    	    			}
                    	    		}
                    			}
        						break;
        					}
                		}
            		}
        		}
			}
        	
    	}
    }
    protected void onEscapeMode(){
    	if (!addedModify) {
        	this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(new AttributeModifier("Escape Mode Bonus", 0.2d, 0));
        	addedModify = true;
		}
    }
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (super.attackEntityFrom(source, amount))
        {
            EntityLivingBase entitylivingbase = this.getAttackTarget();

            if (entitylivingbase == null && source.getEntity() instanceof EntityLivingBase)
            {
                entitylivingbase = (EntityLivingBase)source.getEntity();
            }

            int i = MathHelper.floor_double(this.posX);
            int j = MathHelper.floor_double(this.posY);
            int k = MathHelper.floor_double(this.posZ);
/*            if (entitylivingbase != null && this.worldObj.getDifficulty() == EnumDifficulty.HARD && (double)this.rand.nextFloat() < this.getEntityAttribute(reinforcementChance).getAttributeValue()) {
				EntityUnknown entityunkonwn = new EntityUnknown(worldObj);
                for (int l = 0; l < 50; ++l)
                {
                    int i1 = i + MathHelper.getRandomIntegerInRange(this.rand, 7, 40) * MathHelper.getRandomIntegerInRange(this.rand, -1, 1);
                    int j1 = j + MathHelper.getRandomIntegerInRange(this.rand, 7, 40) * MathHelper.getRandomIntegerInRange(this.rand, -1, 1);
                    int k1 = k + MathHelper.getRandomIntegerInRange(this.rand, 7, 40) * MathHelper.getRandomIntegerInRange(this.rand, -1, 1);

                    if (World.doesBlockHaveSolidTopSurface(this.worldObj, new BlockPos(i1, j1 - 1, k1)) && this.worldObj.getLightFromNeighbors(new BlockPos(i1, j1, k1)) < 10)
                    {
                        entityunkonwn.setPosition((double)i1, (double)j1, (double)k1);

                        if (!this.worldObj.isAnyPlayerWithinRangeAt((double)i1, (double)j1, (double)k1, 7.0D) && this.worldObj.checkNoEntityCollision(entityunkonwn.getEntityBoundingBox(), entityunkonwn) && this.worldObj.getCollidingBoundingBoxes(entityunkonwn, entityunkonwn.getEntityBoundingBox()).isEmpty() && !this.worldObj.isAnyLiquid(entityunkonwn.getEntityBoundingBox()))
                        {
                            this.worldObj.spawnEntityInWorld(entityunkonwn);
                            if (entitylivingbase != null) entityunkonwn.setAttackTarget(entitylivingbase);
                            entityunkonwn.onInitialSpawn(this.worldObj.getDifficultyForLocation(new BlockPos(entityunkonwn)), (IEntityLivingData)null);
                            this.getEntityAttribute(reinforcementChance).applyModifier(new AttributeModifier("CYCBoss reinforcement caller charge", -0.05000000074505806D, 0));
                            entityunkonwn.getEntityAttribute(reinforcementChance).applyModifier(new AttributeModifier("CYCBoss reinforcement callee charge", -0.05000000074505806D, 0));
                            break;
                        }
                    }
                }
			}*/
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean attackEntityAsMob(Entity entityIn)
    {
        return super.attackEntityAsMob(entityIn);
    }
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata)
    {
        Object p_180482_2_1 = super.onInitialSpawn(difficulty, livingdata);
        float f = difficulty.getClampedAdditionalDifficulty();
        this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * f);

        this.setEquipmentBasedOnDifficulty(difficulty);
        this.setEnchantmentBasedOnDifficulty(difficulty);

        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextDouble() * 0.05000000074505806D, 0));
        double d0 = this.rand.nextDouble() * 1.5D * (double)f;

        if (d0 > 1.0D)
        {
            this.getEntityAttribute(SharedMonsterAttributes.followRange).applyModifier(new AttributeModifier("Random spawn bonus", d0, 2));
        }

        if (this.rand.nextFloat() < f * 0.05F)
        {
            this.getEntityAttribute(reinforcementChance).applyModifier(new AttributeModifier("Leader bonus", this.rand.nextDouble() * 0.25D + 0.5D, 0));
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).applyModifier(new AttributeModifier("Leader bonus", this.rand.nextDouble() * 3.0D + 1.0D, 2));
        }

        return (IEntityLivingData)p_180482_2_1;
    }
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
        super.setEquipmentBasedOnDifficulty(difficulty);
        this.setCurrentItemOrArmor(0, new ItemStack(Items.iron_sword));
    }
}
