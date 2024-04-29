package dk.kea.personapi.dtos.api;

public record CountryResponseDTO(
        String country_id,
        Double probability
) {
}
