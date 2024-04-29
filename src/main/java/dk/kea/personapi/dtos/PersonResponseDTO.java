package dk.kea.personapi.dtos;

public record PersonResponseDTO(
        String fullName,
        String firstName,
        String middleName,
        String lastName,
        String gender,
        Double genderProbability,
        Integer age,
        Double ageProbability,
        String country,
        Double countryProbability
) {
}
