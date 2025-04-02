package uk.version1.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.version1.repository.NameRepository;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NameServiceSpyTest {
    @Spy
    private NameRepository nameRepositoryMocked;
    @InjectMocks
    private NameService nameService;
    @Captor
    private ArgumentCaptor<Long> idArgumentCaptor;
    @Captor
    private ArgumentCaptor<String> nameCaptor;

    @Nested
    class GetName{
        @DisplayName("Partial mock- Should get name")
        @Test
        public void getNameShouldGetNamePartialMock(){

            doReturn(Optional.of("John")).when(nameRepositoryMocked).getName(anyLong());

             var actualResult= nameService.getName(1L);

            verify(nameRepositoryMocked).getName(idArgumentCaptor.capture());
            Assertions.assertThat(idArgumentCaptor.getValue()).isEqualTo(1L);
            assertThat(actualResult).isEqualTo("John");

        }

        @DisplayName("No mock- Should get name")
        @Test
        public void getNameShouldGetNameNoMock(){

            var actualResult= nameService.getName(1L);

            verify(nameRepositoryMocked).getName(idArgumentCaptor.capture());
            Assertions.assertThat(idArgumentCaptor.getValue()).isEqualTo(1L);
            assertThat(actualResult).isEqualTo("David");

        }

    }

    public void getDate(){
      var expected=   nameService.getDate();

   //   assertThat(expected.toString())
    }

}
