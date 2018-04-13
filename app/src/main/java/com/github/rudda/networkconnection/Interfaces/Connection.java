package com.github.rudda.networkconnection.Interfaces;

/**
 * Created by Rudda Beltrao on 27-Jul-16.
 */
public abstract interface Connection {


    public void  fail(String message, int requestCode);
    public void  loading(boolean loading, int requestCode);


}
