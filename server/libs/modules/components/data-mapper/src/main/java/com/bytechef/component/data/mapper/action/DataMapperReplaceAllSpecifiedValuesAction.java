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

package com.bytechef.component.data.mapper.action;

import static com.bytechef.component.data.mapper.constant.DataMapperConstants.FROM;
import static com.bytechef.component.data.mapper.constant.DataMapperConstants.INPUT;
import static com.bytechef.component.data.mapper.constant.DataMapperConstants.MAPPINGS;
import static com.bytechef.component.data.mapper.constant.DataMapperConstants.TO;
import static com.bytechef.component.data.mapper.constant.DataMapperConstants.TYPE;
import static com.bytechef.component.data.mapper.constant.DataMapperConstants.VALUE_TYPE_FROM;
import static com.bytechef.component.data.mapper.constant.DataMapperConstants.VALUE_TYPE_TO;
import static com.bytechef.component.definition.ComponentDSL.array;
import static com.bytechef.component.definition.ComponentDSL.bool;
import static com.bytechef.component.definition.ComponentDSL.date;
import static com.bytechef.component.definition.ComponentDSL.dateTime;
import static com.bytechef.component.definition.ComponentDSL.integer;
import static com.bytechef.component.definition.ComponentDSL.nullable;
import static com.bytechef.component.definition.ComponentDSL.number;
import static com.bytechef.component.definition.ComponentDSL.object;
import static com.bytechef.component.definition.ComponentDSL.option;
import static com.bytechef.component.definition.ComponentDSL.string;
import static com.bytechef.component.definition.ComponentDSL.time;

import com.bytechef.component.data.mapper.util.mapping.Mapping;
import com.bytechef.component.data.mapper.util.mapping.ObjectMapping;
import com.bytechef.component.data.mapper.util.mapping.ObjectTypeMapping;
import com.bytechef.component.definition.ActionContext;
import com.bytechef.component.definition.ComponentDSL;
import com.bytechef.component.definition.ComponentDSL.ModifiableActionDefinition;
import com.bytechef.component.definition.Parameters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Ivica Cardic
 */
public class DataMapperReplaceAllSpecifiedValuesAction {

    private DataMapperReplaceAllSpecifiedValuesAction() {
    }

