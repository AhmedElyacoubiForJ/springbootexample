package ay.education.springbootexample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {

    public final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository) {
		return args -> {
			Customer customer = new Customer();
			customer.setName("Peter");
			customer.setAge(20);
			customer.setEmail("peter@gmx.de");
			customerRepository.save(customer);

			customer = new Customer();
			customer.setName("Jamila");
			customer.setAge(22);
			customer.setEmail("jamila@gmx.de");
			customerRepository.save(customer);

			customer = new Customer();
			customer.setName("Ahmed");
			customer.setAge(23);
			customer.setEmail("ahmed@gmx.de");
			customerRepository.save(customer);
		};
	}

    @GetMapping
    public List<Customer> getCustomers() {
        //return List.of();
        return customerRepository.findAll();
    }

	record NewCustomerRequest(String name, String email, Integer age) {}

	@PostMapping
	public void addCustomer(@RequestBody NewCustomerRequest request) {
		Customer customer = new Customer();
		customer.setName(request.name);
		customer.setEmail(request.email);
		customer.setAge(request.age);
		customerRepository.save(customer);
	}

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id) {
        customerRepository.deleteById(id);
    }

    @PutMapping("{customerId}")
    public void updateCustomer(
            @PathVariable("customerId") Integer id,
            @RequestBody NewCustomerRequest request
    ) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(request.name);
        customer.setEmail(request.email);
        customer.setAge(request.age);
        customerRepository.save(customer);
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

    record Person(String name, int age, double savings) {
    }

    record GreetResponse(
            String message,
            List<String> favProgrammingLanguages,
            Person person
    ) {
    }
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