[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.rudda/networkconnection/badge.svg)(https://maven-badges.herokuapp.com/maven-central/com.github.rudda/networkconnetion)
# NetworkConnection Setup

# Gradle
```gradle

compile 'com.github.rudda:networkconnetion:0.0.1'

```
# Permissions

add line in android manifest file

```xml
<uses-permission android:name="android.permission.INTERNET"></uses-permission>
```

# Request GET

```java

 CustomRequest customRequest = new CustomRequest();
               HashMap<String, String> params = new HashMap<>();
               params.put("Authorization", "Basic " + CustomRequest.generateAutorizationHeader(email, pass));
               customRequest.setHeaders(params);
               customRequest.setMethod(Request.Method.GET);
               customRequest.setBaseUrl(myContext.getString(R.string.url));
               customRequest.setRouter(this.URL_LOGIN);

               NetworkConnection.getInstance(myContext)
                       .execute(myJsonObjectListener, customRequest);


```

# Params - Get
```java

```
# Request POST
```java

```
# Params POST

```java

```

# Headers
```java

```

# Calbacks

```java

```






