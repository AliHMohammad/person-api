package dk.kea.personapi.dtos;

import jakarta.validation.constraints.NotNull;

public record PersonRequestDTO(

        @NotNull
        String firstName,
        String middleName,

        @NotNull
        String lastName
) {
}
