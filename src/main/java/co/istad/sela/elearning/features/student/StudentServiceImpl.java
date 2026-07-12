package co.istad.sela.elearning.features.student;

import co.istad.sela.elearning.config.props.KeycloakAdminClientProps;
import co.istad.sela.elearning.features.auth.AuthServiceImpl;
import co.istad.sela.elearning.features.student.dto.StudentProfileResponse;
import co.istad.sela.elearning.features.student.dto.UpdateProfileStudentRequest;
import co.istad.sela.elearning.security.AuthUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService{

    private final Keycloak keycloak;
    private final KeycloakAdminClientProps props;
    private final StudentProfileMapper studentProfileMapper;
    private final StudentProfileRepository studentProfileRepository;

    @Override
    public StudentProfileResponse Me() {
        String userId = AuthUtils.extractUserId();

        UserRepresentation user = keycloak.realm(props.getTargetRealm())
                .users()
                .get(userId)
                .toRepresentation();

        log.info("User {} logged in ", user);

        StudentProfile studentProfile = studentProfileRepository.findById(user.getId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Student Profile has been found"
                ));
        return studentProfileMapper.ToStudentProfileResponse(studentProfile, user);
    }

    @Override
    public StudentProfileResponse updateProfile(UpdateProfileStudentRequest updateProfileStudentRequest) {
        String userId = AuthUtils.extractUserId();
        UserResource userResource = keycloak.realm(props.getTargetRealm())
                .users()
                .get(userId);
        UserRepresentation keycloakUser = userResource.toRepresentation();

        if(updateProfileStudentRequest.firstName() != null)
            keycloakUser.setFirstName(updateProfileStudentRequest.firstName());
        if(updateProfileStudentRequest.lastName() != null)
            keycloakUser.setLastName(updateProfileStudentRequest.lastName());

        Map<String, List<String>> attributes = new HashMap<>();

        if(updateProfileStudentRequest.gender() != null)
            attributes.put("gender", List.of(updateProfileStudentRequest.gender().getGender()));

        if (updateProfileStudentRequest.biology() != null)
            attributes.put("biology",  List.of(updateProfileStudentRequest.biology()));

        keycloakUser.setAttributes(attributes);

        StudentProfile studentProfile = studentProfileRepository.findById(keycloakUser.getId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Student Profile has been found"
                ));

        studentProfileMapper.mapUpdateStudentRequestToStudentProfile(updateProfileStudentRequest, studentProfile);
        studentProfileRepository.save(studentProfile);


        userResource.update(keycloakUser);
        return studentProfileMapper.ToStudentProfileResponse(studentProfile, keycloakUser);
    }
}
