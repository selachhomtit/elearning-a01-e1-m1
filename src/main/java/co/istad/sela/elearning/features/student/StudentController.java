package co.istad.sela.elearning.features.student;

import co.istad.sela.elearning.features.student.dto.StudentProfileResponse;
import co.istad.sela.elearning.features.student.dto.UpdateProfileStudentRequest;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/Me")
    public StudentProfileResponse me(){
          return studentService.Me();
    }

    @PatchMapping("/{userId}")
    public StudentProfileResponse UpdateProfile(@RequestBody UpdateProfileStudentRequest updateProfileStudentRequest){
        return studentService.updateProfile(updateProfileStudentRequest);
    }
}
