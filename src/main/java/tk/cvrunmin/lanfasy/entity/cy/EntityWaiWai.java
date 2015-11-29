package tk.cvrunmin.lanfasy.entity.cy;

import com.google.common.base.Predicate;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import tk.cvrunmin.lanfasy.entity.ai.EntityAIAttackWithDrop;
import tk.cvrunmin.lanfasy.init.LFHecItems;

public class EntityWaiWai extends EntityMess implements IBossDisplayData{
    protected static final IAttribute reinforcementChance = (new RangedAttribute((IAttribute)null, "cyboss.spawnReinforcements", 0.0D, 0.0D, 1.0D)).setDescription("Spawn Reinforcements Chance");
    private EntityAIAttackOnCollide attackOnCollide = new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true);
    private EntityAIAttackWithDrop attackWithDrop = new EntityAIAttackWithDrop(this, EntityPlayer.class, 1.0D, true);
    private EntityAIWatchClosest watchClosest = new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F);
    private EntityAIMoveThroughVillage moveThroughVillage = new EntityAIMoveThroughVillage(this, 5.0D, false);
    private EntityAIHurtByTarget hurtByTarget = new EntityAIHurtByTarget(this, true, new Class[] {EntityUnknown.class});
    private EntityAINearestAttackableTarget nearestAttackableTarget = new EntityAINearestAttackableTarget(this, EntityPlayer.class, true);
    protected boolean addedModify = false;
	public EntityWaiWai(World worldIn) {
		super(worldIn);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiAvoidExplodingCreepers);
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.applyEntityBossAI();
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
        this.tasks.addTask(8, watchClosest);
        this.tasks.addTask(6, moveThroughVillage);
        this.targetTasks.addTask(1, hurtByTarget);
        this.targetTasks.addTask(2, nearestAttackableTarget);
    }
    protected void removeEntityBossAI()
    {
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
    }

    public void onLivingUpdate()
    {
    	if (this.getHealth() / this.getMaxHealth() > 0.5f) {
			setMode((byte)0);
		}
    	if (this.getHealth() / this.getMaxHealth() <= 0.5f) {
			setMode((byte)2);
		}
    	switch (getMode()) {
		case 0:case 1:
			onAttackMode();
			break;
		case 2:case 3:
			onStrongAttackMode();
			break;
		default:
			break;
		}
    	super.onLivingUpdate();
    }
    protected void onAttackMode(){
        this.tasks.addTask(2, attackOnCollide);
        this.tasks.removeTask(attackWithDrop);
    }
    protected void onStrongAttackMode(){
        this.tasks.addTask(2, attackWithDrop);
        this.tasks.removeTask(attackOnCollide);
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
    protected Item getDropItem()
    {
        return LFHecItems.macrystal;
    }
    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
    {
        if (p_70628_1_)
        {
            int j = this.rand.nextInt(2 + p_70628_2_);

            for (int k = 0; k < j; ++k)
            {
                this.entityDropItem(new ItemStack(LFHecItems.macrystal, 1, 2), 0f);
            }
        }
    }
}
