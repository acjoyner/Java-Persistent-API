package com.anthony.coding.jpa;

import com.anthony.coding.jpa.models.Video;
import com.anthony.coding.jpa.models.embedded.Author;
import com.anthony.coding.jpa.repositories.AuthorRepository;
import com.anthony.coding.jpa.repositories.VideoRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(JpaApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(
			AuthorRepository authorRepository,
			VideoRepository videoRepository
	) {
		return args -> {
			Faker faker = new Faker();
			for(int i=0; i < 50; i++){
				var author = Author.builder()
						.firstName(faker.name().firstName())
						.lastName(faker.name().lastName())
						.age(faker.number().numberBetween(19,50))
						.email(faker.internet().emailAddress())
						.build();
				authorRepository.save(author);
			}


			// Update author with ID 1
			var author =  Author.builder()
					.firstName("Anthony")
					.lastName("Joyner")
					.age(44)
					.email("anthony.c.joyner@gmail.com")
					.build();
			authorRepository.save(author);

			//Update Author a  set a.age = 22 where a.id = 1
			authorRepository.updateAuthor(22,1);

			// update all authors
			authorRepository.updateAllAuthorAges(99);

			authorRepository.findByNamedQuery(99)
					.forEach(System.out::println);

			authorRepository.updateByNamedQuery(12);




//			var video = Video.builder()
//					.name("abc")
//					.length(5)
//					.build();
//			videoRepository.save(video);
		};
	}

}
