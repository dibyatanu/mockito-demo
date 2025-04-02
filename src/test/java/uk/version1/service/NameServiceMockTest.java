package uk.version1.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.version1.repository.NameRepository;
import uk.version1.utils.Utils;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NameServiceMockTest {
    @Mock
    private NameRepository nameRepositoryMocked;

    @InjectMocks
    private NameService nameService;
    @Captor
    private ArgumentCaptor<Long> idArgumentCaptor;
    @Captor
    private ArgumentCaptor<String> nameCaptor;

    public void resetMock(){
      //  nameService.r
    }
    @Nested
    class GetName{

      @DisplayName("Should get Name if it exits")
      @Test
      public void getNameShouldGetName(){
          //Stubbing
       when(nameRepositoryMocked.getName(anyLong())).thenReturn(Optional.of("David"));

       nameService.getName(1L);

       verify(nameRepositoryMocked).getName(idArgumentCaptor.capture());
       assertThat(idArgumentCaptor.getValue()).isEqualTo(1L);
    }

    @DisplayName("Should throw exception if Id does not exits")
    @Test
      public void getNameShouldThrowException(){
         var message= assertThrows(RuntimeException.class, () ->{
            nameService.getName(1L);
        });
         assertThat(message.getMessage()).isEqualTo("Name not Found");
    }
        @DisplayName("Should throw exception if repository service throws exception")
        @Test
        public void getNameRepositoryServiceThrowsException(){
            when(nameRepositoryMocked.getName(anyLong())).thenThrow( UnsupportedOperationException.class);

           assertThrows(UnsupportedOperationException.class, () ->{
                nameService.getName(1L);
            });

        }

    }
    @Nested
    class StoreName{
        @DisplayName("doNothing: Should Store Name")
        @Test
        public void storeNameShouldAddName(){
            //default behaviour
            doNothing().when(nameRepositoryMocked).storeName(isA(Long.class),isA(String.class));

            nameService.storeName(1L,"David");

            verify(nameRepositoryMocked).storeName(idArgumentCaptor.capture(),nameCaptor.capture());
            assertThat(idArgumentCaptor.getValue()).isEqualTo(1L);
            assertThat(nameCaptor.getValue()).isEqualTo("David");
        }


    @DisplayName("Answer: Should Store Name")
    @Test
    public void storeNameShouldAddNameAnswer(){
            doAnswer( answers -> {
                Object arg1= answers.getArgument(0);
                Object arg2= answers.getArgument(1);
                assertEquals(1L,arg1);
                assertEquals("John",arg2);
                return null;
            }).when(nameRepositoryMocked).storeName(isA(Long.class),isA(String.class));

          nameService.storeName(1L,"John");

          verify(nameRepositoryMocked).storeName(idArgumentCaptor.capture(),nameCaptor.capture());
          assertThat(idArgumentCaptor.getValue()).isEqualTo(1L);
          assertThat(nameCaptor.getValue()).isEqualTo("John");
    }
    @DisplayName("callRealMethod: Should Store Name")
    @Test
    public void storeNameShouldAddShouldCallRealMethod(){
            doCallRealMethod().when(nameRepositoryMocked).storeName(isA(Long.class),isA(String.class));

            nameService.storeName(1L,"David");

            verify(nameRepositoryMocked).storeName(idArgumentCaptor.capture(),nameCaptor.capture());
            assertThat(idArgumentCaptor.getValue()).isEqualTo(1L);
            assertThat(nameCaptor.getValue()).isEqualTo("David");

        }

        @DisplayName("callRealMethod: Should Store Name")
        @Test
        public void storeNameShouldAdThrowsException(){
            doThrow(UnsupportedOperationException.class).when(nameRepositoryMocked).storeName(isA(Long.class),isA(String.class));
            assertThrows(UnsupportedOperationException.class, ()->{
                nameService.storeName(1L,"David");
            });

        }
    }


    public static String staticMethod(){
        return "dd";
    }

}
