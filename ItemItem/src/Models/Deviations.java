package Models;

public class Deviations {
    double rating;
    int productID;
    int amountOfRatings;

    public Deviations(int productID,double rating,int amountOfRatings){
        this.productID = productID;
        this.rating = rating;
        this.amountOfRatings = amountOfRatings;
    }

    public double getRating() {
        return rating;
    }

    public int getProductID() {
        return productID;
    }

    public int getAmountOfRatings() {
        return amountOfRatings;
    }

    public void updateAmountOfRatings(){ amountOfRatings++;}

    public void updateDeviaton(UserPreference user){

        double tempDeviation = rating * amountOfRatings;

    }
}
