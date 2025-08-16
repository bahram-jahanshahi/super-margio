package se.bahram.ai.super_margio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.bahram.ai.super_margio.usecases.merge_two_homogeneous_lists.applications.ports.in.MergeTwoHomogeneousListsUseCase;

@SpringBootApplication
public class SuperMargioApplication implements CommandLineRunner {

	private final MergeTwoHomogeneousListsUseCase mergeTwoHomogeneousListsUseCase;

    public SuperMargioApplication(MergeTwoHomogeneousListsUseCase mergeTwoHomogeneousListsUseCase) {
        this.mergeTwoHomogeneousListsUseCase = mergeTwoHomogeneousListsUseCase;
    }

    public static void main(String[] args) {
		SpringApplication.run(SuperMargioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.mergeTwoHomogeneousListsUseCase.execute();
	}
}
