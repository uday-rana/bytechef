
/*
 * Copyright 2021 <your company/name>.
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

package com.bytechef.hermes.definition.registry.rsocket.client.facade;

import com.bytechef.commons.discovery.util.DiscoveryUtils;
import com.bytechef.commons.reactor.util.MonoUtils;
import com.bytechef.commons.rsocket.util.RSocketUtils;
import com.bytechef.hermes.definition.registry.dto.ComponentDefinitionDTO;
import com.bytechef.hermes.definition.registry.facade.ComponentDefinitionFacade;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * @author Ivica Cardic
 */
public class ComponentDefinitionFacadeRSocketClient implements ComponentDefinitionFacade {

    private final DiscoveryClient discoveryClient;
    private final RSocketRequester.Builder rSocketRequesterBuilder;

    public ComponentDefinitionFacadeRSocketClient(
        DiscoveryClient discoveryClient, RSocketRequester.Builder rSocketRequesterBuilder) {

        this.discoveryClient = discoveryClient;
        this.rSocketRequesterBuilder = rSocketRequesterBuilder;
    }

    @Override
    public List<ComponentDefinitionDTO> getComponentDefinitions(
        Boolean actionDefinitions, Boolean connectionDefinitions, Boolean connectionInstances,
        Boolean triggerDefinitions) {

        return MonoUtils.get(
            Mono.zip(
                DiscoveryUtils.filterServiceInstances(discoveryClient.getInstances("worker-service-app"))
                    .stream()
                    .map(serviceInstance -> RSocketUtils.getRSocketRequester(serviceInstance, rSocketRequesterBuilder)
                        .route("ComponentDefinitionFacade.getComponentDefinitions")
                        .data(new HashMap<>() {
                            {
                                put("actionDefinitions", actionDefinitions);
                                put("connectionDefinitions", connectionDefinitions);
                                put("connectionInstances", connectionInstances);
                                put("triggerDefinitions", triggerDefinitions);
                            }
                        })
                        .retrieveMono(new ParameterizedTypeReference<List<ComponentDefinitionDTO>>() {}))
                    .toList(),
                this::toComponentDefinitions));
    }

    @SuppressWarnings("unchecked")
    private List<ComponentDefinitionDTO> toComponentDefinitions(Object[] objectArray) {
        return Arrays.stream(objectArray)
            .map(object -> (List<ComponentDefinitionDTO>) object)
            .flatMap(Collection::stream)
            .distinct()
            .toList();
    }
}
