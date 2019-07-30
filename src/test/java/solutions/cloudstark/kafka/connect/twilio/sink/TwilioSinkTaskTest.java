package solutions.cloudstark.kafka.connect.twilio.sink;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.connect.sink.SinkRecord;
import org.junit.Test;
import solutions.cloudstark.kafka.connect.twilio.config.TwilioSinkConnectorConfig;

public class TwilioSinkTaskTest {
//    @Mock
//    TwilioWriter writer;
//    @Mock
//    Logger log;
//    @Mock
//    SinkTaskContext context;
//    @InjectMocks
//    TwilioSinkTask sendGridSinkTask;

//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    public void testVersion() throws Exception {
//        String result = sendGridSinkTask.version();
//        Assert.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    public void testStart() throws Exception {
        Map<String, String> props = new HashMap<>();
        props.put(TwilioSinkConnectorConfig.TWILIO_ACCOUNT_SID, "ABC");
        props.put(TwilioSinkConnectorConfig.TWILIO_AUTH_TOKEN, "XYZ");
        props.put(TwilioSinkConnectorConfig.TWILIO_FROM_NUMBER, "...");

        TwilioSinkTask task = new TwilioSinkTask();

        task.start(props);

        task.put(Collections.singleton(
                new SinkRecord("", 1, null, null, null, "", 42)
        ));
        //        sendGridSinkTask.start(new HashMap<String, String>() {{
        //    put("String", "String");
        //}});
    }

    @Test
    public void testPut() throws Exception {
        // sendGridSinkTask.put(Arrays.<SinkRecord>asList(null));
    }

    @Test
    public void testStop() throws Exception {
        //   sendGridSinkTask.stop();
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme