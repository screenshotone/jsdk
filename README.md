# jsdk

[![Build](https://github.com/screenshotone/jsdk/actions/workflows/build.yml/badge.svg?branch=main)](https://github.com/screenshotone/jsdk/actions/workflows/build.yml)

An official [Screenshot API](https://screenshotone.com/) client for Java. 

It takes minutes to start taking screenshots. Just [sign up](https://screenshotone.com/) to get access and secret keys, import the client, and you are ready to go. 

The SDK client is synchronized with the latest [screenshot API options](https://screenshotone.com/docs/options/).

## Installation

Add the library as a dependency to your Maven project: 
```
<dependencies>
    <dependency>
        <groupId>com.screenshotone.jsdk</groupId>
        <artifactId>screenshotone-api-jsdk</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>    
```

Or Gradle: 
```
dependencies {
    compile 'com.screenshotone.jsdk:screenshotone-api-jsdk:1.0.0'
}
```

## Usage

Generate a screenshot URL without executing request: 
```java
import com.screenshotone.jsdk.Client;
import com.screenshotone.jsdk.TakeOptions;

public class App {
    public static void main(String[] args) throws Exception {
        final Client client = Client.withKeys("IVmt2ghj9TG_jQ", "Sxt94yAj9aQSgg");
        TakeOptions takeOptions = TakeOptions.url("https://scalabledeveloper.com")
                .fullPage(true)
                .deviceScaleFactor(1)
                .viewportHeight(1200)
                .viewportWidth(1200)
                .format("png")
                .omitBackground(true);
        final String url = client.generateTakeUrl(takeOptions);

        System.out.println(url);
        // Output: https://api.screenshotone.com/take?access_key=IVmt2ghj9TG_jQ&device_scale_factor=1&format=png&full_page=true&omit_background=true&url=https%3A%2F%2Fscalabledeveloper.com&viewport_height=1200&viewport_width=1200&signature=3c0c5543599067322e8c84470702330e3687c6a08eef6b7311b71c32d04e1bd5
    }
}
```

Take a screenshot and save the image in the file: 
```java
import com.screenshotone.jsdk.Client;
import com.screenshotone.jsdk.TakeOptions;

import java.io.File;
import java.nio.file.Files;

public class App {
    public static void main(String[] args) throws Exception {
        final Client client = Client.withKeys("IVmt2ghj9TG_jQ", "Sxt94yAj9aQSgg");
        TakeOptions takeOptions = TakeOptions.url("https://scalabledeveloper.com")
                .fullPage(true)
                .deviceScaleFactor(1)
                .viewportHeight(1200)
                .viewportWidth(1200)
                .format("png")
                .omitBackground(true);
        final byte[] image = client.take();

        Files.write(new File("./example.png").toPath(), image);
    }
}
```

## Tests 

To run tests, just execute: 
```shell
./gradlew test
```

## License 

`screenshotone/jsdk` is released under [the MIT license](LICENSE).
