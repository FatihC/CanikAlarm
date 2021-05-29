package com.caniksoft.canikalarm.ui.alarm;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.caniksoft.canikalarm.DBHelper;
import com.caniksoft.canikalarm.entity.AlarmEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class AlarmViewModel extends ViewModel {

    private MutableLiveData<List<AlarmEntity>> alarmList;

    AlarmEntity sabahNamaziAlarm = new AlarmEntity("Sabah Namazı", 5, 0, new Boolean[] {true, true, true, true, true, true, true});

    LiveData<List<AlarmEntity>> getAlarmList() {
        if (alarmList == null) {
            alarmList = new MutableLiveData<>();
            loadAlarms();
        }
        return alarmList;
    }

    private void loadAlarms() {
        // do async operation to fetch users
        Handler myHandler = new Handler();
        myHandler.postDelayed(() -> {
            List<AlarmEntity> alarmEntityList = DBHelper.get().getAlarmList();
            long seed = System.nanoTime();
            Collections.shuffle(alarmEntityList, new Random(seed));

            alarmList.setValue(alarmEntityList);
        }, 5000);

    }

    public LiveData<List<AlarmEntity>> getText() {
        return alarmList;
    }

}