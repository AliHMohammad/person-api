package dk.kea.personapi.controllers;

import dk.kea.personapi.dtos.PersonRequestDTO;
import dk.kea.personapi.dtos.PersonResponseDTO;
import dk.kea.personapi.services.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/persons")
public class PersonController {


    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @Operation(
            summary = "Gets a detailed person by first-, middle- and lastname",
            description = "Returns a detailed person with the most likely age, nationality and gender.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "A Person"),
                    @ApiResponse(responseCode = "422", description = "Invalid name or missing parameter in RestClient"),
                    @ApiResponse(responseCode = "429", description = "Request limit reached")
            }
    )
    @GetMapping
    public ResponseEntity<PersonResponseDTO> getPerson(
            @RequestParam String firstName,
            @RequestParam Optional<String> middleName,
            @RequestParam String lastName
            ) {

        return ResponseEntity.ok(personService.getPerson(firstName, middleName, lastName));
    }
}
