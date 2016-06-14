/*
 * Copyright (c) 2016.  SmartHMA ESA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.wasat.smarthma.helper.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 2015-11-05 23:26.
 * Part of the project  SmartHMA
 */
public enum CloudType {
    /**
     * The Google drive.
     */
    GOOGLE_DRIVE("Google Drive", 0),
    /**
     * Dropbox cloud type.
     */
    DROPBOX("Dropbox", 1);

    private final String name;
    private final int position;

    CloudType(String name, int pos) {
        this.name = name;
        this.position = pos;
    }

    /**
     * Gets enum.
     *
     * @param position the position
     * @return the enum
     */
    public static CloudType getEnum(int position) {
        if (position == 0) {
            return GOOGLE_DRIVE;
        } else if (position == 1) {
            return DROPBOX;
        }
        throw new IllegalArgumentException("No Enum specified for this position");
    }

    /**
     * To list char sequence [ ].
     *
     * @return the char sequence [ ]
     */
    public static CharSequence[] toList() {
        List<String> cloudTypeList = new ArrayList<>();
        for (CloudType cloud : CloudType.values()) {
            cloudTypeList.add(cloud.name);
        }
        return cloudTypeList.toArray(new CharSequence[cloudTypeList.size()]);
    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return name;
    }
}