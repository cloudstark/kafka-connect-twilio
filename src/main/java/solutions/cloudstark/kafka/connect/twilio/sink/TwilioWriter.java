/*
 * Copyright 2019 SMB GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package solutions.cloudstark.kafka.connect.twilio.sink;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.Collection;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.connect.sink.SinkRecord;
import solutions.cloudstark.kafka.connect.twilio.config.TwilioSinkConnectorConfig;

/**
 *
 */
@Slf4j
public class TwilioWriter {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    final TwilioSinkConnectorConfig config;

    public TwilioWriter(Map<String, String> properties) {
        config = new TwilioSinkConnectorConfig(properties);

        Twilio.init(
                config.getString(TwilioSinkConnectorConfig.TWILIO_ACCOUNT_SID),
                config.getString(TwilioSinkConnectorConfig.TWILIO_AUTH_TOKEN));
    }

    public void write(Collection<SinkRecord> records) {
        for (SinkRecord sinkRecord : records) {
            try {
                JsonNode jsonNode = MAPPER.valueToTree(sinkRecord.value());

                log.info(MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode));

                JsonNode recipients = jsonNode.get("recipients");
                if (recipients.isArray()) {
                    for (final JsonNode recipient : recipients) {
                        Message message = Message.creator(
                                new PhoneNumber(recipient.get("phoneNumber").asText()),
                                new PhoneNumber(config.getString(TwilioSinkConnectorConfig.TWILIO_FROM_NUMBER)),
                                jsonNode.get("body").asText()).create();
                    }
                }
            } catch (Exception e) {
                log.error("Error while sending", e);
            }
        }

    }
}