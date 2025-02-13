package vttp.batch5.paf.movies.models;

public class Director {

    private String name;
    private int numMovies;
    private Double revenue;
    private Double budget;
    private Boolean profit;
    
    public Director() {
    }

    public Director(String name, int numMovies, Double revenue, Double budget, Boolean profit) {
        this.name = name;
        this.numMovies = numMovies;
        this.revenue = revenue;
        this.budget = budget;
        this.profit = profit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumMovies() {
        return numMovies;
    }

    public void setNumMovies(int numMovies) {
        this.numMovies = numMovies;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Boolean getProfit() {
        return profit;
    }

    public void setProfit(Boolean profit) {
        this.profit = profit;
    }
    
}
