package co.istad.sela.elearning.features.student.dto;

import co.istad.sela.elearning.features.auth.dto.GenderOptions;

public record UpdateProfileStudentRequest(
        String firstName,
        String lastName,
        GenderOptions gender,
        String profilePicture,
        String biology,
        String university,
        String major,
        String phoneNumber,
        String githubLink,
        String facebookLink
) {
}
