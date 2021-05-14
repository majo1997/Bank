package rdg;

public class ReacquiredCustomerStatistic {
    private Integer year;
    private Integer yearsSinceDeactivated;
    private Integer customerCount;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getYearsSinceDeactivated() {
        return yearsSinceDeactivated;
    }

    public void setYearsSinceDeactivated(Integer yearsSinceDeactivated) {
        this.yearsSinceDeactivated = yearsSinceDeactivated;
    }

    public Integer getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(Integer customerCount) {
        this.customerCount = customerCount;
    }
}
