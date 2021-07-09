package stopwatch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

class StopWatchTest {
    private StopWatch stopWatch;
    private FakeTimeProvider timeProvider;

    @BeforeEach
    public void setUp() {
        timeProvider = new FakeTimeProvider(LocalDateTime.of(LocalDate.of(2018, 8, 9), LocalTime.of(10, 0)));
        stopWatch = new StopWatch(timeProvider);
    }

    @Test
    void timeIsZeroWhenNotStarted() {
        assertThat(stopWatch.getTime()).isZero();
    }

    @Test
    void timeIsOneAfterOneSecond() {
        stopWatch.start();
        waitSeconds(1);
        assertThat(stopWatch.getTime()).isEqualTo(1);
    }

    @Test
    void timeIsTwoAfterTwoSeconds() {
        stopWatch.start();
        waitSeconds(2);
        assertThat(stopWatch.getTime()).isEqualTo(2);
    }

    @Test
    void canResetTheTime() {
        stopWatch.start();
        waitSeconds(1);
        stopWatch.reset();
        assertThat(stopWatch.getTime()).isZero();
    }

    @Test
    void resetShouldDeleteStoppedRounds() {
        stopWatch.start();
        waitSeconds(1);
        stopWatch.stop();
        waitSeconds(1);
        stopWatch.reset();
        assertThat(stopWatch.getTime()).isZero();
    }

    @Test
    void canStopAndContinue() {
        stopWatch.start();
        waitSeconds(1);
        stopWatch.stop();
        waitSeconds(2);
        stopWatch.start();
        waitSeconds(1);
        assertThat(stopWatch.getTime()).isEqualTo(2);
    }

    private void waitSeconds(int seconds) {
        timeProvider.setCurrentTime(timeProvider.currentTime().plusSeconds(seconds));
    }
}
