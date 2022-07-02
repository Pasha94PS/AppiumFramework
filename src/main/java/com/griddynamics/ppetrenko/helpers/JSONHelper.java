package com.griddynamics.ppetrenko.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.griddynamics.ppetrenko.devices.Device;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.griddynamics.ppetrenko.global_variables.GlobalVariables.IS_BROWSERSTACK_ENABLED;

public class JSONHelper {

    public static Map<String, Device> loadDevicesFromJson() {
        try {
            return Arrays.stream(new ObjectMapper().readValue(new File(IS_BROWSERSTACK_ENABLED ?
                            "src/main/resources/browserStackDevices.json" : "src/main/resources/localDevices.json"),
                             Device[].class)).collect(Collectors.toMap(Device::getId, Function.identity()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
