package org.firstonlineuniversity.formvalidation.rest;

import java.util.ArrayList;
import java.util.List;

import org.firstonlineuniversity.models.courses.CourseResources;
import org.springframework.stereotype.Component;

@Component
public class EditCourseResourcesFormValidation {
	public List<Errors> validateForm(CourseResources courseResources) {
		List<Errors> errorsList = new ArrayList<Errors>();

		if (courseResources.getId() == 0) {
			errorsList.add(new Errors("fileDescription",
					"Resources id can not be empty !"));
		}
		if (courseResources.getFileDescription() == null
				|| courseResources.getFileDescription().isEmpty()) {
			errorsList.add(new Errors("fileDescription",
					"Resources Description can not be empty !"));
		}
		if (courseResources.getFileDisplayName() == null
				|| courseResources.getFileDisplayName().isEmpty()) {
			errorsList.add(new Errors("fileDisplayName",
					"Resources Display Name can not be empty !"));
		}
		if (courseResources.getFileFormat() == null
				|| courseResources.getFileFormat().isEmpty()) {
			errorsList.add(new Errors("fileFormat",
					"Resources Format can not be empty !"));
		}
		if (courseResources.getFileIndex() == 0) {
			errorsList.add(new Errors("fileIndex",
					"Resources Index can not be empty !"));
		}
		if (courseResources.getFileName() == null
				|| courseResources.getFileName().isEmpty()) {
			errorsList.add(new Errors("fileName",
					"File Name can not be empty !"));
		}
		if (courseResources.getFilePath() == null
				|| courseResources.getFilePath().isEmpty()) {
			errorsList.add(new Errors("filePath",
					"Resources Path can not be empty !"));
		}
		if (courseResources.getFileSize() == 0) {
			errorsList.add(new Errors("fileSize",
					"Resources Size can not be empty !"));
		}
		if (courseResources.getFileType() == null
				|| courseResources.getFileType().isEmpty()) {
			errorsList.add(new Errors("fileType",
					"Resources Type can not be empty !"));
		}
		if (courseResources.getCourse() == null
				|| courseResources.getCourse().getId() == 0) {
			errorsList
					.add(new Errors("course", "Course id can not be empty !"));
		}
		if (courseResources.getLecture() == null
				|| courseResources.getLecture().getId() == 0) {
			errorsList.add(new Errors("lecture",
					"lecture id can not be empty !"));
		}
		return errorsList;
	}
}
