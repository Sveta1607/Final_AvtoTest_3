import com.max.BookingService;
import com.max.CantBookException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import static org.mockito.ArgumentMatchers.*;


@ExtendWith(MockitoExtension.class)
public class Task3Test {
    private static final Logger logger
            = LoggerFactory.getLogger(Task3Test.class);

    @Mock
    BookingService bookingService = new BookingService();

    @Test
    void positiveBookingTest() throws CantBookException {
//        BookingService bookingService = mock(BookingService.class);
// второй вариант создания мока, без @ExtendWith(MockitoExtension.class)
        logger.info("запуск positiveBookingTest");
        //given
        Mockito.when(bookingService.book(anyString(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(true);
        //then
        Assertions.assertEquals(true, bookingService
                .book("User", LocalDateTime.now(), LocalDateTime.now()));
        Mockito.verify(bookingService, Mockito.times(1)).book(anyString()
                , any(LocalDateTime.class)
                , any(LocalDateTime.class));
        Mockito.verifyNoMoreInteractions(bookingService);
        logger.info("Тест positiveBookingTest прошёл успешно");
    }

    @Test
    void negativeBookingTest() throws CantBookException {
        //given
        logger.info("запуск negativeBookingTest");
        Mockito.doThrow(CantBookException.class)
                .when(bookingService).book(anyString(), any(LocalDateTime.class), any(LocalDateTime.class));
        //then
        Assertions.assertThrows(CantBookException.class, () -> bookingService
                .book("User", LocalDateTime.now(), LocalDateTime.now()));

        Mockito.verify(bookingService, Mockito.times(1)).book(anyString()
                , any(LocalDateTime.class)
                , any(LocalDateTime.class));
        Mockito.verifyNoMoreInteractions(bookingService);
        logger.info("Тест negativeBookingTest прошёл успешно");
    }
}
