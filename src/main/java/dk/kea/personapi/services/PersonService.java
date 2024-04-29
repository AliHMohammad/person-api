package dk.kea.personapi.services;


import dk.kea.personapi.dtos.PersonRequestDTO;
import dk.kea.personapi.dtos.PersonResponseDTO;
import dk.kea.personapi.entities.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonService {


    private final AgifyService agifyService;
    private final GenderizeService genderizeService;
    private final NationalizeService nationalizeService;

    public PersonService(AgifyService agifyService, GenderizeService genderizeService, NationalizeService nationalizeService) {
        this.agifyService = agifyService;
        this.genderizeService = genderizeService;
        this.nationalizeService = nationalizeService;
    }

    public PersonResponseDTO getPerson(PersonRequestDTO personRequestDTO) {
        return null;
    }


    private Person toEntity(PersonRequestDTO personRequestDTO) {
        return new Person(
                personRequestDTO.firstName(),
                personRequestDTO.middleName(),
                personRequestDTO.lastName()
        );
    }

    /*private PersonResponseDTO toDTO(Person person) {
        return new PersonResponseDTO(
                person.getFullName(),
                person.getFirstName(),
                person.getMiddleName(),
                person.getLastName()
        );
    }*/
}
