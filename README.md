# Gleap Android OKHttpInterceptor

![Gleap Android SDK Intro](https://raw.githubusercontent.com/GleapSDK/iOS-SDK/main/imgs/gleapheader.png)

Intercept your okhttp requests on andoird. This library uses [OkHttp](https://square.github.io/okhttp/) and [GleapSDK](https://github.com/GleapSDK/Android-SDK).

## Docs & Examples
Checkout our [documentation](https://docs.gleap.io/android/network-logs) for full reference.

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

