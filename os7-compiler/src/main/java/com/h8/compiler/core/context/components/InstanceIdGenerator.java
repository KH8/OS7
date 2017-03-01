package com.h8.compiler.core.context.components;

import lombok.Synchronized;

class InstanceIdGenerator {
    private static Integer counter = 1;

    @Synchronized
    static String getNextId() {
        return getNext().toString();
    }

    private static Integer getNext() {
        return counter++;
    }
}
