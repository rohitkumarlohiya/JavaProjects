package org.firstonlineuniversity.formvalidation.rest;

import java.util.ArrayList;
import java.util.List;

import org.firstonlineuniversity.models.courses.CourseContent;
import org.springframework.stereotype.Component;

@Component
public class EditCoursesFilesFormValidation {
	public List<Errors> validateForm(CourseContent courseFiles) {
		List<Errors> errorsList = new ArrayList<Errors>();

		if (courseFiles.getId() == 0) {
			errorsList.add(new Errors("id", "File id can not be empty !"));
		}
		if (courseFiles.getFileDescription() == null
				|| courseFiles.getFileDescription().isEmpty()) {
			errorsList.add(new Errors("fileDescription",
					"File Description can not be empty !"));
		}
		if (courseFiles.getFileDisplayName() == null
				|| courseFiles.getFileDisplayName().isEmpty()) {
			errorsList.add(new Errors("fileDisplayName",
					"File Display Name can not be empty !"));
		}
		if (courseFiles.getFileDuration() == 0) {
			errorsList.add(new Errors("fileDuration",
					"File Duration can not be empty !"));
		}
		if (courseFiles.getFileFormat() == null
				|| courseFiles.getFileFormat().isEmpty()) {
			errorsList.add(new Errors("fileFormat",
					"File Format can not be empty !"));
		}
		if (courseFiles.getFileIndex() == 0) {
			errorsList.add(new Errors("fileIndex",
					"File Index can not be empty !"));
		}
		if (courseFiles.getFileName() == null
				|| courseFiles.getFileName().isEmpty()) {
			errorsList
					.add(new Errors("fileNam", "File Name can not be empty !"));
		}
		if (courseFiles.getFilePath() == null
				|| courseFiles.getFilePath().isEmpty()) {
			errorsList.add(new Errors("filePath",
					"File Path can not be empty !"));
		}
		if (courseFiles.getFileSize() == 0) {
			errorsList.add(new Errors("fileSize",
					"File Size can not be empty !"));
		}
		if (courseFiles.getFileType() == null
				|| courseFiles.getFileType().isEmpty()) {
			errorsList.add(new Errors("fileType",
					"File Type can not be empty !"));
		}
		if (courseFiles.getCourse() == null
				|| courseFiles.getCourse().getId() == 0) {
			errorsList
					.add(new Errors("course", "Course id can not be empty !"));
		}
		if (courseFiles.getLecture() == null
				|| courseFiles.getLecture().getId() == 0) {
			errorsList.add(new Errors("lecture",
					"lecture id can not be empty !"));
		}
		return errorsList;
	}
}
