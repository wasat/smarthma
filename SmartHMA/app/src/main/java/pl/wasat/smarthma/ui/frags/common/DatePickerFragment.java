/*
 * Copyright (c) 2016.  SmartHMA ESA
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

package pl.wasat.smarthma.ui.frags.common;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Const;


/**
 * The type Date picker fragment.
 */
public class DatePickerFragment extends DialogFragment implements
        DatePickerDialog.OnDateSetListener {

    private String textViewTag;
    private Calendar calendar;
    private DatePicker datePicker;
    private int dpYear;
    private int dpMonth;
    private int dpDay;

    private OnDatePickerFragmentListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = context instanceof Activity ? (Activity) context : null;
        try {
            mListener = (OnDatePickerFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + activity.getString(R.string.must_implement)
                    + OnDatePickerFragmentListener.class.getSimpleName());
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (getArguments() != null) {
            textViewTag = getArguments().getString(Const.KEY_DATE_PICKER_DT_VIEW_TAG);
            calendar = (Calendar) getArguments().getSerializable(Const.KEY_DATE_TIME_PICKER_CALENDAR);
        }

        dpYear = calendar != null ? calendar.get(Calendar.YEAR) : 0;
        dpMonth = calendar != null ? calendar.get(Calendar.MONTH) : 0;
        dpDay = calendar != null ? calendar.get(Calendar.DAY_OF_MONTH) : 0;

        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_date_picker);

        Button cancel = (Button) dialog.findViewById(R.id.dialog_date_picker_cancel);
        Button choose = (Button) dialog.findViewById(R.id.dialog_date_picker_choose);
        datePicker = (DatePicker) dialog.findViewById(R.id.dialog_date_picker_datePicker);

        datePicker.init(dpYear, dpMonth, dpDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                dpYear = year;
                dpMonth = monthOfYear;
                dpDay = dayOfMonth;
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDateSet(datePicker, dpYear, dpMonth, dpDay);
                dialog.dismiss();
            }
        });
        return dialog;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        calendar.set(year, month, day);
        mListener.onDatePickerFragmentDateChoose(calendar, textViewTag);
    }

    /**
     * The interface On date picker fragment listener.
     */
    public interface OnDatePickerFragmentListener {
        /**
         * On date picker fragment date choose.
         *
         * @param calendar the calendar
         * @param viewTag  the view tag
         */
        void onDatePickerFragmentDateChoose(Calendar calendar, String viewTag);
    }
}

