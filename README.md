# mabaya-assignment #
Repo for my solution for Mabaya home exam

# How to Use #
Please run `mvn clean package` to have the latests JAR in the `/targer`, once this is done you can run the `docker-compose.yml`.
This will load a PostgreSQL DB with a `product` table containing the values set in the `sql/02_load_data.sql` file.
An `app` container will be loaded which will handle all the requests (using Spring Boot).

## Usages and Examples ##
You can query the app by sending requests to localhost/api/v1/{API} as follow:

`GET health`: A simple endpoint to check if the application started successfully.

`POST create_campaign`: Used for creating a campaign with these fields:
```json
{
	"name":"CAMPAIGN_NAME", // This is the campaign ID.
	"bid":1.1, // Any double number goes here.
	"startDate" : "YYYY-MM-ddTHH:mm:ss", // Such as "2023-07-14T16:01:30"
	"productIdentifierToPromote": [1, 2] // List of the products' id
}
```

`GET serve_add?category=YOUR_CATEGORY`: Get a product to promote according to a category and highest bid in a Campaign,
or if there is no such product returns any other product with the highest bid in a Campaign.

You may use the attached Insomnia collection (or the HAR collection).
