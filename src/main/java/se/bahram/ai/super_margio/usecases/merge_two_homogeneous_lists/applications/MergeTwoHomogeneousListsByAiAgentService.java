package se.bahram.ai.super_margio.usecases.merge_two_homogeneous_lists.applications;

import org.springframework.stereotype.Service;
import se.bahram.ai.super_margio.ai_agents.merger.usecases.merge_two_homogenous_lists.applications.ports.in.MergeTwoHomogeneousLists_AiAgentUseCase;
import se.bahram.ai.super_margio.usecases.merge_two_homogeneous_lists.applications.ports.in.MergeTwoHomogeneousListsByAiAgent_UseCase;
import se.bahram.ai.super_margio.usecases.merge_two_homogeneous_lists.domain.Person;
import se.bahram.ai.super_margio.usecases.merge_two_homogeneous_lists.domain.Student;

import java.util.List;
import java.util.function.Supplier;

@Service
public class MergeTwoHomogeneousListsByAiAgentService implements MergeTwoHomogeneousListsByAiAgent_UseCase {

    private final MergeTwoHomogeneousLists_AiAgentUseCase aiAgent;

    public MergeTwoHomogeneousListsByAiAgentService(MergeTwoHomogeneousLists_AiAgentUseCase aiAgent) {
        this.aiAgent = aiAgent;
    }

    @Override
    public void execute() {
        List<Student> students = aiAgent
                .merge(
                        people1(),
                        people2(),
                        Person.class,
                        Student.class,
                        """
                        You are an AI agent that merges two lists of people
                        and converts them to a list of students.
                        """
                );
    }

    public List<Person> people2() {
        return List.of(new Person(4L, "David", 28),
                new Person(5L, "Eve", 22),
                new Person(6L, "Frank", 40));
    }

    public List<Person> people1() {
        return List.of(new Person(1L, "Alice", 30),
                new Person(2L, "Bob", 25),
                new Person(3L, "Charlie", 35));
    }
}
