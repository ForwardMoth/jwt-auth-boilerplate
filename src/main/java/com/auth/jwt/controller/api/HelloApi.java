package com.auth.jwt.controller.api;

import com.auth.jwt.domain.dto.response.JwtAuthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Test hello endpoint")
public interface HelloApi {

    @Operation(summary = "Access for all users")
    @ApiResponse(
            responseCode = "200",
            description = "Success hello message",
            content = { @Content(schema = @Schema(
                    implementation = String.class,
                    example = "hello"),
                    mediaType = "application/json"
            ) }
    )
    String get();
}
