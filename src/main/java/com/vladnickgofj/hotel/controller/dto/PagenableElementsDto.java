package com.vladnickgofj.hotel.controller.dto;

import java.util.Objects;

public class PagenableElementsDto {
    private final Integer itemsOnPage;
    private final Integer numberOfPage;

    private PagenableElementsDto(Builder builder) {
        itemsOnPage = builder.itemsOnPage;
        numberOfPage = builder.numberOfPage;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer itemsOnPage;
        private Integer numberOfPage;

        private Builder() {
        }

        public Builder itemsOnPage(Integer val) {
            itemsOnPage = val;
            return this;
        }

        public Builder numberOfPage(Integer val) {
            numberOfPage = val;
            return this;
        }

        public PagenableElementsDto build() {
            return new PagenableElementsDto(this);
        }
    }

    public Integer getItemsOnPage() {
        return itemsOnPage;
    }

    public Integer getNumberOfPage() {
        return numberOfPage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PagenableElementsDto that = (PagenableElementsDto) o;
        return Objects.equals(itemsOnPage, that.itemsOnPage) && Objects.equals(numberOfPage, that.numberOfPage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemsOnPage, numberOfPage);
    }

    @Override
    public String toString() {
        return "PagenableElementsDto{" +
                "itemsOnPage=" + itemsOnPage +
                ", numberOfPage=" + numberOfPage +
                '}';
    }
}
