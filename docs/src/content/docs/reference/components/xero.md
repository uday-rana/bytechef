---
title: "Xero"
description: "Xero is an online accounting software platform designed for small businesses and accountants to manage finances efficiently."
---
## Reference
<hr />

Xero is an online accounting software platform designed for small businesses and accountants to manage finances efficiently.


Categories: [accounting]


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



## Triggers


### New Bill
Trigger off whenever a new bill is added.

#### Type: STATIC_WEBHOOK
#### Properties

|      Name      |     Type     |     Control Type     |     Description     |
|:--------------:|:------------:|:--------------------:|:-------------------:|
| Webhook Key | STRING | TEXT  |  The key used to sign the webhook request.  |


### Output



Type: OBJECT


#### Properties

|     Type     |     Control Type     |
|:------------:|:--------------------:|
| STRING | TEXT  |
| STRING | TEXT  |
| {STRING\(ContactID), STRING\(Name), STRING\(EmailAddress)} | OBJECT_BUILDER  |
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |
| [{STRING\(Description), INTEGER\(Quantity), NUMBER\(UnitAmount)}] | ARRAY_BUILDER  |
| STRING | TEXT  |







### New Contact
Triggers when a contact is created.

#### Type: STATIC_WEBHOOK
#### Properties

|      Name      |     Type     |     Control Type     |     Description     |
|:--------------:|:------------:|:--------------------:|:-------------------:|
| Webhook Key | STRING | TEXT  |  The key used to sign the webhook request.  |


### Output



Type: OBJECT


#### Properties

|     Type     |     Control Type     |
|:------------:|:--------------------:|
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |
| [{STRING\(AddressType), STRING\(City), STRING\(Region), STRING\(PostalCode), STRING\(Country)}] | ARRAY_BUILDER  |
| [{STRING\(PhoneType), STRING\(PhoneNumber), STRING\(PhoneAreaCode), STRING\(PhoneCountryCode)}] | ARRAY_BUILDER  |







### New Invoice
Trigger off whenever a new invoice is added.

#### Type: STATIC_WEBHOOK
#### Properties

|      Name      |     Type     |     Control Type     |     Description     |
|:--------------:|:------------:|:--------------------:|:-------------------:|
| Webhook Key | STRING | TEXT  |  The key used to sign the webhook request.  |


### Output



Type: OBJECT


#### Properties

|     Type     |     Control Type     |
|:------------:|:--------------------:|
| STRING | TEXT  |
| STRING | TEXT  |
| {STRING\(ContactID), STRING\(Name), STRING\(EmailAddress)} | OBJECT_BUILDER  |
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |
| [{STRING\(Description), INTEGER\(Quantity), NUMBER\(UnitAmount)}] | ARRAY_BUILDER  |
| STRING | TEXT  |







<hr />



## Actions


### Create Bill
Creates draft bill (Accounts Payable).

#### Properties

|      Name      |     Type     |     Control Type     |     Description     |
|:--------------:|:------------:|:--------------------:|:-------------------:|
| Contact ID | STRING | SELECT  |  ID of the contact to create the bill for.  |
| Date | DATE | DATE  |  Date of the bill. If no date is specified, the current date will be used.  |
| Due Date | DATE | DATE  |  Date bill is due. If no date is specified, the current date will be used.  |
| Line Amount Type | STRING | SELECT  |  |
| Line Items | [{STRING\(Description), NUMBER\(Quantity), NUMBER\(UnitAmount), STRING\(AccountCode)}\($LineItem)] | ARRAY_BUILDER  |  Line items on the bill.  |
| Currency | STRING | SELECT  |  Currency that bill is raised in.  |
| Invoice Reference | STRING | TEXT  |  Reference number of the bill.  |


### Output



Type: OBJECT


#### Properties

|     Type     |     Control Type     |
|:------------:|:--------------------:|
| STRING | TEXT  |
| STRING | TEXT  |
| {STRING\(ContactID), STRING\(Name), STRING\(EmailAddress)} | OBJECT_BUILDER  |
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |
| [{STRING\(Description), INTEGER\(Quantity), NUMBER\(UnitAmount)}] | ARRAY_BUILDER  |
| STRING | TEXT  |






### Create Contact
Creates a new contact.

#### Properties

