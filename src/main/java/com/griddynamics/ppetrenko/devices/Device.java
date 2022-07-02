package com.griddynamics.ppetrenko.devices;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonInclude (JsonInclude.Include.NON_NULL)
@Getter
public class Device {
    private String id;

    @JsonProperty("device.udid")
    private String deviceUdid = "";

    @JsonProperty("emulator.name")
    private String emulatorName;

    @JsonProperty("device.name")
    private String deviceName;

    @JsonProperty("os.version")
    private String osVersion;
}
