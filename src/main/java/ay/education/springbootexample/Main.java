package ay.education.springbootexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@SpringBootApplication
@RestController
public class Main {

	private GreetResponse response;

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@GetMapping("/greetAsString")
	public String greet() {
		return "hello, response as string";
	}

    @GetMapping("/greetAsObject")
    public GreetResponse greetAsResponse() {
		GreetResponse response = new GreetResponse(
				"hello, response as json object (w. different field types)",
				List.of("Java", "Kotlin", "JavaScript"),
				new Person("Peter", 45, 300_000)
		);
		return response;
    }

	record Person(String name, int age, double savings) {}

    record GreetResponse(
			String message,
			List<String> favProgrammingLanguages,
			Person person
			) {}
	// class def. is the same thing as record def.
	/*class GreetResponse {
		private final String message;

		public GreetResponse(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

		@Override
		public String toString() {
			return "GreetResponse{" +
					"message='" + message + '\'' +
					'}';
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			GreetResponse that = (GreetResponse) o;
			return Objects.equals(message, that.message);
		}

		@Override
		public int hashCode() {
			return Objects.hash(message);
		}
	}*/
}