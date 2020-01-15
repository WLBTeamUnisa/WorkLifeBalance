package it.unisa.wlb.bean;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unisa.wlb.model.bean.*;

/**
 * The aim of this class is testing SmartWorkingPrenotation.java and SmartWorkingPrenotationPK.java
 * 
 * @author Sabato Nocera
 *
 */
class SmartWorkingPrenotationTest {

    private SmartWorkingPrenotation smartWorkingPrenotation;
    private int calendarWeek;
    private Employee employee;
    private SmartWorkingPrenotationPK id;
    private List<PrenotationDate> prenotationDates;
    private int year;

    @BeforeEach
    void setUp() throws Exception {
        smartWorkingPrenotation = new SmartWorkingPrenotation();
        employee = new Employee();
        id = new SmartWorkingPrenotationPK();
        prenotationDates = new ArrayList<PrenotationDate>();

        Calendar localCalendar = Calendar.getInstance();
        TimeZone timeZone = localCalendar.getTimeZone();
        ZoneId zoneId = timeZone == null ? ZoneId.systemDefault() : timeZone.toZoneId();
        LocalDate today = LocalDateTime.ofInstant(localCalendar.toInstant(), zoneId).toLocalDate();
        localCalendar.setTime(Date.from(today.atStartOfDay().atZone(zoneId).toInstant()));

        /**
         * Calendar Week
         */
        calendarWeek = localCalendar.get(Calendar.WEEK_OF_YEAR);
        smartWorkingPrenotation.setCalendarWeek(calendarWeek);

        /**
         * Year
         */
        year = localCalendar.get(Calendar.YEAR);
        smartWorkingPrenotation.setYear(year);

        /**
         * Employee
         */
        employee.setEmail("m.anager10@wlb.it");
        employee.setMessages(new ArrayList<Message>());
        employee.setName("ManagerName");
        employee.setPassword("Manager1234.");
        employee.setProjects1(new ArrayList<Project>());
        employee.setProjects2(new ArrayList<Project>());
        List<SmartWorkingPrenotation> smartWorkingPrenotations = new ArrayList<SmartWorkingPrenotation>();
        smartWorkingPrenotations.add(smartWorkingPrenotation);
        employee.setSmartWorkingPrenotations(smartWorkingPrenotations);
        employee.setStatus(1);
        employee.setSurname("ManagerSurname");
        employee.setWorkstationPrenotations(new ArrayList<WorkstationPrenotation>());
        smartWorkingPrenotation.setEmployee(employee);

        /**
         * SmartWorkingPrenotationPK
         */
        id.setEmployeeEmail(employee.getEmail());
        id.setId(1);
        smartWorkingPrenotation.setId(id);

        /**
         * Prenotation Dates
         */
        PrenotationDate prenotationDate = new PrenotationDate();
        PrenotationDatePK prenotationDatePK = new PrenotationDatePK();
        prenotationDatePK.setDate(new Date());
        prenotationDatePK.setEmployeeEmail(employee.getEmail());
        prenotationDatePK.setIdPrenotationSw(1);
        prenotationDate.setId(prenotationDatePK);
        prenotationDate.setSmartWorkingPrenotation(smartWorkingPrenotation);
        prenotationDates.add(prenotationDate);
        smartWorkingPrenotation.setPrenotationDates(prenotationDates);
    }

    @Test
    final void testSmartWorkingPrenotation() {
        assertNotNull(smartWorkingPrenotation);
    }

    @Test
    final void testGetId() {
        assertEquals(smartWorkingPrenotation.getId(), id);
    }

    @Test
    final void testSetId() {
        SmartWorkingPrenotationPK newSmartWorkingPrenotationPK = new SmartWorkingPrenotationPK();
        newSmartWorkingPrenotationPK.setEmployeeEmail(id.getEmployeeEmail());
        newSmartWorkingPrenotationPK.setId(id.getId() + 2);
        smartWorkingPrenotation.setId(newSmartWorkingPrenotationPK);
        assertEquals(smartWorkingPrenotation.getId(), newSmartWorkingPrenotationPK);
    }

    @Test
    final void testGetCalendarWeek() {
        assertEquals(smartWorkingPrenotation.getCalendarWeek(), calendarWeek);
    }

    @Test
    final void testSetCalendarWeek() {
        int calendarWeek2 = calendarWeek + 1;
        smartWorkingPrenotation.setCalendarWeek(calendarWeek2);
        assertEquals(smartWorkingPrenotation.getCalendarWeek(), calendarWeek2);
    }

    @Test
    final void testGetYear() {
        assertEquals(smartWorkingPrenotation.getYear(), year);
    }

