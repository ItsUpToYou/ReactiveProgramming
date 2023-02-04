import java.io.IOException;
import java.time.Duration;

import reactor.core.publisher.Flux;

public class Exercise6 {

	public static void main(String[] args) throws IOException {

		// Use ReactiveSources.unresponsiveFlux() and ReactiveSources.unresponsiveMono()

		// Get the value from the Mono into a String variable but give up after 5 seconds
		String foo = ReactiveSources.unresponsiveMono().block(Duration.ofSeconds(2));

		// Get the value from unresponsiveFlux into a String list but give up after 5 seconds
		// Come back and do this when you've learnt about operators!
		//		Flux<String> foo1 = ReactiveSources.unresponsiveFlux().delayElements(Duration.ofSeconds(5));
		Flux<String> stringFlux = ReactiveSources.unresponsiveFlux()
				.delayElements(Duration.ofSeconds(3));
		stringFlux
				.timeout(Duration.ofSeconds(5))
				.subscribe(System.out::println, error -> System.out.println("Error: " + error));

		System.out.println("Press a key to end");
		System.in.read();
	}

}
