package com.example.quoteapi.service;

import com.example.quoteapi.model.Quote;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class QuoteService {
    private final List<Quote> quotes = Arrays.asList(
            new Quote("Success is not final; failure is not fatal."),
            new Quote("Believe you can and you're halfway there."),
            new Quote("The harder you work for something, the greater you’ll feel when you achieve it.")
    );

    private final Random random = new Random();

    public Quote getRandomQuote() {
        return quotes.get(random.nextInt(quotes.size()));
    }
}
