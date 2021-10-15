package com.ivandimitrov.revenue.domain;

import com.ivandimitrov.revenue.application.command.IndexCompanyCommand;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

@Entity
@Table(name = "company")
@Getter
@NoArgsConstructor
public class Company {
    @Id
    @NonNull
    private String name;
    @NonNull
    private Integer value;

    @Builder
    protected Company(
            @NonNull String name,
            @NonNull Integer value) {
        this.name = requireNonNull(name, "name is required.");
        this.value = requireNonNull(value, "value is required.");
    }

    public static Company saveOn(@NonNull IndexCompanyCommand command) {
        Objects.requireNonNull(command, "command is required.");

        return new Company(command.getName(), command.getValue());
    }
}