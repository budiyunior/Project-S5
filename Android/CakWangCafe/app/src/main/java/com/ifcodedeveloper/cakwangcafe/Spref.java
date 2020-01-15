package com.ifcodedeveloper.cakwangcafe;

import android.content.Context;
import android.content.SharedPreferences;

import com.ifcodedeveloper.cakwangcafe.activity.LoginActivity;

public class Spref {
    public static final String SP_id_pengguna="spid_pengguna";
    public static final String SP_Sukses_Login = "spSuksesLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public Spref(Context context){
        sp = context.getSharedPreferences(SP_id_pengguna, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public Boolean getSP_Sukses_Login() {
        return sp.getBoolean(SP_Sukses_Login,false);
    }
}
