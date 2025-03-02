package com.jozufozu.flywheel.api;

import com.jozufozu.flywheel.core.model.ModelSupplier;

public interface InstancerFactory<D extends InstanceData> {

	/**
	 * Get an instancer for the given model. Calling this method twice with the same key will return the same instancer.
	 *
	 * @param modelKey An object that uniquely identifies and provides the model.
	 * @return An instancer for the given model, capable of rendering many copies for little cost.
	 */
	Instancer<D> model(ModelSupplier modelKey);

}
