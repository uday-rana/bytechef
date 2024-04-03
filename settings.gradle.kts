/*
 * This file was generated by the Gradle "init" task.
 *
 * The settings file is used to specify which projects to include in your build.
 *
 * Detailed information about configuring a multi-project build in Gradle can be found
 * in the user manual at https://docs.gradle.org/7.1/userguide/multi_project_builds.html
 */

pluginManagement {
    repositories {
        gradlePluginPortal()

        mavenCentral()

        maven {
            url = uri("https://repo.spring.io/release")
        }
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}

rootProject.name = "bytechef"

include("cli:cli-app")
include("cli:commands:component")

include("server:apps:server-app")

include("server:libs:atlas:atlas-configuration:atlas-configuration-api")
include("server:libs:atlas:atlas-configuration:atlas-configuration-config")
include("server:libs:atlas:atlas-configuration:atlas-configuration-converter")
include("server:libs:atlas:atlas-configuration:atlas-configuration-repository:atlas-configuration-repository-api")
include("server:libs:atlas:atlas-configuration:atlas-configuration-repository:atlas-configuration-repository-jdbc")
include("server:libs:atlas:atlas-configuration:atlas-configuration-repository:atlas-configuration-repository-resource")
include("server:libs:atlas:atlas-configuration:atlas-configuration-service")
include("server:libs:atlas:atlas-coordinator:atlas-coordinator-api")
include("server:libs:atlas:atlas-coordinator:atlas-coordinator-config")
include("server:libs:atlas:atlas-coordinator:atlas-coordinator-impl")
include("server:libs:atlas:atlas-execution:atlas-execution-api")
include("server:libs:atlas:atlas-execution:atlas-execution-config")
include("server:libs:atlas:atlas-execution:atlas-execution-repository:atlas-execution-repository-api")
include("server:libs:atlas:atlas-execution:atlas-execution-repository:atlas-execution-repository-jdbc")
include("server:libs:atlas:atlas-execution:atlas-execution-repository:atlas-execution-repository-memory")
include("server:libs:atlas:atlas-execution:atlas-execution-service")
include("server:libs:atlas:atlas-file-storage:atlas-file-storage-api")
include("server:libs:atlas:atlas-file-storage:atlas-file-storage-service")
include("server:libs:atlas:atlas-sync-executor")
include("server:libs:atlas:atlas-worker:atlas-worker-api")
include("server:libs:atlas:atlas-worker:atlas-worker-config")
include("server:libs:atlas:atlas-worker:atlas-worker-impl")

include("server:libs:automation:automation-connection:automation-connection-rest")
include("server:libs:automation:automation-workflow:automation-workflow-coordinator")
include("server:libs:automation:automation-configuration:automation-configuration-api")
include("server:libs:automation:automation-configuration:automation-configuration-instance-impl")
include("server:libs:automation:automation-configuration:automation-configuration-rest:automation-configuration-rest-api")
include("server:libs:automation:automation-configuration:automation-configuration-rest:automation-configuration-rest-impl")
include("server:libs:automation:automation-configuration:automation-configuration-service")
include("server:libs:automation:automation-demo-config")
include("server:libs:automation:automation-workflow:automation-workflow-execution:automation-workflow-execution-api")
include("server:libs:automation:automation-workflow:automation-workflow-execution:automation-workflow-execution-rest")
include("server:libs:automation:automation-workflow:automation-workflow-execution:automation-workflow-execution-service")
include("server:libs:automation:automation-swagger")

include("server:libs:core:async-config")
include("server:libs:core:cache-config")
include("server:libs:core:category:category-api")
include("server:libs:core:category:category-service")
include("server:libs:core:commons:commons-data")
include("server:libs:core:commons:commons-util")
include("server:libs:core:data-storage:data-storage-api")
include("server:libs:core:data-storage:data-storage-db:data-storage-db-api")
include("server:libs:core:data-storage:data-storage-db:data-storage-db-service")
include("server:libs:core:encryption:encryption-api")
include("server:libs:core:encryption:encryption-filesystem")
include("server:libs:core:encryption:encryption-impl")
include("server:libs:core:environment-config")
include("server:libs:core:error-api")
include("server:libs:core:evaluator")
include("server:libs:core:file-storage:file-storage-api")
include("server:libs:core:file-storage:file-storage-base64-service")
include("server:libs:core:file-storage:file-storage-filesystem-service")
include("server:libs:core:file-storage:file-storage-noop-service")
include("server:libs:core:jackson-config")
include("server:libs:core:jdbc-config")
include("server:libs:core:liquibase-config")
include("server:libs:core:message:message-api")
include("server:libs:core:message:message-broker:message-broker-amqp")
include("server:libs:core:message:message-broker:message-broker-api")
include("server:libs:core:message:message-broker:message-broker-jms")
include("server:libs:core:message:message-broker:message-broker-kafka")
include("server:libs:core:message:message-broker:message-broker-redis")
include("server:libs:core:message:message-broker:message-broker-sync")
include("server:libs:core:message:message-event:message-event-api")
include("server:libs:core:message:message-event:message-event-impl")
include("server:libs:core:rest:rest-api")
include("server:libs:core:rest:rest-impl")
include("server:libs:core:tag:tag-api")
include("server:libs:core:tag:tag-service")

include("server:libs:platform:platform-api")
include("server:libs:platform:platform-component:platform-component-registry:platform-component-registry-api")
include("server:libs:platform:platform-component:platform-component-api")
include("server:libs:platform:platform-component:platform-component-registry:platform-component-registry-service")
include("server:libs:platform:platform-component:platform-component-test-int-support")
include("server:libs:platform:platform-configuration:platform-configuration-api")
include("server:libs:platform:platform-configuration:platform-configuration-instance-api")
include("server:libs:platform:platform-configuration:platform-configuration-rest:platform-configuration-rest-api")
include("server:libs:platform:platform-configuration:platform-configuration-rest:platform-configuration-rest-impl")
include("server:libs:platform:platform-configuration:platform-configuration-service")
include("server:libs:platform:platform-connection:platform-connection-api")
include("server:libs:platform:platform-connection:platform-connection-rest:platform-connection-rest-api")
include("server:libs:platform:platform-connection:platform-connection-rest:platform-connection-rest-impl")
include("server:libs:platform:platform-connection:platform-connection-service")
include("server:libs:platform:platform-workflow:platform-workflow-coordinator:platform-workflow-coordinator-api")
include("server:libs:platform:platform-workflow:platform-workflow-coordinator:platform-workflow-coordinator-impl")
include("server:libs:platform:platform-file-storage:platform-file-storage-api")
include("server:libs:platform:platform-file-storage:platform-file-storage-service")
include("server:libs:platform:platform-oauth2:platform-oauth2-api")
include("server:libs:platform:platform-oauth2:platform-oauth2-service")
include("server:libs:platform:platform-scheduler:platform-scheduler-api")
include("server:libs:platform:platform-scheduler:platform-scheduler-impl")
include("server:libs:platform:platform-swagger")
include("server:libs:platform:platform-user:platform-user-api")
include("server:libs:platform:platform-user:platform-user-rest")
include("server:libs:platform:platform-user:platform-user-service")
include("server:libs:platform:platform-workflow:platform-workflow-task-dispatcher:platform-workflow-task-dispatcher-api")
include("server:libs:platform:platform-workflow:platform-workflow-task-dispatcher:platform-workflow-task-dispatcher-registry:platform-workflow-task-dispatcher-registry-api")
include("server:libs:platform:platform-workflow:platform-workflow-task-dispatcher:platform-workflow-task-dispatcher-registry:platform-workflow-task-dispatcher-registry-service")
include("server:libs:platform:platform-workflow:platform-workflow-task-dispatcher:platform-workflow-task-dispatcher-test-int-support")
include("server:libs:platform:platform-workflow:platform-workflow-test:platform-workflow-test-api")
include("server:libs:platform:platform-workflow:platform-workflow-test:platform-workflow-test-rest")
include("server:libs:platform:platform-workflow:platform-workflow-test:platform-workflow-test-service")
include("server:libs:platform:platform-webhook:platform-webhook-api")
include("server:libs:platform:platform-webhook:platform-webhook-impl")
include("server:libs:platform:platform-webhook:platform-webhook-rest")
include("server:libs:platform:platform-workflow:platform-workflow-worker:platform-workflow-worker-api")
include("server:libs:platform:platform-workflow:platform-workflow-worker:platform-workflow-worker-impl")
include("server:libs:platform:platform-workflow:platform-workflow-execution:platform-workflow-execution-api")
include("server:libs:platform:platform-workflow:platform-workflow-execution:platform-workflow-execution-rest:platform-workflow-execution-rest-api")
include("server:libs:platform:platform-workflow:platform-workflow-execution:platform-workflow-execution-rest:platform-workflow-execution-rest-impl")
include("server:libs:platform:platform-workflow:platform-workflow-execution:platform-workflow-execution-service")

include("server:libs:modules:components:accelo")
include("server:libs:modules:components:active-campaign")
include("server:libs:modules:components:affinity")
include("server:libs:modules:components:airtable")
include("server:libs:modules:components:aws:aws-s3")
include("server:libs:modules:components:bash")
include("server:libs:modules:components:capsule-crm")
include("server:libs:modules:components:copper")
include("server:libs:modules:components:csv-file")
include("server:libs:modules:components:data-mapper")
include("server:libs:modules:components:data-storage")
include("server:libs:modules:components:data-stream")
include("server:libs:modules:components:delay")
include("server:libs:modules:components:dropbox")
include("server:libs:modules:components:email")
include("server:libs:modules:components:encharge")
include("server:libs:modules:components:example")
include("server:libs:modules:components:file-storage")
include("server:libs:modules:components:filesystem")
include("server:libs:modules:components:freshsales")
include("server:libs:modules:components:google:google-calendar")
include("server:libs:modules:components:google:google-commons")
include("server:libs:modules:components:google:google-contacts")
include("server:libs:modules:components:google:google-docs")
include("server:libs:modules:components:google:google-drive")
include("server:libs:modules:components:google:google-mail")
include("server:libs:modules:components:google:google-people")
include("server:libs:modules:components:google:google-sheets")
include("server:libs:modules:components:http-client")
include("server:libs:modules:components:hubspot")
include("server:libs:modules:components:infobip")
include("server:libs:modules:components:insightly")
include("server:libs:modules:components:jira")
include("server:libs:modules:components:json-file")
include("server:libs:modules:components:keap")
include("server:libs:modules:components:logger")
include("server:libs:modules:components:mailchimp")
include("server:libs:modules:components:map")
include("server:libs:modules:components:microsoft:microsoft-one-drive")
include("server:libs:modules:components:microsoft:microsoft-outlook-365")
include("server:libs:modules:components:mysql")
include("server:libs:modules:components:object-helper")
include("server:libs:modules:components:ods-file")
include("server:libs:modules:components:openai")
include("server:libs:modules:components:petstore")
include("server:libs:modules:components:pipedrive")
include("server:libs:modules:components:pipeliner")
include("server:libs:modules:components:postgresql")
include("server:libs:modules:components:quickbooks")
include("server:libs:modules:components:random-helper")
include("server:libs:modules:components:rabbitmq")
include("server:libs:modules:components:resend")
include("server:libs:modules:components:salesflare")
include("server:libs:modules:components:schedule")
include("server:libs:modules:components:script")
include("server:libs:modules:components:sendgrid")
include("server:libs:modules:components:slack")
include("server:libs:modules:components:teamwork")
include("server:libs:modules:components:text-helper")
include("server:libs:modules:components:twilio")
//include("server:libs:modules:components:shopify")
include("server:libs:modules:components:var")
include("server:libs:modules:components:xero")
include("server:libs:modules:components:xlsx-file")
include("server:libs:modules:components:xml-file")
include("server:libs:modules:components:xml-helper")
include("server:libs:modules:components:webhook")
include("server:libs:modules:components:zendesk-sell")

include("server:libs:modules:task-dispatchers:branch")
include("server:libs:modules:task-dispatchers:condition")
include("server:libs:modules:task-dispatchers:each")
include("server:libs:modules:task-dispatchers:fork-join")
include("server:libs:modules:task-dispatchers:loop")
include("server:libs:modules:task-dispatchers:map")
include("server:libs:modules:task-dispatchers:parallel")
include("server:libs:modules:task-dispatchers:sequence")
include("server:libs:modules:task-dispatchers:subflow")

include("server:libs:test:test-support")
include("server:libs:test:test-int-support")

include("server:sdks:java:component-api")
include("server:sdks:java:definition-api")

// EE

include("cli:ee:commands:component:init:openapi")

include("server:ee:apps:api-gateway-app")
include("server:ee:apps:config-server-app")
include("server:ee:apps:configuration-app")
include("server:ee:apps:connection-app")
include("server:ee:apps:coordinator-app")
include("server:ee:apps:execution-app")
include("server:ee:apps:scheduler-app")
include("server:ee:apps:webhook-app")
include("server:ee:apps:worker-app")

include("server:ee:libs:atlas:atlas-configuration:atlas-configuration-repository:atlas-configuration-repository-git")
include("server:ee:libs:atlas:atlas-execution:atlas-execution-remote-rest")
include("server:ee:libs:atlas:atlas-execution:atlas-execution-remote-client")
include("server:ee:libs:atlas:atlas-worker:atlas-worker-remote-client")

include("server:ee:libs:automation:automation-configuration:automation-configuration-remote-client")
include("server:ee:libs:automation:automation-configuration:automation-configuration-remote-rest")

include("server:ee:libs:core:commons:commons-discovery")
include("server:ee:libs:core:commons:commons-rest-client")
include("server:ee:libs:core:data-storage:data-storage-db:data-storage-db-remote-rest")
include("server:ee:libs:core:data-storage:data-storage-db:data-storage-db-remote-client")
include("server:ee:libs:core:discovery:discovery-metadata-api")
include("server:ee:libs:core:discovery:discovery-redis")

include("server:ee:libs:platform:platform-component:platform-component-registry:platform-component-registry-remote-client")
include("server:ee:libs:platform:platform-component:platform-component-registry:platform-component-registry-remote-rest")
include("server:ee:libs:platform:platform-configuration:platform-configuration-remote-client")
include("server:ee:libs:platform:platform-configuration:platform-configuration-remote-rest")
include("server:ee:libs:platform:platform-connection:platform-connection-remote-client")
include("server:ee:libs:platform:platform-connection:platform-connection-remote-rest")
include("server:ee:libs:platform:platform-scheduler:platform-scheduler-remote-client")
include("server:ee:libs:platform:platform-scheduler:platform-scheduler-remote-rest")
include("server:ee:libs:platform:platform-workflow:platform-workflow-task-dispatcher:platform-workflow-task-dispatcher-registry:platform-workflow-task-dispatcher-registry-remote-client")
include("server:ee:libs:platform:platform-workflow:platform-workflow-task-dispatcher:platform-workflow-task-dispatcher-registry:platform-workflow-task-dispatcher-registry-remote-rest")
include("server:ee:libs:platform:platform-workflow:platform-workflow-execution:platform-workflow-execution-remote-rest")
include("server:ee:libs:platform:platform-workflow:platform-workflow-execution:platform-workflow-execution-remote-client")
