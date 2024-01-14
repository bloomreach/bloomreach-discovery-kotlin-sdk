[GO BACK](README.md)



# SEO WIDGET API Integration

For more information on the format of the JSON String, please refer to the [SEO WIDGET API](https://documentation.bloomreach.com/discovery/reference/widget-api) page.

### Initializing the BrSeoWidgetApi  object:
```
val seoWidgetApi = BrSeoWidgetApi.Builder()
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


### SEO WIDGET API

Create the object of SeoWidgetRequest for the request parameter to be passed to the SEO WIDGET API with different types of fields supported.

```

val seoWidgetRequest = SeoWidgetRequest().acctAuth("<ACC_AUTH>")
            .pType("product")
            .url("example.com")
            .prodId("p123")


//If 2nd parameter is passed as ResponseType.HTML then response will return HTML string else will return WidgetResponse object

        val response = seoWidgetApi?.seoWidgetApi(request, ResponseType.HTML)

        if(response is WidgetResponse) {
             //gets required response in response object of type WidgetResponse
        }
        else if(response is String){
            // response as HTML string
        } else {
             val error = response as BrApiError
             // if the API fails, handle error here.
        }


```
Supported parameters for creating SeoWidgetRequest object

| Parameter     | Method calls                                                                                             |
|---------------|----------------------------------------------------------------------------------------------------------|
| acct_auth | .acctAuth() |
| ptype         | .pType()|
| prod_id           | ..prodId()                                                                                         |
| prod_name           | .prodName()                                                                                     |
| pstatus           | .pStatus()                                                                                         |
| url           | .url()                                                                                         |
| user_agent           | .userAgent()                                                                                         |
