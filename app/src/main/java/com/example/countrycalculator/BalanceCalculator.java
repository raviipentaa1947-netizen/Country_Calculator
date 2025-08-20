package com.example.countrycalculator;

import java.util.*;

public class BalanceCalculator {

    public static class PersonBalance {
        public long personId;
        public String name;
        public double paid;
        public double incoming;
        public double outgoing;
        public double fairShare;
        public double net; // +ve receive, -ve pay
    }

    public static List<PersonBalance> compute(AppDatabase db) {
        List<Person> people = db.personDao().all();
        List<PersonBalance> out = new ArrayList<>();

        Double totalExpenses = db.expenseDao().totalPaid();
        if (totalExpenses == null) totalExpenses = 0.0;

        int n = Math.max(people.size(), 1);
        double fairShare = totalExpenses / n;

        for (Person p : people) {
            PersonBalance b = new PersonBalance();
            b.personId = p.id;
            b.name = p.name;

            Double paid = db.expenseDao().totalPaidBy(p.id);
            b.paid = (paid == null) ? 0.0 : paid;

            b.incoming = db.transferDao().totalIncoming(p.id);
            b.outgoing = db.transferDao().totalOutgoing(p.id);
            b.fairShare = fairShare;
            b.net = (b.paid + b.incoming - b.outgoing) - fairShare;

            out.add(b);
        }
        return out;
    }
}
