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

package com.bytechef.component.pinecone.action;

import static com.bytechef.component.definition.ComponentDsl.action;
import static com.bytechef.component.definition.ComponentDsl.string;
import static com.bytechef.component.pinecone.constant.PineconeConstants.API_KEY;
import static com.bytechef.component.pinecone.constant.PineconeConstants.EMBEDDING_API_KEY;
import static com.bytechef.component.pinecone.constant.PineconeConstants.ENVIRONMENT;
import static com.bytechef.component.pinecone.constant.PineconeConstants.INDEX_NAME;
import static com.bytechef.component.pinecone.constant.PineconeConstants.PROJECT_ID;
import static com.bytechef.component.vector.database.constant.VectorDatabaseConstants.QUERY;

import com.bytechef.component.definition.ActionContext;
import com.bytechef.component.definition.ComponentDsl.ModifiableActionDefinition;
import com.bytechef.component.definition.Parameters;
import com.bytechef.component.vector.database.VectorDatabase;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.vectorstore.PineconeVectorStore;

/**
 * @author Monika Kušter
 */
public class PineconeDataQueryAction {

    public static final ModifiableActionDefinition ACTION_DEFINITION = action("dataQuery")
        .title("Data Query")
        .description("Loads data into a Pinecone vector store using OpenAI embeddings.")
        .properties(
            string(QUERY)
                .label("Query")
                .description("The query to be executed.")
                .required(true))
        .output()
        .perform(PineconeDataQueryAction::perform);

    private PineconeDataQueryAction() {
    }

    protected static Object perform(
        Parameters inputParameters, Parameters connectionParameters, ActionContext actionContext) {

        return VECTOR_STORE.query(inputParameters, connectionParameters);
    }

    public static final VectorDatabase VECTOR_STORE = connectionParameters -> {
        OpenAiEmbeddingModel openAiEmbeddingModel = new OpenAiEmbeddingModel(
            new OpenAiApi(connectionParameters.getRequiredString(EMBEDDING_API_KEY)));

        PineconeVectorStore.PineconeVectorStoreConfig pineconeVectorStoreConfig =
            PineconeVectorStore.PineconeVectorStoreConfig.builder()
                .withApiKey(connectionParameters.getRequiredString(API_KEY))
                .withEnvironment(connectionParameters.getRequiredString(ENVIRONMENT))
                .withProjectId(connectionParameters.getRequiredString(PROJECT_ID))
                .withIndexName(connectionParameters.getRequiredString(INDEX_NAME))
                .build();

        return new PineconeVectorStore(pineconeVectorStoreConfig, openAiEmbeddingModel);
    };
}
