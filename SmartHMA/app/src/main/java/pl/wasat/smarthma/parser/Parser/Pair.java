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

package pl.wasat.smarthma.parser.Parser;

/**
 * Created by marcel paduch on 2015-08-07 00:09.
 * Part of the project  SmartHMA
 *
 * @param <T> the type parameter
 * @param <U> the type parameter
 */
public class Pair<T, U> {
    /**
     * The Title.
     */
    public final T title;
    /**
     * The Content.
     */
    public final U content;

    /**
     * Instantiates a new Pair.
     *
     * @param t the t
     * @param u the u
     */
    public Pair(T t, U u) {
        this.content = u;
        this.title = t;
    }
}