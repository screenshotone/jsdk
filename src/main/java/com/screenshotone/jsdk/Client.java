package com.screenshotone.jsdk;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

public class Client {
    private final static String BASE_URL = "https://api.screenshotone.com";
    private final static String TAKE_PATH = "/take";


    private final String accessKey;
    private final String secretKey;

    private Client(final String accessKey, final String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;

    }

    public static Client withKeys(final String accessKey, final String secretKey) {
        return new Client(accessKey, secretKey);
    }

    /**
     * Generates URL for taking screenshots.
     */
    public String generateTakeUrl(final TakeOptions takeOptions) {
        try {
            final String queryString = "access_key=" + accessKey + buildQueryString(takeOptions.query());

            return BASE_URL + TAKE_PATH + "?" + sign(queryString);
        } catch (final UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private String buildQueryString(final Map<String, List<String>> query) throws UnsupportedEncodingException {
        final StringBuilder queryString = new StringBuilder();
        for (final String key : query.keySet()) {
            for (final String value : query.get(key)) {
                queryString.append('&').append(key).append("=").append(URLEncoder.encode(value, "UTF-8").replaceAll("\\+", "%20"));
            }
        }

        return queryString.toString();
    }

    private String sign(String queryString) {
        try {
            final String algorithm = "HmacSHA256";
            final Mac mac = Mac.getInstance(algorithm);
            final SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), algorithm);
            mac.init(secretKeySpec);
            final byte[] digest = mac.doFinal(queryString.getBytes());

            return queryString + "&signature=" + toHex(digest);
        } catch (final NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }

        return builder.toString().toLowerCase();
    }

    /**
     * Takes screenshot and returns an image as a stream of bytes.
     */
    public byte[] take(TakeOptions takeOptions) throws IOException, ResponseException {
        URL url = new URL(generateTakeUrl(takeOptions));

        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setConnectTimeout(60000);
        connection.setReadTimeout(60000);
        connection.setUseCaches(true);

        connection.connect();

        int code = connection.getResponseCode();
        if (code != 200) {
            throw new ResponseException(String.format("failed to take a screenshot, the server responded with %d %s", code, connection.getResponseMessage()));
        }

        return readAllBytes(connection.getInputStream());
    }

    private static byte[] readAllBytes(final InputStream inputStream) throws IOException {
        final int bufferLength = 4 * 0x400;
        byte[] buffer = new byte[bufferLength];
        int readLength;
        IOException exception = null;

        try {
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                while ((readLength = inputStream.read(buffer, 0, bufferLength)) != -1)
                    outputStream.write(buffer, 0, readLength);

                return outputStream.toByteArray();
            }
        } catch (IOException e) {
            exception = e;
            throw e;
        } finally {
            if (exception == null) {
                inputStream.close();
            } else try {
                inputStream.close();
            } catch (IOException e) {
                exception.addSuppressed(e);
            }
        }
    }
}
