/**
 * MIT License
 *
 * Copyright (c) 2017-2019 Yurii Dubinka
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
package com.github.dgroup.dockertest.cmd.arg;

import com.github.dgroup.dockertest.cmd.Arg;
import org.cactoos.Scalar;
import org.cactoos.scalar.UncheckedScalar;

/**
 * Argument that doesn't throw the checked {@link Exception}.
 *
 * @author Yurii Dubinka (yurii.dubinka@gmail.com)
 * @version $Id$
 * @param <T> Type of command-line argument.
 * @since 1.0
 */
public final class Unchecked<T> implements Arg<T> {

    /**
     * Origin.
     */
    private final Scalar<Arg<T>> origin;

    /**
     * Ctor.
     * @param arg Origin.
     */
    public Unchecked(final Arg<T> arg) {
        this(() -> arg);
    }

    /**
     * Ctor.
     * @param arg Origin.
     */
    public Unchecked(final Scalar<Arg<T>> arg) {
        this.origin = arg;
    }

    @Override
    public String name() {
        return new UncheckedScalar<>(this.origin).value().name();
    }

    @Override
    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    public T value() {
        // @checkstyle IllegalCatchCheck (5 lines)
        try {
            return this.origin.value().value();
        } catch (final Exception exp) {
            throw new IllegalStateException(exp);
        }
    }

    @Override
    public boolean specifiedByUser() {
        return new UncheckedScalar<>(this.origin).value().specifiedByUser();
    }

}