    @Test
    final void testSetYear() {
        int year2 = year + 5;
        smartWorkingPrenotation.setYear(year2);
        assertEquals(smartWorkingPrenotation.getYear(), year2);
    }

    @Test
    final void testGetPrenotationDates() {
        List<PrenotationDate> list = smartWorkingPrenotation.getPrenotationDates();
        if (list.size() != prenotationDates.size())
            assertTrue(false);
        for (PrenotationDate prenotationDate : list)
            if (!prenotationDates.contains(prenotationDate)) {
                assertTrue(false);
                return;
            }
        assertTrue(true);
    }

    @Test
    final void testSetPrenotationDates() {
        List<PrenotationDate> prenotationDatesList = new ArrayList<PrenotationDate>();

        PrenotationDate prenotationDate = new PrenotationDate();
        PrenotationDatePK prenotationDatePK = new PrenotationDatePK();
        prenotationDatePK.setDate(new Date());
        prenotationDatePK.setEmployeeEmail(employee.getEmail());
        prenotationDatePK.setIdPrenotationSw(2);
        prenotationDate.setId(prenotationDatePK);
        prenotationDate.setSmartWorkingPrenotation(smartWorkingPrenotation);
        prenotationDates.add(prenotationDate);

        prenotationDatesList.add(prenotationDate);

        smartWorkingPrenotation.setPrenotationDates(prenotationDatesList);

        if (!smartWorkingPrenotation.getPrenotationDates().contains(prenotationDate)) {
            assertTrue(false);
            return;
        }
        assertTrue(true);
    }

    @Test
    final void testAddPrenotationDate() {
        PrenotationDate prenotationDate = new PrenotationDate();
        PrenotationDatePK prenotationDatePK = new PrenotationDatePK();
        prenotationDatePK.setDate(new Date());
        prenotationDatePK.setEmployeeEmail(employee.getEmail());
        prenotationDatePK.setIdPrenotationSw(2);
        prenotationDate.setId(prenotationDatePK);
        prenotationDate.setSmartWorkingPrenotation(smartWorkingPrenotation);

        smartWorkingPrenotation.addPrenotationDate(prenotationDate);

        List<PrenotationDate> list = smartWorkingPrenotation.getPrenotationDates();

        if (!list.contains(prenotationDate)) {
            assertTrue(false);
            return;
        }
        assertTrue(true);
    }

    @Test
    final void testRemovePrenotationDate() {
        PrenotationDate prenotationDate = new PrenotationDate();
        PrenotationDatePK prenotationDatePK = new PrenotationDatePK();
        prenotationDatePK.setDate(new Date());
        prenotationDatePK.setEmployeeEmail(employee.getEmail());
        prenotationDatePK.setIdPrenotationSw(2);
        prenotationDate.setId(prenotationDatePK);
        prenotationDate.setSmartWorkingPrenotation(smartWorkingPrenotation);

        smartWorkingPrenotation.addPrenotationDate(prenotationDate);

        List<PrenotationDate> list = smartWorkingPrenotation.getPrenotationDates();

        if (list.contains(prenotationDate)) {
            smartWorkingPrenotation.removePrenotationDate(prenotationDate);
            if (!list.contains(prenotationDate)) {
                assertTrue(true);
                return;
            }
        }
        assertTrue(false);
    }

    @Test
    final void testGetEmployee() {
        assertEquals(employee, smartWorkingPrenotation.getEmployee());
    }

    @Test
    final void testSetEmployee() {
        Employee newEmployee = new Employee();
        newEmployee.setEmail("n.ewemployee1@wlb.it");
        newEmployee.setMessages(employee.getMessages());
        newEmployee.setName("New" + employee.getEmail());
        newEmployee.setPassword(employee.getPassword());
        newEmployee.setProjects1(employee.getProjects1());
        newEmployee.setProjects2(employee.getProjects2());
        newEmployee.setSmartWorkingPrenotations(employee.getSmartWorkingPrenotations());
        newEmployee.setStatus(1);
        newEmployee.setSurname("New" + employee.getSurname());
        newEmployee.setWorkstationPrenotations(employee.getWorkstationPrenotations());
        smartWorkingPrenotation.setEmployee(newEmployee);
        assertEquals(newEmployee, smartWorkingPrenotation.getEmployee());
    }

    @Test
    final void testToString() {
        String string = "SmartWorkingPrenotation [id=" + id + ", calendarWeek=" + calendarWeek + ", year=" + year
                + ", employee=" + employee + "]";
        assertEquals(string, smartWorkingPrenotation.toString());
    }

