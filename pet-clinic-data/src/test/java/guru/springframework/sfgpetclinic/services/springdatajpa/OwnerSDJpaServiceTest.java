package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    public static final String LAST_NAME = "Smith";
    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetTypeRepository petTypeRepository;
    @InjectMocks
    OwnerSDJpaService ownerSDJpaService;

    Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {
        Mockito.when(ownerRepository.findByLastName(Mockito.any())).thenReturn(returnOwner);
        Owner owner = ownerSDJpaService.findByLastName(LAST_NAME);
        assertEquals(LAST_NAME, owner.getLastName());
        Mockito.verify(ownerRepository).findByLastName(Mockito.any());
    }

    @Test
    void findAll() {
        Set<Owner> returnOwnerSet = new HashSet<>();
        returnOwnerSet.add(Owner.builder().id(1L).build());
        returnOwnerSet.add(Owner.builder().id(1L).build());

        Mockito.when(ownerSDJpaService.findAll()).thenReturn(returnOwnerSet);
        Set<Owner> owners = ownerSDJpaService.findAll();
        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        Mockito.when(ownerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(returnOwner));
        Owner owner = ownerSDJpaService.findById(1L);
        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        Mockito.when(ownerRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Owner owner = ownerSDJpaService.findById(1L);
        assertNull(owner);
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(1L).build();
        Mockito.when((ownerRepository.save(Mockito.any()))).thenReturn(ownerToSave);

        Owner owner = ownerSDJpaService.save(ownerToSave);
        assertNotNull(owner);
        Mockito.verify(ownerRepository).save(Mockito.any());
    }

    @Test
    void delete() {
        ownerSDJpaService.delete(returnOwner);
        Mockito.verify(ownerRepository).delete(Mockito.any());
    }

    @Test
    void deleteById() {
        ownerSDJpaService.deleteById(1L);
        Mockito.verify(ownerRepository).deleteById(Mockito.anyLong());
    }
}