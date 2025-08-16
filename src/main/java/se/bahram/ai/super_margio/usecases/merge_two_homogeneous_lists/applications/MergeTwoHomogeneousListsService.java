package se.bahram.ai.super_margio.usecases.merge_two_homogeneous_lists.applications;

import org.springframework.stereotype.Service;
import se.bahram.ai.super_margio.usecases.merge_two_homogeneous_lists.applications.ports.in.MergeTwoHomogeneousLists_UseCase;
import se.bahram.ai.super_margio.usecases.merge_two_homogeneous_lists.domain.Person;
import se.bahram.ai.super_margio.usecases.merge_two_homogeneous_lists.domain.Student;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
public class MergeTwoHomogeneousListsService implements MergeTwoHomogeneousLists_UseCase {
    @Override
    public void execute() {

        List<Student> students = this.merge(people1(), people2(), new Merger());
    }

    private <T, S> List<S> merge(Supplier<List<T>> listSupplier1,
                                 Supplier<List<T>> listSupplier2,
                                 Function<List<List<T>>, List<S>> merger) {

        return merger.apply(List.of(listSupplier1.get(), listSupplier2.get()));
    }

    static class Merger implements Function<List<List<Person>>, List<Student>> {

        @Override
        public List<Student> apply(List<List<Person>> lists) {
            return lists.stream()
                    .flatMap(List::stream)
                    .map(person -> new Student(person.id().toString(), person.name(), person.age()))
                    .toList();
        }
    }

    public static Supplier<List<Person>> people2() {
        return () -> List.of(new Person(4L, "David", 28),
                new Person(5L, "Eve", 22),
                new Person(6L, "Frank", 40));
    }

    public static Supplier<List<Person>> people1() {
        return () -> List.of(new Person(1L, "Alice", 30),
                new Person(2L, "Bob", 25),
                new Person(3L, "Charlie", 35));
    }
}
