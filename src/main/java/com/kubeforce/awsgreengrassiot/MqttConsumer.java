package com.kubeforce.awsgreengrassiot;


import org.hibernate.cache.internal.StandardTimestampsCacheFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.function.Consumer;


public class MqttConsumer  implements Consumer<Map<String,String>> {
    public static final Logger LOGGER = LoggerFactory.getLogger(MqttConsumer.class);

    @Autowired
    private MqttPublish mqttPublish;

    @Override
    public void accept (Map<String, String> map )
    {
        LOGGER.info("Adding Device info", map);
        MqttPublish mqttPublish= new MqttPublish();
    }

}