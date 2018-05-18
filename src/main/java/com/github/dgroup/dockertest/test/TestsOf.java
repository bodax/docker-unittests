/**
 * MIT License
 *
 * Copyright (c) 2017 Yurii Dubinka
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
package com.github.dgroup.dockertest.test;

import com.github.dgroup.dockertest.cmd.Arg;
import com.github.dgroup.dockertest.docker.process.DockerProcessOf;
import com.github.dgroup.dockertest.text.TextFile;
import com.github.dgroup.dockertest.yml.YmlString;
import org.cactoos.collection.CollectionEnvelope;
import org.cactoos.iterable.Mapped;
import org.cactoos.list.StickyList;

/**
 * Tests to be executed.
 *
 * @author Yurii Dubinka (yurii.dubinka@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class TestsOf extends CollectionEnvelope<Test> {

    /**
     * Ctor.
     * @param image The name of the docker image.
     * @param file The name of the YML file with tests.
     */
    public TestsOf(final Arg<String> image, final Arg<String> file) {
        super(() -> new StickyList<>(
            new Mapped<>(
                ymlTagTest -> new TestOf(
                    ymlTagTest.assume(),
                    ymlTagTest.cmd(),
                    ymlTagTest.output(),
                    new DockerProcessOf(
                        image.value(),
                        ymlTagTest.containerCommandAsArray()
                    )
                ),
                new YmlString(
                    new TextFile(file.value())
                ).asTests()
            )
        ));
    }

}