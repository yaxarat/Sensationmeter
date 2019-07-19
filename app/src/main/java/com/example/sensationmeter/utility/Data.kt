package com.example.sensationmeter.utility

class Data {
    var senseValue: Int = 0
    var tenseness: Int = 0
    var tingling: Int = 0
    var pressure: Int = 0
    var pain: Int = 0
    var otherSense: Int = 0
    var intakeVolume: Int = 0
    var sugar: Int = 0
    var caffeine: Int = 0
    var alcohol: Int = 0
    var carbonation: Int = 0
    var voidVolume: Int = 0
    var locationLabel: String = ""

    constructor()
    constructor(
        parameter1: Int,
        parameter2: Int,
        parameter3: Int,
        parameter4: Int,
        parameter5: Int,
        dataSource: String
    ) {
        when (dataSource) {
            "DrinkLogFragment" -> {
                this.intakeVolume = parameter1
                this.sugar = parameter2
                this.caffeine = parameter3
                this.alcohol = parameter4
                this.carbonation = parameter5
            }
            "MeterDialogFragment" -> {
                this.tenseness = parameter1
                this.tingling = parameter2
                this.pressure = parameter3
                this.pain = parameter4
                this.otherSense = parameter5
            }
        }
    }

    constructor(voidVolume: Int, locationLabel: String) {
        this.voidVolume = voidVolume
        this.locationLabel = locationLabel
    }

    constructor(senseValue: Int) {
        this.senseValue = senseValue
    }
}