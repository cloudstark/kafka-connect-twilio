package solutions.cloudstark.kafka.connect.twilio.sink;

import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import solutions.cloudstark.kafka.connect.twilio.config.TwilioSinkConnectorConfig;

import static org.junit.Assert.assertEquals;

public class TwilioSinkConnectorTest {

    private TwilioSinkConnector connector;

    @Before
    public void setup() {
        connector = new TwilioSinkConnector();
    }

    @Test
    public void testTaskClass() {
        assertEquals(TwilioSinkTask.class, connector.taskClass());
    }

    @Test
    public void testStartConnectionFailure() throws Exception {
        connector.start(Collections.singletonMap(TwilioSinkConnectorConfig.TWILIO_ACCOUNT_SID, "jdbc:foo"));
    }
}