package com.hsalf.smileyrating.helper;

import java.util.HashSet;
import java.util.Set;

public class SmileyActiveIndicator {

    private final Set<TouchActiveIndicator> indicators = new HashSet<>();

    public boolean isActive() {
        for (TouchActiveIndicator indicator : indicators) {
            if (indicator.isBeingTouched()) {
                return true;
            }
        }
        return false;
    }

    public void bind(TouchActiveIndicator indicator) {
        indicators.add(indicator);
    }

}
