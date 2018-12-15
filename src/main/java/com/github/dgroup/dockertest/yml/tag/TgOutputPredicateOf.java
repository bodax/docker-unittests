/**
 * MIT License
 *
 * Copyright (c) 2017-2018 Yurii Dubinka
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom
 * the Software is  furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.github.dgroup.dockertest.yml.tag;

import com.github.dgroup.dockertest.text.TextOf;
import com.github.dgroup.dockertest.yml.IllegalYmlFormatException;
import com.github.dgroup.dockertest.yml.TgOutputPredicate;
import org.cactoos.BiFunc;
import org.cactoos.func.UncheckedBiFunc;
import org.cactoos.text.RepeatedText;
import org.cactoos.text.UncheckedText;

/**
 * Default implementation of {@link TgOutputPredicate}.
 * Represents yml tag
 * {@code /tests/test/output/contains|equal|startsWith|endsWith|matches}.
 *
 * @author Yurii Dubinka (yurii.dubinka@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class TgOutputPredicateOf implements TgOutputPredicate {

    /**
     * Compare type: contains, equals, startsWith, endsWith, matches.
     */
    private final String type;
    /**
     * Expected value from test scenario.
     */
    private final String expected;
    /**
     * Condition which should satisfy the actual value.
     */
    private final UncheckedBiFunc<String, String, Boolean> predicate;

    /**
     * Ctor.
     * @param type Comparing type like contains, equal, startsWiths, endsWith.
     * @param expected Expected value from test scenario.
     * @param predicate Condition, which should satisfy the actual value.
     */
    public TgOutputPredicateOf(final String type, final String expected,
        final BiFunc<String, String, Boolean> predicate) {
        this(type, expected, new UncheckedBiFunc<>(predicate));
    }

    /**
     * Ctor.
     * @param type Comparing type like contains, equal, startsWiths, endsWith.
     * @param expected Expected value from test scenario.
     * @param predicate Condition, which should satisfy the actual value.
     */
    public TgOutputPredicateOf(final String type, final String expected,
        final UncheckedBiFunc<String, String, Boolean> predicate) {
        this.type = type;
        this.expected = expected;
        this.predicate = predicate;
    }

    @Override
    public String comparingType() {
        return this.type;
    }

    @Override
    public String expectedValue() {
        return this.expected;
    }

    @Override
    public boolean test(final String actual) throws IllegalYmlFormatException {
        if (this.predicate == null) {
            throw new IllegalYmlFormatException(
                new TextOf(
                    "Unsupported comparing expression `%s:%s`",
                    this.comparingType(), this.expectedValue()
                )
            );
        }
        return this.predicate.apply(actual, this.expected);
    }

    @Override
    public String toString() {
        return this.asYmlString();
    }

    /**
     * Gives predicate details as YML string.
     * @return Predicate details.
     * @checkstyle StringLiteralsConcatenationCheck (10 lines)
     */
    public String asYmlString() {
        return new TextOf(
            "%s:%s\"%s\"",
            this.comparingType(),
            new UncheckedText(
                new RepeatedText(
                    " ", this.differenceInLengthComparingTo("startsWith") + 1
                )
            ).asString(),
            this.expectedValue()
        ).text();
    }

    /**
     * Amount of spaces between predicate name and value based on
     *  longest predicate.
     * @param longest Predicate name.
     * @return Difference in spaces
     */
    private int differenceInLengthComparingTo(final String longest) {
        return longest.length() - this.comparingType().length();
    }

}
