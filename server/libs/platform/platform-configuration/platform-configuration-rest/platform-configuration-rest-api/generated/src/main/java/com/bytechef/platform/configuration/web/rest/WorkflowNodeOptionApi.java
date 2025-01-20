/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.10.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.bytechef.platform.configuration.web.rest;

import com.bytechef.platform.configuration.web.rest.model.OptionModel;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-01-20T14:51:10.068478+01:00[Europe/Zagreb]", comments = "Generator version: 7.10.0")
@Validated
@Tag(name = "workflow-node-option", description = "The Platform Workflow Node Option Internal API")
public interface WorkflowNodeOptionApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /workflows/{id}/properties/{workflowNodeName}/options/{propertyName} : Get an action or trigger property options shown in the editor
     * Get an action or trigger property options shown in the editor.
     *
     * @param id The workflow id (required)
     * @param workflowNodeName The name of a workflow&#39;s action task or trigger (E.g. mailchimp_1) (required)
     * @param propertyName The name of a property. (required)
     * @param lookupDependsOnPaths The list of dependency paths. (optional)
     * @param searchText Optional search text used to filter option items (optional)
     * @return The list of options. (status code 200)
     */
    @Operation(
        operationId = "getWorkflowNodeOptions",
        summary = "Get an action or trigger property options shown in the editor",
        description = "Get an action or trigger property options shown in the editor.",
        tags = { "workflow-node-option" },
        responses = {
            @ApiResponse(responseCode = "200", description = "The list of options.", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OptionModel.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/workflows/{id}/properties/{workflowNodeName}/options/{propertyName}",
        produces = { "application/json" }
    )
    
    default ResponseEntity<List<OptionModel>> getWorkflowNodeOptions(
        @Parameter(name = "id", description = "The workflow id", required = true, in = ParameterIn.PATH) @PathVariable("id") String id,
        @Parameter(name = "workflowNodeName", description = "The name of a workflow's action task or trigger (E.g. mailchimp_1)", required = true, in = ParameterIn.PATH) @PathVariable("workflowNodeName") String workflowNodeName,
        @Parameter(name = "propertyName", description = "The name of a property.", required = true, in = ParameterIn.PATH) @PathVariable("propertyName") String propertyName,
        @Parameter(name = "lookupDependsOnPaths", description = "The list of dependency paths.", in = ParameterIn.QUERY) @Valid @RequestParam(value = "lookupDependsOnPaths", required = false) List<String> lookupDependsOnPaths,
        @Parameter(name = "searchText", description = "Optional search text used to filter option items", in = ParameterIn.QUERY) @Valid @RequestParam(value = "searchText", required = false) String searchText
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"description\" : \"description\", \"label\" : \"label\", \"value\" : \"\" }, { \"description\" : \"description\", \"label\" : \"label\", \"value\" : \"\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