|      Name      |     Type     |     Control Type     |     Description     |
|:--------------:|:------------:|:--------------------:|:-------------------:|
| Name | STRING | TEXT  |  Full name of a contact or organisation.  |
| Company Number | STRING | TEXT  |  Company registration number.  |
| Account Number | STRING | TEXT  |  Unique account number to identify, reference and search for the contact.  |
| Contact Status | STRING | SELECT  |  Current status of a contact.  |
| First Name | STRING | TEXT  |  First name of primary person.  |
| Last Name | STRING | TEXT  |  Last name of primary person.  |
| Email Address | STRING | EMAIL  |  Email address of contact person.  |
| Bank Account Number | STRING | TEXT  |  Bank account number of contact.  |
| Tax Number | STRING | TEXT  |  Tax number of contact – this is also known as the ABN (Australia), GST Number (New Zealand), VAT Number (UK) or Tax ID Number (US and global) in the Xero UI depending on which regionalized version of Xero you are using.  |
| Phones | [{STRING\(PhoneType), STRING\(PhoneNumber), STRING\(PhoneAreaCode), STRING\(PhoneCountryCode)}] | ARRAY_BUILDER  |  |
| Addresses | [{STRING\(AddressType), STRING\(City), STRING\(Region), STRING\(PostalCode), STRING\(Country)}] | ARRAY_BUILDER  |  |


### Output



Type: OBJECT


#### Properties

|     Type     |     Control Type     |
|:------------:|:--------------------:|
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |
| [{STRING\(AddressType), STRING\(City), STRING\(Region), STRING\(PostalCode), STRING\(Country)}] | ARRAY_BUILDER  |
| [{STRING\(PhoneType), STRING\(PhoneNumber), STRING\(PhoneAreaCode), STRING\(PhoneCountryCode)}] | ARRAY_BUILDER  |






### Create Invoice
Creates draft invoice (Acount Receivable).

#### Properties

|      Name      |     Type     |     Control Type     |     Description     |
|:--------------:|:------------:|:--------------------:|:-------------------:|
| Contact ID | STRING | SELECT  |  ID of the contact to create the invoice for.  |
| Date | DATE | DATE  |  Date invoice was issued. If no date is specified, the current date will be used.  |
| Due Date | DATE | DATE  |  Date invoice is due. If no date is specified, the current date will be used.  |
| Line Amount Type | STRING | SELECT  |  |
| Line Items | [{STRING\(Description), INTEGER\(Quantity), NUMBER\(UnitAmount), NUMBER\(DiscountRate)}] | ARRAY_BUILDER  |  Line items on the invoice.  |
| Currency Code | STRING | SELECT  |  Currency code that invoice is raised in.  |
| Invoice Reference | STRING | TEXT  |  Reference number of the invoice.  |


### Output



Type: OBJECT


#### Properties

|     Type     |     Control Type     |
|:------------:|:--------------------:|
| STRING | TEXT  |
| STRING | TEXT  |
| {STRING\(ContactID), STRING\(Name), STRING\(EmailAddress)} | OBJECT_BUILDER  |
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |
| [{STRING\(Description), INTEGER\(Quantity), NUMBER\(UnitAmount)}] | ARRAY_BUILDER  |
| STRING | TEXT  |






### Create Quote
Creates a new quote draft.

#### Properties

|      Name      |     Type     |     Control Type     |     Description     |
|:--------------:|:------------:|:--------------------:|:-------------------:|
| Contact ID | STRING | SELECT  |  ID of the contact that the quote is being raised for.  |
| Date | DATE | DATE  |  Date quote was issued.  |
| Line Items | [{STRING\(Description), INTEGER\(Quantity), NUMBER\(UnitAmount), NUMBER\(DiscountRate)}] | ARRAY_BUILDER  |  Line items on the invoice.  |
| Line Amount Type | STRING | SELECT  |  |
| Expiry Date | DATE | DATE  |  Date quote expires  |
| Currency Code | STRING | SELECT  |  The currency code that quote has been raised in.  |
| Quote Number | STRING | TEXT  |  Unique alpha numeric code identifying a quote.  |
| Reference | STRING | TEXT  |  Additional reference number  |
| Branding Theme ID | STRING | SELECT  |  The branding theme ID to be applied to this quote.  |
| Title | STRING | TEXT  |  The title of the quote.  |
| Summary | STRING | TEXT  |  The summary of the quote.  |
| Terms | STRING | TEXT_AREA  |  The terms of the quote.  |


### Output



Type: OBJECT


#### Properties

|     Type     |     Control Type     |
|:------------:|:--------------------:|
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |
| {STRING\(ContactID), STRING\(Name), STRING\(EmailAddress)} | OBJECT_BUILDER  |
| [{STRING\(LineItemID), STRING\(Description), NUMBER\(UnitAmount), INTEGER\(DiscountRate), INTEGER\(Quantity)}] | ARRAY_BUILDER  |
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |
| STRING | TEXT  |






