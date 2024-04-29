package dk.kea.personapi.dtos.api;

public record GenderizeResponseDTO(
        Integer count,
        String name,
        String gender,
        Double probability
) {
}
