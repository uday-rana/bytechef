/* tslint:disable */
/* eslint-disable */
/**
 * The Platform Configuration Internal API
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

import { mapValues } from '../runtime';
/**
 * Definition of a connection to an outside service.
 * @export
 * @interface ConnectionDefinitionBasic
 */
export interface ConnectionDefinitionBasic {
    /**
     * The description used from the connection's component.
     * @type {string}
     * @memberof ConnectionDefinitionBasic
     */
    componentDescription?: string;
    /**
     * The component name used from the connection's component.
     * @type {string}
     * @memberof ConnectionDefinitionBasic
     */
    componentName: string;
    /**
     * The title used from the connection's component
     * @type {string}
     * @memberof ConnectionDefinitionBasic
     */
    componentTitle?: string;
    /**
     * The version of a connection.
     * @type {number}
     * @memberof ConnectionDefinitionBasic
     */
    version: number;
}

/**
 * Check if a given object implements the ConnectionDefinitionBasic interface.
 */
export function instanceOfConnectionDefinitionBasic(value: object): value is ConnectionDefinitionBasic {
    if (!('componentName' in value) || value['componentName'] === undefined) return false;
    if (!('version' in value) || value['version'] === undefined) return false;
    return true;
}

export function ConnectionDefinitionBasicFromJSON(json: any): ConnectionDefinitionBasic {
    return ConnectionDefinitionBasicFromJSONTyped(json, false);
}

export function ConnectionDefinitionBasicFromJSONTyped(json: any, ignoreDiscriminator: boolean): ConnectionDefinitionBasic {
    if (json == null) {
        return json;
    }
    return {
        
        'componentDescription': json['componentDescription'] == null ? undefined : json['componentDescription'],
        'componentName': json['componentName'],
        'componentTitle': json['componentTitle'] == null ? undefined : json['componentTitle'],
        'version': json['version'],
    };
}

export function ConnectionDefinitionBasicToJSON(json: any): ConnectionDefinitionBasic {
    return ConnectionDefinitionBasicToJSONTyped(json, false);
}

export function ConnectionDefinitionBasicToJSONTyped(value?: ConnectionDefinitionBasic | null, ignoreDiscriminator: boolean = false): any {
    if (value == null) {
        return value;
    }

    return {
        
        'componentDescription': value['componentDescription'],
        'componentName': value['componentName'],
        'componentTitle': value['componentTitle'],
        'version': value['version'],
    };
}

