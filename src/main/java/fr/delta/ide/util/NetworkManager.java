package fr.delta.ide.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Locale;

public class NetworkManager {

    public static NetworkManager.OS getOS() {
        String s = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        if(s.contains("win")) {
            return OS.WINDOWS;
        }else if(s.contains("mac")) {
            return OS.MACOS;
        }else if(s.contains("solaris")) {
            return OS.SOLARIS;
        }else if(s.contains("sunos")) {
            return OS.SOLARIS;
        }else if(s.contains("linux")) {
            return OS.LINUX;
        }else {
            return s.contains("unix") ? OS.LINUX : OS.UNKNOWN;
        }
    }

    public static enum OS {
        WINDOWS {
            protected String[] getOpenURLArgs(URL url) {
                return new String[] { "rundll32", "url.dll,FileProtocolHandler", url.toString() };
            }
        },
        MACOS {
            protected String[] getOpenURLArgs(URL url) {
                return new String[] { "open", url.toString() };
            }
        },
        LINUX,
        SOLARIS,
        UNKNOWN;

        private OS() {
        }

        public void openURL(URL url) {
            try {
                Process process = AccessController.doPrivileged((PrivilegedExceptionAction<Process>) (() -> {
                    return Runtime.getRuntime().exec(this.getOpenURLArgs(url));
                }));

                process.getInputStream().close();
                process.getOutputStream().close();
                process.getErrorStream().close();
            }catch(IOException | PrivilegedActionException e) {
                e.printStackTrace();
            }
        }

        protected String[] getOpenURLArgs(URL url) {
            String s = url.toString();
            if("file".equals(url.getProtocol())) {
                s = s.replace("file:", "file://");
            }

            return new String[] { "xdg-open", s };
        }

        public void openURI(String uri) {
            try {
                this.openURL((new URI(uri)).toURL());
            } catch (MalformedURLException | URISyntaxException e) {
                e.printStackTrace();
            }
        }

        public void openURI(URI uri) {
            try {
                this.openURL(uri.toURL());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }
}
