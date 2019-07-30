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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;
import solutions.cloudstark.kafka.connect.twilio.Version;

/**
 *
 */
@Slf4j
public class TwilioSinkTask extends SinkTask {

    private TwilioWriter writer;

    @Override
    public String version() {
        return Version.getVersion();
    }

    @Override
    public void start(Map<String, String> properties) {
        log.info(asciiArt());
        initWriter(properties);
    }

    @Override
    public void put(Collection<SinkRecord> records) {
        log.trace("Putting {} to Twilio.", records);
        writer.write(records);
    }

    @Override
    public void stop() {
        log.info("Stopping TwilioSinkTask.");
        if (writer != null) {
            //writer.stop();
        }
    }

    private void initWriter(final Map<String, String> config) {
        this.writer = new TwilioWriter(config);
    }

    private String asciiArt() {
        return new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("/twilio-sink-ascii.txt")))
                .lines()
                .collect(Collectors.joining("\n"));
    }
}
