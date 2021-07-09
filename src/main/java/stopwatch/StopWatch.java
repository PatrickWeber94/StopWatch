package stopwatch;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StopWatch {

    private List<Duration> rounds = new ArrayList<>();
    private Optional<LocalDateTime> timeStarted = Optional.empty();

    public long getTime() {
        return timeOfStoppedRounds() + timeOfActiveRound();
    }

    private long timeOfActiveRound() {
        return timeStarted.map(this::secondsSince).orElse(0L);
    }

    private long timeOfStoppedRounds() {
        return rounds.stream()
                .mapToLong(Duration::getSeconds)
                .sum();
    }

    private long secondsSince(LocalDateTime time) {
        return durationSince(time).getSeconds();
    }

    private Duration durationSince(LocalDateTime time) {
        Duration duration = Duration.between(time, LocalDateTime.now());
        return duration;
    }

    public void start() {
        timeStarted = Optional.of(LocalDateTime.now());
    }

    public void reset() {
        timeStarted = Optional.empty();
        rounds = new ArrayList<>();
    }

    public void stop() {
        timeStarted.ifPresent(time -> rounds.add(durationSince(time)));
        timeStarted = Optional.empty();
    }

}
