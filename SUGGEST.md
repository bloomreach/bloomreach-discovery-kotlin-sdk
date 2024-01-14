[GO BACK](README.md)

# Auto Suggest API Integration

For more information on the format of the JSON String, please refer to the [Autosuggest API](https://documentation.bloomreach.com/discovery/reference/autosuggest-api) page.

### Initializing the Autosuggest API object:
```
val brSuggestApi = BrSuggestApi.Builder()
.accountId("<ACCOUNT_ID>")
                	.uuid("<UUID>")
                	.visitorType(VisitorType.NEW_USER)
                	.domainKey("<DOMAIN_KEY>")
                	.authKey("AUTH_KEY")
                	.userId("USER_ID")
                	.environment(Env.STAGE)
                	.build()

```

| Parameter  | Description                                                                                |
| ------------- |--------------------------------------------------------------------------------------------|
| accountId  | Account ID provided by Bloomreach                                                          |
| uuid  | A 13 digit random number                                                                   |
| visitorType  | ENUM type for New User or returning user.                                                  |
| domainKey  | Your site domain's ID, which is provided by Bloomreach.                                    |
| authKey  | The Bloomreach-provided authentication key                                                 |
| userId  | The Bloomreach-provided authentication key                                                 |
| environment  | ENUM to specify APIs to be pointed to which environment. STAGE or PROD. Defaulted to STAGE |
| baseUrl  | The base url of the API env if its different the default|
| connectionTimeOut  | Connection timeout in milliseconds                                                         |
| maxTotalConnections  | Max total connections                                                                      |
| responseTimeout  | Connection timeout for getting a response                                                  |


### Autosuggest API

Create the object of AutosuggestRequest for the request parameter to be passed to the Autosuggest API with different types of fields supported.

```

val autoSuggestRequest = AutosuggestRequest()
            .catalogViews(mapOf("product" to "store", "p1" to "sq"))
	.url("example.com")


//Call the autoSuggestApi method and pass the request object.

val response = suggestApi?.autoSuggestApi(autoSuggestRequest)
    if(response is AutosuggestRequest) {
         //gets required response in response object of type AutosuggestRequest
    } else {
        val error = response as BrApiError
        // if the API fails, handle error here.
    }

```
Supported parameters for creating ProductSearchRequest object

| Parameter     | Method calls                                                                                                   |
|---------------|----------------------------------------------------------------------------------------------------------------|
| catalog_views | .catalogViews(“product:store1`&#124;`p1:s1”)<br/><br/>.catalogViews(mapOf("product" to "store", "p1" to "s1")) |
| q         | .searchTerm(“test”)                                                                                                |
| user_agent           | .userAgent(“”)                                                                                           |
| user_id           | .userId(“”)                                                                                          |
| url           | .url(“”)                                                                                          |
