package stopwatch;

import java.time.LocalDateTime;

public class FakeTimeProvider implements TimeProvider {
    private LocalDateTime currentTime;

    public FakeTimeProvider(LocalDateTime currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public LocalDateTime currentTime() {
        return currentTime;
    }

    public void setCurrentTime(LocalDateTime currentTime) {
        this.currentTime = currentTime;
    }
}
