package com.jozufozu.flywheel.backend.instancing;

import java.util.Arrays;
import java.util.stream.Stream;

import com.jozufozu.flywheel.api.InstancerManager;
import com.jozufozu.flywheel.api.instance.DynamicInstance;
import com.jozufozu.flywheel.api.instance.Instance;
import com.jozufozu.flywheel.api.instance.TickableInstance;
import com.jozufozu.flywheel.backend.instancing.blockentity.BlockEntityInstanceManager;
import com.jozufozu.flywheel.core.structs.FlatLit;
import com.jozufozu.flywheel.light.LightListener;
import com.jozufozu.flywheel.light.LightProvider;
import com.jozufozu.flywheel.light.ListenerStatus;
import com.jozufozu.flywheel.util.box.ImmutableBox;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

/**
 * A general interface providing information about any type of thing that could use Flywheel's instanced rendering.
 * Right now, that's only {@link BlockEntityInstanceManager}, but there could be an entity equivalent in the future.
 */
public abstract class AbstractInstance implements Instance, LightListener {

	protected final InstancerManager instancerManager;
	public final Level level;

	public AbstractInstance(InstancerManager instancerManager, Level level) {
		this.instancerManager = instancerManager;
		this.level = level;
	}

	/**
	 * Initialize models here.
	 */
	public void init() {

	}

	/**
	 * Free any acquired resources.
	 */
	public abstract void remove();

	/**
	 * Update instance data here. Good for when data doesn't change very often and when animations are GPU based.
	 * Don't query lighting data here, that's handled separately in {@link #updateLight()}.
	 *
	 * <br><br> If your animations are complex or more CPU driven, see {@link DynamicInstance} or {@link TickableInstance}.
	 */
	public void update() {
	}

	/**
	 * Called after construction and when a light update occurs in the world.
	 *
	 * <br> If your model needs it, update light here.
	 */
	public void updateLight() {
	}

	/**
	 * When an instance is reset, the instance is deleted and re-created.
	 *
	 * <p>
	 *     Just before {@link #update()} would be called, {@code shouldReset()} is checked.
	 *     If this function returns {@code true}, then this instance will be {@link #remove removed},
	 *     and another instance will be constructed to replace it. This allows for more sane resource
	 *     acquisition compared to trying to update everything within the lifetime of an instance.
	 * </p>
	 *
	 * @return {@code true} if this instance should be discarded and refreshed.
	 */
	public boolean shouldReset() {
		return false;
	}

	@Override
	public ListenerStatus status() {
		return ListenerStatus.OKAY;
	}

	@Override
	public void onLightUpdate(LightProvider world, LightLayer type, ImmutableBox changed) {
		updateLight();
	}

	protected void relight(BlockPos pos, FlatLit<?>... models) {
		relight(level.getBrightness(LightLayer.BLOCK, pos), level.getBrightness(LightLayer.SKY, pos), models);
	}

	protected <L extends FlatLit<?>> void relight(BlockPos pos, Stream<L> models) {
		relight(level.getBrightness(LightLayer.BLOCK, pos), level.getBrightness(LightLayer.SKY, pos), models);
	}

	protected void relight(int block, int sky, FlatLit<?>... models) {
		relight(block, sky, Arrays.stream(models));
	}

	protected <L extends FlatLit<?>> void relight(int block, int sky, Stream<L> models) {
		models.forEach(model -> model.setBlockLight(block)
				.setSkyLight(sky));
	}
}
