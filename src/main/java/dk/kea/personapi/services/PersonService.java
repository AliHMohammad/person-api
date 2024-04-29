package dk.kea.personapi.services;


import dk.kea.personapi.dtos.PersonRequestDTO;
import dk.kea.personapi.dtos.PersonResponseDTO;
import dk.kea.personapi.dtos.api.AgifyResponseDTO;
import dk.kea.personapi.dtos.api.GenderizeResponseDTO;
import dk.kea.personapi.dtos.api.NationalizeResponseDTO;
import dk.kea.personapi.entities.Person;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PersonService {


    private final AgifyService agifyService;
    private final GenderizeService genderizeService;
    private final NationalizeService nationalizeService;

    private final Map<String, PersonResponseDTO> personCache;

    public PersonService(AgifyService agifyService, GenderizeService genderizeService, NationalizeService nationalizeService) {
        this.agifyService = agifyService;
        this.genderizeService = genderizeService;
        this.nationalizeService = nationalizeService;
        this.personCache = new HashMap<>();
    }

    public PersonResponseDTO getPerson(String firstName, Optional<String> middleName, String lastName) {
        Person personEntity = new Person(firstName, middleName.orElse(null), lastName);
        var personInCache = findPersonInCache(personEntity.getFullName());

        if (personInCache != null){
            return personInCache;
        }


        AgifyResponseDTO agifyResponse = agifyService.getAgifyByFirstName(personEntity.getFirstName());
        GenderizeResponseDTO genderizeResponse = genderizeService.getGenderByFirstName(personEntity.getFirstName());
        NationalizeResponseDTO nationalizeResponse = nationalizeService.getNationalityByLastName(personEntity.getLastName());

        PersonResponseDTO personResponseDTO = toDTO(personEntity, agifyResponse, genderizeResponse, nationalizeResponse);
        return savePersonInCache(personResponseDTO);
    }

    private PersonResponseDTO findPersonInCache(String fullName) {
        String key = fullName.toUpperCase();
        return personCache.get(key);
    }

    private PersonResponseDTO savePersonInCache(PersonResponseDTO personResponseDTO) {
        String key = personResponseDTO.fullName().toUpperCase();
        personCache.put(key, personResponseDTO);
        return personResponseDTO;
    }

    private PersonResponseDTO toDTO(
            Person person,
            AgifyResponseDTO agifyResponseDTO,
            GenderizeResponseDTO genderizeResponseDTO,
            NationalizeResponseDTO nationalizeResponseDTO
    ) {
        return new PersonResponseDTO(
                person.getFullName(),
                person.getFirstName(),
                person.getMiddleName(),
                person.getLastName(),
                genderizeResponseDTO.gender(),
                genderizeResponseDTO.probability(),
                agifyResponseDTO.age(),
                // Agify API returnerer null ved probability.
                (agifyResponseDTO.probability() == null ? null : agifyResponseDTO.probability()),
                nationalizeResponseDTO.country().get(0).country_id(),
                nationalizeResponseDTO.country().get(0).probability()
        );
    }
}
