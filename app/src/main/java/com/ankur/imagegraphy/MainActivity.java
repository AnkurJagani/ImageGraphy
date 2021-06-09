/*
 * Copyright 2015 Udacity, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ankur.imagegraphy;


import android.content.res.Resources;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import static android.widget.ImageView.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imageView;

    // Radio Buttons
    RadioButton noneBtn;
    RadioButton centerBtn;
    RadioButton centerCropBtn;
    RadioButton centerInsideBtn;
    RadioButton fitCenterBtn;
    RadioButton fitEndBtn;
    RadioButton fitStartBtn;
    RadioButton fitXYBtn;
    RadioButton matrixBtn;
    Matrix matrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        noneBtn = findViewById(R.id.noneBtn);
        centerBtn = findViewById(R.id.centerBtn);
        centerCropBtn = findViewById(R.id.centerCropBtn);
        centerInsideBtn = findViewById(R.id.centerInsideBtn);
        fitCenterBtn = findViewById(R.id.fitCenterBtn);
        fitEndBtn = findViewById(R.id.fitEndBtn);
        fitStartBtn = findViewById(R.id.fitStartBtn);
        fitXYBtn = findViewById(R.id.fitXYBtn);
        matrixBtn = findViewById(R.id.matrixBtn);

        noneBtn.setOnClickListener(this);
        centerBtn.setOnClickListener(this);
        centerCropBtn.setOnClickListener(this);
        centerInsideBtn.setOnClickListener(this);
        fitCenterBtn.setOnClickListener(this);
        fitEndBtn.setOnClickListener(this);
        fitStartBtn.setOnClickListener(this);
        fitXYBtn.setOnClickListener(this);
        matrixBtn.setOnClickListener(this);

        fitCenterBtn.setChecked(true);
        matrix = new Matrix();
        matrix.postScale(0.5f, 0.5f);
        matrix.postTranslate(100, 100);
    }

    public RadioButton getSelectedRadio(View view) {
        RadioButton[] btns = {centerBtn, centerCropBtn, centerInsideBtn, fitCenterBtn,
                fitEndBtn, fitStartBtn, fitXYBtn, matrixBtn, noneBtn};
        for (RadioButton radioButton : btns) {
            if (radioButton.isChecked() && radioButton != view) {
                return radioButton;
            }
        }
        return null;
    }


    @Override
    public void onClick(View view) {
        clickRadioButton((RadioButton) view);
    }

    public void clickRadioButton(RadioButton view) {
        // Check to see what is clicked
        RadioButton checkedRadio = getSelectedRadio(view);
        if (checkedRadio != null && checkedRadio != view) {
            checkedRadio.setChecked(false);
        }
        // If currently checked, do nothing.
        imageView.setPadding(0, 0, 0, 0);
        switch ((String) view.getText()) {

            case "NO!!!!":
                int px = dpToPx(32);
                imageView.setPadding(px, px, px, px);
                imageView.setScaleType(ScaleType.FIT_CENTER);
                break;
            case "CENTER":
                imageView.setScaleType(ScaleType.CENTER);
                break;
            case "CENTER_CROP":
                imageView.setScaleType(ScaleType.CENTER_CROP);
                break;
            case "CENTER_INSIDE":
                imageView.setScaleType(ScaleType.CENTER_INSIDE);
                break;
            case "FIT_CENTER":
                imageView.setScaleType(ScaleType.FIT_CENTER);
                break;
            case "FIT_END":
                imageView.setScaleType(ScaleType.FIT_END);
                break;
            case "FIT_START":
                imageView.setScaleType(ScaleType.FIT_START);
                break;
            case "FIT_XY":
                imageView.setScaleType(ScaleType.FIT_XY);
                break;
            case "MATRIX":
                imageView.setScaleType(ScaleType.MATRIX);
                imageView.setImageMatrix(matrix);
                break;

        }
        // If different, alter view

    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

}
