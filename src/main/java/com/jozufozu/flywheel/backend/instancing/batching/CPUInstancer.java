package com.jozufozu.flywheel.backend.instancing.batching;

import com.jozufozu.flywheel.api.InstanceData;
import com.jozufozu.flywheel.api.struct.BatchedStructType;
import com.jozufozu.flywheel.backend.instancing.AbstractInstancer;
import com.jozufozu.flywheel.backend.instancing.TaskEngine;
import com.jozufozu.flywheel.backend.model.DirectVertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public class CPUInstancer<D extends InstanceData> extends AbstractInstancer<D> {

//	private final Batched<D> batchingType;
//
//	final ModelTransformer sbb;

	public CPUInstancer(BatchedStructType<D> type) {
		super(type::create);
//		batchingType = type;
//
//		sbb = new ModelTransformer(modelData.get());
	}

	void submitTasks(PoseStack stack, TaskEngine pool, DirectVertexConsumer consumer) {
//		int instances = getInstanceCount();
//
//		while (instances > 0) {
//			int end = instances;
//			instances -= 512;
//			int start = Math.max(instances, 0);
//
//			int verts = getModelVertexCount() * (end - start);
//
//			DirectVertexConsumer sub = consumer.split(verts);
//
//			pool.submit(() -> drawRange(stack, sub, start, end));
//		}
	}

	private void drawRange(PoseStack stack, VertexConsumer buffer, int from, int to) {
//		ModelTransformer.Params params = new ModelTransformer.Params();
//
//		for (D d : data.subList(from, to)) {
//			params.loadDefault();
//
//			batchingType.transform(d, params);
//
//			sbb.renderInto(params, stack, buffer);
//		}
	}

	void drawAll(PoseStack stack, VertexConsumer buffer) {
//		ModelTransformer.Params params = new ModelTransformer.Params();
//		for (D d : data) {
//			params.loadDefault();
//
//			batchingType.transform(d, params);
//
//			sbb.renderInto(params, stack, buffer);
//		}
	}

	void setup() {
//		if (anyToRemove) {
//			data.removeIf(InstanceData::isRemoved);
//			anyToRemove = false;
//		}
	}

	@Override
	public void notifyDirty() {
		// noop
	}
}
