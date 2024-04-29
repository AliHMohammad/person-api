package dk.kea.personapi.dtos;

import jakarta.validation.constraints.NotNull;

public record PersonRequestDTO(

        @NotNull(message = "firstName must be specified")
        String firstName,
        String middleName,
        @NotNull(message = "lastName must be specified")
        String lastName
) {
}
