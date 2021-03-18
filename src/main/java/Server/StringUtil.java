package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StringUtil {
    //converts input stream into a string
    public static String getStringFromInputStream(InputStream is) throws IOException {
        StringBuilder sbuf = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sbuf.append(buf, 0, len);
        }
        return sbuf.toString();
    }

}
