package co.istad.sela.elearning;

import co.istad.sela.elearning.features.course.Course;
import co.istad.sela.elearning.features.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@EnableConfigurationProperties
@EnableJpaAuditing
@SpringBootApplication
public class ElearningA01M1Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ElearningA01M1Application.class, args);
    }

    private CourseRepository courseRepository;

    @Autowired
    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Seed data only if table is empty
        if (courseRepository.allCourses().isEmpty()) {
            Course c1 = new Course();
            c1.setTitle("Java OOP Basics");

            Course c2 = new Course();
            c2.setTitle("Spring Boot Fundamentals");

            courseRepository.save(c1);
            courseRepository.save(c2);
        }

        // Now query safely
        List<Course> courses = courseRepository.allCourses();
        courses.forEach(c -> IO.print(c.getTitle()));

        Course course = courseRepository.byId(2);
        if (course != null) {
            IO.print("Found: " + course.getTitle());
        } else {
            IO.print("Course id 2 not found");
        }
    }
}
