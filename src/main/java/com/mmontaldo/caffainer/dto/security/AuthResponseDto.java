package com.mmontaldo.caffainer.dto.security;

import java.time.LocalDateTime;

public record AuthResponseDto(String username, String token, LocalDateTime expires) {
}
