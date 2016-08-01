package com.bignerdranch.android.criminalintent0728.database;

/**
 * Created by 이임경 on 2016-08-01.
 */

public class CrimeDbSchema {
    public static final class CrimeTable {
        public static final String NAME = "crimes";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String TIME="time";
            public static final String SOLVED = "solved";
        }
    }
}
