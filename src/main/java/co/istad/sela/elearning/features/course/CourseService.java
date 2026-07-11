package co.istad.sela.elearning.features.course;

import co.istad.sela.elearning.features.course.dto.CourseResponse;
import co.istad.sela.elearning.features.course.dto.CreateCourseRequest;

public interface CourseService {

    // Create a new course
    CourseResponse createCourse(CreateCourseRequest createCourseRequest);

}
