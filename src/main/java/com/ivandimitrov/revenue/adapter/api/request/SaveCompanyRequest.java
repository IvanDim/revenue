package com.ivandimitrov.revenue.adapter.api.request;


import lombok.NonNull;
import lombok.Value;

@Value
public class SaveCompanyRequest {
    @NonNull String name;
    @NonNull Integer value;
}
