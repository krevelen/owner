/*
 * Copyright (c) 2012-2014, Luigi R. Viggiano
 * All rights reserved.
 *
 * This software is distributable under the BSD license.
 * See the terms of the BSD license in the documentation provided with this software.
 */

package org.aeonbits.owner.loaders;

import sun.net.www.protocol.zookeeper.Handler;
import sun.net.www.protocol.zookeeper.ZooKeeperConnection;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;

/**
 * @author Koray Sariteke
 * @author Luigi R. Viggiano
 */
public class ZooKeeperLoader implements Loader {


    public boolean accept(URI uri) {
        return uri.getScheme().equals(Handler.PROTOCOL);
    }

    public void load(Properties result, URI uri) throws IOException {
        InputStream input = uri.toURL().openStream();
        try {
            load(result, input);
        } finally {
            input.close();
        }
    }

    void load(Properties result, InputStream input) throws IOException {
        ZooKeeperConnection.ZooKeeperStream zkStream = (ZooKeeperConnection.ZooKeeperStream) input;
        result.putAll(zkStream.pairs());
    }

    public String defaultSpecFor(String urlPrefix) {
        return urlPrefix;
    }
}
