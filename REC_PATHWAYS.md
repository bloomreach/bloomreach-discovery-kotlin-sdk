[GO BACK](README.md)


# Recommendations and Pathways API Integration

For more information on the format of the JSON String, please refer to the [Recommendations and Pathways API v2](https://documentation.bloomreach.com/discovery/reference/recommendations-and-pathways-apis) page.

### Initializing the BrRecsAndPathwaysApi object:

```
val recsAndPathwaysApi = BrRecsAndPathwaysApi.Builder()
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


### Item-based Recommendation Widget

Create the object of WidgetRequest for the request parameter to be passed to the Item-based Recommendation Widget API with different types of fields supported.

```
 val widgetRequest = WidgetRequest()
            .itemIds("<ITEM_IDs>")
            .contextId("<CONTEXT_ID>")
            .url("example.com")

//Call the itemBasedRecommendationWidgetApi method and pass the request object.

        val response = recsAndPathwaysApi?.itemBasedRecommendationWidgetApi("<WIDGET_ID>", widgetRequest)
        if(response is RecsAndPathwaysResponse) {
              //gets required response in response object of type RecsAndPathwaysResponse
        } else {
            val error = response as BrApiError
            // if the API fails, handle error here.
        }
```

Supported parameters for creating WidgetRequest object

| Parameter  | Method calls                                                                                                                                                                                                                                                                                                                                                                                                   |
|------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| item_ids       | .itemIds(“1234”)<br/><br/>.itemIds(listOf(“1234”, “98765”))                                                                                                                                                                                                                                                                                                                                                    |
| url      | .url()                                                                                                                                                                                                                                                                                                                                                                                                         |
| context_id        | .contextId(“test”)                                                                                                                                                                                                                                                                                                                                                                                             |
| fields| .fields(“f1,f2”)<br/> <br/> .fields(listOf(“f1”, “f2”))                                                                                                                                                                                                                                                                                                                                                        |
| filter | .filter(“”) <br/> <br/>.filter("price", 10.. 100, true) <br/> <br/> .filter("fabric", listOf("cotton", "linen"), Operator.AND)                                                                                                                                                                                                                                                                                 |
| rows    | .rows()                                                                                                                                                                                                                                                                                                                                                                                              |
| user_id    | .userId()                                                                                                                                                                                                                                                                                                                                                                                                   |
| viewId  | .viewId()                                                                                                                                                                                                                                                                                                                                                                                        |

### Category-based Widget API

Create the object of WidgetRequest for the request parameter to be passed to the Category-based Widget API with different types of fields supported.

```
val widgetRequest = WidgetRequest()
            .catId("1234")
            .url("example.com")

Call the categoryBasedWidgetApi method and pass the request object.

        val response = recsAndPathwaysApi?.categoryBasedWidgetApi("<WIDGET_ID>", widgetRequest)
        if(response is RecsAndPathwaysResponse) {
              //gets required response in response object of type RecsAndPathwaysResponse
        } else {
            val error = response as BrApiError
            // if the API fails, handle error here.
        }
```
Supported parameters for creating WidgetRequest object

| Parameter  | Method calls                                                                                                                               |
|------------|--------------------------------------------------------------------------------------------------------------------------------------------|
| cat_id       | .catId(“1234”)                                                                                                                             |
| url      | .url()                                                                                                                                     |
| facet        | .facet(true)                                                                                                                               |
| filter_facet| .filterFacet("color:\"red\"")<br/> <br/> .filterFacet("color", "red") <br/> <br/> .filterFacet("color", listOf("red", "blue"), Operator.OR) |
| filter | .filter(“”) <br/> <br/>.filter("price", 10.. 100, true) <br/> <br/> .filter("fabric", listOf("cotton", "linen"), Operator.AND)             |
| fields    | .fields(“f1,f2”) <br/> <br/> .fields(listOf(“f1”, “f2”))                                                                                   |
| rows    | .rows()                                                                                                                                    |
| start    | .start()                                                                                                                                    |
| user_id    | .userId()                                                                                                                                  |
| viewId  | .viewId()                                                                                                                                  |

### Keyword-based Widget API

Create the object of WidgetRequest for the request parameter to be passed to the Keyword-based Widget API with different types of fields supported.

```
val widgetRequest = WidgetRequest()
            .query("cap")
            .url("example.com")

 val response = recsAndPathwaysApi?.keywordBasedWidgetApi("<WIDGET_ID>", widgetRequest)
        if(response is RecsAndPathwaysResponse) {
              //gets required response in response object of type RecsAndPathwaysResponse
        } else {
            val error = response as BrApiError
            // if the API fails, handle error here.
        }

```

| Parameter   | Method calls                                                                                                                                                                                                                                                                                                                                                                                                                |
|-------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| query        | .query(“cap”)                                                                                                                                                                                                                                                                                                                                                                                                                 |
Note: Other parameters same as Category Widget API

### Personalization-based Widget API

Create the object of WidgetRequest for the request parameter to be passed to the Personalization-based Widget API with different types of fields supported.

```
val widgetRequest = WidgetRequest()
            .query("cap")
	.userId(“u123”)
.url("example.com")

 val response = recsAndPathwaysApi?.personalizationBasedWidgetApi("<WIDGET_ID>", widgetRequest)
        if(response is RecsAndPathwaysResponse) {
              //gets required response in response object of type RecsAndPathwaysResponse
        } else {
            val error = response as BrApiError
        // if the API fails, handle error here.

        }


```

| Parameter   | Method calls                                                                                                                                                                                                                                                                                                                                                                                                                |
|-------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| user_id        | .userId()                                                                                                                                                                                                                                                                                                                                                                                                                 |
| query        | .query(“cap”)                                                                                                                                                                                                                                                                                                                                                                                                                 |
Note: Other parameters same as Item-based Widget API

### Global Recommendation Widget API

Create the object of WidgetRequest for the request parameter to be passed to the Global Recommendation Widget API
with different types of fields supported.

```
val widgetRequest = WidgetRequest()

 val response = recsAndPathwaysApi?.globalRecommendationWidgetApi("<WIDGET_ID>", widgetRequest)
        if(response is RecsAndPathwaysResponse) {
              //gets required response in response object of type RecsAndPathwaysResponse
        } else {
            val error = response as BrApiError
        // if the API fails, handle error here.

        }

```
Note: Other parameters same as Item-based Widget API

### Visual Search

1. Upload Image API
```
val result = pathwaysApi?.uploadImageForVisualSearch("<WIDGET_ID>", inputStream, "test.png")

    if (result is ImageUploadResponse) {
    //gets required response in response object of type ImageUploadResponse
        val imageId = result.response?.imageId ?: ""
        // use the imageId value to create WidgetRequest object
    }
    else if (result is BrApiError) {
         val error = response as BrApiError
        // if the API fails, handle error here.
    }
```

2. Visual Search API

Create the object of WidgetRequest for the request parameter to be passed to the Visual Search Widget API
with different types of fields supported.

```
val widgetRequest = WidgetRequest()
        .url("https://www.example.com")
        .fields(listOf("pid", "title", "brand", "price", "thumb_image"))
        .imageId(imageId)

 val response = recsAndPathwaysApi?.visualSearchWidgetApi("<WIDGET_ID>", widgetRequest)
        if(response is RecsAndPathwaysResponse) {
              //gets required response in response object of type RecsAndPathwaysResponse
        } else {
            val error = response as BrApiError
        // if the API fails, handle error here.
        }
```
For more information, please refer to the [Visual Search APIs](https://documentation.bloomreach.com/discovery/reference/visual-search-apis) page.

