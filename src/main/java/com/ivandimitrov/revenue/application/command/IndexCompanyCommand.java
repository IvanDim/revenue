package com.ivandimitrov.revenue.application.command;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class IndexCompanyCommand {
        @NonNull String name;
        @NonNull Integer value;
}
