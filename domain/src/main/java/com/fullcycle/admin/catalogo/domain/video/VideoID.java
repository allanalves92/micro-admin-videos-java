package com.fullcycle.admin.catalogo.domain.video;

import com.fullcycle.admin.catalogo.domain.Identifier;
import com.fullcycle.admin.catalogo.domain.utils.IdUtils;


import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;

public class VideoID extends Identifier {

    protected final String value;

    private VideoID(final String value) {
        this.value = requireNonNull(value);
        ;
    }

    public static VideoID from(final String anId) {
        return new VideoID(anId.toLowerCase());
    }

    public static VideoID unique() {
        return VideoID.from(IdUtils.uuid());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final VideoID that = (VideoID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return hash(getValue());
    }
}
