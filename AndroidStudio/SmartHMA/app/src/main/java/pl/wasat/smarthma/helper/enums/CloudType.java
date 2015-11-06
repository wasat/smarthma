package pl.wasat.smarthma.helper.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 2015-11-05 23:26.
 * Part of the project  SmartHMA_home
 */
public enum CloudType {
    GOOGLE_DRIVE("Google Drive", 0),
    DROPBOX("Dropbox", 1);

    private String name;
    private int position;

    CloudType(String name, int pos) {
        this.name = name;
        this.position = pos;
    }

    public int getPosition() {
        return position;
    }

    public static CloudType getEnum(int position) {
        if (position == 0) {
            return GOOGLE_DRIVE;
        } else if (position == 1) {
            return DROPBOX;
        }
        throw new IllegalArgumentException("No Enum specified for this position");
    }

    public static CharSequence[] toList() {
        CharSequence[] sequences = new CharSequence[2];
        List<String> cloudTypeList = new ArrayList<>();
        for (CloudType cloud : CloudType.values()) {
            cloudTypeList.add(cloud.name);
        }
        return cloudTypeList.toArray(new CharSequence[cloudTypeList.size()]);
    }

    @Override
    public String toString() {
        return name;
    }
}