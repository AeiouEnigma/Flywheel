package com.jozufozu.flywheel.core.compile;

import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;
import static org.lwjgl.opengl.GL20.glGetProgrami;
import static org.lwjgl.opengl.GL20.glLinkProgram;

import com.jozufozu.flywheel.backend.Backend;
import com.jozufozu.flywheel.backend.gl.shader.GlProgram;
import com.jozufozu.flywheel.backend.gl.shader.GlShader;

import net.minecraft.resources.ResourceLocation;

public class ProgramAssembler {
	public final int program;
	private final ResourceLocation name;

	public ProgramAssembler(ResourceLocation name) {
		this.name = name;
		this.program = glCreateProgram();
	}

	/**
	 * Links the attached shaders to this program.
	 */
	public ProgramAssembler link() {
		glLinkProgram(this.program);

		String log = glGetProgramInfoLog(this.program);

		if (!log.isEmpty()) {
			Backend.LOGGER.debug("Program link log for " + name + ": " + log);
		}

		int result = glGetProgrami(this.program, GL_LINK_STATUS);

		if (result != GL_TRUE) {
			throw new RuntimeException("Shader program linking failed, see log for details");
		}

		return this;
	}

	public ProgramAssembler attachShader(GlShader glShader) {
		glAttachShader(this.program, glShader.handle());
		return this;
	}

	public <P extends GlProgram> P build(GlProgram.Factory<P> factory) {
		return factory.create(name, program);
	}
}
