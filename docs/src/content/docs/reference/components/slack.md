---
title: "Slack"
description: "Slack is a messaging platform for teams to communicate and collaborate."
---
## Reference
<hr />

Slack is a messaging platform for teams to communicate and collaborate.


Categories: [communication, developer-tools]


Version: 1

<hr />



## Connections

Version: 1


### OAuth2 Authorization Code

#### Properties

|      Name      |     Type     |     Control Type     |     Description     |
|:--------------:|:------------:|:--------------------:|:-------------------:|
| Client Id | STRING | TEXT  |  |
| Client Secret | STRING | TEXT  |  |





<hr />





## Actions


### Send message
Sends a message to a public channel, private channel, or existing direct message conversation.

#### Properties

|      Name      |     Type     |     Control Type     |     Description     |
|:--------------:|:------------:|:--------------------:|:-------------------:|
| Channel ID | STRING | SELECT  |  ID of the channel, private group, or IM channel to send message to.  |
| Message | STRING | TEXT_AREA  |  The text of your message.  |


### Output



Type: OBJECT


#### Properties

|     Type     |     Control Type     |
|:------------:|:--------------------:|
| BOOLEAN | SELECT  |
| STRING | TEXT  |
| STRING | TEXT  |
| {STRING\(user), STRING\(type), STRING\(ts), STRING\(text), STRING\(team), STRING\(subtype)} | OBJECT_BUILDER  |
| STRING | TEXT  |
| {[STRING]\(messages)} | OBJECT_BUILDER  |






### Send Direct Message
Sends a direct message to another user in a workspace. If it hasn't already, a direct message conversation will be created.

#### Properties

|      Name      |     Type     |     Control Type     |     Description     |
|:--------------:|:------------:|:--------------------:|:-------------------:|
| User ID | STRING | SELECT  |  ID of the user to send the direct message to.  |
| Message | STRING | TEXT_AREA  |  The text of your message.  |


### Output



Type: OBJECT


#### Properties

|     Type     |     Control Type     |
|:------------:|:--------------------:|
| BOOLEAN | SELECT  |
| STRING | TEXT  |
| STRING | TEXT  |
| {STRING\(user), STRING\(type), STRING\(ts), STRING\(text), STRING\(team), STRING\(subtype)} | OBJECT_BUILDER  |
| STRING | TEXT  |
| {[STRING]\(messages)} | OBJECT_BUILDER  |






