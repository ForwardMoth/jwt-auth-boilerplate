package com.auth.jwt.exception.message.record;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "Forbidden message")
public record ForbiddenMessage(
        @Schema(description = "Error code", example = "403")
        int code,
        @Schema(description = "Error message", example = "FORBIDDEN")
        String message) {

}