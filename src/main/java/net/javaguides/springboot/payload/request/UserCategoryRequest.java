package net.javaguides.springboot.payload.request;

public class UserCategoryRequest {
    private String name;
    private long userId;
    private double coefficient;
    private long userCategoryId;

    public long getUserCategoryId() {
        return userCategoryId;
    }

    public void setUserCategoryId(long userCategoryId) {
        this.userCategoryId = userCategoryId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }
}
