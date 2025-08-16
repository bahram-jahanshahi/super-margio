package se.bahram.ai.super_margio.ai_agents.merger.usecases.merge_two_homogenous_lists.applications.ports.in;

import java.util.List;

public interface MergeTwoHomogeneousLists_AiAgentUseCase {

    public <S, T> List<T> merge(List<S> list1, List<S> list2, Class<S> sourceType, Class<T> targetType, String prompt);
}
