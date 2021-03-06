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
import com.github.dgroup.dockertest.text.TextFile;
import com.github.dgroup.dockertest.yml.Tags;
import com.github.dgroup.dockertest.yml.tag.TagsOf;
import java.io.File;

/**
 * YML file with tests as object.
 *
 * @author Yurii Dubinka (yurii.dubinka@gmail.com)
 * @version $Id$
 * @since 1.1
 */
public final class YmlTags extends ArgEnvelope<Tags> {

    /**
     * Ctor.
     * @param src The YML file with tests.
     * @checkstyle IndentationCheck (30 lines)
     */
    public YmlTags(final Arg<File> src) {
        super(
            () -> new Arg<Tags>() {
                @Override
                public String name() {
                    return src.name();
                }

                @Override
                public Tags value() {
                    return new TagsOf(new TextFile(src::value));
                }

                @Override
                public boolean specifiedByUser() {
                    return src.specifiedByUser();
                }
            }
        );
    }
}
