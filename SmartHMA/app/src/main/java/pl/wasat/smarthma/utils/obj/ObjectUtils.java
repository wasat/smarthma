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

package pl.wasat.smarthma.utils.obj;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Object utils.
 */
class ObjectUtils {

    private ObjectUtils() {
    }

    /**
     * Gets field names and values.
     *
     * @param obj        the obj
     * @param publicOnly the public only
     * @return the field names and values
     * @throws IllegalArgumentException the illegal argument exception
     * @throws IllegalAccessException   the illegal access exception
     */
    public static Map<String, Object> getFieldNamesAndValues(final Object obj, boolean publicOnly)
            throws IllegalArgumentException, IllegalAccessException {

        Class<?> c1 = obj.getClass();
        Map<String, Object> map = new HashMap<>();
        Field[] fields = c1.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            if (publicOnly) {
                if (Modifier.isPublic(field.getModifiers())) {
                    Object value = field.get(obj);
                    map.put(name, value);
                }
            } else {
                field.setAccessible(true);
                Object value = field.get(obj);
                map.put(name, value);
            }
        }
        return map;
    }
}
