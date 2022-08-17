package com.kubeforce.awsgreengrassiot;


import java.nio.ByteBuffer;
import java.util.Timer;
import java.util.TimerTask;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.greengrass.javasdk.IotDataClient;
import com.amazonaws.greengrass.javasdk.model.*;



public class MqttPublish {
    static {
        Timer timer = new Timer();
        // Repeat publishing a message every 5 seconds
        timer.scheduleAtFixedRate(new PublishDeviceInfo(), 0, 5000);
    }

    public String handleRequest(Object input, Context context) {
        return "Here is the device info";

    }
}
    class PublishDeviceInfo extends TimerTask {
        private IotDataClient iotDataClient = new IotDataClient();
        private String publishMessage = String.format("Device info sent from device running on platform: %s-%s using Java", System.getProperty("os.name"), System.getProperty("os.version"));
        private PublishRequest publishRequest = new PublishRequest()
                .withTopic("device/info")
                .withPayload(ByteBuffer.wrap(String.format("{\"message\":\"%s\"}", publishMessage).getBytes()))
                .withQueueFullPolicy(QueueFullPolicy.AllOrException);

        public void run() {
            try {
                iotDataClient.publish(publishRequest);
            } catch (Exception ex) {
                System.err.println(ex);
            }
        }
    }


