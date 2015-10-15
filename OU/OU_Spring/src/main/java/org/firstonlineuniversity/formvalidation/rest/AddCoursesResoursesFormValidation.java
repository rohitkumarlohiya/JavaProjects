package org.firstonlineuniversity.formvalidation.rest;

import java.util.ArrayList;
import java.util.List;

import org.firstonlineuniversity.models.courses.CourseResources;
import org.springframework.stereotype.Component;

@Component
public class AddCoursesResoursesFormValidation {
	public List<Errors> validateForm(CourseResources courseResources) {
		List<Errors> errorsList = new ArrayList<Errors>();

		if (courseResources.getFileDescription() == null
				|| courseResources.getFileDescription().isEmpty()) {
			errorsList.add(new Errors("fileDescription",
					"File Description can not be empty !"));
		}
		if (courseResources.getFileDisplayName() == null
				|| courseResources.getFileDisplayName().isEmpty()) {
			errorsList.add(new Errors("fileDisplayName",
					"File Display Name can not be empty !"));
		}
		if (courseResources.getFileFormat() == null
				|| courseResources.getFileFormat().isEmpty()) {
			errorsList.add(new Errors("fileFormat",
					"File Format can not be empty !"));
		}
		if (courseResources.getFileIndex() == 0) {
			errorsList.add(new Errors("fileIndex",
					"File Index can not be empty !"));
		}
		if (courseResources.getFileName() == null
				|| courseResources.getFileName().isEmpty()) {
			errorsList
					.add(new Errors("fileNam", "File Name can not be empty !"));
		}
		if (courseResources.getFilePath() == null
				|| courseResources.getFilePath().isEmpty()) {
			errorsList.add(new Errors("filePath",
					"File Path can not be empty !"));
		}
		if (courseResources.getFileSize() == 0) {
			errorsList.add(new Errors("fileSize",
					"File Size can not be empty !"));
		}
		if (courseResources.getFileType() == null
				|| courseResources.getFileType().isEmpty()) {
			errorsList.add(new Errors("fileType",
					"File Type can not be empty !"));
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
