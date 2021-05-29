package com.caniksoft.canikalarm.entity;


public class AlarmEntity {

    private Integer serno;
    private String name;
    private Integer hour;
    private Integer minute;
    private Boolean[] days = new Boolean[] {false, false, false, false, false, false, false};


    public AlarmEntity() {}

    public AlarmEntity(String name, Integer hour, Integer minute, Boolean[] days) {
        this.name = name;
        this.hour = hour;
        this.minute = minute;
        this.days = days;
    }

    public AlarmEntity(Integer serno, String name, Integer hour, Integer minute, Boolean[] days) {
        this.serno = serno;
        this.name = name;
        this.hour = hour;
        this.minute = minute;
        this.days = days;
    }

    public String getName() {
        return name;
    }

    public Integer getSerno() {
        return serno;
    }

    public void setSerno(Integer serno) {
        this.serno = serno;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Boolean[] getDays() {
        return days;
    }

    public void setDays(Boolean[] days) {
        this.days = days;
    }

    public String getDaysString() {
        StringBuilder sb = new StringBuilder();

        if (days[0]) sb.append("Pzt");
        if (days[1]) sb.append("Sal");
        if (days[2]) sb.append("Çrş");
        if (days[3]) sb.append("Prş");
        if (days[4]) sb.append("Cum");
        if (days[5]) sb.append("Cts");
        if (days[6]) sb.append("Pzr");

        for (int i = 0; i < sb.length(); i=i+5) {
            sb.insert(i+3, ", ");
        }

        sb.replace(sb.length() - 2, sb.length(), "");

        return sb.toString();
    }

    public void setDays(String days) {
        if (days == null || days.isEmpty()) {
            this.days = new Boolean[] {false, false, false, false, false, false, false};
            return;
        }

        for (int i = 0; i < days.length(); i++) {
            this.days[i] = Integer.parseInt(days.substring(i, i+1)) == 1;
        }

    }
}
