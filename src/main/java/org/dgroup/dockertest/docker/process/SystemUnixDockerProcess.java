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
package org.dgroup.dockertest.docker.process;

import java.io.IOException;
import java.util.List;
import org.cactoos.list.Joined;
import org.cactoos.list.ListOf;
import org.dgroup.dockertest.docker.DockerProcessExecutionException;
import org.dgroup.dockertest.docker.ProcessOf;
import org.dgroup.dockertest.docker.output.CmdOutputOf;

/**
 * Represents an instance of docker process on Unix-related systems.
 *
 * @author Yurii Dubinka (yurii.dubinka@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class SystemUnixDockerProcess extends DockerProcessEnvelope {

    /**
     * Ctor.
     * @param img Docker image for testing
     * @param cmd Command to be executed within container
     */
    public SystemUnixDockerProcess(final String img, final String... cmd) {
        this(
            new Joined<>(
                new ListOf<>("docker", "run", "--rm", img),
                new ListOf<>(cmd)
            )
        );
    }

    /**
     * Ctor.
     * @param cmd Docker container command.
     */
    public SystemUnixDockerProcess(final List<String> cmd) {
        this(new ProcessOf(cmd));
    }

    /**
     * Ctor.
     * @param process System process associated with docker container.
     */
    public SystemUnixDockerProcess(final ProcessOf process) {
        super(() -> {
            try {
                return new CmdOutputOf(process.execute());
            } catch (final IOException ex) {
                throw new DockerProcessExecutionException(ex);
            }
        });
    }
}