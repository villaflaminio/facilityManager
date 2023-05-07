package it.bruffa.facilitymanager.utilities.mqtt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqttMessageListener implements Runnable {
    @Autowired
    MqttSubscriberImpl subscriber;

    @Override
    public void run() {
        while (true) {
            subscriber.subscribeMessage("test/facilitymanager/edobruffa");
        }

    }
}