    @Test
    final void testEqualsObject1() {
        SmartWorkingPrenotation newSmartWorkingPrenotation = new SmartWorkingPrenotation();
        newSmartWorkingPrenotation.setCalendarWeek(calendarWeek);
        newSmartWorkingPrenotation.setEmployee(employee);
        newSmartWorkingPrenotation.setId(id);
        newSmartWorkingPrenotation.setPrenotationDates(prenotationDates);
        newSmartWorkingPrenotation.setYear(year);
        assertEquals(newSmartWorkingPrenotation, smartWorkingPrenotation);
    }

    @Test
    final void testEqualsObject2() {
        SmartWorkingPrenotation newSmartWorkingPrenotation = new SmartWorkingPrenotation();
        newSmartWorkingPrenotation.setCalendarWeek(calendarWeek + 1);
        newSmartWorkingPrenotation.setEmployee(employee);
        newSmartWorkingPrenotation.setId(id);
        newSmartWorkingPrenotation.setPrenotationDates(prenotationDates);
        newSmartWorkingPrenotation.setYear(year);
        assertNotEquals(newSmartWorkingPrenotation, smartWorkingPrenotation);
    }

    @Test
    final void testEqualsObject3() {
        SmartWorkingPrenotation newSmartWorkingPrenotation = new SmartWorkingPrenotation();
        newSmartWorkingPrenotation.setCalendarWeek(calendarWeek);
        newSmartWorkingPrenotation.setEmployee(new Employee());
        newSmartWorkingPrenotation.setId(id);
        newSmartWorkingPrenotation.setPrenotationDates(prenotationDates);
        newSmartWorkingPrenotation.setYear(year);
        assertNotEquals(newSmartWorkingPrenotation, smartWorkingPrenotation);
    }

    @Test
    final void testEqualsObject4() {
        SmartWorkingPrenotation newSmartWorkingPrenotation = new SmartWorkingPrenotation();
        newSmartWorkingPrenotation.setCalendarWeek(calendarWeek);
        newSmartWorkingPrenotation.setEmployee(employee);
        newSmartWorkingPrenotation.setId(new SmartWorkingPrenotationPK());
        newSmartWorkingPrenotation.setPrenotationDates(prenotationDates);
        newSmartWorkingPrenotation.setYear(year);
        assertNotEquals(newSmartWorkingPrenotation, smartWorkingPrenotation);
    }

    @Test
    final void testEqualsObject5() {
        SmartWorkingPrenotation newSmartWorkingPrenotation = new SmartWorkingPrenotation();
        newSmartWorkingPrenotation.setCalendarWeek(calendarWeek);
        newSmartWorkingPrenotation.setEmployee(employee);
        newSmartWorkingPrenotation.setId(id);
        newSmartWorkingPrenotation.setPrenotationDates(new ArrayList<PrenotationDate>());
        newSmartWorkingPrenotation.setYear(year);
        assertNotEquals(newSmartWorkingPrenotation, smartWorkingPrenotation);
    }

    @Test
    final void testEqualsObject6() {
        SmartWorkingPrenotation newSmartWorkingPrenotation = new SmartWorkingPrenotation();
        newSmartWorkingPrenotation.setCalendarWeek(calendarWeek);
        newSmartWorkingPrenotation.setEmployee(employee);
        newSmartWorkingPrenotation.setId(id);
        newSmartWorkingPrenotation.setPrenotationDates(prenotationDates);
        newSmartWorkingPrenotation.setYear(year + 1);
        assertNotEquals(newSmartWorkingPrenotation, smartWorkingPrenotation);
    }

    @Test
    final void testEqualsObject8() {
        SmartWorkingPrenotation newSmartWorkingPrenotation = new SmartWorkingPrenotation();
        newSmartWorkingPrenotation.setCalendarWeek(calendarWeek);
        newSmartWorkingPrenotation.setEmployee(null);
        newSmartWorkingPrenotation.setId(id);
        newSmartWorkingPrenotation.setPrenotationDates(prenotationDates);
        newSmartWorkingPrenotation.setYear(year);
        assertNotEquals(newSmartWorkingPrenotation, smartWorkingPrenotation);
    }

    @Test
    final void testEqualsObject9() {
        SmartWorkingPrenotation newSmartWorkingPrenotation = new SmartWorkingPrenotation();
        newSmartWorkingPrenotation.setCalendarWeek(calendarWeek);
        newSmartWorkingPrenotation.setEmployee(employee);
        newSmartWorkingPrenotation.setId(null);
        newSmartWorkingPrenotation.setPrenotationDates(prenotationDates);
        newSmartWorkingPrenotation.setYear(year);
        assertNotEquals(newSmartWorkingPrenotation, smartWorkingPrenotation);
    }

