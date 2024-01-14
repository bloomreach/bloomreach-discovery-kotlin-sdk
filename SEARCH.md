[GO BACK](README.md)


# Search API Integration

For more information on the underlying API call and the associated parameters, please refer to the related:
[Bloomreach Search and Merchandising APIs page](https://documentation.bloomreach.com/discovery/reference/bloomreach-search-and-merchandising-apis).

### Initializing the Product API object:

[Kotlin]
```
val coreApi = BrCoreApi.Builder()
           .accountId("<ACCOUNT_ID>")
           .uuid("<UUID>")
           .visitorType(VisitorType.NEW_USER)
           .domainKey("<DOMAIN_KEY>")
           .authKey("AUTH_KEY")
           .userId("USER_ID")
           .environment(Env.STAGE)
           .build()
```

[Java]
```
BrCoreApi coreApi  = new BrCoreApi.Builder()
        .accountId("<ACCOUNT_ID>")
        .uuid("<UUID>")
        .visitorType(VisitorType.NEW_USER)
        .domainKey("<DOMAIN_KEY>")
        .authKey("AUTH_KEY")
        .userId("USER_ID")
        .environment(Env.STAGE)
        .build();
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


### Product Search

Create the object of ProductSearchRequest for the request parameter to be passed to the Product search API with different types of fields supported.

```
[Kotlin]

val productSearchRequest = ProductSearchRequest()
        .fl(listOf("pid", "title", "brand", "price"))
        .searchTerm("table")
        .url("example.com")

val response = coreApi?.productSearchApi(productSearchRequest)
    if(response is CoreResponse) {
        //gets required response in response object of type CoreResponse
    } else {
        val error = response as BrApiError
        // if the API fails, handle error here.
    }

```

```
[Java]

ProductSearchRequest productSearchRequest = new ProductSearchRequest()
                .fl(Arrays.asList("pid", "title", "brand", "price"))
                .searchTerm("test")
                .url("example.com");

//Call the productSearchApi method and pass the request object.

        Object response = coreApi.productSearchApi(productSearchRequest);
        if(response instanceof CoreResponse) {
            //gets required response in response object of type CoreResponse
        } else if(response instanceof BrApiError) {
             // if the API fails, handle error here.
        }
```
Supported parameters for creating ProductSearchRequest object

| Parameter   | Method calls                                                                                                                                                                                                                                                                                                                                                                                                                |
|-------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| rows        | .rows(10)                                                                                                                                                                                                                                                                                                                                                                                                                   |
| start       | .start(0)                                                                                                                                                                                                                                                                                                                                                                                                                   |
| url         | .url(“example.com”)                                                                                                                                                                                                                                                                                                                                                                                                         |
| fl          | Can be set using below ways<br/> <br/> .fl(“pid,title”)<br/>.fl(listOf(“pid”, “title”))<br/>.fl(arrayOf(“pid”, “title”))                                                                                                                                                                                                                                                                                                    |
| searchTerm  | .searchTerm(“test”)                                                                                                                                                                                                                                                                                                                                                                                                         |
| fq          | .fq("color:\"red\"")<br/>.fq("color", listOf("blue", "red"))<br/>.fq("color", "red")                                                                                                                                                                                                                                                                                                                                        |
| stats.field | .statsField("sale_price,length,width")<br/> .statsField(listOf("sale_price","length","width"))                                                                                                                                                                                                                                                                                                                              |
| efq         | .efq(“attribute:(\“value\”)”)<br/>efq(attribute= "Fabric", value= “Cotton”)<br/>//efq with NOT <br/>.efq(attribute= "Fabric", value= “Cotton”, isNot=true)<br/>//single attribute multiple values<br/>.efq(attribute= "Fabric", values = listOf("Cotton", "Linen"), operator= Operator.OR)<br/>//multiple attributes with operator<br/>.efq(values = mapOf("Fabric" to "Cotton", "Color" to "Red"), operator= Operator.AND) |
| facet.range | .facetRange(“price”) <br/> .facetRange(listOf("price","rating"))                                                                                                                                                                                                                                                                                                                                                            |
| facet.prefix | Max total connections                                                                                                                                                                                                                                                                                                                                                                                                       |
| sort        | //using simple string<br/>.sort("price+asc")<br/> <br/>//using Sort Object<br/>.sort(Sort("price", SortOrder.ASCENDING))                                                                                                                                                                                                                                                                                                    |
| user_id     | .userId(“usr123”)                                                                                                                                                                                                                                                                                                                                                                                                           |
| view_id     | .viewId(“”)                                                                                                                                                                                                                                                                                                                                                                                                                 |
| widget_id   | .widgetId(“widget123”)                                                                                                                                                                                                                                                                                                                                                                                                      |
| ll   | //[BOPIS]<br/>.latLong(“38.880657,-77.396935”)                                                                                                                                                                                                                                                                                                                                                                              |
| fl          | //[BOPIS]<br/>.fl(“store_lat_lon,pid,title”)                                                                                                                                                                                                                                                                                                                                                                                |
| fq          | //[BOPIS]<br/>.fq("store_lat_lon:\"100\"")                                                                                                                                                                                                                                                                                                                                                                                       |


### Category Search

Create the object of CategorySearchRequest for the request parameter to be passed to the Category search API with different types of fields supported.

```
val categorySearchRequest = CategorySearchRequest()
            .fl(
                listOf(
                    "pid",
                    "title",
                    "brand",
                    "price"
                )
            )
            .searchTerm("test")
.url("example.com")


//Call the categorySearchApi method and pass the request object.

val response = coreApi?.categorySearchApi(categorySearchRequest)
    if(response is CoreResponse) {
         //gets required response in response object of type CoreResponse
    } else {
        val error = response as BrApiError
        // if the API fails, handle error here.

    }
```
Note: Refer other parameters same as Product Search Parameters


### Content Search

Create the object of ContentSearchRequest for the request parameter to be passed to the Content search API with different types of fields supported.

```
val contentSearchRequest = ContentSearchRequest()
            .fl(
                listOf(
                    "pid",
                    "title",
                    "brand",
                    "price"
                )
            )
            .catalogName("Chair")
            .searchTerm("test")
.url("example.com")


Call the contentSearchApi method and pass the request object.

val response = coreApi?.contentSearchApi(contentSearchRequest)
    if(response is CoreResponse) {
         //gets required response in response object of type CoreResponse
    } else {
        val error = response as BrApiError
        // if the API fails, handle error here.
    }
```

| Parameter   | Method calls                                                                                                                                                                                                                                                                                                                                                                                                                |
|-------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| catalog_name        | .catalogName(“catName”)                                                                                                                                                                                                                                                                                                                                                                                                                 |
Note: Refer other parameters same as Product Search Parameters

### BestSeller API
Create the object of BestSellerRequest for the request parameter to be passed to the BestSeller API with different types of fields supported.

```
val bestSellerRequest = BestSellerRequest()
            .fl(
                listOf(
                    "pid",
                    "title",
                    "brand",
                    "price"
                )
            )
            .searchTerm("test")
	.url("example.com")

//Call the bestSellerApi method and pass the request object.

val response = coreApi?.bestSellerApi(bestSellerRequest)
    if(response is CoreResponse) {
         //gets required response in response object of type CoreResponse
    } else {
        val error = response as BrApiError
        // if the API fails, handle error here.
    }

```

| Parameter   | Method calls                                                                                                                                                                                                                                                                                                                                                                                                               |
|-------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| title        | .title(“productName”)|
Note: Refer other parameters same as Product Search Parameters

### Thematic API

For more information on the format of the JSON String, please refer to the [Thematic Pages API](https://documentation.bloomreach.com/discovery/reference/thematic-pages-api) page.

Create the object of ThematicRequest for the request parameter to be passed to the Thematic API with different types of fields supported.

```
val thematicRequest = ThematicRequest().fl("pid")
.searchTerm("jack chains")
        	.url("example.com")

..Call the thematicApi method and pass the request object.

val response = coreApi?.thematicApi(thematicRequest)
    if(response is CoreResponse) {
         //gets required response in response object of type CoreResponse
    } else {
        val error = response as BrApiError
        // if the API fails, handle error here.
    }
```
