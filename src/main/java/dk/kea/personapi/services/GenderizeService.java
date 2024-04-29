package dk.kea.personapi.services;

import dk.kea.personapi.dtos.api.AgifyResponseDTO;
import dk.kea.personapi.dtos.api.GenderizeResponseDTO;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class GenderizeService {


    String URL = "https://api.genderize.io";

    public GenderizeResponseDTO getGenderByFirstName(String firstName) {
        return RestClient.create().get()
                .uri(URL + "?name=" + firstName)
                .retrieve()
                .onStatus(status -> status.value() == 422, ((request, response) -> {
                    throw new BadRequestException("Invalid name or missing first name. The Current first name value is: " + firstName);
                }))
                .onStatus(status -> status.value() == 429, ((request, response) -> {
                    throw new BadRequestException("Request limit reached");
                }))
                .body(GenderizeResponseDTO.class);
    }
}
