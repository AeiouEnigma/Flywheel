package com.jozufozu.flywheel.api.struct;

import com.jozufozu.flywheel.core.model.ModelTransformer;

public interface BatchedStructType<S> extends StructType<S> {
	void transform(S d, ModelTransformer.Params b);
}
