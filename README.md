# kafka-admin-client

A very simple Java 11 application that shows basic usage of Kafka's AdminClient. This also works with [Azure Event Hubs and its Kafka protocol support](https://github.com/Azure/azure-event-hubs-for-kafka).

## Configuring

Make sure to edit `src/main/resources/kakfa.config` and provide the connection details for your Kafka cluster or Azure Event Hub namespace.

## Building

`mvn clean package`

## Running

`mvn exec:java -Dexec.mainClass="TestAdminClient"`
