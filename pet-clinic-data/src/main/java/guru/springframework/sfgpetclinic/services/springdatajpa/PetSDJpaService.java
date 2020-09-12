package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetSDJpaService implements PetService {

    private final PetRepository petRespository;

    public PetSDJpaService(PetRepository petRespository) {
        this.petRespository = petRespository;
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();
        petRespository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet findById(Long aLong) {
        return petRespository.findById(aLong).orElse(null);
    }

    @Override
    public Pet save(Pet object) {
        return petRespository.save(object);
    }

    @Override
    public void delete(Pet object) {
        petRespository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        petRespository.deleteById(aLong);
    }
}
