public class Exercise1 {

	public static void main(String[] args) {

		// Use StreamSources.intNumbersStream() and StreamSources.userStream()

		// Print all numbers in the intNumbersStream stream
		StreamSources.intNumbersStream()
				.forEach(x -> System.out.println(x));
		StreamSources.intNumbersStream()
				.forEach(System.out::println);

		// Print numbers from intNumbersStream that are less than 5
		StreamSources.intNumbersStream()
				.filter(number -> number < 5)
				.forEach(e -> System.out.println(e));

		// Print the second and third numbers in intNumbersStream that's greater than 5
		StreamSources.intNumbersStream().filter(number -> number > 5)
				.skip(1)
				.limit(2)
				.forEach(e -> System.out.println(e));

		//  Print the first number in intNumbersStream that's greater than 5.
		//  If nothing is found, print -1
		Integer value = StreamSources.intNumbersStream()
				.filter(number -> number > 5)
				.findFirst()
				.orElse(-1);
		System.out.println(value);

		// Print first names of all users in userStream
		//I
		StreamSources.userStream()
				.forEach(user -> System.out.println(user.getFirstName()));
		//II
		StreamSources.userStream()
				.map(user -> user.getFirstName())
				.forEach(userName -> System.out.println(userName));

		// Print first names in userStream for users that have IDs from number stream
		//I
		System.out.println("=======================");
		StreamSources.userStream()
				.filter(user -> StreamSources.intNumbersStream()
						.anyMatch(i -> user.getId() == i))
				.forEach(System.out::println);
		//II
		System.out.println("=======================");
		StreamSources.intNumbersStream()
				.flatMap(id -> StreamSources.userStream()
						.filter(user -> user.getId() == id))
				.map(User::getFirstName)
				.forEach(System.out::println);

	}

}
