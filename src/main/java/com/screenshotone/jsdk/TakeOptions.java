package com.screenshotone.jsdk;

import java.math.BigDecimal;
import java.util.*;

/**
 * Represents options for taking screenshots.
 */
public class TakeOptions {
    private SortedMap<String, List<String>> query;

    private TakeOptions(String key, String value) {
        this.query = new TreeMap<>();

        put(key, value);
    }

    /**
     * Instantiates options with the specified site URL.
     */
    public static TakeOptions url(String url) {
        return new TakeOptions("url", url);
    }

    /**
     * Instantiates options with the specified site HTML.
     */
    public static TakeOptions html(String html) {
        return new TakeOptions("html", html);
    }

    private void put(String key, String ...value) {
        query.put(key, Arrays.asList(value));
    }

    /**
     * Selector is a CSS-like selector of the element to take a screenshot of.
     */
    public TakeOptions selector(String selector) {
        put("selector", selector);

        return this;
    }

    /**
     * It determines the behavior of what to do when selector is not found.
     */
    public TakeOptions errorOnSelectorNotFound(boolean errorOn) {
        put("error_on_selector_not_found", errorOn ? "true" : "false");

        return this;
    }

    /**
     * Styles specifies custom CSS styles for the page.
     */
    public TakeOptions styles(String styles)
    {
        put("styles", styles);

        return this;
    }

    /**
     * Scripts specifies custom scripts for the page.
     */
    public TakeOptions scripts(String scripts)
    {
        put("scripts", scripts);

        return this;
    }

    /**
     * Renders the full page.
     */
    public TakeOptions fullPage(boolean fullPage) {
        put("full_page", fullPage ? "true" : "false");

        return this;
    }

    /**
     * Sets response format, one of: "png", "jpeg", "webp" or "jpg".
     */
    public TakeOptions format(String format) {
        put("format", format);

        return this;
    }

    /**
     * Renders image with the specified quality. Available for the next formats: "jpeg" ("jpg"), "webp".
     */
    public TakeOptions imageQuality(int imageQuality) {
        put("image_quality", String.valueOf(imageQuality));

        return this;
    }

    /**
     * Renders a transparent background for the image. Works only if the site has not defined background color.
     * Available for the following response formats: "png", "webp".
     */
    public TakeOptions omitBackground(boolean omitBackground) {
        put("omit_background", omitBackground ? "true" : "false");

        return this;
    }

    /**
     * Sets the width of the browser viewport (pixels).
     */
    public TakeOptions viewportWidth(int viewportWidth) {
        put("viewport_width", String.valueOf(viewportWidth));

        return this;
    }

    /**
     * Sets the height of the browser viewport (pixels).
     */
    public TakeOptions viewportHeight(int viewportHeight) {
        put("viewport_height", String.valueOf(viewportHeight));

        return this;
    }

    /**
     * Sets the device scale factor. Acceptable value is one of: 1, 2 or 3.
     */
    public TakeOptions deviceScaleFactor(int deviceScaleFactor) {
        put("device_scale_factor", String.valueOf(deviceScaleFactor));

        return this;
    }

    /**
     * Sets geolocation latitude for the request.
     * Both latitude and longitude are required if one of them is set.
     */
    public TakeOptions geolocationLatitude(double latitude) {
        put("geolocation_latitude", new BigDecimal(latitude).toPlainString());

        return this;
    }

    /**
     * Sets geolocation longitude for the request. Both latitude and longitude are required if one of them is set.
     */
    public TakeOptions geolocationLongitude(double longitude) {
        put("geolocation_longitude", new BigDecimal(longitude).toPlainString());

        return this;
    }

    /**
     * Sets the geolocation accuracy in meters.
     */
    public TakeOptions geolocationAccuracy(int accuracy) {
        put("geolocation_accuracy", String.valueOf(accuracy));

        return this;
    }

    /**
     * Blocks ads.
     */
    public TakeOptions blockAds(boolean blockAds) {
        put("block_ads", blockAds ? "true" : "false");

        return this;
    }

    /**
     * Blocks trackers.
     */
    public TakeOptions blockTrackers(boolean blockTrackers) {
        put("block_trackers", blockTrackers ? "true" : "false");

        return this;
    }

    /**
     * Blocks requests by specifying URL, domain, or even a simple pattern.
     */
    public TakeOptions blockRequests(String... blockRequests) {
        put("block_requests", blockRequests);

        return this;
    }


    /**
     * Blocks loading resources by type. Available resource types are: "document", "stylesheet", "image", "media",
     * "font", "script", "texttrack", "xhr", "fetch", "eventsource", "websocket", "manifest", "other".
     */
    public TakeOptions blockResources(String... blockResources) {
        put("block_resources", blockResources);

        return this;
    }

    /**
     * Enables caching.
     */
    public TakeOptions cache(boolean cache) {
        put("cache", cache ? "true" : "false");

        return this;
    }

    /**
     * Sets cache TTL.
     */
    public TakeOptions cacheTtl(int cacheTtl) {
        put("cache_ttl", String.valueOf(cacheTtl));

        return this;
    }

    /**
     * Sets cache key.
     */
    public TakeOptions cacheKey(String cacheKey) {
        put("cache_key", cacheKey);

        return this;
    }

    /**
     * Sets a user agent for the request.
     */
    public TakeOptions userAgent(String userAgent) {
        put("user_agent", userAgent);

        return this;
    }

    /**
     * Sets an authorization header for the request.
     */
    public TakeOptions authorization(String authorization) {
        put("authorization", authorization);

        return this;
    }

    /**
     * Set cookies for the request.
     */
    public TakeOptions cookies(String ...cookies) {
        put("cookies", cookies);

        return this;
    }

    /**
     * Sets extra headers for the request.
     */
    public TakeOptions headers(String ...headers) {
        put("headers", headers);

        return this;
    }

    /**
     * TimeZone sets time zone for the request.
     * Available time zones are: "America/Santiago", "Asia/Shanghai", "Europe/Berlin", "America/Guayaquil",
     * "Europe/Madrid", "Pacific/Majuro", "Asia/Kuala_Lumpur", "Pacific/Auckland", "Europe/Lisbon", "Europe/Kiev",
     * "Asia/Tashkent", "Europe/London".
     */
    public TakeOptions timeZone(String timeZone) {
        put("time_zone", timeZone);

        return this;
    }

    /**
     * Sets delay.
     */
    public TakeOptions delay(int delay) {
        put("delay", String.valueOf(delay));

        return this;
    }

    /**
     * Sets timeout.
     */
    public TakeOptions timeout(int timeout) {
        put("timeout", String.valueOf(timeout));

        return this;
    }

    protected Map<String, List<String>> query() {
        return query;
    }
}