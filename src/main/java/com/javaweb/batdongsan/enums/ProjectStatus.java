package com.javaweb.batdongsan.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ProjectStatus {
    IN_PROGRESS, COMPLETED, PAUSED, NOT_STARTED;
}
