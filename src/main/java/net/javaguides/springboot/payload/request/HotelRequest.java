package net.javaguides.springboot.payload.request;

public class HotelRequest {
    private long hotelId;
    private String employeeName;
    private String employeePosition;
    private int employeeHours;
    private int employeeSalary;

    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePosition() {
        return employeePosition;
    }

    public void setEmployeePosition(String employeePosition) {
        this.employeePosition = employeePosition;
    }

    public int getEmployeeHours() {
        return employeeHours;
    }

    public void setEmployeeHours(int employeeHours) {
        this.employeeHours = employeeHours;
    }

    public int getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(int employeeSalary) {
        this.employeeSalary = employeeSalary;
    }
}
