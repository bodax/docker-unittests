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
package com.github.dgroup.dockertest.yml;

import com.github.dgroup.dockertest.yml.tag.TgSetup;
import com.github.dgroup.dockertest.yml.tag.TgVersion;
import java.util.Collection;

/**
 * Transform *.yml file with tests to collection of {@link TgTest}.
 *
 * @author Yurii Dubinka (yurii.dubinka@gmail.com)
 * @version $Id$
 * @since 1.1
 */
public interface YmlTags {

    /**
     * Give the <em>version</em> tag.
     * @return The yml tag <em>version</em>.
     * @throws IllegalYmlFormatException in case if YML file has
     *  wrong/corrupted/unsupported format.
     */
    TgVersion version() throws IllegalYmlFormatException;

    /**
     * Give the <em>setup</em> tag.
     * @return The yml tag <em>setup</em>.
     * @throws IllegalYmlFormatException in case if YML file has
     *  wrong/corrupted/unsupported format.
     */
    TgSetup setup() throws IllegalYmlFormatException;

    /**
     * Give the <em>tests</em> tag.
     * @return The yml tag <em>tests</em>.
     * @throws IllegalYmlFormatException in case if YML file has
     *  wrong/corrupted/unsupported format.
     */
    Collection<TgTest> tests() throws IllegalYmlFormatException;

    /**
     * Give the yml tree as raw string.
     * @return The yml tree as string.
     * @throws IllegalYmlFormatException in case if YML file has
     *  wrong/corrupted/unsupported format.
     */
    String raw() throws IllegalYmlFormatException;

}
