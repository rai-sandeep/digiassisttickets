# digiassisttickets
Ticket MS for DigiAssist

## Running Locally
[If you are authenticated in the Cloud SDK](https://cloud.google.com/sdk/gcloud/reference/auth/application-default/login), your credentials will be automatically found by the Spring Boot Starter for Google Cloud Firestore.

Alternatively, [create a service account from the Google Cloud Console](https://console.cloud.google.com/iam-admin/serviceaccounts) and download its private key.
Then, uncomment the `spring.cloud.gcp.firestore.credentials.location` property in the [application.properties](link:src/main/resources/application.properties) file and fill its value with the path to your service account private key on your local file system, prepended with `classpath:`.

## Deploying to GCP
Can be deployed directly to a GCP project with associated Firestore database and Firestore API enabled.

## Reference
1. [Spring Data Cloud Firestore](https://docs.spring.io/spring-cloud-gcp/docs/current/reference/html/firestore.html)
1. [Spring Cloud GCP Spring Data Firestore Example](https://github.com/spring-attic/spring-cloud-gcp/tree/main/spring-cloud-gcp-samples/spring-cloud-gcp-data-firestore-sample)