package com.github.rudda.networkconnection.Interfaces;

import org.json.JSONArray;

/**
 * Created by Rudda Beltrao on 09/09/2017.
 */

public interface JsonArrayListener extends Connection {

    public void  sucess(JSONArray Jsonarray, int requestCode);

}
