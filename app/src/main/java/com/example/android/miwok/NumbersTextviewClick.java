package com.example.android.miwok;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class NumbersTextviewClick  implements View.OnClickListener{


    @Override
    public void onClick(View view) {
        // Bağlam - view Elemanının MainActivity' ile olan köprüsü.
       Toast.makeText(view.getContext(),"TIKLANDI",Toast.LENGTH_LONG).show();
    }




}
