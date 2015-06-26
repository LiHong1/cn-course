package cm.commons.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

public class BlobUtils {
    public static byte[] inputStreamToByte(InputStream is) throws IOException {

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int ch;
        while ((ch = is.read()) != -1) {
            byteStream.write(ch);
        }
        byte imgdata[] = byteStream.toByteArray();
        byteStream.close();

        return imgdata;
    }

    public static byte[] blobToBytes(Blob blob) throws IOException, SQLException {
        return inputStreamToByte(blob.getBinaryStream());
    }
}
