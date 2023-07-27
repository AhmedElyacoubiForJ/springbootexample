package ay.education.springbootexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@GetMapping("/greetAsString")
	public String greet() {
		return "hello, response as string";
	}

    @GetMapping("/greetAsObject")
    public GreetResponse greetAsResponse() {
		return new GreetResponse("hello, response as json object");
    }

    record GreetResponse(String message) {}
}