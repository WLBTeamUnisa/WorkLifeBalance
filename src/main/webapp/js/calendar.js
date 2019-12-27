$(document).ready(function () {
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

                if (iter_date <= lunediProssimo || iter_date > fineSettimana) {

                    if (checkToday(iter_date)) {
                        var m = '<div class="today">';
                    } else {
                        var m = '<div class="past-date">';
                    }
                }
                else {
                    var m = "<div>";
                }
                calendar.datesBody.append(m + shownDate + "</div>");
            }
        }

        calendar.calHeader.find("h2").text(i[passed_month] + " " + passed_year);

        var clicked;

        clickedElement = calendar.datesBody.find('div');

        clickedElement.on("click", function () {
            clicked = $(this);
            if (clicked.hasClass('past-date')) { return; }
            if (clicked.hasClass('blank')) { return; }
            if (clicked.hasClass('choose')) { return; }
            if (clicked.hasClass('today')) { return; }
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
                if (result.value) {
                    if (numGiorni.giorni.length >= 3) {
                        Swal.fire(
                            'Errore!',
                            'Troppe prenotazioni!',
                            'error'
                        )
                    } else {
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

                        //ABILITO IL PULSANTE DI PRENOTAZIONE
                        var btn = $("#sendButton");
                        btn.prop("disabled", false);
                    }
                } else if (result.dismiss === Swal.DismissReason.cancel) {
                    Swal.fire(
                        'Cancellata!',
                        'La prenotazione e\' stata eliminata',
                        'error'
                    )
                }
            });
        });

    }

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

    var e = 480;

    var today;
    var year,
        month,
        nextMonth,
        nextYear;

    var numGiorni = {
        giorni: []
    };

    var r = [];
    var i = ["GENNAIO", "FEBBRAIO", "MARZO", "APRILE", "MAGGIO",
        "GIUGNO", "LUGLIO", "AGOSTO", "SETTEMBRE", "OTTOBRE",
        "NOVEMBRE", "DICEMBRE"];
    var daysArray = ["Domemica", "Lunedì", "Martedì",
        "Mercoledì", "Giovedì", "Venerdì", "Sabato"];
    var o = ["#16a085", "#1abc9c", "#c0392b", "#27ae60",
        "#FF6860", "#f39c12", "#f1c40f", "#e67e22",
        "#2ecc71", "#e74c3c", "#d35400", "#2c3e50"];

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

    $("#sendButton").on("click", function () {
        var form = $("#smartWorkingDays");
        for (var i = 0; i < numGiorni.giorni.length; i++) {
            var x = 0;
            var y = 0;
            
            if(numGiorni.giorni[i].giorno<10){
                x=1;
            }
            if(numGiorni.giorni[i].mese<10){
                y = 1;
            }
            switch(x){
                case 0: break;
                case 1: numGiorni.giorni[i].giorno = "0"+numGiorni.giorni[i].giorno;
            }
            switch(y){
                case 0: break;
                case 1: numGiorni.giorni[i].mese = "0"+numGiorni.giorni[i].mese;
            }
            form.append('<input type="hidden" name="dates" value="' + numGiorni.giorni[i].anno + "-" + numGiorni.giorni[i].mese + "-" + numGiorni.giorni[i].giorno + '">');
        }
    });
});