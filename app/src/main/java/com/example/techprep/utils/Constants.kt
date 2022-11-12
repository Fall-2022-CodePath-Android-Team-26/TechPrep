package com.example.techprep.utils

import com.example.techprep.dashboard.Record

object Constants {

    fun getRecords(): ArrayList<Record>{
        val records = ArrayList<Record>()

        val record1 = Record(
            "11/10/2022","Linux",
            20,20
        )

        records.add(record1)

        val record2 = Record(
            "11/10/2022","Linux",
            18,20
        )

        records.add(record2)

        val record3 = Record(
            "11/9/2022","Docker",
            10,20
        )

        records.add(record3)

        val record4 = Record(
            "11/9/2022","Array",
            11,20
        )

        records.add(record4)

        val record5 = Record(
            "11/8/2022","Systems",
            15,20
        )

        records.add(record5)

        return records
    }
}