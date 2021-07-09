package stopwatch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class StopWatchTest {
    private StopWatch stopWatch;

    @BeforeEach
    public void setUp() {
        stopWatch = new StopWatch();
    }

    @Test
    void timeIsZeroWhenNotStarted() throws Exception {
        assertThat(new StopWatch().getTime()).isZero();
    }

    @Test
    void timeIsOneAfterOneSecond() throws Exception {
        stopWatch.start();
        wait(1000);
        assertThat(stopWatch.getTime()).isEqualTo(1);
    }

    @Test
    void timeIsTwoAfterTwoSeconds() throws Exception {
        stopWatch.start();
        wait(2000);
        assertThat(stopWatch.getTime()).isEqualTo(2);
    }

    @Test
    void canResetTheTime() throws Exception {
        stopWatch.start();
        wait(1000);
        stopWatch.reset();
        assertThat(stopWatch.getTime()).isZero();
    }

    @Test
    void resetShouldDeleteStoppedRounds() throws Exception {
        stopWatch.start();
        wait(1000);
        stopWatch.stop();
        wait(1000);
        stopWatch.reset();
        assertThat(stopWatch.getTime()).isZero();
    }

    @Test
    void canStopAndContinue() throws Exception {
        stopWatch.start();
        wait(1000);
        stopWatch.stop();
        wait(2000);
        stopWatch.start();
        wait(1000);
        assertThat(stopWatch.getTime()).isEqualTo(2);
    }

    private void wait(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }
}
