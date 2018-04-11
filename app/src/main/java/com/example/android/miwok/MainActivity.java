/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        TextView numbers = (TextView) findViewById(R.id.numbers);
        TextView phrases = (TextView) findViewById(R.id.phrases);
        TextView colors = (TextView) findViewById(R.id.colors);
        TextView familiy = (TextView) findViewById(R.id.family);

        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), NumbersActivity.class);
                startActivity(i);
            }
        });

        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), PhrasesActivity.class);
                startActivity(i);
            }
        });

        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ColorsActivity.class);
                startActivity(i);
            }
        });

        familiy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), FamilyActivity.class);
                startActivity(i);
            }
        });






        /* String[] words = new String[10];

        words[0] = "one";
        words[1] = "two";
        words[2] = "three";
        words[3] = "four";
        words[4] = "five";
        words[5] = "six";
        words[6] = "seven";
        words[7] = "eight";
        words[8] = "nine";
        words[9] = "ten"; */




        /*
        Log.v("NumbersActivity", "Word at index 0 " + words[0]);
        Log.v("NumbersActivity", "Word at index 1 " + words[1]);
        Log.v("NumbersActivity", "Word at index 2 " + words[2]);
        Log.v("NumbersActivity", "Word at index 3 " + words[3]);
        Log.v("NumbersActivity", "Word at index 4 " + words[4]);
        Log.v("NumbersActivity", "Word at index 5 " + words[5]);
        Log.v("NumbersActivity", "Word at index 6 " + words[6]);
        Log.v("NumbersActivity", "Word at index 7 " + words[7]);
        Log.v("NumbersActivity", "Word at index 8 " + words[8]);
        Log.v("NumbersActivity", "Word at index 9 " + words[9]); */

        // Diziler ile ilgili alıştırma platformu http://codingbat.com/java/Array-1


    }


}
