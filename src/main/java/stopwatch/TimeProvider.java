package stopwatch;

import java.time.LocalDateTime;

public interface TimeProvider {

    default LocalDateTime currentTime() {
        return LocalDateTime.now();
    }
}
