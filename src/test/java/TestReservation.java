import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.restaurant.Restaurant;
import org.restaurant.model.Reservation;

import java.time.LocalDate;


public class TestReservation {
    static Restaurant restaurant;
    static String SELECTED_DATE = "2025-01-16";

    @BeforeAll
    public static void beforeAllTest(){
        restaurant = new Restaurant();
    }

    @Test()
    public void testNoReservation(){
        LocalDate selectedDate = LocalDate.parse(SELECTED_DATE);

        Reservation[] reservations = new Reservation[]{};
        Assertions.assertEquals(restaurant.findMinimumTables(selectedDate,reservations),0);
    }

    @Test()
    public void testNoCollapseReservationWithDivisibleNumber(){
        LocalDate selectedDate = LocalDate.parse(SELECTED_DATE);
        Reservation reservation1 = new Reservation(
                "name1","0xx-xxxxxxx",SELECTED_DATE,"09:00","12:00",8
        );

        Reservation[] reservations = new Reservation[]{reservation1};
        Assertions.assertEquals(restaurant.findMinimumTables(selectedDate,reservations),2);
    }

    @Test()
    public void testNoCollapseReservationWithIndivisibleNumber(){
        LocalDate selectedDate = LocalDate.parse(SELECTED_DATE);
        Reservation reservation1 = new Reservation(
                "name1","0xx-xxxxxxx",SELECTED_DATE,"09:00","12:00",11
        );

        Reservation[] reservations = new Reservation[]{reservation1};
        Assertions.assertEquals(restaurant.findMinimumTables(selectedDate,reservations),3);
    }

    @Test()
    public void testNoCollapseMultipleReservationsWithTheSameDate(){
        LocalDate selectedDate = LocalDate.parse(SELECTED_DATE);
        Reservation reservation1 = new Reservation(
                "name1","0xx-xxxxxxx",SELECTED_DATE,"09:00","12:00",7
        );

        Reservation reservation2 = new Reservation(
                "name2","0xx-xxxxxxx",SELECTED_DATE,"12:01","15:00",7
        );
        Reservation[] reservations = new Reservation[]{reservation1,reservation2};
        Assertions.assertEquals(restaurant.findMinimumTables(selectedDate,reservations),2);
    }

    @Test
    public void testNoCollapseMultipleReservationsWithDifferentDate(){
        LocalDate selectedDate = LocalDate.parse(SELECTED_DATE);
        Reservation reservation1 = new Reservation(
                "name1","0xx-xxxxxxx",SELECTED_DATE,"09:00","12:00",3
        );

        Reservation reservation2 = new Reservation(
                "name2","0xx-xxxxxxx", "2025-01-17","10:00","15:00",3
        );
        Reservation[] reservations = new Reservation[]{reservation1,reservation2};
        Assertions.assertEquals(restaurant.findMinimumTables(selectedDate,reservations),1);
    }

    @Test
    public void testCollapseReservationByMinute(){
        LocalDate selectedDate = LocalDate.parse(SELECTED_DATE);
        Reservation reservation1 = new Reservation(
                "name1","0xx-xxxxxxx",SELECTED_DATE,"09:00","12:00",3
        );

        Reservation reservation2 = new Reservation(
                "name2","0xx-xxxxxxx",SELECTED_DATE,"11:59","15:00",3
        );
        Reservation[] reservations = new Reservation[]{reservation1,reservation2};
        Assertions.assertEquals(restaurant.findMinimumTables(selectedDate,reservations),2);
    }

    @Test
    public void testCollapse2Reservations(){
        LocalDate selectedDate = LocalDate.parse(SELECTED_DATE);
        Reservation reservation1 = new Reservation(
                "name1","0xx-xxxxxxx",SELECTED_DATE,"09:00","12:00",21
        );

        Reservation reservation2 = new Reservation(
                "name2","0xx-xxxxxxx",SELECTED_DATE,"10:00","15:00",4
        );
        Reservation[] reservations = new Reservation[]{reservation1,reservation2};
        Assertions.assertEquals(restaurant.findMinimumTables(selectedDate,reservations),7);
    }

    @Test
    public void testCollapseMultipleReservations(){
        LocalDate selectedDate = LocalDate.parse(SELECTED_DATE);
        Reservation reservation1 = new Reservation(
                "name1","0xx-xxxxxxx",SELECTED_DATE,"09:00","12:00",21
        );

        Reservation reservation2 = new Reservation(
                "name2","0xx-xxxxxxx",SELECTED_DATE,"10:00","15:00",4
        );

        Reservation reservation3 = new Reservation(
                "name3","0xx-xxxxxxx",SELECTED_DATE,"07:00","09:40",10
        );

        Reservation reservation4 = new Reservation(
                "name4","0xx-xxxxxxx",SELECTED_DATE,"08:00","10:10",13
        );
        Reservation[] reservations = new Reservation[]{reservation1,reservation2,reservation3,reservation4};
        Assertions.assertEquals(restaurant.findMinimumTables(selectedDate,reservations),13);
    }

    @Test
    public void testCollapseMultipleReservations2(){
        LocalDate selectedDate = LocalDate.parse(SELECTED_DATE);
        Reservation reservation1 = new Reservation(
                "name1","0xx-xxxxxxx",SELECTED_DATE,"09:00","12:00",21
        );

        Reservation reservation2 = new Reservation(
                "name2","0xx-xxxxxxx",SELECTED_DATE,"10:00","15:00",4
        );

        Reservation reservation3 = new Reservation(
                "name3","0xx-xxxxxxx",SELECTED_DATE,"07:00","09:40",10
        );

        Reservation reservation4 = new Reservation(
                "name4","0xx-xxxxxxx",SELECTED_DATE,"08:00","10:10",13
        );

        Reservation reservation5 = new Reservation(
                "name5","0xx-xxxxxxx",SELECTED_DATE,"11:00","16:00",25
        );

        Reservation reservation6 = new Reservation(
                "name6","0xx-xxxxxxx",SELECTED_DATE,"11:30","17:00",12
        );
        Reservation[] reservations = new Reservation[]{reservation1,reservation2,reservation3,reservation4,reservation5,reservation6};
        Assertions.assertEquals(restaurant.findMinimumTables(selectedDate,reservations),17);
    }

}
