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

    @Test
    public void generateTakeURLWithStyles() {
        final Client client = Client.withKeys("h0JGykI7yEi8kA", "Das7C3rFXMO-sg");
        final String takeUrl = client.generateTakeUrl(
                TakeOptions.url("https://scalabledeveloper.com")
                        .fullPage(true)
                        .deviceScaleFactor(1)
                        .viewportHeight(1200)
                        .viewportWidth(1200)
                        .format("png")
                        .omitBackground(true)
                        .styles("*,\n" +
                                "*::after,\n" +
                                "*::before {\n" +
                                "    transition-delay: 0s !important;\n" +
                                "    transition-duration: 0s !important;\n" +
                                "    animation-delay: -0.0001s !important;\n" +
                                "    animation-duration: 0s !important;\n" +
                                "    animation-play-state: paused !important;\n" +
                                "    caret-color: transparent !important;\n" +
                                "    color-adjust: exact !important;\n" +
                                "    opacity: 1.0 !important;\n" +
                                "}")
        );

        assertEquals("https://api.screenshotone.com/take?access_key=h0JGykI7yEi8kA&device_scale_factor=1&format=png&full_page=true&omit_background=true&styles=*%2C%0A*%3A%3Aafter%2C%0A*%3A%3Abefore%20%7B%0A%20%20%20%20transition-delay%3A%200s%20%21important%3B%0A%20%20%20%20transition-duration%3A%200s%20%21important%3B%0A%20%20%20%20animation-delay%3A%20-0.0001s%20%21important%3B%0A%20%20%20%20animation-duration%3A%200s%20%21important%3B%0A%20%20%20%20animation-play-state%3A%20paused%20%21important%3B%0A%20%20%20%20caret-color%3A%20transparent%20%21important%3B%0A%20%20%20%20color-adjust%3A%20exact%20%21important%3B%0A%20%20%20%20opacity%3A%201.0%20%21important%3B%0A%7D&url=https%3A%2F%2Fscalabledeveloper.com&viewport_height=1200&viewport_width=1200&signature=ac18962a8a756b35047ea5c9eaf860ffcefadfd39bd76388670fb20ce5260d17", takeUrl);
    }
}