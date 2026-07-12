package co.istad.sela.elearning.features.student;

import co.istad.sela.elearning.features.student.dto.StudentProfileResponse;
import co.istad.sela.elearning.features.student.dto.UpdateProfileStudentRequest;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring")
public abstract class StudentProfileMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract StudentProfile mapUpdateStudentRequestToStudentProfile(
            UpdateProfileStudentRequest updateProfileStudentRequest,
            @MappingTarget StudentProfile studentProfile);

    public StudentProfileResponse ToStudentProfileResponse(
            StudentProfile studentProfile, UserRepresentation representation
            ) {
        return StudentProfileResponse.builder()
                .userId(representation.getId())
                .firstName(representation.getFirstName())
                .lastName(representation.getLastName())
                .email(representation.getEmail())
                .gender(representation.getAttributes().get("gander").getFirst())
                .biology(representation.getAttributes().get("biology").getFirst())
                .profilePicture(studentProfile.getProfilePicture())
                .facebookLink(studentProfile.getFacebookLink())
                .githubLink(studentProfile.getGithubLink())
                .phoneNumber(studentProfile.getPhoneNumber())
                .university(studentProfile.getUniversity())
                .major(studentProfile.getMajor())
                .build();

    }
}
