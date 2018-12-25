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
package com.github.dgroup.dockertest.cmd;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.cactoos.Text;
import org.cactoos.text.FormattedText;

/**
 * Generate the Docker container name based on YML file name and current date.
 *
 * @author Yurii Dubinka (yurii.dubinka@gmail.com)
 * @version $Id$
 * @since 1.1
 */
public final class ContainerName extends ArgEnvelope<Text> {

    /**
     * Ctor.
     * @param file The name of YML file with tests.
     * @checkstyle IndentationCheck (50 lines)
     * @checkstyle AnonInnerLengthCheck (50 lines)
     */
    public ContainerName(final Arg<File> file) {
        super(
            () -> new Arg<Text>() {

                @Override
                public String name() {
                    return "--container";
                }

                @Override
                public Text value() {
                    final String name = new Unchecked<>(file).value().getName();
                    return new FormattedText(
                        "%s-%s",
                        name.substring(0, name.lastIndexOf('.')),
                        new SimpleDateFormat("yyyyMMdd-HH-mm-ss", Locale.US)
                            .format(new Date())
                    );
                }

                @Override
                public boolean specifiedByUser() {
                    return file.specifiedByUser();
                }
            }
        );
    }
}