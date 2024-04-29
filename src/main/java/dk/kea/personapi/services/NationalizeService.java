package dk.kea.personapi.services;


import dk.kea.personapi.dtos.api.GenderizeResponseDTO;
import dk.kea.personapi.dtos.api.NationalizeResponseDTO;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class NationalizeService {


    String URL = "https://api.nationalize.io";

    public NationalizeResponseDTO getNationalityByLastName(String lastName) {
        return RestClient.create().get()
                .uri(URL + "?name=" + lastName)
                .retrieve()
                .onStatus(status -> status.value() == 422, ((request, response) -> {
                    throw new BadRequestException("Invalid name or missing first name. The Current first name value is: " + lastName);
                }))
                .onStatus(status -> status.value() == 429, ((request, response) -> {
                    throw new BadRequestException("Request limit reached");
                }))
                .body(NationalizeResponseDTO.class);
    }
}