    @Test
    final void testEqualsObject10() {
        SmartWorkingPrenotation newSmartWorkingPrenotation = new SmartWorkingPrenotation();
        newSmartWorkingPrenotation.setCalendarWeek(calendarWeek);
        newSmartWorkingPrenotation.setEmployee(employee);
        newSmartWorkingPrenotation.setId(id);
        newSmartWorkingPrenotation.setPrenotationDates(null);
        newSmartWorkingPrenotation.setYear(year);
        assertNotEquals(newSmartWorkingPrenotation, smartWorkingPrenotation);
    }

    @Test
    final void testEqualsObject11() {
        SmartWorkingPrenotation newSmartWorkingPrenotation = new SmartWorkingPrenotation();
        newSmartWorkingPrenotation.setCalendarWeek(calendarWeek);
        newSmartWorkingPrenotation.setEmployee(employee);
        newSmartWorkingPrenotation.setId(id);
        newSmartWorkingPrenotation.setPrenotationDates(prenotationDates);
        newSmartWorkingPrenotation.setYear(year);
        smartWorkingPrenotation.setEmployee(null);
        assertNotEquals(newSmartWorkingPrenotation, smartWorkingPrenotation);
    }

    @Test
    final void testEqualsObject12() {
        SmartWorkingPrenotation newSmartWorkingPrenotation = new SmartWorkingPrenotation();
        newSmartWorkingPrenotation.setCalendarWeek(calendarWeek);
        newSmartWorkingPrenotation.setEmployee(employee);
        newSmartWorkingPrenotation.setId(id);
        newSmartWorkingPrenotation.setPrenotationDates(prenotationDates);
        newSmartWorkingPrenotation.setYear(year);
        smartWorkingPrenotation.setId(null);
        assertNotEquals(newSmartWorkingPrenotation, smartWorkingPrenotation);
    }

    @Test
    final void testEqualsObject13() {
        SmartWorkingPrenotation newSmartWorkingPrenotation = new SmartWorkingPrenotation();
        newSmartWorkingPrenotation.setCalendarWeek(calendarWeek);
        newSmartWorkingPrenotation.setEmployee(employee);
        newSmartWorkingPrenotation.setId(id);
        newSmartWorkingPrenotation.setPrenotationDates(prenotationDates);
        newSmartWorkingPrenotation.setYear(year);
        smartWorkingPrenotation.setPrenotationDates(null);
        assertNotEquals(newSmartWorkingPrenotation, smartWorkingPrenotation);
    }

    @Test
    final void testEqualsObject14() {
        String newSmartWorkingPrenotation = "";
        assertNotEquals(smartWorkingPrenotation, newSmartWorkingPrenotation);
    }

    @Test
    final void testEqualsObject15() {
        SmartWorkingPrenotation newSmartWorkingPrenotation = null;
        assertNotEquals(newSmartWorkingPrenotation, smartWorkingPrenotation);
    }

    @Test
    final void testEqualsObject16() {
        SmartWorkingPrenotation newSmartWorkingPrenotation = new SmartWorkingPrenotation();
        newSmartWorkingPrenotation.setCalendarWeek(calendarWeek);
        newSmartWorkingPrenotation.setEmployee(employee);
        newSmartWorkingPrenotation.setId(id);
        newSmartWorkingPrenotation.setPrenotationDates(prenotationDates);
        newSmartWorkingPrenotation.setYear(year);
        smartWorkingPrenotation = null;
        assertNotEquals(newSmartWorkingPrenotation, smartWorkingPrenotation);
    }

    @Test
    final void testEqualsObject17() {
        SmartWorkingPrenotation newSmartWorkingPrenotation = smartWorkingPrenotation;
        assertEquals(newSmartWorkingPrenotation, smartWorkingPrenotation);
    }

    @Test
    final void testEqualsObject7() {
        SmartWorkingPrenotationPK smartWorkingPrenotationPK = new SmartWorkingPrenotationPK();
        smartWorkingPrenotationPK.setEmployeeEmail(id.getEmployeeEmail());
        smartWorkingPrenotationPK.setId(id.getId() + 5);
        assertNotEquals(id, smartWorkingPrenotationPK);
    }

    @Test
    final void testEqualsObject18() {
        SmartWorkingPrenotationPK smartWorkingPrenotationPK = new SmartWorkingPrenotationPK();
        smartWorkingPrenotationPK.setEmployeeEmail("p.rrrr12@wlb.it");
        smartWorkingPrenotationPK.setId(id.getId());
        assertNotEquals(id, smartWorkingPrenotationPK);
    }

    @Test
    final void testEqualsObject19() {
        SmartWorkingPrenotationPK smartWorkingPrenotationPK = new SmartWorkingPrenotationPK();
        smartWorkingPrenotationPK.setEmployeeEmail(id.getEmployeeEmail());
        smartWorkingPrenotationPK.setId(id.getId());
        assertEquals(id, smartWorkingPrenotationPK);
    }
}
