package dk.kea.personapi.controllers;

import dk.kea.personapi.dtos.PersonRequestDTO;
import dk.kea.personapi.dtos.PersonResponseDTO;
import dk.kea.personapi.services.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/persons")
public class PersonController {


    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<PersonResponseDTO> getPerson(@RequestBody PersonRequestDTO personRequestDTO) {
        return ResponseEntity.ok(personService.getPerson(personRequestDTO));
    }
}
