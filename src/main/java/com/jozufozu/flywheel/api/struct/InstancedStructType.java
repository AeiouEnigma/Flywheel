package com.jozufozu.flywheel.api.struct;

import java.nio.ByteBuffer;

import com.jozufozu.flywheel.core.source.FileResolution;

public interface InstancedStructType<S> extends StructType<S> {
	/**
	 * Create a {@link StructWriter} that will consume instances of S and write them to the given buffer.
	 *
	 * @param backing The buffer that the StructWriter will write to.
	 */
	StructWriter<S> getWriter(ByteBuffer backing);

	FileResolution getInstanceShader();
}
