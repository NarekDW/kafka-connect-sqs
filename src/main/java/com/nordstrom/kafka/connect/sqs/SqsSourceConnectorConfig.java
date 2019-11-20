/*
 * Copyright 2019 Nordstrom, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nordstrom.kafka.connect.sqs;

import java.util.Map;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;

public class SqsSourceConnectorConfig extends AbstractConfig {

  private final Integer maxMessages;
  private final String queueUrl;
  private final String topics;
  private final Integer waitTimeSeconds;
  private final Boolean deletionEnable;

  private static final ConfigDef CONFIG_DEF = new ConfigDef()
      .define(SqsConnectorConfigKeys.SQS_MAX_MESSAGES.getValue(), Type.INT, 1, Importance.LOW,
          "Maximum number of messages to read from SQS queue for each poll interval. Range is 0 - 10 with default of 1.")
      .define(SqsConnectorConfigKeys.SQS_QUEUE_URL.getValue(), Type.STRING, Importance.HIGH, "The URL of the SQS queue to be read from.")
      .define(SqsConnectorConfigKeys.SQS_WAIT_TIME_SECONDS.getValue(), Type.INT, 1, Importance.LOW,
          "Duration (in seconds) to wait for a message to arrive in the queue. Default is 1.")
      .define(SqsConnectorConfigKeys.TOPICS.getValue(), Type.STRING, Importance.HIGH, "The Kafka topic to be written to.")
      .define(SqsConnectorConfigKeys.DELETION_ENABLE.getValue(), Type.BOOLEAN, false, Importance.LOW, "This is for SQS Dead-Letter Queues");


  public static ConfigDef config() {
    return CONFIG_DEF;
  }

  public SqsSourceConnectorConfig(Map<?, ?> originals) {
    super(config(), originals);
    maxMessages = getInt(SqsConnectorConfigKeys.SQS_MAX_MESSAGES.getValue());
    queueUrl = getString(SqsConnectorConfigKeys.SQS_QUEUE_URL.getValue());
    topics = getString(SqsConnectorConfigKeys.TOPICS.getValue());
    waitTimeSeconds = getInt(SqsConnectorConfigKeys.SQS_WAIT_TIME_SECONDS.getValue());
    deletionEnable = getBoolean(SqsConnectorConfigKeys.DELETION_ENABLE.getValue());
  }

  public Integer getMaxMessages() {
    return maxMessages;
  }

  public String getQueueUrl() {
    return queueUrl;
  }

  public String getTopics() {
    return topics;
  }

  public Integer getWaitTimeSeconds() {
    return waitTimeSeconds;
  }

  public Boolean isDeletionEnable() {
    return deletionEnable;
  }

}
