package dk.kea.personapi.dtos.api;

public record AgifyResponseDTO(
        Integer count,
        String name,
        Integer age,
        Double probability
) {
}
