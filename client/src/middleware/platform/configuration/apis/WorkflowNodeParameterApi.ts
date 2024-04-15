/* tslint:disable */
/* eslint-disable */
/**
 * The Platform Configuration API
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


import * as runtime from '../runtime';
import type {
  UpdateWorkflowNodeParameter200ResponseModel,
  UpdateWorkflowNodeParameterRequestModel,
} from '../models/index';
import {
    UpdateWorkflowNodeParameter200ResponseModelFromJSON,
    UpdateWorkflowNodeParameter200ResponseModelToJSON,
    UpdateWorkflowNodeParameterRequestModelFromJSON,
    UpdateWorkflowNodeParameterRequestModelToJSON,
} from '../models/index';

export interface UpdateWorkflowNodeParameterRequest {
    id: string;
    updateWorkflowNodeParameterRequestModel?: UpdateWorkflowNodeParameterRequestModel;
}

/**
 * 
 */
export class WorkflowNodeParameterApi extends runtime.BaseAPI {

    /**
     * Updates a workflow node parameter.
     * Updates a workflow node parameter
     */
    async updateWorkflowNodeParameterRaw(requestParameters: UpdateWorkflowNodeParameterRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<UpdateWorkflowNodeParameter200ResponseModel>> {
        if (requestParameters['id'] == null) {
            throw new runtime.RequiredError(
                'id',
                'Required parameter "id" was null or undefined when calling updateWorkflowNodeParameter().'
            );
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        headerParameters['Content-Type'] = 'application/json';

        const response = await this.request({
            path: `/workflows/{id}/parameters`.replace(`{${"id"}}`, encodeURIComponent(String(requestParameters['id']))),
            method: 'PATCH',
            headers: headerParameters,
            query: queryParameters,
            body: UpdateWorkflowNodeParameterRequestModelToJSON(requestParameters['updateWorkflowNodeParameterRequestModel']),
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => UpdateWorkflowNodeParameter200ResponseModelFromJSON(jsonValue));
    }

    /**
     * Updates a workflow node parameter.
     * Updates a workflow node parameter
     */
    async updateWorkflowNodeParameter(requestParameters: UpdateWorkflowNodeParameterRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<UpdateWorkflowNodeParameter200ResponseModel> {
        const response = await this.updateWorkflowNodeParameterRaw(requestParameters, initOverrides);
        return await response.value();
    }

}
