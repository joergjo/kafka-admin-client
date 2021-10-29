
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestAdminClient {
    private static Logger logger = LoggerFactory.getLogger(TestAdminClient.class);

    public static void main(String... args) {
        int status = 0;
        try {
            var adminClient = createAdminClient();
            var topics = adminClient.listTopics().names().get();
            logger.info("Found {} topic(s)", topics.size());
            for (var topic : topics) {
                logger.info("Topic: {}", topic);
            }
            var consumerGroups = adminClient.listConsumerGroups().valid().get();
            logger.info("Found {} consumer group(s)", consumerGroups.size());
            for (var consumerGroup : consumerGroups) {
                logger.info("Consumer Group: {}", consumerGroup.groupId());
            }

        } catch (InterruptedException e) {
            status = 1;
            logger.error("Interrupted: ", e);
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            status = 1;
            logger.error("Fatal error: ", e);
        }
        logger.info("Exiting");
        // System.exit() is being used here to play nicely with mvn exec:java
        System.exit(status);
    }

    private static AdminClient createAdminClient() throws IOException {
        try (var reader = new FileReader("src/main/resources/kafka.config")) {
            var properties = new Properties();
            properties.load(reader);
            return AdminClient.create(properties);
        }
    }
}
