package org.notebasement.identity.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.jvnet.hk2.annotations.Service;
import org.notebasement.common.exception.AppException;
import org.notebasement.common.exception.ErrorCode;
import org.notebasement.identity.dto.request.AuthenticationRequest;
import org.notebasement.identity.dto.request.IntrospecRequest;
import org.notebasement.identity.dto.response.AuthenticationResponse;
import org.notebasement.identity.dto.response.IntrospecResponse;
import org.notebasement.identity.repository.UserRepository;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @NonFinal
//    @Value("${jwt.signerKey}")
    protected String SIGN_KEY;

    public IntrospecResponse introspec(IntrospecRequest req) throws JOSEException, ParseException {
        var token = req.getToken();

        JWSVerifier jwsVerifier = new MACVerifier(SIGN_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiriTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verifier = signedJWT.verify(jwsVerifier);

        return IntrospecResponse.builder()
                .valid(verifier && expiriTime.before(new Date()))
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest req) {
        var user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        boolean authenticated = passwordEncoder.matches(req.getPassword(), user.getPassword());

        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        var token = genarateToken(req.getEmail());

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    String genarateToken(String email) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(email)
                .issuer("note-webapp.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("customClaim", "CustomValue")
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGN_KEY.getBytes()));

            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot sign JWT", e);
            throw new RuntimeException(e);
        }

    }
}
