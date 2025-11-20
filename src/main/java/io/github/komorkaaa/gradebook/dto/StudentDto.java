package io.github.komorkaaa.gradebook.dto;

import java.util.UUID;

public record StudentDto(
        UUID id,
        String name,
        String email
) {}