    public static final ModifiableActionDefinition ACTION_DEFINITION = ComponentDSL.action("replaceAllSpecifiedValues")
        .title("Replace all specified values")
        .description(
            "Goes through all object parameters and replaces all specified input parameter values.")
        .properties(
            integer(TYPE)
                .label("Type")
                .description("The input type.")
                .options(
                    option("Object", 1),
                    option("Array", 2))
                .required(true),
            object(INPUT)
                .label("Input")
                .description("An object containing one or more properties.")
                .displayCondition("type == 1")
                .required(true),
            array(INPUT)
                .label("Input")
                .description("An array containing one or more objects.")
                .displayCondition("type == 2")
                .items(object())
                .required(true),
            integer(VALUE_TYPE_FROM)
                .label("Value type From")
                .description("The value type of 'from' property value.")
                .required(true)
                .options(
                    option("Array", 1),
                    option("Boolean", 2),
                    option("Date", 3),
                    option("Date Time", 4),
                    option("Integer", 5),
                    option("Nullable", 6),
                    option("Number", 7),
                    option("Object", 8),
                    option("String", 9),
                    option("Time", 10))
                .required(true),
            integer(VALUE_TYPE_TO)
                .label("Value type To")
                .description("The value type of 'to' property value.")
                .required(true)
                .options(
                    option("Array", 1),
                    option("Boolean", 2),
                    option("Date", 3),
                    option("Date Time", 4),
                    option("Integer", 5),
                    option("Nullable", 6),
                    option("Number", 7),
                    option("Object", 8),
                    option("String", 9),
                    option("Time", 10))
                .required(true),
            array(MAPPINGS)
                .label("Mappings")
                .description(
                    "Object that contains properties 'from' and 'to'.")
                .items(
                    object().properties(
                        array(FROM)
                            .label("Value from")
                            .description("Defines the property value you want to change.")
                            .displayCondition("valueTypeFrom == 1")
                            .required(true),
                        bool(FROM)
                            .label("Value from")
                            .description("Defines the property value you want to change.")
                            .displayCondition("valueTypeFrom == 2")
                            .required(true),
                        date(FROM)
                            .label("Value from")
                            .description("Defines the property value you want to change.")
                            .displayCondition("valueTypeFrom == 3")
                            .required(true),
                        dateTime(FROM)
                            .label("Value from")
                            .description("Defines the property value you want to change.")
                            .displayCondition("valueTypeFrom == 4")
                            .required(true),
                        integer(FROM)
                            .label("Value from")
                            .description("Defines the property value you want to change.")
                            .displayCondition("valueTypeFrom == 5")
                            .required(true),
                        nullable(FROM)
                            .label("Value from")
                            .description("Defines the property value you want to change.")
                            .displayCondition("valueTypeFrom == 6")
                            .required(true),
                        number(FROM)
                            .label("Value from")
                            .description("Defines the property value you want to change.")
                            .displayCondition("valueTypeFrom == 7")
                            .required(true),
                        object(FROM)
                            .label("Value from")
                            .description("Defines the property value you want to change.")
                            .displayCondition("valueTypeFrom == 8")
                            .required(true),
                        string(FROM)
                            .label("Value from")
                            .description("Defines the property value you want to change.")
                            .displayCondition("valueTypeFrom == 9")
                            .required(true),
                        time(FROM)
                            .label("Value from")
                            .description("Defines the property value you want to change.")
                            .displayCondition("valueTypeFrom == 10")
                            .required(true),
                        array(TO)
                            .label("Value to")
                            .description("Defines what you want to change the property value to.")
                            .displayCondition("valueTypeTo == 1")
                            .required(true),
                        bool(TO)
                            .label("Value to")
                            .description("Defines what you want to change the property value to.")
                            .displayCondition("valueTypeTo == 2")
                            .required(true),
                        date(TO)
                            .label("Value to")
                            .description("Defines what you want to change the property value to.")
                            .displayCondition("valueTypeTo == 3")
                            .required(true),
                        dateTime(TO)
                            .label("Value to")
                            .description("Defines what you want to change the property value to.")
                            .displayCondition("valueTypeTo == 4")
                            .required(true),
                        integer(TO)
                            .label("Value to")
                            .description("Defines what you want to change the property value to.")
                            .displayCondition("valueTypeTo == 5")
                            .required(true),
                        nullable(TO)
                            .label("Value to")
                            .description("Defines what you want to change the property value to.")
                            .displayCondition("valueTypeTo == 6")
                            .required(true),
                        number(TO)
                            .label("Value to")
                            .description("Defines what you want to change the property value to.")
                            .displayCondition("valueTypeTo == 7")
                            .required(true),
                        object(TO)
                            .label("Value to")
                            .description("Defines what you want to change the property value to.")
                            .displayCondition("valueTypeTo == 8")
                            .required(true),
                        string(TO)
                            .label("Value to")
                            .description("Defines what you want to change the property value to.")
                            .displayCondition("valueTypeTo == 9")
                            .required(true),
                        time(TO)
                            .label("Value to")
                            .description("Defines what you want to change the property value to.")
                            .displayCondition("valueTypeTo == 10")
                            .required(true)))
                .required(true))
        .output()
        .perform(DataMapperReplaceAllSpecifiedValuesAction::perform);

    protected static Object perform(
        Parameters inputParameters, Parameters connectionParameters, ActionContext context) {

        List<ObjectMapping> mappingList = inputParameters.getList(MAPPINGS, ObjectMapping.class, List.of());
        Map<Object, Object> mappings = mappingList.stream()
            .collect(Collectors.toMap(Mapping::getFrom, Mapping::getTo));

        Map<String, Object> output = new HashMap<>();
        if (inputParameters.getInteger(TYPE)
            .equals(1)) {
            Map<String, Object> input = inputParameters.getMap(INPUT, Object.class, Map.of());

            fillOutput(input, mappings, output);
        } else {
            List<Object> input = inputParameters.getList(INPUT, Object.class, List.of());

            for (Object object : input) {
                fillOutput(((Map<String, Object>) object), mappings, output);
            }
        }

        return output;
    }

    private static void
        fillOutput(Map<String, Object> input, Map<Object, Object> mappings, Map<String, Object> output) {
        for (Map.Entry<String, Object> entry : input.entrySet()) {
            if (mappings.containsKey(entry.getValue()))
                output.put(entry.getKey(), mappings.get(entry.getValue()));
            else
                output.put(entry.getKey(), entry.getValue());
        }
    }
}
