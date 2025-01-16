package org.restaurant;

import org.restaurant.model.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Restaurant {

    public boolean isReservationIntersected(Reservation a, Reservation b){

        // Use variable to make a state for easily understandable here
        LocalTime startedA = a.getStartedTime();
        LocalTime startedB = b.getStartedTime();
        LocalTime finishedA = a.getFinishedTime();
        LocalTime finishedB = b.getFinishedTime();

        return !finishedA.isBefore(startedB) && !finishedB.isBefore(startedA);

    }

    public int findMinimumTables(LocalDate date, Reservation[] reservations){
        int enoughTable = 0;
        HashMap<Integer,ArrayList<Reservation>> timeTable = new HashMap<>();

        for( Reservation reservation : reservations){

            // Calculate number of in-need tables for each group.
            int numberOfTables = (int)Math.ceil(reservation.getNumberOfCustomer()/4.0);
            reservation.setNumberOfTables( numberOfTables );

            // Since we only need to find the minimum tables of the input date.
            // So the reservations' on the different date will be ignored for now.
            if( !reservation.getDate().equals(date) ){
                continue;
            }

            // Skip the reservation that have finished time before started time.
            if( reservation.getStartedTime().isAfter(reservation.getFinishedTime())){
                continue;
            }

            // Label reservation by the hour that the group customer is in the restaurant
            int startedTimeLabel =  reservation.getStartedTime().getHour();
            int finishedTimeLabel =  reservation.getFinishedTime().getHour();

            // plot data to timetable for faster query and find time collapsing.
            for( int i = startedTimeLabel; i <= finishedTimeLabel; i++){
                // query all reservation that possible to collapse
                ArrayList<Reservation> existedTimeTable = timeTable.get(i);
                if( existedTimeTable == null ){
                    existedTimeTable = new ArrayList<>();
                    timeTable.put(i,existedTimeTable);
                    enoughTable = Math.max(reservation.getNumberOfTables(), enoughTable);
                }else{
                    int sumTableForTheTime = 0;
                    for( Reservation collapseReservation: existedTimeTable){
                        // check collapse
                        if( isReservationIntersected(reservation,collapseReservation) ){
                            // Calculate time to use at the time.
                            sumTableForTheTime += collapseReservation.getNumberOfTables();

                        }
                    }
                    sumTableForTheTime += reservation.getNumberOfTables();
                    // Compare if the current reserved table has already covered.
                    enoughTable = Math.max(sumTableForTheTime, enoughTable);
                }
                existedTimeTable.add(reservation);
            }

        }
        return  enoughTable;
    }

}
