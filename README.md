[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.rudda/networkconnection/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.apache.commons/commons-lang3/)

# NetworkConnection [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.rudda/networkconnection/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/com.github.rudda/networkconnetion) 

# Gradle
```gradle

compile 'com.github.rudda:networkconnetion:0.0.1'

```
# Permissions

add line in android manifest file

```xml
<uses-permission android:name="android.permission.INTERNET"></uses-permission>
```

# Simple GET Request -with Return JSONArray

<h4>1º step:</h4>  you must implements JsonArrayListener interface

```java

public class MainActivity extends AppCompatActivity implements JsonArrayListener 

```

<h4>2º step:</h4>  you must to instance customRequest class and set attribuites base url ( **setBaseUrl** ) , router ( **setRouter** ) and the method to request ( **GET or POST** ) this example GET.

```java

  CustomRequest customRequest = new CustomRequest();
        customRequest.setBaseUrl("http://localhost/users"); //base url
        customRequest.setRouter("/"); //router to REST API
        customRequest.setMethod(Request.Method.GET);// methods ( get or post )
        customRequest.setResult(0); //optional

        NetworkConnection.getInstance(this).execute(this, customRequest);
```
 
 <h4>3º step:</h4>  the return. <br>
 the return can come in three states: success, fail and loading. The first state is always loading the second state can be success or fail.
 warning: you can also use the requestCode to represent a specific call.
 
 
 
```java

  @Override
    public void sucess(JSONArray Jsonarray, int requestCode) {

        Toast.makeText(this, "sucess", Toast.LENGTH_SHORT).show();
        TextView tv = (TextView) findViewById(R.id.response);

        tv.setText(Jsonarray.toString());


    }

    @Override
    public void fail(String message, int requestCode) {

        Toast.makeText(this, "falha", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void loading(boolean loading, int requestCode) {
        Toast.makeText(this, "carregando...", Toast.LENGTH_SHORT).show();

    }

```
# add headers

you must instantiate a hashmap of type <string, string> add key and respective values. Then just add the hashmap to the setHeader method of an instance of the CustomRequest class. **the example below shows how to add an authorization header.** 


```java

 HashMap<String, String> params = new HashMap<>();
               params.put("Authorization", "Basic " + CustomRequest.generateAutorizationHeader(email, pass));
               customRequest.setHeaders(params);
          
```
 





