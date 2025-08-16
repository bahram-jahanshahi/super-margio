package se.bahram.ai.super_margio.ai_agents.merger.usecases.merge_two_homogenous_lists.applications;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import se.bahram.ai.super_margio.ai_agents.merger.usecases.merge_two_homogenous_lists.applications.ports.in.MergeTwoHomogeneousLists_AiAgentUseCase;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class MergeTwoHomogeneousLists_AiAgentService implements MergeTwoHomogeneousLists_AiAgentUseCase {

    private final ChatClient.Builder chatBuilder;

    ObjectMapper objectMapper = new ObjectMapper();

    public MergeTwoHomogeneousLists_AiAgentService(ChatClient.Builder chatBuilder) {
        this.chatBuilder = chatBuilder;
    }

    @Override
    public <S, T> List<T> merge(List<S> firstList,
                                List<S> secondList,
                                Class<S> sourceType,
                                Class<T> targetType,
                                String systemPrompt) {
        String prompt = """
                Merge first list and second list.
                Convert the list of merged list from source type to target type.
                <SOURCE-TYPE> %s </SOURCE-TYPE>
                <TARGET-TYPE> %s </TARGET-TYPE>
                <INPUT-JSON-LIST-1> %s </INPUT-JSON-LIST-1>
                <INPUT-JSON-LIST-2> %s </INPUT-JSON-LIST-2>
                """
                .formatted(
                        jsonType(sourceType),
                        jsonType(targetType),
                        json(firstList),
                        json(secondList));

        return chatClient().prompt()
                .system(systemPrompt)
                .user(prompt)
                .call()
                .entity(new ParameterizedTypeReference<List<T>>() {});
    }

    private <S> String json(List<S> firstList) {
        String firstListAsJson;
        try {
            firstListAsJson = objectMapper.writeValueAsString(firstList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return firstListAsJson;
    }

    ChatClient chatClient() {
        return chatBuilder.build();
    }

    public static String jsonType(Class<?> clazz) {
        Map<String, String> attributes = new LinkedHashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            attributes.put(field.getName(), field.getType().getSimpleName());
        }
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(attributes);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
