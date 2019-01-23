/*
 * Copyright 2018 Confluent Inc.
 *
 * Licensed under the Confluent Community License; you may not use this file
 * except in compliance with the License.  You may obtain a copy of the License at
 *
 * http://www.confluent.io/confluent-community-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.confluent.connect.hdfs.avro;

import org.apache.kafka.common.config.AbstractConfig;

import io.confluent.connect.avro.AvroData;
import io.confluent.connect.hdfs.HdfsSinkConnectorConfig;
import io.confluent.connect.hdfs.hive.HiveMetaStore;
import io.confluent.connect.hdfs.hive.HiveUtil;
import io.confluent.connect.storage.hive.HiveFactory;

public class AvroHiveFactory implements HiveFactory {
  private final AvroData avroData;

  public AvroHiveFactory(AvroData avroData) {
    this.avroData = avroData;
  }

  @Override
  public io.confluent.connect.storage.hive.HiveUtil createHiveUtil(
      AbstractConfig conf,
      io.confluent.connect.storage.hive.HiveMetaStore hiveMetaStore
  ) {
    return createHiveUtil((HdfsSinkConnectorConfig) conf, (HiveMetaStore) hiveMetaStore);
  }

  @Deprecated
  public HiveUtil createHiveUtil(HdfsSinkConnectorConfig conf, HiveMetaStore hiveMetaStore) {
    return new AvroHiveUtil(conf, avroData, hiveMetaStore);
  }
}
