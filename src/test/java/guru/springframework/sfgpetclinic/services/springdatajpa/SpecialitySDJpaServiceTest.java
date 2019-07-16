package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService service;


    @Test
    @DisplayName(" Test Delete By Object")
    void testDeleteByObject() {
        //given
        Speciality speciality = new Speciality();

        //when
        service.delete(speciality);

        //then
        then(specialtyRepository).should().delete(any(Speciality.class));   // this line is the default of 'times(1)'

    }
    @Test
    @DisplayName(" Test Find By Id")
    void testFindById() {
        //given
        Speciality speciality = new Speciality();
        given(specialtyRepository.findById(1L)).willReturn(Optional.of(speciality));

        //when
        Speciality foundSpeciality = service.findById(1L);

        //then
        assertThat(foundSpeciality).isNotNull();
        then(specialtyRepository).should().findById(anyLong());
        then(specialtyRepository).shouldHaveNoMoreInteractions();
    }
    @Test
    @DisplayName(" Test Delete By Id")
    void testDeleteById() {
        //given - none

        //when
        service.deleteById(1L);
        service.deleteById(1L);

        //then
        then(specialtyRepository).should(times(2)).deleteById(1L);

    }
    @Test
    @DisplayName(" Test Delete By At Least")
    void testDeleteByIdAtLeast() {
        //given - none

        //when
        service.deleteById(1L);
        service.deleteById(1L);

        //then
        then(specialtyRepository).should(atLeast(1)).deleteById(1L);
    }
    @Test
    @DisplayName(" Test Delete By Id At Most")
    void testDeleteByIdAtMost() {
        //when
        service.deleteById(1L);
        service.deleteById(1L);
        service.deleteById(1L);
        service.deleteById(1L);
        service.deleteById(1L);

        //then
        then(specialtyRepository).should(atMost(5)).deleteById(1L);
    }

    @Test
    @DisplayName(" Test Delete By Id Never")
    void testDeleteByIdNever() {
        //when
        service.deleteById(1L);
        service.deleteById(2L);
        service.deleteById(6L);
//        service.deleteById(3L);

        //then
        then(specialtyRepository).should(atLeastOnce()).deleteById(1L);
        then(specialtyRepository).should(never()).deleteById(3L);

    }


    @Test
    @DisplayName("Test Delete")
    void testdelete() {
        //when
        service.delete(new Speciality());

        //then
        then(specialtyRepository).should().delete(any());
    }

    @Test
    @DisplayName("Test Throw Exception")
    void testDoThrow() {
        doThrow(new RuntimeException("boom")).when(specialtyRepository).delete(any());

        assertThrows(RuntimeException.class, () -> specialtyRepository.delete(new Speciality()));

        verify(specialtyRepository).delete(any());
    }

    @Test
    @DisplayName("Test Exception on Find By Id")
    void testFindByIDThrows() {
        //given
        given(specialtyRepository.findById(1L)).willThrow(new RuntimeException("Boom"));

        //when
        assertThrows(RuntimeException.class, () -> service.findById(1L));

        //then
        then(specialtyRepository).should().findById(1L);
    }
@Test
    @DisplayName("Test Exception on Delete")
    void testDeleteBDD() {
        willThrow(new RuntimeException("Boom")).given(specialtyRepository).delete(any());

        //when
        assertThrows(RuntimeException.class, () -> specialtyRepository.delete(new Speciality()));

        //then
        then(specialtyRepository).should().delete(any());
    }
}