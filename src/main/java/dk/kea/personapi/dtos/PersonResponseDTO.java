package dk.kea.personapi.dtos;

public record PersonResponseDTO(
        String fullName,
        String firstName,
        String middleName,
        String lastName,
        String gender,
        double genderProbability,
        int age,
        double ageProbability,
        String country,
        double countryProbability
) {
}
