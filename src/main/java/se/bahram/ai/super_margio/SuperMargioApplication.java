package se.bahram.ai.super_margio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.bahram.ai.super_margio.ai_agents.merger.usecases.merge_two_homogenous_lists.applications.ports.in.MergeTwoHomogeneousLists_AiAgentUseCase;
import se.bahram.ai.super_margio.usecases.merge_two_homogeneous_lists.applications.MergeTwoHomogeneousListsService;
import se.bahram.ai.super_margio.usecases.merge_two_homogeneous_lists.applications.ports.in.MergeTwoHomogeneousLists_UseCase;
import se.bahram.ai.super_margio.usecases.merge_two_homogeneous_lists.domain.Person;
import se.bahram.ai.super_margio.usecases.merge_two_homogeneous_lists.domain.Student;

import java.util.List;

@SpringBootApplication
public class SuperMargioApplication implements CommandLineRunner {

	private final MergeTwoHomogeneousLists_UseCase mergeTwoHomogeneousListsUseCase;
	private final MergeTwoHomogeneousLists_AiAgentUseCase mergeTwoHomogeneousListsAiAgentUseCase;

    public SuperMargioApplication(MergeTwoHomogeneousLists_UseCase mergeTwoHomogeneousListsUseCase, MergeTwoHomogeneousLists_AiAgentUseCase mergeTwoHomogeneousListsAiAgentUseCase) {
        this.mergeTwoHomogeneousListsUseCase = mergeTwoHomogeneousListsUseCase;
        this.mergeTwoHomogeneousListsAiAgentUseCase = mergeTwoHomogeneousListsAiAgentUseCase;
    }

    public static void main(String[] args) {
		SpringApplication.run(SuperMargioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.mergeTwoHomogeneousListsUseCase.execute();

		List<Student> students = mergeTwoHomogeneousListsAiAgentUseCase
				.merge(
						MergeTwoHomogeneousListsService.people1().get(),
						MergeTwoHomogeneousListsService.people2().get(),
						Person.class,
						Student.class,
						"You are an AI agent that merges two lists of homogeneous objects. " +
								"Convert the list of Person objects to a list of Student objects. " +
								"Each Student should have an id, name, and age. " +
								"Return the merged list of Students."
				);
		System.out.println("AI Agent Merged Students: " + students);
	}
}
