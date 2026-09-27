# Gleap Android OKHttpInterceptor

> [!IMPORTANT]
> **Deprecated.** The [Gleap Android SDK](https://github.com/GleapSDK/Android-SDK) includes `io.gleap.GleapOkHttpInterceptor` since version 18.2.0. Remove the `io.gleap:gleap-okhttp-interceptor` dependency and keep your code as it is: the class name, the import and `addInterceptor(new GleapOkHttpInterceptor())` stay the same (keeping both dependencies fails the build with a duplicate class error).
>
> The built-in interceptor logs request and response headers and text bodies (JSON, XML, text and forms, up to 150 KB each), copies the response body while your app reads it so nothing is consumed or delayed (streaming responses pass through untouched), logs failed requests with their error, and applies the network log filters from the dashboard. Versions up to 7.4.2 of this artifact dropped the headers and body of JSON requests, kept only 2 KB of each response and did not log failed requests.


![Gleap Android SDK Intro](https://raw.githubusercontent.com/GleapSDK/Gleap-iOS-SDK/main/Resources/GleapHeaderImage.png)

Capture HTTP request and response logs from [OkHttp](https://square.github.io/okhttp/) with the [Gleap Android SDK](https://github.com/GleapSDK/Android-SDK). Give your team network context for in-app bug reports and customer support.

## Docs & Examples
Checkout our [documentation](https://docs.gleap.ai/documentation/android/network-logs) for full reference.

## Installation with Maven

Open your project in your favorite IDE. (e.g. Android Studio). Open the **build.gradle** of your project.

**Scroll down to the dependencies**

```
dependencies {
...
}
```

**Add the OkHttp-Interceptor to your dependencies**

```
dependencies {
...
implementation group: 'io.gleap', name: 'gleap-okhttp-interceptor', version: '1.0.0'
}

```

Sync the gradle file to start the download of the library. 🎉


**Use the Interceptor**

This is the example of okhttp for intercepting request with a small adaption.

```
import io.gleap.GleapOkHttpInterceptor;

....
OkHttpClient client = new OkHttpClient.Builder()
    .addInterceptor(new GleapOkHttpInterceptor())
    .build();

Request request = new Request.Builder()
    .url("http://www.publicobject.com/helloworld.txt")
    .header("User-Agent", "OkHttp Example")
    .build();

Response response = client.newCall(request).execute();
response.body().close();
```

