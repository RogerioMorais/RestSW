package com.b2w.poc.api.responses;

public class ListPlanetResponse implements IResponse {
       private Integer count;
       private String next;
       private String previous;
       private PlanetResponse[] results;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public PlanetResponse[] getResults() {
        return results;
    }

    public void setResults(PlanetResponse[] results) {
        this.results = results;
    }


}
