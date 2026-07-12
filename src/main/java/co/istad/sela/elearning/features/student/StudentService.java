package co.istad.sela.elearning.features.student;

import co.istad.sela.elearning.features.student.dto.StudentProfileResponse;
import co.istad.sela.elearning.features.student.dto.UpdateProfileStudentRequest;

public interface StudentService {
    StudentProfileResponse Me();
    StudentProfileResponse updateProfile(UpdateProfileStudentRequest updateProfileStudentRequest);
}
