package io.github.cottonmc.libam.api;

import net.minecraft.client.render.entity.model.ModelWithHat;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.passive.AbstractTraderEntity;
import net.minecraft.util.math.MathHelper;

public abstract class CustomHelmetModel<T extends LivingEntity> extends CustomArmorModel<T> implements ModelWithHat, ModelWithHead {

	public CustomHelmetModel(float scale, int textureWidth, int textureHeight) {
		super(scale, textureWidth, textureHeight);
	}

	@Override
	public void setAngles(T entity, float float_1, float float_2, float roll, float yaw, float pitch, float float_3) {
		boolean isRollingHead = false;
		if (entity instanceof AbstractTraderEntity) {
			isRollingHead = ((AbstractTraderEntity)entity).getHeadRollingTimeLeft() > 0;
		}

		if (entity instanceof ArmorStandEntity) {
			ArmorStandEntity stand = (ArmorStandEntity) entity;
			this.getHead().yaw = stand.getHeadRotation().getYaw() * DEGREE_IN_RADIANS;
			this.getHead().pitch = stand.getHeadRotation().getPitch() * DEGREE_IN_RADIANS;
			this.getHead().roll = stand.getHeadRotation().getRoll() * DEGREE_IN_RADIANS;
		} else {
			this.getHead().yaw = yaw * DEGREE_IN_RADIANS;
			this.getHead().pitch = pitch * DEGREE_IN_RADIANS;
			if (isRollingHead) {
				this.getHead().roll = 0.3F * MathHelper.sin(0.45F * roll);
				this.getHead().pitch = 0.4F;
			} else {
				this.getHead().roll = 0.0F;
			}
		}

	}

}
