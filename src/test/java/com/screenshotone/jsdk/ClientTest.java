package com.screenshotone.jsdk;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ClientTest {
    @Test
    public void withKeys() {
        final Client client = Client.withKeys("IVmt2ghj9TG_jQ", "Sxt94yAj9aQSgg");

        assertNotNull(client);
    }

    @Test
    public void generateTakeURL() {
        final Client client = Client.withKeys("IVmt2ghj9TG_jQ", "Sxt94yAj9aQSgg");
        final String takeUrl = client.generateTakeUrl(TakeOptions.url("https://scalabledeveloper.com").fullPage(true).deviceScaleFactor(1).viewportHeight(1200).viewportWidth(1200).format("png").omitBackground(true));

        assertEquals("https://api.screenshotone.com/take?access_key=IVmt2ghj9TG_jQ&device_scale_factor=1&format=png&full_page=true&omit_background=true&url=https%3A%2F%2Fscalabledeveloper.com&viewport_height=1200&viewport_width=1200&signature=3c0c5543599067322e8c84470702330e3687c6a08eef6b7311b71c32d04e1bd5", takeUrl);
    }
}