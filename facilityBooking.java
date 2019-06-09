package com.company;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class facilityBooking {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Map<String,Integer> clubHouseBookedDateList = new HashMap<>();
        String clubHouseBookedStartTimeList[][] = new String[100][100];
        String clubHouseBookedEndTimeList[][] = new String[100][100];
        Map<String,Integer> tennisCourtBookedDateList = new HashMap<>();
        String tennisCourtBookedStartTimeList[][] = new String[100][100];
        String tennisCourtBookedEndTimeList[][] = new String[100][100];
        int i;
        do {
            System.out.println("\n1.Booking\n2.Exit");
            i = in.nextInt();
            if (i == 1) {
                booking(in,clubHouseBookedDateList,clubHouseBookedStartTimeList,clubHouseBookedEndTimeList,tennisCourtBookedDateList,tennisCourtBookedStartTimeList,tennisCourtBookedEndTimeList);
            }
        } while (i == 1);
    }

        public static void  booking(Scanner in, Map<String,Integer> clubHouseBookedDateList,String clubHouseBookedStartTimeList[][],String clubHouseBookedEndTimeList[][],Map<String,Integer> tennisCourtBookedDateList,String tennisCourtBookedStartTimeList[][],String tennisCourtBookedEndTimeList[][]){
            Map<String, Integer> priceChart = new HashMap<>();
            priceChart.put("ClubHouse", 500);
            priceChart.put("TennisCourt", 100);

            String gameName = in.next();
            String inputDate = in.next();
            String startTime = in.next();
            String endTime = in.next();

            if(gameName.equals("ClubHouse")) {
                Integer indexValue = getIndexValue(clubHouseBookedDateList, inputDate);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                int i = indexValue;
                for (int j = 0; j < clubHouseBookedStartTimeList.length; j++) {
                    if (clubHouseBookedStartTimeList[i][j] == null) {
                        clubHouseBookedStartTimeList[i][j] = startTime;
                        clubHouseBookedEndTimeList[i][j] = endTime;
                        try {
                            Integer pricePerHour = priceChart.get(gameName);
                            Date startTimeFormat = simpleDateFormat.parse(startTime);
                            Date endTimeFormat = simpleDateFormat.parse(endTime);
                            long diff = endTimeFormat.getHours() - startTimeFormat.getHours();
                            long totalAmount = diff * pricePerHour;
                            System.out.println("Booked,Rs. "+totalAmount);
                            break;
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } else {
                        String bookedStartTime = clubHouseBookedStartTimeList[i][j];
                        String bookedEndTime = clubHouseBookedEndTimeList[i][j];
                        try {
                            Date bookedStartTimeFormat = simpleDateFormat.parse(bookedStartTime);
                            Date bookedEndTimeFormat = simpleDateFormat.parse(bookedEndTime);
                            Date startTimeFormat = simpleDateFormat.parse(startTime);
                            Date endTimeFormat = simpleDateFormat.parse(endTime);
                            if ((startTimeFormat.after(bookedStartTimeFormat) && startTimeFormat.before(bookedEndTimeFormat)) || startTime.equals(bookedStartTime)) {
                                System.out.println("\nBooking Failed, Already Booked");
                                break;
                            }

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }


            else if(gameName.equals("TennisCourt")){
                        Integer indexValue = getIndexValue(tennisCourtBookedDateList,inputDate);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                        int i = indexValue;
                        for(int  j=0;j<tennisCourtBookedStartTimeList.length;j++) {
                            if (tennisCourtBookedStartTimeList[i][j] == null) {
                                tennisCourtBookedStartTimeList[i][j] = startTime;
                                tennisCourtBookedEndTimeList[i][j] = endTime;
                                try {
                                    Integer pricePerHour = priceChart.get(gameName);
                                    Date startTimeFormat = simpleDateFormat.parse(startTime);
                                    Date endTimeFormat = simpleDateFormat.parse(endTime);
                                    long diff = endTimeFormat.getHours() - startTimeFormat.getHours();
                                    long totalAmount = diff * pricePerHour;
                                    System.out.println("Booked,Rs. "+totalAmount);
                                    break;
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                String bookedStartTime = tennisCourtBookedStartTimeList[i][j];
                                String bookedEndTime = tennisCourtBookedEndTimeList[i][j];
                                try {
                                    Date bookedStartTimeFormat = simpleDateFormat.parse(bookedStartTime);
                                    Date bookedEndTimeFormat = simpleDateFormat.parse(bookedEndTime);
                                    Date startTimeFormat = simpleDateFormat.parse(startTime);
                                    Date endTimeFormat = simpleDateFormat.parse(endTime);
                                    if ((startTimeFormat.after(bookedStartTimeFormat) && startTimeFormat.before(bookedEndTimeFormat)) || startTime.equals(bookedStartTime)) {
                                        System.out.println("\nBooking Failed, Already Booked");
                                        break;
                                    }

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                            }
                        }

            }




        }

        public static Integer getIndexValue(Map<String,Integer> bookedDateList,String date){
            Integer indexValue = 0;
            if(bookedDateList.size() == 0){
                bookedDateList.put(date,1);
                indexValue = 1;
            }
            else {
                Set<String> keys = bookedDateList.keySet();
                int check = 0;
                for(String bookedDate:keys){
                    if(bookedDate.equals(date)){
                        check =1;
                        indexValue = bookedDateList.get(bookedDate);
                    }
                }
                if(check == 0){
                    indexValue = keys.size()+1;
                    bookedDateList.put(date,indexValue);

                }
            }
            return indexValue;
        }
    }
