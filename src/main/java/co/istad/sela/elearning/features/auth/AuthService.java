package co.istad.sela.elearning.features.auth;

import co.istad.sela.elearning.features.auth.dto.RegisterRequest;
import co.istad.sela.elearning.features.auth.dto.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest registerRequest);

}
