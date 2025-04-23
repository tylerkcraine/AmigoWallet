package com.infy.AmigoWallet.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class FutureWordDTO {
    @Size(min = 5, max = 5)
    private String word;

    @Future
    private LocalDate date;

    public FutureWordDTO(String word, LocalDate date) {
        this.word = word;
        this.date = date;
    }

    public FutureWordDTO() {
    }

    public @Size(min = 5, max = 5) String getWord() {
        return word;
    }

    public void setWord(@Size(min = 5, max = 5) String word) {
        this.word = word;
    }

    public @Future LocalDate getDate() {
        return date;
    }

    public void setDate(@Future LocalDate date) {
        this.date = date;
    }
}
