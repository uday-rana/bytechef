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

package com.bytechef.platform.component.definition;

import com.bytechef.component.definition.ComponentDefinition;

import java.util.List;
import java.util.Optional;

/**
 * @author Ivica Cardic
 */
public interface DataStreamComponentDefinition extends ComponentDefinition {

    enum ComponentType {
        SOURCE, DESTINATION
    }

    /**
     *
     * @return
     */
    FilterComponentDefinitionBiFunction getFilterComponentDefinition();

    /**
     * @return
     */
    default List<String> getWorkflowConnectionKeys() {
        return List.of("source", "destination");
    }

    /**
     *
     */
    @FunctionalInterface
    interface FilterComponentDefinitionBiFunction {

        /**
         *
         * @param componentDefinition
         * @return
         */
        Optional<ComponentDefinition> apply(ComponentDefinition componentDefinition, ComponentType componentType);
    }
}
