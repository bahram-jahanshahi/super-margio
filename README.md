# Super Margio (Agentic AI vs Traditional Approach — Merging Lists in Spring)

This project demonstrates how to **merge two lists of domain objects (`Person`)** and transform them into another type (`Student`) in two different ways:  

1. **Traditional Java approach** (manual mapping with streams and DTOs).  
2. **Agentic AI approach** using **Spring AI** and an **LLM as an agent** to handle merging and transformation via natural language prompts.  

It’s a simple use case, but it highlights how **Agentic AI** can reduce boilerplate and shift our mindset from *“writing logic”* to *“describing intent”*.  

---

## 📂 Project Structure

```
src/main/java/se/bahram/ai/super_margio/
│
├── ai_agents/
│   └── merger/
│       └── usecases/
│           └── merge_two_homogenous_lists/
│               └── applications/
│                   └── MergeTwoHomogeneousLists_AiAgentService.java   # AI-powered merge
│
├── usecases/
│   └── merge_two_homogeneous_lists/
│       ├── applications/
│       │   ├── MergeTwoHomogeneousListsByAiAgentService.java          # Uses AI agent
│       │   └── MergeTwoHomogeneousListsService.java                   # Traditional approach
│       └── domain/
│           ├── Person.java
│           └── Student.java
```

---

## 👥 Domain Models

```java
public record Person(Long id, String name, int age) {}
public record Student(String id, String studentName, int age) {}
```

---

## ⚙️ Traditional Approach

- Uses **Java Streams** to flatten and map lists.  
- Deterministic and predictable.  
- Boilerplate-heavy (every new transformation requires code changes).  

Snippet:

```java
lists.stream()
     .flatMap(List::stream)
     .map(person -> new Student(person.id().toString(), person.name(), person.age()))
     .toList();
```

---

## 🤖 Agentic AI Approach (Spring AI)

- Uses **Spring AI’s ChatClient** to delegate merging + mapping to an LLM.  
- You provide:  
  - Source type (`Person`)  
  - Target type (`Student`)  
  - Input lists (as JSON)  
  - System prompt (natural language instruction)  

Snippet:

```java
List<Student> students = aiAgent.merge(
    people1(),
    people2(),
    Person.class,
    Student.class,
    """
    You are an AI agent that merges two lists of people
    and converts them to a list of students.
    """
);
```

---

## 🔍 Comparison

| Aspect           | Traditional Approach                          | Agentic AI Approach                          |
|------------------|-----------------------------------------------|----------------------------------------------|
| **Implementation** | Explicit Java code with manual mapping        | Natural language prompt + AI agent           |
| **Flexibility**    | Fixed to Person→Student unless modified       | Easily adapted by tweaking the prompt        |
| **Boilerplate**    | Requires streams + DTOs + mapping logic       | Minimal Java, AI handles logic               |
| **Reliability**    | Deterministic and predictable                 | Depends on AI quality and prompt design      |
| **Use case fit**   | Stable, performance-critical systems          | Prototyping, evolving models, experimentation |

---

## 🏃 Running the Project

### Prerequisites
- Java 21+  
- Maven or Gradle  
- Spring Boot 3.x  
- Spring AI configured with your LLM provider (e.g. OpenAI API key).  

### Run
```bash
./mvnw spring-boot:run
```

---

## 🌟 Conclusion

- The **traditional approach** is best when you need reliability and performance.  
- The **Agentic AI approach** is a glimpse into the future — less boilerplate, more intent-driven development.  

This repo is a **first step in teaching our brain to think agentically** when building modern applications.  

---

## 📖 Resources

- [Spring AI Documentation](https://docs.spring.io/spring-ai/)  
- [Medium Article (explaining this project)](https://medium.com/your-article-link)  

---

## 📂 Full Code

👉 [GitHub Repo](https://github.com/your-repo-link)  
