$(document).ready(function () {

    //-------------VARIABILI-------------
    var today;
    var year;
    var month;
    var nextMonth;
    var nextYear;

    var giorniScelti = new Array();

    var numGiorni = {
        giorni: []
    };

    var i = ["GENNAIO", "FEBBRAIO", "MARZO", "APRILE", "MAGGIO",
        "GIUGNO", "LUGLIO", "AGOSTO", "SETTEMBRE", "OTTOBRE",
        "NOVEMBRE", "DICEMBRE"];
    var daysArray = ["Domenica", "Lunedì", "Martedì",
        "Mercoledì", "Giovedì", "Venerdì", "Sabato"];

    var cal1 = $("#calendar_first");
    var calHeader1 = cal1.find(".calendar_header");
    var weekline1 = cal1.find(".calendar_weekdays");
    var datesBody1 = cal1.find(".calendar_content");

    var cal2 = $("#calendar_second");
    var calHeader2 = cal2.find(".calendar_header");
    var weekline2 = cal2.find(".calendar_weekdays");
    var datesBody2 = cal2.find(".calendar_content");

    var bothCals = $(".calendar");

    var switchButton = bothCals.find(".calendar_header").find('.switch-month');

    var calendars = {
        "cal1": {
            "name": "first",
            "calHeader": calHeader1,
            "weekline": weekline1,
            "datesBody": datesBody1
        },
        "cal2": {
            "name": "second",
            "calHeader": calHeader2,
            "weekline": weekline2,
            "datesBody": datesBody2
        }
    }
    //-------------FINE VARIABILI-------------

    function c(passed_month, passed_year, calNum) {
        var calendar = calNum == 0 ? calendars.cal1 : calendars.cal2;
        makeWeek(calendar.weekline);
        calendar.datesBody.empty();
        var calMonthArray = makeMonthArray(passed_month, passed_year);
        var r = 0;
        var u = false;

        var oggi = new Date();
        var lunediProssimo = new Date();
        var fineSettimana = new Date();
        var differenza = (oggi.getDate() - oggi.getDay() + (oggi.getDay() == 0 ? 1 : 8));
        lunediProssimo.setDate(differenza - 1);
        fineSettimana.setDate(differenza + 4);            //03/01
        oggi.setHours(0, 0, 0, 0);
        lunediProssimo.setHours(0, 0, 0, 0);
        fineSettimana.setHours(0, 0, 0, 0);

        while (!u) {
            if (daysArray[r] == calMonthArray[0].weekday) { u = true }
            else {
                calendar.datesBody.append('<div class="blank"></div>');
                r++;
            }
        }

        for (var cell = 0; cell < 42 - r; cell++) { // 42 date-cells in calendar
            if (cell >= calMonthArray.length) {
                calendar.datesBody.append('<div class="blank"></div>');
            } else {
                var shownDate = calMonthArray[cell].day;
                var iter_date = new Date(passed_year, passed_month, shownDate);
                iter_date.setHours(0, 0, 0, 0);

                //GIORNI NON PRENOTABILI
                if (iter_date <= lunediProssimo || iter_date > fineSettimana) {

                    //CONTROLLO SE NEI GIORNI NON PRENOTABILI C'E' IL GIORNO CORRENTE
                    if (checkToday(iter_date)) {
                        var m = '<div class="today">';
                    } else {
                        //ALTRIMENTI LO RENDO NON CLICCABILE
                        var m = '<div class="past-date">';
                    }
                }
                else {
                    var m = "<div>";
                    //CONTROLLO SE LA DATA NON SIA STATA GIA' SCELTA
                    for (var k = 0; k < giorniScelti.length; k++) {
                        if (iter_date.getTime() === giorniScelti[k].getTime()) {
                            var m = '<div class="choose">';
                        }
                    }
                }
                calendar.datesBody.append(m + shownDate + "</div>");
            }
        }

        calendar.calHeader.find("h2").text(i[passed_month] + " " + passed_year);

        var clicked;

        clickedElement = calendar.datesBody.find('div');

        clickedElement.on("click", function () {
            clicked = $(this);
            //CLASSI CHE NON SONO PIU' CLICCABILI
            if (clicked.hasClass('past-date')) { return; }
            if (clicked.hasClass('blank')) { return; }
            if (clicked.hasClass('choose')) { return; }
            if (clicked.hasClass('today')) { return; }
            //SWEETALERT
            Swal.fire({
                title: 'Sei sicuro?',
                text: "Stai prenotando un giorno di SW per questa data: " + clicked.html() + "/" + (passed_month + 1) + "/" + passed_year,
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                cancelButtonText: 'Cancella',
                confirmButtonText: 'Si, voglio prenotare'
            }).then((result) => {
                //SE PREMO "PRENOTA"
                if (result.value) {
                    //CONTROLLO SE HO GIA' FATTO 3 SCELTE
                    if (numGiorni.giorni.length >= 3) {
                        Swal.fire(
                            'Errore!',
                            'Hai effettuato già il massimo numero di prenotazioni.',
                            'error'
                        )
                    } else {
                        //ALTRIMENTI EFFETTUO LA SCELTA E ABILITO IL PULSANTE
                        Swal.fire(
                            'Successo!',
                            'La prenotazione e\' stata effettuata.',
                            'success'
                        )
                        clicked.addClass("choose");
                        numGiorni.giorni.push({
                            "giorno": clicked.html(),
                            "mese": (passed_month + 1),
                            "anno": passed_year
                        })

                        var x = new Date(passed_year, passed_month, clicked.html());
                        console.log("Giorno scelto: " + x);
                        giorniScelti.push(x);
                        console.log("Lenght" + giorniScelti.length);

                        //ABILITO IL PULSANTE DI PRENOTAZIONE
                        var btn = $("#sendButton");
                        btn.prop("disabled", false);
                    }
                } else if (result.dismiss === Swal.DismissReason.cancel) {
                    //PREMO SU "ELIMINA" PER NON SCEGLIERE IL GIORNO CHE HO CLICCATO
                    Swal.fire(
                        'Cancellata!',
                        'La prenotazione e\' stata eliminata',
                        'error'
                    )
                }
            });
        });

    }

    //EVENTO CHE VIENE ESEGUITO QUANDO L'UTENTE CONFERMA LA PRENOTAZIONE
    $("#sendButton").on("click", function () {
        var form = $("#smartWorkingDays");
        for (var i = 0; i < numGiorni.giorni.length; i++) {
            var x = 0;
            var y = 0;

            if (numGiorni.giorni[i].giorno < 10) {
                x = 1;
            }
            if (numGiorni.giorni[i].mese < 10) {
                y = 1;
            }
            switch (x) {
                case 0: break;
                case 1: numGiorni.giorni[i].giorno = "0" + numGiorni.giorni[i].giorno;
            }
            switch (y) {
                case 0: break;
                case 1: numGiorni.giorni[i].mese = "0" + numGiorni.giorni[i].mese;
            }
            form.append('<input type="hidden" name="dates" value="' + numGiorni.giorni[i].anno + "-" + numGiorni.giorni[i].mese + "-" + numGiorni.giorni[i].giorno + '">');
        }
    });

    function makeMonthArray(passed_month, passed_year) { // creates Array specifying dates and weekdays
        var e = [];
        for (var r = 1; r < getDaysInMonth(passed_year, passed_month) + 1; r++) {
            e.push({
                day: r,
                // Later refactor -- weekday needed only for first week
                weekday: daysArray[getWeekdayNum(passed_year, passed_month, r)]
            });
        }
        return e;
    }

    function makeWeek(week) {
        week.empty();
        for (var e = 0; e < 7; e++) {
            week.append("<div>" + daysArray[e].substring(0, 3) + "</div>")
        }
    }

    function getDaysInMonth(currentYear, currentMon) {
        return (new Date(currentYear, currentMon + 1, 0)).getDate();
    }

    function getWeekdayNum(e, t, n) {
        return (new Date(e, t, n)).getDay();
    }

    function checkToday(e) {
        var todayDate = today.getFullYear() + '/' + (today.getMonth() + 1) + '/' + today.getDate();
        var checkingDate = e.getFullYear() + '/' + (e.getMonth() + 1) + '/' + e.getDate();
        return todayDate == checkingDate;

    }

    function getAdjacentMonth(curr_month, curr_year, direction) {
        var theNextMonth;
        var theNextYear;
        if (direction == "next") {
            theNextMonth = (curr_month + 1) % 12;
            theNextYear = (curr_month == 11) ? curr_year + 1 : curr_year;
        } else {
            theNextMonth = (curr_month == 0) ? 11 : curr_month - 1;
            theNextYear = (curr_month == 0) ? curr_year - 1 : curr_year;
        }
        return [theNextMonth, theNextYear];
    }

    function b() {
        today = new Date;
        year = today.getFullYear();
        month = today.getMonth();
        var nextDates = getAdjacentMonth(month, year, "next");
        nextMonth = nextDates[0];
        nextYear = nextDates[1];
    }

    var clickedElement;

    b();
    c(month, year, 0);
    c(nextMonth, nextYear, 1);
    switchButton.on("click", function () {
        var clicked = $(this);
        var generateCalendars = function (e) {
            var nextDatesFirst = getAdjacentMonth(month, year, e);
            var nextDatesSecond = getAdjacentMonth(nextMonth, nextYear, e);
            month = nextDatesFirst[0];
            year = nextDatesFirst[1];
            nextMonth = nextDatesSecond[0];
            nextYear = nextDatesSecond[1];

            c(month, year, 0);
            c(nextMonth, nextYear, 1);
        };
        if (clicked.attr("class").indexOf("left") != -1) {
            generateCalendars("previous");
        } else { generateCalendars("next"); }
        clickedElement = bothCals.find(".calendar_content").find("div");
    });

    //  Click picking stuff
    function getClickedInfo(element, calendar) {
        var clickedInfo = {};
        var clickedCalendar,
            clickedMonth,
            clickedYear;
        clickedCalendar = calendar.name;
        //console.log(element.parent().parent().attr('class'));
        clickedMonth = clickedCalendar == "first" ? month : nextMonth;
        clickedYear = clickedCalendar == "first" ? year : nextYear;
        clickedInfo = {
            "calNum": clickedCalendar,
            "date": parseInt(element.text()),
            "month": clickedMonth,
            "year": clickedYear
        }
        //console.log(clickedInfo);
        return clickedInfo;
    }
});