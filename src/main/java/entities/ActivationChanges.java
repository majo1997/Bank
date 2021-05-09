package entities;

import main.DbContext;

import java.sql.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class ActivationChanges {
    private final List<ActivationChange> activationChanges;

    public ActivationChanges() {
        activationChanges = new ArrayList<>();
        loadAllActivationChanges();
    }

    private void loadAllActivationChanges() {
        String sql = "SELECT * FROM activation_changes ORDER BY datetime";

        Connection connection = DbContext.getConnection();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Integer activationChangeId = rs.getInt(1);
                Boolean active = rs.getBoolean(2);
                Timestamp datetime = rs.getTimestamp(3);
                Integer customerId = rs.getInt(4);

                //todo check all var names in similar code...
                ActivationChange activationChange = new ActivationChange(activationChangeId, active, datetime, customerId);
                activationChanges.add(activationChange);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getYear(Timestamp datetime) {
        long timestamp = datetime.getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);

        return calendar.get(Calendar.YEAR);
    }

    private int getYearQuaterIndex(Timestamp datetime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(datetime.getTime());

        int month = calendar.get(Calendar.MONTH);
        if(month < Calendar.APRIL) {
            return 0;
        }
        else if(month < Calendar.JULY) {
            return 1;
        }
        else if(month < Calendar.OCTOBER) {
            return 2;
        }

        return 3;
    }

    /**
     * Prints customers counts for each year quater.
     * */
    public void printCustomerCountsByQuaterYears() {
        Map<Integer, ActivationChange> customerLastActivationChangeMap = new HashMap<>();
        Map<Integer, Map<Integer, Integer>> yearToQuaterYearCountMap = new HashMap<>();

        Integer minYear = null;
//        Integer year = null

        Calendar cal = Calendar.getInstance();
        Integer actualYear = cal.get(Calendar.YEAR);

        int count = 0;
//        Integer lastQuater = null;//not needed

        for(ActivationChange activationChange: activationChanges) {
            if(minYear == null) {
                long timestamp = activationChange.getDatetime().getTime();
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(timestamp);
                minYear = calendar.get(Calendar.YEAR);
            }

            Timestamp activationChangeDatetime = activationChange.getDatetime();

            Integer quater = getYearQuaterIndex(activationChangeDatetime);
            Integer year = getYear(activationChangeDatetime);

            if(!customerLastActivationChangeMap.containsKey(activationChange.getCustomerId())) {
                count += (activationChange.getActive() ? 1 : 0);
            }
            else {
                ActivationChange prevActivationChange = customerLastActivationChangeMap.get(activationChange.getCustomerId());

                if(prevActivationChange.getActive() && !activationChange.getActive()) {
                    count -= 1;
                }
                else if(!prevActivationChange.getActive() && activationChange.getActive()) {
                    count += 1;
                }
            }


            if(!yearToQuaterYearCountMap.containsKey(year)) {
                yearToQuaterYearCountMap.put(year, new HashMap<>());
            }
            Map<Integer, Integer> quaterYearToCount = yearToQuaterYearCountMap.get(year);
            quaterYearToCount.put(quater, count);

            customerLastActivationChangeMap.put(activationChange.getCustomerId(), activationChange);

        }

        Integer lastCount = 0;
        //musi sa definovat hore takto to nepojde, neviem teda.. mozno tam je len chyba
        for(Integer year = minYear; year <= actualYear; year++) {
            for(int yearQuater = 0; yearQuater < 4; yearQuater++) {
                if(yearToQuaterYearCountMap.containsKey(year) && yearToQuaterYearCountMap.get(year).containsKey(yearQuater)) {
                    System.out.println(year + "|" + (yearQuater+1) + "|" + (yearToQuaterYearCountMap.get(year).get(yearQuater) - lastCount)/*yearToQuaterYearCountMap.get(year).get(yearQuater)*/);
                    lastCount = yearToQuaterYearCountMap.get(year).get(yearQuater);
                }
                else {
                    System.out.println(year + "|" + (yearQuater+1) + "|" + 0/*lastCount*/);
                }
            }
        }
    }

    /**
     * Prints customers count for each year starting from first activation change until now for each n from
     * interval <1, N>
     * */
    public void printReacquiredCustomers(Integer maxYearsDeactivated) {
        Map<Integer, ActivationChange> customerLastActivationChangeMap = new HashMap<>();
        Map<Integer, Map<Integer, Integer>> yearToDeactivatedYearsCountMap = new HashMap<>();

        Integer minYear = null;

        Calendar cal = Calendar.getInstance();
        Integer actualYear = cal.get(Calendar.YEAR);

        for(ActivationChange activationChange: activationChanges) {
            if(minYear == null) {
                long timestamp = activationChange.getDatetime().getTime();
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(timestamp);
                minYear = calendar.get(Calendar.YEAR);
            }
            Integer customerId = activationChange.getCustomerId();
            Boolean active = activationChange.getActive();

            if(customerLastActivationChangeMap.containsKey(customerId) && !customerLastActivationChangeMap.get(customerId).getActive() && active) {
                Timestamp datetime = activationChange.getDatetime();
                Timestamp prevDatetime = customerLastActivationChangeMap.get(customerId).getDatetime();

//                System.out.println(datetime.getTime() - prevDatetime.getTime() + " - " + activationChange.getCustomerId());
                long days = TimeUnit.MILLISECONDS.toDays(datetime.getTime() - prevDatetime.getTime());
                int years = (int) (days / 365); //todo problem??
//                System.out.println(days);
//                System.out.println(years);

//                long timestamp = datetime.getTime();
//                Calendar calendar = Calendar.getInstance();
//                calendar.setTimeInMillis(timestamp);
//                Integer year = calendar.get(Calendar.YEAR);
                Integer year = getYear(datetime);
//                System.out.println(cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.DAY_OF_MONTH));


                if(!yearToDeactivatedYearsCountMap.containsKey(year)) {
                    yearToDeactivatedYearsCountMap.put(year, new HashMap<>());
                }

                if(!yearToDeactivatedYearsCountMap.get(year).containsKey(years)) {
                    yearToDeactivatedYearsCountMap.get(year).put(years, 1);
                }
                else {
                    Integer count = yearToDeactivatedYearsCountMap.get(year).get(years);
                    yearToDeactivatedYearsCountMap.get(year).put(years, count + 1);
                }

            }

            customerLastActivationChangeMap.put(customerId, activationChange);
        }

        for(Integer year = minYear; year <= actualYear; year++) {
            for(int deactivatedYears = 1; deactivatedYears <= maxYearsDeactivated; deactivatedYears++) {
                if(yearToDeactivatedYearsCountMap.containsKey(year) && yearToDeactivatedYearsCountMap.get(year).containsKey(deactivatedYears)) {
                    System.out.println(year + "|" + deactivatedYears + "|" + yearToDeactivatedYearsCountMap.get(year).get(deactivatedYears));                }
                else {
                    System.out.println(year + "|" + deactivatedYears + "|0");
                }
            }
        }

    }
}
