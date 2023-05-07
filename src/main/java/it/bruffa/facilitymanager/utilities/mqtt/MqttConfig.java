package it.bruffa.facilitymanager.utilities.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public abstract class MqttConfig {
   protected final String broker = "public.mqtthq.com";
   protected final int qos = 1;
   protected Boolean hasSSL = false; /*By default SSL is disabled */
   protected Integer port = 1883; /* Default port */
   protected final String userName = "";
   protected final String password = "";
   protected final String TCP = "tcp://";
   protected final String SSL = "ssl://";
   public MqttClient mqttClient = null;

   public abstract void publishMessage(String topic, String message);

   public abstract void subscribeMessage(String topic);

   public abstract void disconnect();

   /**
    * Custom Configuration
    * 
    * @param broker
    * @param port
    * @param ssl
    * @param withUserNamePass
    */
   protected abstract void config(String broker, Integer port, Boolean ssl, Boolean withUserNamePass) throws MqttException;

   /**
    * Default Configuration
    */
   protected abstract void config() throws MqttException;
   

}