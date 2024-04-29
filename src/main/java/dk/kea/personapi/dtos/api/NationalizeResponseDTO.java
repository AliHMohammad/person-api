package dk.kea.personapi.dtos.api;

import java.util.List;

public record NationalizeResponseDTO(
        Integer count,
        String name,
        List<CountryResponseDTO> country
) {
}



