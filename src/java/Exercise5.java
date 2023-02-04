import java.io.IOException;

import org.reactivestreams.Subscription;

import reactor.core.publisher.BaseSubscriber;

public class Exercise5 {

	public static void main(String[] args) throws IOException {

		// Use ReactiveSources.intNumbersFlux( and ReactiveSources.userMono()

		// Subscribe to a flux using the error and completion hooks
		ReactiveSources.intNumbersFlux().subscribe(
				number -> System.out.println(number),
				err -> System.out.println(err.getMessage()),
				() -> System.out.println("Complete"));

		ReactiveSources.userMono().subscribe(
				number -> System.out.println(number),
				err -> System.out.println(err.getMessage()),
				() -> System.out.println("Complete"));

		// Subscribe to a flux using an implementation of BaseSubscriber
		ReactiveSources.intNumbersFlux().subscribe(new MySubscriber<>());

		System.out.println("Press a key to end");
		System.in.read();
	}

	static class MySubscriber<T> extends BaseSubscriber<T> {

		public void hookOnSubscriber(Subscription subscription) {
			System.out.println("Subscribe happened");
			request(1);
		}

		public void hookOnNext(T value) {
			System.out.println(value.toString() + " received");
			request(3);
		}
	}
}