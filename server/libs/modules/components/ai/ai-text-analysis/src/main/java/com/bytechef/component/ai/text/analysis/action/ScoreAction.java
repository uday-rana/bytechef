/*
 * Copyright 2023-present ByteChef Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bytechef.component.ai.text.analysis.action;

import static com.bytechef.component.ai.llm.constant.LLMConstants.MAX_TOKENS_PROPERTY;
import static com.bytechef.component.ai.llm.constant.LLMConstants.MODEL;
import static com.bytechef.component.ai.llm.constant.LLMConstants.TEMPERATURE_PROPERTY;
import static com.bytechef.component.ai.text.analysis.constant.AiTextAnalysisConstants.CRITERIA;
import static com.bytechef.component.ai.text.analysis.constant.AiTextAnalysisConstants.CRITERION;
import static com.bytechef.component.ai.text.analysis.constant.AiTextAnalysisConstants.HIGHEST_SCORE;
import static com.bytechef.component.ai.text.analysis.constant.AiTextAnalysisConstants.IS_DECIMAL;
import static com.bytechef.component.ai.text.analysis.constant.AiTextAnalysisConstants.LOWEST_SCORE;
import static com.bytechef.component.ai.text.analysis.constant.AiTextAnalysisConstants.MODEL_NO_OPTIONS_PROPERTY;
import static com.bytechef.component.ai.text.analysis.constant.AiTextAnalysisConstants.MODEL_OPTIONS_PROPERTY;
import static com.bytechef.component.ai.text.analysis.constant.AiTextAnalysisConstants.MODEL_PROVIDER_PROPERTY;
import static com.bytechef.component.ai.text.analysis.constant.AiTextAnalysisConstants.MODEL_URL_PROPERTY;
import static com.bytechef.component.ai.text.analysis.constant.AiTextAnalysisConstants.TEXT;
import static com.bytechef.component.definition.ComponentDsl.action;
import static com.bytechef.component.definition.ComponentDsl.array;
import static com.bytechef.component.definition.ComponentDsl.bool;
import static com.bytechef.component.definition.ComponentDsl.number;
import static com.bytechef.component.definition.ComponentDsl.object;
import static com.bytechef.component.definition.ComponentDsl.string;

import com.bytechef.component.ai.text.analysis.action.definition.AiTextAnalysisActionDefinition;
import com.bytechef.component.ai.text.analysis.constant.AiTextAnalysisConstants;
import com.bytechef.component.ai.text.analysis.util.AiTextAnalysisUtil;
import com.bytechef.component.definition.Parameters;
import com.bytechef.config.ApplicationProperties;
import com.bytechef.platform.component.definition.ParametersFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreAction implements AITextAnalysisAction {
    public final AiTextAnalysisActionDefinition actionDefinition;

    public ScoreAction(ApplicationProperties.Ai.Component component) {
        this.actionDefinition = getActionDefinition(component);
    }

    private AiTextAnalysisActionDefinition getActionDefinition(ApplicationProperties.Ai.Component component) {
        return new AiTextAnalysisActionDefinition(
            action(AiTextAnalysisConstants.SCORE)
                .title("Score")
                .description("Scores the text based on several criteria")
                .properties(
                    MODEL_PROVIDER_PROPERTY,
                    MODEL_OPTIONS_PROPERTY,
                    MODEL_NO_OPTIONS_PROPERTY,
                    MODEL_URL_PROPERTY,
                    string(TEXT)
                        .label("Text")
                        .description("The text that is to be scored.")
                        .required(true),
                    array(CRITERIA)
                        .label("Criteria")
                        .items(
                            object()
                                .properties(
                                    string(CRITERION)
                                        .label("Criterion")
                                        .description("What is the criterion of the text that is to be scored.")
                                        .exampleValue("Grammar")
                                        .required(true),
                                    number(LOWEST_SCORE)
                                        .label("Lowest Score")
                                        .description("The lowest possible score the text can achieve in this category.")
                                        .defaultValue(1),
                                    number(HIGHEST_SCORE)
                                        .label("Highest Score")
                                        .description(
                                            "The highest possible score the text can achieve in this category.")
                                        .defaultValue(10),
                                    bool(IS_DECIMAL)
                                        .label("Decimal Numbers")
                                        .description("Whether the score should use decimal numbers or not.")
                                        .defaultValue(false)))
                        .required(true),
                    MAX_TOKENS_PROPERTY,
                    TEMPERATURE_PROPERTY)
                .output(),
            component, this);
    }

    public Parameters createParameters(Parameters inputParameters) {
        Map<String, Object> modelInputParametersMap = new HashMap<>();

        String systemPrompt =
            "You are an objective text scoring judge. You will receive a text and list of criteria that you will score the text on. Within the list of criteria you will also receive `Lowest Score` which indicates the lowest possible score you can give, `Highest Score` which indicates the highest possible score you can give and `Decimal` which tells you that you will be using decimal numbers if true or only integers if false. Your response will be a JSON array of objects for each criteria containing your score and a short explanation.";

        StringBuilder userBuilder = new StringBuilder();
        userBuilder.append("Text: ")
            .append(inputParameters.getString(TEXT))
            .append("\n");

        List<AiTextAnalysisUtil.Criteria> criteria =
            inputParameters.getList(CRITERIA, AiTextAnalysisUtil.Criteria.class, List.of());

        userBuilder.append("Criteria: {")
            .append("\n");

        criteria.forEach(critrion -> userBuilder.append("{")
            .append("\n")
            .append("Criterion: ")
            .append(critrion.criterion())
            .append("\n")
            .append("Lowest Score: ")
            .append(critrion.lowestScore())
            .append("\n")
            .append("Highest Score: ")
            .append(critrion.highestScore())
            .append("\n")
            .append("Decimal: ")
            .append(critrion.isDecimal())
            .append("\n")
            .append("},")
            .append("\n"));

        userBuilder.append("}\n");

        modelInputParametersMap.put("messages",
            List.of(
                Map.of("content", systemPrompt, "role", "system"),
                Map.of("content", userBuilder.toString(), "role", "user")));
        modelInputParametersMap.put("model", inputParameters.getString(MODEL));

        return ParametersFactory.createParameters(modelInputParametersMap);
    }

}
