package com.github.rudda.networkconnection.Interfaces;

import org.json.JSONObject;

/**
 * Created by Rudda Beltrao on 09/09/2017.
 */

public interface JsonObjectListener extends Connection {

    public void  sucess(JSONObject jsonObject, int requestCode);

}
