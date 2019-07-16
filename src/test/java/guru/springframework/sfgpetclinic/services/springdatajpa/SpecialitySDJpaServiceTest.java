package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService service;

    @Test
    void testDeleteByObject() {
        Speciality speciality = new Speciality();

        service.delete(speciality);

        verify(specialtyRepository, times(1)).delete(any(Speciality.class));
        verify(specialtyRepository).delete(any(Speciality.class));   // this line is the default of 'times(1)'
    }

    @Test
    void testFindById() {
        Speciality speciality = new Speciality();
        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(speciality));

        Speciality foundSpeciality = service.findById(1L);

        assertThat(foundSpeciality).isNotNull();
        verify(specialtyRepository, times(1)).findById(1L);
        verify(specialtyRepository).findById(1L);   // this line is the default of 'times(1)'
        verify(specialtyRepository).findById(anyLong());   // this line is the default of 'times(1)'
    }

    @Test
    void testDeleteById() {
        service.deleteById(1L);
        service.deleteById(1L);
        verify(specialtyRepository, times(2)).deleteById(1L);
    }
    @Test
    void testDeleteByIdAtLeast() {
        service.deleteById(1L);
        service.deleteById(1L);
        verify(specialtyRepository, atLeast(1)).deleteById(1L);
    }
    @Test
    void testDeleteByIdAtMost() {
        service.deleteById(1L);
        service.deleteById(1L);
        service.deleteById(1L);
        service.deleteById(1L);
        service.deleteById(1L);
        verify(specialtyRepository, atMost(5)).deleteById(1L);
    }

    @Test
    void testDeleteByIdNever() {
        service.deleteById(1L);
        service.deleteById(2L);
        service.deleteById(6L);
//        service.deleteById(3L);

        verify(specialtyRepository, atLeast(1)).deleteById(1L);
        verify(specialtyRepository, never()).deleteById(3L);
//        verify(specialtyRepository, atLeast(1)).deleteById(3L);
    }


    @Test
    void testdelete() {
        service.delete(new Speciality());
    }
}