package com.jozufozu.flywheel.backend.gl.shader;

import org.lwjgl.opengl.GL20;

public enum ShaderType {
	VERTEX("vertex", "VERTEX_SHADER", "vert", GL20.GL_VERTEX_SHADER),
	FRAGMENT("fragment", "FRAGMENT_SHADER", "frag", GL20.GL_FRAGMENT_SHADER),
	;

	public final String name;
	public final String define;
	public final String extension;
	public final int glEnum;

	ShaderType(String name, String define, String extension, int glEnum) {
		this.name = name;
		this.define = define;
		this.extension = extension;
		this.glEnum = glEnum;
	}

	public String getDefineStatement() {
		return "#define " + define + "\n";
	}

	public String getFileName(String baseName) {
		return baseName + "." + extension;
	}
}
