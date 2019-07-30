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

package solutions.cloudstark.kafka.connect.twilio.config;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;

/**
 *
 */
@Slf4j
public class TwilioSinkConnectorConfig extends AbstractConfig {

    public static final String TWILIO_ACCOUNT_SID = "twilio.account.sid";
    public static final String TWILIO_AUTH_TOKEN = "twilio.auth.token";
    public static final String TWILIO_FROM_NUMBER = "twilio.from.number";
    private static final String TWILIO_ACCOUNT_SID_DOC = "Twilio Account Sid.";
    private static final String TWILIO_AUTH_TOKEN_DOC = "Twilio Auth Token";
    private static final String TWILIO_FROM_NUMBER_DOC = "Twilio SMS From Number";

    public TwilioSinkConnectorConfig(ConfigDef config, Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public TwilioSinkConnectorConfig(final Map<String, String> parsedConfig) {
        super(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        return new ConfigDef()
                .define(TWILIO_ACCOUNT_SID, ConfigDef.Type.STRING, ConfigDef.Importance.HIGH, TWILIO_ACCOUNT_SID_DOC)
                .define(TWILIO_AUTH_TOKEN, ConfigDef.Type.STRING, ConfigDef.Importance.HIGH, TWILIO_AUTH_TOKEN_DOC)
                .define(TWILIO_FROM_NUMBER, ConfigDef.Type.STRING, ConfigDef.Importance.HIGH, TWILIO_FROM_NUMBER_DOC);
    }

}
