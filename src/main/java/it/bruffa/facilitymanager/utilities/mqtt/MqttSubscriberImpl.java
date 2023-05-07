package it.bruffa.facilitymanager.utilities.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.UUID;

@Component
public class MqttSubscriberImpl extends MqttConfig implements MqttCallback {

    private static final String fota_fetch_record = "fota_fetch_record";
    private String brokerUrl = null;
    final private String colon = ":";
    final private String clientId = UUID.randomUUID().toString();
    private static final Logger logger = LoggerFactory.getLogger(MqttSubscriberImpl.class);

    private MqttConnectOptions connectionOptions = null;
    private MemoryPersistence persistence = null;


  public MqttSubscriberImpl() throws MqttException {
        this.config();
    }

    @Override
    public void connectionLost(Throwable cause) {
        logger.info("Connection Lost" + cause);
        try {
            this.config();
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void config(String broker, Integer port, Boolean ssl, Boolean withUserNamePass) throws MqttException {
        logger.info("Inside Parameter Config");
        String protocal = this.TCP;

        this.brokerUrl = protocal + this.broker + colon + port;
        this.persistence = new MemoryPersistence();
        this.connectionOptions = new MqttConnectOptions();

        try {
            this.mqttClient = new MqttClient(brokerUrl, clientId, persistence);
            this.connectionOptions.setCleanSession(true);
            this.connectionOptions.setPassword(this.password.toCharArray());
            this.connectionOptions.setUserName(this.userName);
            this.mqttClient.connect(this.connectionOptions);
            this.mqttClient.setCallback(this);
        } catch (MqttException me) {
            throw me;
        }
    }

    @Override
    protected void config() throws MqttException {
        logger.info("Inside Config with parameter");
        this.brokerUrl = this.TCP + this.broker + colon + this.port;
        this.persistence = new MemoryPersistence();
        this.connectionOptions = new MqttConnectOptions();
        try {
            this.mqttClient = new MqttClient(brokerUrl, clientId, persistence);
            this.connectionOptions.setCleanSession(true);
            this.connectionOptions.setPassword(this.password.toCharArray());
            this.connectionOptions.setUserName(this.userName);
            this.mqttClient.connect(this.connectionOptions);
            this.mqttClient.setCallback(this);
        } catch (MqttException me) {
           throw me;
        }
    }

    @Override
    public void publishMessage(String topic, String message) {

    }

    @Override
    public void subscribeMessage(String topic) {
        try {

            this.mqttClient.subscribe(topic, this.qos);
        } catch (MqttException me) {
            System.out.println("Not able to Read Topic  "+ topic);
            // me.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        try {
            this.mqttClient.disconnect();
        } catch (MqttException me) {
                logger.info("Not able to disconnect");

            }
    }


    @Override
    public void messageArrived(String mqttTopic, MqttMessage mqttMessage) throws Exception {
       String time = new Timestamp(System.currentTimeMillis()).toString();
   System.out.println("***********************************************************************");
   System.out.println("Message Arrived at Time: " + time + "  Topic: " + mqttTopic + "  Message: "
       + new String(mqttMessage.getPayload()));
//    System.out.println("***********************************************************************");

      
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}