package com.auth.jwt.controller.api;

import com.auth.jwt.domain.dto.request.SignInRequest;
import com.auth.jwt.domain.dto.request.SignUpRequest;
import com.auth.jwt.domain.dto.response.JwtAuthResponse;
import com.auth.jwt.exception.message.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Authentication")
public interface AuthApi {

    @Operation(summary = "Registration user")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful registration",
                    content = { @Content(schema = @Schema(
                            implementation = JwtAuthResponse.class),
                            mediaType = "application/json"
                    ) }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = { @Content(
                            schema = @Schema(implementation = ErrorMessage.class),
                            mediaType = "application/json"
                    ) }
            )
    })
    ResponseEntity<JwtAuthResponse> signUp(@RequestBody @Valid SignUpRequest request);

    @ApiResponses({
          @ApiResponse(
                  responseCode = "200",
                  description = "Successful login",
                  content = { @Content(schema = @Schema(
                          implementation = JwtAuthResponse.class),
                          mediaType = "application/json"
                  ) }
          ),
        @ApiResponse(
                responseCode = "401",
                description = "Incorrect email or password",
                content = { @Content(schema = @Schema(
                        implementation = ErrorMessage.class),
                        mediaType = "application/json"
                ) }
        )
    })
    @Operation(summary = "Login user")
    ResponseEntity<JwtAuthResponse> signIn(@RequestBody @Valid SignInRequest request);
}
