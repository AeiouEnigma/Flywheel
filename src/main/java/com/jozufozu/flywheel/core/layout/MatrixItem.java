package com.jozufozu.flywheel.core.layout;

import java.util.function.Consumer;

import com.jozufozu.flywheel.backend.gl.GlNumericType;
import com.jozufozu.flywheel.backend.gl.VertexAttribute;

public record MatrixItem(int rows, int cols) implements LayoutItem {

	@Override
	public void provideAttributes(Consumer<VertexAttribute> consumer) {
		for (int i = 0; i < rows; i++) {
			consumer.accept(new VertexAttribute(cols, GlNumericType.FLOAT, false));
		}
	}

}
