package cm.dto;

import java.util.UUID;

/**
 * Created by li hong on 2015/5/15.
 */
public class CourseDto {
	private UUID id;
	private String name;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CourseDto(UUID id, String name) {
		this.id = id;
		this.name = name;
	}
}
