package it.flaminiovilla.facilitymanager.model.builder;

import it.flaminiovilla.facilitymanager.utilities.mqtt.MqttPublishModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public final class MqttPublishModelBuilder {
    private @NotNull @Size(min = 1, max = 255) String topic;
    private @NotNull @Size(min = 1, max = 255) String message;
    private @NotNull Boolean retained;
    private @NotNull Integer qos;

    private MqttPublishModelBuilder() {
    }

    public static MqttPublishModelBuilder builder() {
        return new MqttPublishModelBuilder();
    }

    public MqttPublishModelBuilder topic(String topic) {
        this.topic = topic;
        return this;
    }

    public MqttPublishModelBuilder message(String message) {
        this.message = message;
        return this;
    }

    public MqttPublishModelBuilder retained(Boolean retained) {
        this.retained = retained;
        return this;
    }

    public MqttPublishModelBuilder qos(Integer qos) {
        this.qos = qos;
        return this;
    }

    public MqttPublishModel build() {
        MqttPublishModel mqttPublishModel = new MqttPublishModel();
        mqttPublishModel.setTopic(topic);
        mqttPublishModel.setMessage(message);
        mqttPublishModel.setRetained(retained);
        mqttPublishModel.setQos(qos);
        return mqttPublishModel;
    }
}
