package io.github.cottonmc.libam.api;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

public abstract class CustomArmorModel<T extends LivingEntity> extends EntityModel<T> {
	public static final float DEGREE_IN_RADIANS = 0.017453292F;

	public float scale;
	public int textureWidth;
	public int textureHeight;

	public CustomArmorModel(float scale, int textureWidth, int textureHeight) {
		this.scale = scale;
		this.textureWidth = textureWidth;
		this.textureHeight = textureHeight;
	}

}
