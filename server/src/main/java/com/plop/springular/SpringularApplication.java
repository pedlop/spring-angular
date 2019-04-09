package com.plop.springular;

// import java.util.stream.LongStream;

// import com.plop.springular.model.Note;
// import com.plop.springular.repository.NoteRepository;

// import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringularApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringularApplication.class, args);
	}

	// @Bean
	// CommandLineRunner init(NoteRepository repository) {
	// return args -> {
	// repository.deleteAll();
	// LongStream.range(1, 11).mapToObj(i -> {
	// Note n = new Note();
	// n.setTitle("Note " + i);
	// n.setDescription("Note description" + i);
	// return n;
	// }).map(v -> repository.save(v)).forEach(System.out::println);
	// };
	// }
}
